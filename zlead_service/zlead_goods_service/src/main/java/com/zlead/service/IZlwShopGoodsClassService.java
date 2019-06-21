package com.zlead.service;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.entity.goods.ZlwShopGoods;
import com.zlead.entity.goods.ZlwShopGoodsClass;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface IZlwShopGoodsClassService extends IService<ZlwShopGoodsClass> {



    List<ZlwShopGoodsClass> selectListAll(String sgcParentId,String shopId);

    /**
     * nayunhao
     * @param className
     * @return
     */
    ZlwShopGoodsClass selectOneByName(String className);

    int editShopGoodsClass( Map<String, Object> map);

    boolean removeShopGoodsClass(String sgcId);

    List<ZlwShopGoodsClass> selectListAllTree();
}
