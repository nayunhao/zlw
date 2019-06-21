package com.zlead.controller;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zlead.common.FileUpload;
import com.zlead.config.OSSClientConfig;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.constant.ServiceReturnCode;
import com.zlead.service.UploadFileService;
import com.zlead.utils.AliyunOSSClientUtil;
import com.zlead.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/common")
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class UploadFileController {
    @Autowired
    UploadFileService uploadFileService;
    Logger logger= LoggerFactory.getLogger(UploadFileController.class);
    @PostMapping("/upload/{shopId}/{type}")
    @HystrixCommand(fallbackMethod = "uploadFallback")
    public ApiResult uploadFile(@RequestParam("file") MultipartFile[] files,
                                @PathVariable("shopId") String shopId,
                                @PathVariable("type") String type) {
        List<String> list=new ArrayList<>();
        try {
            logger.info("上传图片开始");
            if (files != null) {
                Arrays.asList(files).forEach(e -> {
                    String uploadPath = "D:/".concat(shopId).concat("/").concat(type);
                    String filename = FileUpload.getFileName(e.getOriginalFilename());
                    if (FileUpload.isErrorSuffixFile(filename)) {
                        throw new RuntimeException(ServiceReturnCode.IMAGE_FORMAT_ERROR.getMessage());
                    }
                    File ftemp = new File(uploadPath);
                    if (!ftemp.exists()) ftemp.mkdirs();
                    String finalPath = uploadPath.concat("/").concat(filename);
                    try {
                        e.transferTo(new File(finalPath));//存放在本地
                        list.add(finalPath);
                    } catch (IOException e1) {
                        throw new RuntimeException(ServiceReturnCode.IMAGE_UPLOAD_ERROR.getMessage());
                    }
                });
            } else {
                return ApiResult.isErrMessage(ServiceReturnCode.NO_UPLOAD_IMAGE);
            }
        }catch (Exception e){
            logger.info("上传图片有异常".concat(e.getMessage()));
            return ApiResult.isErrMessage(e.getMessage());
        }
        return ApiResult.isOkNoToken(ServiceReturnCode.IMAGE_UPLOAD_SUCCESS,list);
    }


    /*
     * @Author zhangyang
     * @Description 
     * @Date 2019/6/10 9:33
     * @Param   @param apiRequest : 
     * @return com.zlead.domain.ApiResult
     */
    @PostMapping("/uploadImage")
    @HystrixCommand(fallbackMethod = "uploadFallback")
    public ApiResult uploadImage(@RequestParam("imageFiles") MultipartFile[] files,
                                 @RequestBody ApiRequest apiRequest) {
        ApiResult apiResult = null;
        try {
            logger.info("上传图片前台请求:  "+ JSON.toJSONString(apiRequest));
            Map data = apiRequest.getData();
            String shopId = (String) data.get("shopId");
            String userId = (String) data.get("userId");
            if (StringUtil.isNull(shopId)||StringUtil.isNull(userId)){
                return ApiResult.isErrMessage("页面正在加载，请稍后重试。。");
            }
            if (files!=null && files.length>0){
                data.put("images",files);
                Map resultMap = uploadFileService.uploadImage(data);
                apiResult = ApiResult.isOk(null,"图片上传成功",resultMap);
                logger.info("上传图片后台返回:  "+ JSON.toJSONString(apiResult));
                return apiResult;
            }else {
                return ApiResult.isErrMessage("页面正在加载，请稍后重试。 。");
            }
        }catch (Exception e){
            logger.info("上传图片有异常".concat(e.getMessage()));
            return ApiResult.isErrMessage(e.getMessage());
        }
    }



    public String uploadFallback(@RequestParam("files") MultipartFile[] files,
                                 @PathVariable("shopId") String shopId,
                                 @PathVariable("type") String type) {
        System.out.println("上传失败");
        return "文件上传失败";
    }
    @Autowired
    OSSClientConfig ossClientConfig;
    @GetMapping("/OssTest/{operateType}/{param}")
    @HystrixCommand(fallbackMethod = "uploadFallback")
    public ApiResult OssTest(@PathVariable("operateType") String operateType,
                             @PathVariable("param") String param) {
        String buckName = ossClientConfig.getBucketName();
        OSSClient ossClient = ossClientConfig.getOSSClient();

        try {
            if ("creatFolder".equals(operateType)){
                AliyunOSSClientUtil.createFolder(ossClient,buckName,param+"/");
            }else if("deleteFile".equals(operateType)){
                AliyunOSSClientUtil.deleteFile(ossClient,buckName, "11155972591363300005/28/detail/","20190606174448848148bak.jpg");
            }else if ("uploadFile".equals(operateType)){
                AliyunOSSClientUtil.uploadObjectOSS(ossClient,AliyunOSSClientUtil.image2Bytes("C:\\Users\\appril\\Desktop\\oss上传图片目录.txt"),buckName,"11155972591363300005/28/detail/test.txt");
            }
            return ApiResult.isOk("","oss操作成功","");
        }catch (Exception e){
            logger.info("oss操作有异常".concat(e.getMessage()));
            return ApiResult.isErrMessage(e.getMessage());
        }finally {
            AliyunOSSClientUtil.ossShutDown(ossClient);
        }
    }
}