package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwCompanyMapper;
import com.zlead.dao.mapper.ZlwShopMapper;
import com.zlead.dao.mapper.ZlwUserMapper;
import com.zlead.entity.company.ZlwCompany;
import com.zlead.entity.shop.ZlwShop;
import com.zlead.entity.user.ZlwUser;
import com.zlead.service.ZlwUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZlwUserServiceImpl extends ServiceImpl<ZlwUserMapper,ZlwUser> implements ZlwUserService {


    @Override
    public ZlwUser getUserByName(String name) {
        return this.baseMapper.queryUserByName(name).get(0);
    }

    @Override
    public boolean addAUser(ZlwUser zlwUser) {
        int res =  this.baseMapper.insert(zlwUser);
       return res > 0 ? true:false;
    }

    @Override
    public boolean updateUserInfo(ZlwUser zlwUser) {

        try {
            //自定义sql的UPDATE语句没有返回值，只要不报错，就是成功了，所以这里用Try，Catch来判断是否成功
            this.baseMapper.updateUserPassword(zlwUser);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ZlwUser getShopListByUser(String userId) {
            //获取当前用户基本信息
            ZlwUser zlwUser = this.baseMapper.getUserById(userId);
            //获取当前用户下所有的公司,并且获得公司下对应的店铺
            List<ZlwCompany> companyList = zlwCompanyMapper.getCompanysByUser(userId);
            companyList.stream().forEach(
                    company -> {
                        List<ZlwShop> shops = zlwShopMapper.getShopsByCompanyId(company.getCompanyId());
                        company.setZlwShopList(shops);
                    }
            );

            zlwUser.setZlwCompanyList(companyList);
        return zlwUser;
    }

    @Autowired
    private ZlwShopMapper zlwShopMapper;

    @Autowired
    private ZlwCompanyMapper zlwCompanyMapper;

}
