package com.zlead.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwShopGoodsClassMapper;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.entity.goods.ZlwShopGoodsClass;
import com.zlead.service.IZlwShopGoodsClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ZlwShopGoodsClassServiceImpl extends ServiceImpl<ZlwShopGoodsClassMapper, ZlwShopGoodsClass> implements IZlwShopGoodsClassService {




    @Transactional(rollbackFor=Exception.class)
    @Override
    public  List<ZlwShopGoodsClass> selectListAll(String sgcParentId,String shopId){
        Map<String,Object>map=new HashMap<>();
        map.put("sgcParentId",sgcParentId);
        map.put("shopId",shopId);
        return zlwShopGoodsClassMapper.selectListAll(map);
    }


    @Autowired
    private  ZlwShopGoodsClassMapper zlwShopGoodsClassMapper;

    /*@Override
    public List<ZlwShopGoodsClass> selectByMap( Map<String, Object> map){
        return zlwShopGoodsClassMapper.selectByMap(map);
    }*/

    @Override
    public int editShopGoodsClass(Map<String, Object> map) {
        try {
            String sgcId = (String) map.get("sgcId");
            String sgcName = (String) map.get("sgcName");
            ZlwShopGoodsClass zlwShopGoodsClass = this.baseMapper.selectById(sgcId);
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("sgcName",sgcName);
            param.put("sgcLevel",zlwShopGoodsClass.getSgcLevel());
            List<ZlwShopGoodsClass> list = zlwShopGoodsClassMapper.selectByName(param);
            if(null != list && list.size()>0){
                return 1;
            }
            zlwShopGoodsClass.setSgcName(sgcName);
            int i = this.baseMapper.updateById(zlwShopGoodsClass);
            if(i<1){
                return 2;
            }
        } catch (Exception e){
            e.printStackTrace();
            return 2;
        }
        return 3;
    }

    @Override
    public boolean removeShopGoodsClass(String sgcId) {
        try {
            ZlwShopGoodsClass zlwShopGoodsClass = this.baseMapper.selectById(sgcId);
            if(null != zlwShopGoodsClass){
                Integer level = zlwShopGoodsClass.getSgcLevel();
                zlwShopGoodsClass.setSgcIsDelete(1);//删除
                if(1 == level){  //如果是一级分类  删除下面的二级分类
                    zlwShopGoodsClassMapper.updateByParentId(zlwShopGoodsClass);
                }
                int i = this.baseMapper.updateById(zlwShopGoodsClass);
                if(i<1){
                    return false;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }



    @Override
    public ZlwShopGoodsClass selectOneByName(String className) {
        QueryWrapper<ZlwShopGoodsClass> queryWrapper = new QueryWrapper<ZlwShopGoodsClass>();
        queryWrapper.eq("sgc_name",className);
        ZlwShopGoodsClass zlwShopGoodsClass = zlwShopGoodsClassMapper.selectOne(queryWrapper);
        return  zlwShopGoodsClass;
    }

    @Override
   public  List<ZlwShopGoodsClass> selectListAllTree(){
        List<ZlwShopGoodsClass> zlwShopGoodsClasses = zlwShopGoodsClassMapper.selectListAllTree();
        return parseMenuTree(zlwShopGoodsClasses);
   }


    /**
     * @方法名: parseMenuTree<br>
     * @描述: 组装店铺<br>
     * @param list 数据库里面获取到的全量店铺分类
     * @return
     */
    public static List<ZlwShopGoodsClass> parseMenuTree(List<ZlwShopGoodsClass> list){
        List<ZlwShopGoodsClass> result = new ArrayList<ZlwShopGoodsClass>();

        // 1、获取第一级节点
        for (ZlwShopGoodsClass menu : list) {
            if(menu.getSgcParentId().equals("0")) {
                result.add(menu);
            }
        }

        // 2、递归获取子节点
        for (ZlwShopGoodsClass parent : result) {
            recursiveTree(parent, list);
        }

        return result;
    }

    public static ZlwShopGoodsClass recursiveTree(ZlwShopGoodsClass parent, List<ZlwShopGoodsClass> list) {
        for (ZlwShopGoodsClass menu : list) {
            if(parent.getSgcId().equals(menu.getSgcParentId())) {
                menu = recursiveTree(menu, list);
                parent.getChildren().add(menu);

            }
        }
        return parent;
    }

}
