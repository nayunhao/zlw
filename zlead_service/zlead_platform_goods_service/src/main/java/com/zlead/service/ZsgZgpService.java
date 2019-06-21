package com.zlead.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwPlatformGoods;
import com.zlead.entity.goods.ZsgZpg;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */

public interface ZsgZgpService extends IService<ZsgZpg> {






    void createTableZlw();

    void delTableZlw();

    boolean insertZsgZpgList(List collect);



}
