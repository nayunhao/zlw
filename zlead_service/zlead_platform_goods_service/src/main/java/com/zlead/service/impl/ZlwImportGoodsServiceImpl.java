package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwPlatformGoodsMapper;
import com.zlead.entity.goods.*;
import com.zlead.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZlwImportGoodsServiceImpl implements IZlwImportGoodsService {
    @Autowired
    private IZlwPlatformGoodsService zlwPlatformGoodsService;
    @Autowired
    private IZlwPlatformGoodsImagesService zlwPlatformGoodsImagesService;
    @Autowired
    private IZlwPlatformGoodsManufacturerService zlwPlatformGoodsManufacturerService;
    @Autowired
    private IZlwPlatformGoodsBrandService zlwPlatformGoodsBrandService;
    @Autowired
    private IZlwPlatformGoodsSpecsGroupService zlwPlatformGoodsSpecsGroupService;

    @Autowired
    private IZlwPlatformGoodsSpecsService zlwPlatformGoodsSpecsService;
    public ZlwPlatformGoodsVO getZlwPlatformGoodsVOByPgId(String pgIdParam){
        ZlwPlatformGoodsVO zpgVo=new ZlwPlatformGoodsVO();
        Long pgId=Long.valueOf(pgIdParam);
        ZlwPlatformGoods zpg=zlwPlatformGoodsService.getZlwPlatformGoodsByPgId(pgId);
        if(zpg==null){
            throw new RuntimeException("未查询出平台商品数据");
        }
        BeanUtils.copyProperties(zpg,zpgVo);
        List<ZlwPlatformGoodsImages> zlwPlatformGoodsImages=
                zlwPlatformGoodsImagesService.getZlwPlatfomrGoodsImagesByPgId(pgId);
        zpgVo.setZlwPlatformGoodsImagesList(zlwPlatformGoodsImages);
        Long pgmId=Long.valueOf(zpg.getPgmId());
        ZlwPlatformGoodsManufacturer zlwPlatformGoodsManufacturer=
                zlwPlatformGoodsManufacturerService.getZlwPlatformGoodsManufacturerByPgId(pgmId);
        zpgVo.setZlwPlatformGoodsManufacturer(zlwPlatformGoodsManufacturer);
        Long pgbId=Long.valueOf(zpg.getPgbId());
        ZlwPlatformGoodsBrand zlwPlatformGoodsBrand=
                zlwPlatformGoodsBrandService.getZlwPlatformGoodsBrandByPgId(pgbId);
        zpgVo.setZlwPlatformGoodsBrand(zlwPlatformGoodsBrand);
        List<ZlwPlatformGoodsSpecsGroupVO> zlwPlatformGoodsSpecsGroupVOList=
                zlwPlatformGoodsSpecsGroupService.getZlwPlatformGoodsSpecsGroupVOByPgId(pgId);
        zpgVo.setZlwPlatformGoodsSpecsGroupVOList(zlwPlatformGoodsSpecsGroupVOList);
      /*  List<ZlwPlatformGoodsSpecs> zlwPlatformGoodsSpecsList=
                zlwPlatformGoodsSpecsService.getZlwPlatformGoodsSpecsByPgId(pgId);
        zpgVo.setZlwPlatformGoodsSpecsList(zlwPlatformGoodsSpecsList);*/
        return zpgVo;
    }

}
