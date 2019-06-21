package com.zlead.controller;

import com.zlead.constant.DataKeysEnum;
import com.zlead.entity.company.ZlwCompany;
import com.zlead.entity.shop.ZlwShop;
import com.zlead.entity.user.ZlwUser;
import com.zlead.service.ZlwCompanyService;
import com.zlead.service.ZlwShopService;
import com.zlead.service.ZlwUserService;
import com.zlead.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ZlwUser")
@Slf4j
public class ZlwUserContoller {

    @GetMapping("/getUserByName/{phone}")
    public ZlwUser getUserById(@PathVariable("phone") String phone){
        log.info("传入的电话号码：{}",phone);
        ZlwUser zlwUser =  zlwUserService.getUserByName(phone);

        return zlwUser;
    }


    @PostMapping("/createUserByInfo")
    public boolean createUserByInfo(@RequestBody Map userInfo){

        String password = (String)userInfo.get(DataKeysEnum.P_PASSWORD.getValue());
        String phone = (String)userInfo.get(DataKeysEnum.P_PHONE.getValue());
        //获得userId
        String userId = (String) userInfo.get(DataKeysEnum.P_USER_ID.getValue());
        ZlwUser zlwUser = new ZlwUser();
         zlwUser.setUserPassword(password);
        zlwUser.setUserId(userId);
        zlwUser.setUserPhone(phone);
        boolean res = zlwUserService.addAUser(zlwUser);
        return res;
    }

    @PostMapping("/setUserPassword")
    public boolean setUserPassword(@RequestBody Map data){

        String phone = (String) data.get(DataKeysEnum.P_PHONE.getValue());
        String password = (String) data.get(DataKeysEnum.P_PASSWORD.getValue());
        ZlwUser zlwUser = new ZlwUser();
        zlwUser.setUserPhone(phone);
        zlwUser.setUserPassword(password);
        //要根据当前的电话查询一下用户，这样可以避免因为意外情况数据库中有相同的数据
        ZlwUser oragnlUser =  zlwUserService.getUserByName(phone);
        zlwUser.setUserId(oragnlUser.getUserId());
        return zlwUserService.updateUserInfo(zlwUser);
    }



    @PostMapping("/createShopByInfo")
    @Transactional
    public boolean createShopByInfo(@RequestBody Map data){

        //拿到公司ID
        String companyId = (String) data.get(DataKeysEnum.P_COMPANY_ID.getValue());
        String userId = (String) data.get(DataKeysEnum.P_USER_ID.getValue());

        //创建公司记录
        ZlwCompany zlwCompany = new ZlwCompany();
        zlwCompany.setCompanyId(companyId);
        zlwCompany.setUserId(userId);
        boolean createCompany;
        createCompany = zlwCompanyService.addCompany(zlwCompany);

        if(!createCompany){
            log.info("创建公司记录失败，当前的用户id为：{}",userId);
            return createCompany;
        }
        //店铺地址码值
        String region = (String) data.get("region");
        //店铺名称
        String shopName = (String) data.get("shopName");

        ZlwShop zlwShop = new ZlwShop();
        zlwShop.setShopName(shopName);
        zlwShop.setShopRegion(region);
        zlwShop.setUserId(userId);
        zlwShop.setCompanyId(companyId);
        boolean createShopRes;
        createShopRes =  zlwShopService.addShop(zlwShop);
        return createShopRes;
    }

    @PostMapping("/checkPassword")
    public boolean checkPassword(@RequestBody Map data) throws Exception {
        String phone = (String)data.get(DataKeysEnum.P_PHONE.getValue());
        String password = (String)data.get(DataKeysEnum.P_PASSWORD.getValue());
        ZlwUser zlwUser = zlwUserService.getUserByName(phone);

        if(zlwUser == null){
            //TODO 这里不应该这样处理
            throw new Exception("没有这个用户");
        }

        String pwd = zlwUser.getUserPassword();

        if(StringUtils.isEmpty(pwd)){
            throw new Exception("该用户还没有设置密码");
        }

        if(pwd.equals(password)){
            return true;
        }

        return false;
    }


    @GetMapping("/getShopsByUserId/{userId}")
    public ZlwUser getShopsByUserId(@PathVariable String userId){
        return zlwUserService.getShopListByUser(userId);
    }

    @PostMapping("/getShopByName")
    public boolean getShopByName(@RequestBody String shopName){

        ZlwShop zlwShop = new ZlwShop();
        zlwShop.setShopName(shopName);
        return zlwShopService.getShopByInfo(zlwShop);

    }


    @Autowired
    private ZlwUserService zlwUserService;

    @Autowired
    private ZlwCompanyService zlwCompanyService;

    @Autowired
    private ZlwShopService zlwShopService;
}
