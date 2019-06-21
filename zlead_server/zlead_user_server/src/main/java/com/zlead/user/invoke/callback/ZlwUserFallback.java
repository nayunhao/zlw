package com.zlead.user.invoke.callback;

import com.zlead.domain.ApiResult;
import com.zlead.entity.user.ZlwUser;
import com.zlead.user.invoke.ZlwUserInvoke;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ZlwUserFallback implements ZlwUserInvoke {

    @Override
    public ZlwUser getUserByPhone(String Phone) {
        ApiResult err = ApiResult.isErrMessage("");
        return null;
    }

    @Override
    public boolean setPassWord(Map data) {
        ApiResult err =  ApiResult.isErrMessage("");
        return false;
    }

    @Override
    public boolean createUser(Map data) {
        ApiResult err =  ApiResult.isErrMessage("");
        return false;
    }

//    @Override
//    public boolean addCompany(String userId) {
//        ApiResult err =  ApiResult.isErrMessage("");
//        return false;
//    }

    @Override
    public boolean addShop(Map data) {
        ApiResult err =  ApiResult.isErrMessage("");
        return false;
    }

    public boolean checkPassword(Map data) {
        ApiResult err =  ApiResult.isErrMessage("");
        return false;
    }

    @Override
    public boolean getShopByName(String shopName) {
        return false;
    }

    @Override
    public ZlwUser getShopByUser(String userId) {
        ApiResult err =  ApiResult.isErrMessage("");
        return null;
    }


}
