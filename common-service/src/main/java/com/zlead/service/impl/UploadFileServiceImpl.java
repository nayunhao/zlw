package com.zlead.service.impl;

import com.aliyun.oss.OSSClient;
import com.zlead.common.FileUpload;
import com.zlead.config.OSSClientConfig;
import com.zlead.service.UploadFileService;

import com.zlead.utils.AliyunOSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("uploadFileService")
public class UploadFileServiceImpl implements UploadFileService {
    @Autowired
    OSSClientConfig ossClientConfig;

    @Override
    public Map uploadImage(Map data) throws Exception {
        String buckName = ossClientConfig.getBucketName();
        MultipartFile[] filesList = (MultipartFile[]) data.get("images");
        String shopId = (String) data.get("shopId");
        String userId = (String) data.get("userId");
        MultipartFile imageFile = null;
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> coverImgList = new ArrayList<>();
        List<Map<String, Object>> imagesList = new ArrayList<>();
        String pictureName = "";
        String fileName = "";
        String type = "";
        String folder = "";
        String imgUrl = "";
        String thumbnailUrl = "";
        String imgName = "";
        String thumbName = "";
        String desc = "";
        String sort = "";
        OSSClient ossClient = ossClientConfig.getOSSClient();
        for (int i = 0; i < filesList.length; i++) {
            Map<String, Object> impMap = new HashMap<>();
            imageFile = filesList[i];
            fileName = imageFile.getOriginalFilename();
            pictureName = fileName.substring(0,fileName.lastIndexOf("."));
            String [] names =pictureName.split("_");
            type = names[0];
            sort = names[1];
            imgName = FileUpload.getPictureName();
            folder = userId + "/" + shopId + "/" + type + "/";
            //原图上传OSS
            AliyunOSSClientUtil.uploadMultipartFileOSS(ossClient, imageFile, buckName, folder, imgName);
            imgUrl = AliyunOSSClientUtil.getUrl(ossClient, buckName, folder + imgName);
            //生成缩略图并上传至OSS
            thumbName = imgName.substring(0, imgName.lastIndexOf(".")) + "_thum.jpg";
            AliyunOSSClientUtil.uploadByteOSS(ossClient, FileUpload.getThumPicture(imageFile), buckName, folder, thumbName);
            thumbnailUrl = AliyunOSSClientUtil.getUrl(ossClient, buckName, folder + thumbName);
            impMap.put("imgUrl", imgUrl);
            impMap.put("thumbnailUrl", thumbnailUrl);
            impMap.put("imgName", imgName);
            impMap.put("thumbname", thumbName);
            impMap.put("sort", sort);
            impMap.put("desc", desc);
            if ("title".equals(type)) {//标题图片
                coverImgList.add(impMap);
            } else {//详情图片
                imagesList.add(impMap);
            }
        }
        AliyunOSSClientUtil.ossShutDown(ossClient);
        resultMap.put("isSccuess", true);
        resultMap.put("shopId", shopId);
        resultMap.put("userId", userId);
        resultMap.put("coverImg", coverImgList);
        resultMap.put("images", imagesList);
        return resultMap;
    }
}
