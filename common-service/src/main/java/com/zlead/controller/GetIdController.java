package com.zlead.controller;

import com.zlead.utils.GetIDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class GetIdController {
    @Value("${zlw.service-id}")
    private String machineId;

    @RequestMapping("/getUserId")
    public String getUserId(){
        return GetIDUtils.getId(machineId);
    }
    @RequestMapping("/getOrderId")
    public String getOrderId(){
        return GetIDUtils.getId(machineId);
    }
    @RequestMapping("/getGoodsId")
    public String getGoodsId(){
        return GetIDUtils.getId(machineId);
    }
    @RequestMapping("/getCompanyId")
    public String getCompanyId(){
        return GetIDUtils.getId(machineId);
    }
}
