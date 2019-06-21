package com.zlead.entity.goods;

import java.io.Serializable;
import java.util.List;

public class ZlwPlatformGoodsVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    private String pgId;
    /**
     * SPU编码
     */
    private String spuCode;
    /**
     * 条形码
     */
    private String pgCodebar;
    /**
     * 商品名称
     */
    private String pgName;
    /**
     * 类别1
     */
    private String pgcId1;
    /**
     * 类别2
     */
    private String pgcId2;
    /**
     * 类别3
     */
    private String pgcId3;
    /**
     * 生产企业编号
     */
    private String pgmId;
    /**
     * 商品品牌
     */
    private String pgbId;
    /**
     * 备注
     */
    private String pgRemark;
    private List<ZlwPlatformGoodsImages> zlwPlatformGoodsImagesList;
    private ZlwPlatformGoodsManufacturer zlwPlatformGoodsManufacturer;
    private ZlwPlatformGoodsBrand zlwPlatformGoodsBrand;
    private List<ZlwPlatformGoodsSpecs> zlwPlatformGoodsSpecsList;
    private List<ZlwPlatformGoodsSpecsGroupVO> zlwPlatformGoodsSpecsGroupVOList;
    public String getPgId() {
        return pgId;
    }

    public void setPgId(String pgId) {
        this.pgId = pgId;
    }

    public String getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(String spuCode) {
        this.spuCode = spuCode;
    }

    public String getPgCodebar() {
        return pgCodebar;
    }

    public void setPgCodebar(String pgCodebar) {
        this.pgCodebar = pgCodebar;
    }

    public String getPgName() {
        return pgName;
    }

    public void setPgName(String pgName) {
        this.pgName = pgName;
    }

    public String getPgcId1() {
        return pgcId1;
    }

    public void setPgcId1(String pgcId1) {
        this.pgcId1 = pgcId1;
    }

    public String getPgcId2() {
        return pgcId2;
    }

    public void setPgcId2(String pgcId2) {
        this.pgcId2 = pgcId2;
    }

    public String getPgcId3() {
        return pgcId3;
    }

    public void setPgcId3(String pgcId3) {
        this.pgcId3 = pgcId3;
    }

    public String getPgmId() {
        return pgmId;
    }

    public void setPgmId(String pgmId) {
        this.pgmId = pgmId;
    }

    public String getPgbId() {
        return pgbId;
    }

    public void setPgbId(String pgbId) {
        this.pgbId = pgbId;
    }

    public String getPgRemark() {
        return pgRemark;
    }

    public void setPgRemark(String pgRemark) {
        this.pgRemark = pgRemark;
    }

    public List<ZlwPlatformGoodsImages> getZlwPlatformGoodsImagesList() {
        return zlwPlatformGoodsImagesList;
    }

    public void setZlwPlatformGoodsImagesList(List<ZlwPlatformGoodsImages> zlwPlatformGoodsImagesList) {
        this.zlwPlatformGoodsImagesList = zlwPlatformGoodsImagesList;
    }

    public ZlwPlatformGoodsManufacturer getZlwPlatformGoodsManufacturer() {
        return zlwPlatformGoodsManufacturer;
    }

    public void setZlwPlatformGoodsManufacturer(ZlwPlatformGoodsManufacturer zlwPlatformGoodsManufacturer) {
        this.zlwPlatformGoodsManufacturer = zlwPlatformGoodsManufacturer;
    }

    public ZlwPlatformGoodsBrand getZlwPlatformGoodsBrand() {
        return zlwPlatformGoodsBrand;
    }

    public void setZlwPlatformGoodsBrand(ZlwPlatformGoodsBrand zlwPlatformGoodsBrand) {
        this.zlwPlatformGoodsBrand = zlwPlatformGoodsBrand;
    }

    public List<ZlwPlatformGoodsSpecs> getZlwPlatformGoodsSpecsList() {
        return zlwPlatformGoodsSpecsList;
    }

    public void setZlwPlatformGoodsSpecsList(List<ZlwPlatformGoodsSpecs> zlwPlatformGoodsSpecsList) {
        this.zlwPlatformGoodsSpecsList = zlwPlatformGoodsSpecsList;
    }

    public List<ZlwPlatformGoodsSpecsGroupVO> getZlwPlatformGoodsSpecsGroupVOList() {
        return zlwPlatformGoodsSpecsGroupVOList;
    }

    public void setZlwPlatformGoodsSpecsGroupVOList(List<ZlwPlatformGoodsSpecsGroupVO> zlwPlatformGoodsSpecsGroupVOList) {
        this.zlwPlatformGoodsSpecsGroupVOList = zlwPlatformGoodsSpecsGroupVOList;
    }
}
