package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwShopGoodsBrandMapper;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.entity.goods.ZlwShopGoodsBrand;
import com.zlead.service.IZlwShopGoodsBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
@Service
public class ZlwShopGoodsBrandServiceImpl extends ServiceImpl<ZlwShopGoodsBrandMapper, ZlwShopGoodsBrand> implements IZlwShopGoodsBrandService {

    @Autowired
    private ZlwShopGoodsBrandMapper zlwShopGoodsBrandMapper;

    /**
     *  获取所有店铺品牌
     */
    @Override
    public Map<String, Object> getShopGoodsBrand(String searchKey) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("sgbName", searchKey);
        List<ZlwShopGoodsBrand> list = zlwShopGoodsBrandMapper.getAllShopGoodsBrand(paramMap);

        SortedSet<String> indexSet = new TreeSet<>();
        SortedMap<String, List<ZlwShopGoodsBrand>> map = new TreeMap();

        Map<String, Object> resultMap = new HashMap<>();

        for(ZlwShopGoodsBrand brand: list){
            String firstLetter = brand.getSgbNamePyFirst();
            indexSet.add(firstLetter);
            if(map.containsKey(firstLetter)){
                List<ZlwShopGoodsBrand> brandList = map.get(firstLetter);
                brandList.add(brand);
            }else{
                List<ZlwShopGoodsBrand> brandList = new ArrayList<>();
                brandList.add(brand);
                map.put(firstLetter, brandList);
            }
        }
        resultMap.put("index", indexSet);
        resultMap.put("brand", map);

        return resultMap;
    }

}
