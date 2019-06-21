package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwShopGoodsPriceMapper;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.entity.goods.ZlwShopGoodsPrice;
import com.zlead.service.IZlwShopGoodsPriceService;
import com.zlead.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
@Service
public class ZlwShopGoodsPriceServiceImpl extends ServiceImpl<ZlwShopGoodsPriceMapper, ZlwShopGoodsPrice> implements IZlwShopGoodsPriceService {

    Logger logger = LoggerFactory.getLogger(ZlwShopGoodsPriceServiceImpl.class);
    @Override
    public boolean insertShopGoodsPrice(Map map) {
        ZlwPlatformGoodsVO zlwPlatformGoodsVO=(ZlwPlatformGoodsVO)map.get("goods");
        ZlwImportGoodsParam zlwImportGoodsParam=(ZlwImportGoodsParam)map.get("param");
        ZlwShopGoodsPrice zlwShopGoodsPrice=new ZlwShopGoodsPrice();
        zlwShopGoodsPrice.setSgpId(UUIDUtils.getOrderIdByUUId("P"));
        zlwShopGoodsPrice.setShopId(zlwImportGoodsParam.getShopId());
        zlwShopGoodsPrice.setSgpPublicPrice(zlwImportGoodsParam.getSgpPublicPrice());
        zlwShopGoodsPrice.setSgpPublicEprice(zlwImportGoodsParam.getSgpPublicEprice());
        if(save(zlwShopGoodsPrice)){
            logger.info("价格信息保存成功");
            map.put("priceId",zlwShopGoodsPrice.getSgpId());
            return true;
        }
        logger.info("价格信息保存失败");
        return false;
    }
}
