package com.zlead.controller;

import com.zlead.common.RedisClient;
import com.zlead.domain.ApiResult;
import com.zlead.constant.ServiceReturnCode;
import com.zlead.domain.VerifyCodeEntity;
import com.zlead.utils.VerifyCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class GenerateCodeConller {
    @Autowired
    private RedisClient redisClient;

    private final Logger logger= LoggerFactory.getLogger(GenerateCodeConller.class);
    @RequestMapping("/GenerateCode")
    public ApiResult generateCode(){
        String path= null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath()+"META-INF/resources/";
            logger.info("生成图片的绝对路径是{}",path);
        } catch (FileNotFoundException e) {
            logger.info("项目路径获取失败");
            return ApiResult.isErrMessage("项目路径获取失败");
        }
        System.out.println(path);
        File dir = new File(path);
        logger.info(path);
        int w = 200, h = 80;
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        File file = new File(dir, verifyCode + ".jpg");
        try {
            VerifyCodeUtils.outputImage(w, h, file, verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResult.isErrMessage("生成验证码图片失败");
        }
        VerifyCodeEntity vce=new VerifyCodeEntity();
        vce.setAuthCodeKey(UUID.randomUUID().toString());
        vce.setVerifyCode(verifyCode);
        //添加到redis
        try {
            boolean flag=redisClient.set(vce.getAuthCodeKey(), vce.getVerifyCode(),1800L);//30分钟过期
            if(flag){
                logger.info("向redis中添加校验码成功");
            }else{
                logger.info("向redis中添加校验码失败");
            }
        }catch (Exception e){
            logger.info("向redis中添加校验码失败");
            return ApiResult.isErrMessage(ServiceReturnCode.REDIS_ADD_VERIFYCODE_ERROR);
        }
        return ApiResult.isOkNoToken("生成成功",vce);
    }


    @PostMapping("/checkCode")
    public ApiResult checkCode(@RequestBody VerifyCodeEntity verifyCodeEntity){
        //从redis中读取
        String verifyCodeOriginal=null;
        try {
            verifyCodeOriginal = String.valueOf(redisClient.get(verifyCodeEntity.getAuthCodeKey()));
        }catch (Exception e){
            logger.info("读取redis信息失败");
            return ApiResult.isErrMessage(ServiceReturnCode.READ_REDIS_INFO_ERROR);
        }
        if(StringUtils.isEmpty(verifyCodeOriginal)){
            return ApiResult.isErrNoToken(ServiceReturnCode.VERIFYCODE_EXPIRE,false);
        }else{
            if(!verifyCodeEntity.getVerifyCode().equalsIgnoreCase(verifyCodeOriginal)){//不区分大小写
                return ApiResult.isErrNoToken(ServiceReturnCode.VERIFYCODE_ERROR,false);
            }
        }
        return ApiResult.isOkNoToken("验证通过",true);
    }

}
