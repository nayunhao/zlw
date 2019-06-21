package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwCompanyMapper;
import com.zlead.entity.company.ZlwCompany;
import com.zlead.service.ZlwCompanyService;
import org.springframework.stereotype.Service;

@Service
public class ZlwCompanyServiceImpl extends ServiceImpl<ZlwCompanyMapper, ZlwCompany> implements ZlwCompanyService{

    @Override
    public boolean addCompany(ZlwCompany zlwCompany) {
        int res =  this.baseMapper.insert(zlwCompany);
        return res > 0 ? true : false;
    }
}
