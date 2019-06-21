package com.zlead.user.invoke;

import com.zlead.entity.user.ZlwUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.zlead.user.invoke.callback.ZlwUserFallback;

import java.util.Map;

@FeignClient(name = "zlead-user-service", fallback = ZlwUserFallback.class)
public interface ZlwUserInvoke {

    @RequestMapping("/ZlwUser/getUserByName/{phone}")
    public ZlwUser getUserByPhone(@PathVariable("phone") String Phone);

    @PostMapping("/ZlwUser/setUserPassword")
    public boolean setPassWord(@RequestBody Map data);

    @PostMapping("/ZlwUser/createUserByInfo")
    public boolean createUser(@RequestBody Map data);

//    @PostMapping("/ZlwUser/createCompanyByInfo")
//    public Long addCompany(@RequestBody String userId);

    @PostMapping("/ZlwUser/createShopByInfo")
    public boolean addShop(@RequestBody Map data);

    @GetMapping("/ZlwUser/getShopsByUserId/{userId}")
    public ZlwUser getShopByUser(@PathVariable("userId") String userId);

    @PostMapping("/ZlwUser/checkPassword")
    public boolean checkPassword(@RequestBody Map data);

    @PostMapping("/ZlwUser/getShopByName")
    public boolean getShopByName(@RequestBody String shopName);




}
