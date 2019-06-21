package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.company.ZlwCompany;

public interface ZlwCompanyService extends IService<ZlwCompany> {

    public boolean addCompany(ZlwCompany zlwCompany);
}
