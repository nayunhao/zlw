package com.zlead.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zlead.base.BaseController;
import com.zlead.common.RedisClient;
import com.zlead.config.SmsConfig;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.utils.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/common")
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class SmsController  extends BaseController {
    private final Logger logger= LoggerFactory.getLogger(SmsController.class);

    @Autowired
    SmsConfig smsConfig;
    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value="/getPhoneValidateCode",method = RequestMethod.POST)
    public ApiResult getPhoneValidateCode(@RequestBody ApiRequest apiRequest) {
        DefaultProfile profile = DefaultProfile.getProfile(smsConfig.getRegionId(), smsConfig.getAccessKeyId(), smsConfig.getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);
        ApiResult apiResult = ApiResult.isErrNoToken("服务处理异常！！",null);

        Map data = apiRequest.getData();

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        if("post".equals(smsConfig.getMethod())){
            request.setMethod(MethodType.POST);
        }else if("put".equals(smsConfig.getMethod())){
            request.setMethod(MethodType.PUT);
        }

        request.setDomain(smsConfig.getDomain());
        request.setVersion(smsConfig.getVersion());
        request.setAction(smsConfig.getAction());
        request.putQueryParameter("PhoneNumbers", (String)data.get("phone"));
        request.putQueryParameter("SignName", smsConfig.getSignname());
        Map<String,String> templates = smsConfig.getTemplates();
        Map<String,String> serviceIds = smsConfig.getServiceIds();
        String templateCode= "";
        String serviceId = (String)data.get("serviceId");
        if(serviceId==null||"".equals(serviceId)){
            return   ApiResult.isErrNoToken("serviceId为空",null);
        }
        if(serviceId.equals(serviceIds.get("idVerify"))){
            templateCode = templates.get("idVerify");
        }else if(serviceId.equals(serviceIds.get("loginConfirm"))){
            templateCode = templates.get("loginConfirm");
        }else if(serviceId.equals(serviceIds.get("loginException"))){
            templateCode = templates.get("loginException");
        }else if(serviceId.equals(serviceIds.get("register"))){
            templateCode = templates.get("register");
        }else if(serviceId.equals(serviceIds.get("updatePass"))){
            templateCode = templates.get("updatePass");
        }else if(serviceId.equals(serviceIds.get("infoUpdate"))){
            templateCode = templates.get("infoUpdate");
        }else{
            return   ApiResult.isErrNoToken("找不到serviceId",null);
        }
        request.putQueryParameter("TemplateCode", templateCode);

        JsonObject json= new JsonObject();
        json.addProperty("code", RandomUtil.getRandomCode(4));
        request.putQueryParameter("TemplateParam", json.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            HashMap<String,String> smsResultMap = new Gson().fromJson(response.getData(),HashMap.class);
            if("OK".equals(smsResultMap.get("Code"))){
                Map<String,Object> resultMap = new HashMap<>();
                resultMap.put("isSuccess",true);
                resultMap.put("phone",(String)data.get("phone"));
                resultMap.put("smsCode",json.get("code").getAsString());
                //验证码放入redis
                boolean flag=redisClient.set((String)data.get("phone"), json.get("code").getAsString(),1800L);//30分钟过期
                if(flag == true){
                    logger.info("短信验证码存储成功！！");
                }else{
                    logger.info("短信验证码存储失败！！");
                }
                apiResult = ApiResult.isOkNoToken("短信发送成功！",resultMap);
            }else{
                Map<String,Object> errorMap = new HashMap<>();
                errorMap.put("isSuccess",false);
                apiResult = ApiResult.isErrNoToken("短信发送失败！",errorMap);
            }
        } catch (Exception e) {
            return apiResult;
        }
        return  apiResult;
    }

    @RequestMapping(value="/checkSmsCode",method = RequestMethod.POST)
    public ApiResult checkCode(@RequestBody ApiRequest request){
        Map data = request.getData();
        String phone = (String) data.get("phone");
        String code = (String)data.get("code");
        Map<String,Object> successResult = new HashMap<>();
        successResult.put("isSuccess",true);
        Map<String,Object> errorResult = new HashMap<>();
        errorResult.put("isSuccess",false);
        if(phone==null||"".equals(phone)){
            return ApiResult.isErrNoToken("电话号码为空或者未传",errorResult);
        }
        if(code==null||"".equals(code)){
            return ApiResult.isErrNoToken("验证码为空或者未传",errorResult);
        }
        String value = String.valueOf(redisClient.get(phone)) ;
        if(value!=null&&code.equals(value)){

            return ApiResult.isOkNoToken("验证码验证成功！",successResult);
        }else{
            return ApiResult.isErrNoToken("验证码验证失败或者已过期！",errorResult);
        }
    }
}