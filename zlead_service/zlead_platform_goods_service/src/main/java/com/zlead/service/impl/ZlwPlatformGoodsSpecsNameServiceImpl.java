package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwPlatformGoodsSpecsNameMapper;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecsName;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecsNameVO;
import com.zlead.service.IZlwPlatformGoodsSpecsNameService;
import com.zlead.service.IZlwPlatformGoodsSpecsValueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
public class ZlwPlatformGoodsSpecsNameServiceImpl extends ServiceImpl<ZlwPlatformGoodsSpecsNameMapper, ZlwPlatformGoodsSpecsName> implements IZlwPlatformGoodsSpecsNameService {

    @Autowired
    private IZlwPlatformGoodsSpecsValueService zlwPlatformGoodsSpecsValueService;
    @Override
    public List<ZlwPlatformGoodsSpecsName> getZlwPlatformGoodsSpecsNameByPgsgId(String pgsgId) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pgsg_id",pgsgId);
        List<ZlwPlatformGoodsSpecsName> zlwPlatformGoodsSpecsNameList=(List<ZlwPlatformGoodsSpecsName>)listByMap(map);
        return zlwPlatformGoodsSpecsNameList;
    }

    @Override
    public List<ZlwPlatformGoodsSpecsNameVO> getZlwPlatformGoodsSpecsNameVO(String pgsgId){
        List<ZlwPlatformGoodsSpecsName> zlwPlatformGoodsSpecsNameList=getZlwPlatformGoodsSpecsNameByPgsgId(pgsgId);
        List<ZlwPlatformGoodsSpecsNameVO> zlwPlatformGoodsSpecsNameVOList=new ArrayList<>();
        zlwPlatformGoodsSpecsNameList.forEach(e->{
            ZlwPlatformGoodsSpecsNameVO zlwPlatformGoodsSpecsNameVO=new ZlwPlatformGoodsSpecsNameVO();
            BeanUtils.copyProperties(e,zlwPlatformGoodsSpecsNameVO);
            zlwPlatformGoodsSpecsNameVO.setZlwPlatformGoodsSpecsValueList(zlwPlatformGoodsSpecsValueService.getZlwPlatformGoodsSpecsValueByPgsnId(e.getPgsnId()));
            zlwPlatformGoodsSpecsNameVOList.add(zlwPlatformGoodsSpecsNameVO);
        });
        return zlwPlatformGoodsSpecsNameVOList;
    }
}
