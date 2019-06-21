package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwShopGoodsImagesMapper;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsImages;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.entity.goods.ZlwShopGoodsImages;
import com.zlead.service.IZlwShopGoodsImagesService;
import com.zlead.utils.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
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
public class ZlwShopGoodsImagesServiceImpl extends ServiceImpl<ZlwShopGoodsImagesMapper, ZlwShopGoodsImages> implements IZlwShopGoodsImagesService {

    @Override
    public boolean insertShopGoodsImage(Map map) {
        ZlwPlatformGoodsVO zlwPlatformGoodsVO=(ZlwPlatformGoodsVO)map.get("goods");
        ZlwImportGoodsParam zlwImportGoodsParam=(ZlwImportGoodsParam)map.get("param");
        List<ZlwPlatformGoodsImages> zlwPlatformGoodsImages=zlwPlatformGoodsVO.getZlwPlatformGoodsImagesList();
        List<ZlwShopGoodsImages> zlwShopGoodsImages=new ArrayList<>();
        zlwPlatformGoodsImages.parallelStream().forEach(e->{
            ZlwShopGoodsImages zlwShopGoodsImages1=new ZlwShopGoodsImages();
            zlwShopGoodsImages1.setSgImageName(e.getPgImageUrl());
            zlwShopGoodsImages1.setSgImageRemark(e.getPgImageRemark());
            zlwShopGoodsImages1.setSgImageSort(Integer.valueOf(e.getPgImageSort()));
            zlwShopGoodsImages1.setSgImageType(Integer.valueOf(e.getPgImageType()));
            zlwShopGoodsImages1.setSgkId(String.valueOf(map.get("skuId")));
            zlwShopGoodsImages1.setShopId(zlwImportGoodsParam.getShopId());
            zlwShopGoodsImages1.setSgImageId(UUIDUtils.getOrderIdByUUId("I"));
            zlwShopGoodsImages.add(zlwShopGoodsImages1);
        });
        if(!CollectionUtils.isEmpty(zlwShopGoodsImages)) {
            return saveBatch(zlwShopGoodsImages, zlwShopGoodsImages.size());
        }
        return false;
    }
}
