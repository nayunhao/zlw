package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwPlatformGoodsSpecsGroupMapper;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecsGroup;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecsGroupVO;
import com.zlead.service.IZlwPlatformGoodsSpecsGroupService;
import com.zlead.service.IZlwPlatformGoodsSpecsNameService;
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
public class ZlwPlatformGoodsSpecsGroupServiceImpl extends ServiceImpl<ZlwPlatformGoodsSpecsGroupMapper, ZlwPlatformGoodsSpecsGroup> implements IZlwPlatformGoodsSpecsGroupService {

@Autowired
private IZlwPlatformGoodsSpecsNameService zlwPlatformGoodsSpecsNameService;
    @Override
    public List<ZlwPlatformGoodsSpecsGroupVO> getZlwPlatformGoodsSpecsGroupVOByPgId(Long pgId) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pg_id",pgId);
        List<ZlwPlatformGoodsSpecsGroup> zlwPlatformGoodsSpecsGroupList=(List<ZlwPlatformGoodsSpecsGroup>)listByMap(map);
        List<ZlwPlatformGoodsSpecsGroupVO> zlwPlatformGoodsSpecsGroupVOS=new ArrayList<>();
        zlwPlatformGoodsSpecsGroupList.forEach(e->{
            ZlwPlatformGoodsSpecsGroupVO zlwPlatformGoodsSpecsGroupVO=new ZlwPlatformGoodsSpecsGroupVO();
            BeanUtils.copyProperties(e,zlwPlatformGoodsSpecsGroupVO);
            zlwPlatformGoodsSpecsGroupVO.setZlwPlatformGoodsSpecsNameVOList(zlwPlatformGoodsSpecsNameService.getZlwPlatformGoodsSpecsNameVO(e.getPgsgId()));
            zlwPlatformGoodsSpecsGroupVOS.add(zlwPlatformGoodsSpecsGroupVO);
        });
        return zlwPlatformGoodsSpecsGroupVOS;
    }
}
