package com.zlead.entity.goods;


import java.io.Serializable;

public class ZlwImportGoodsParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private String shopId;
    private String pgId;
    private String spuCode;
    private String sgInventory;
    private String sgpPublicEprice;
    private String sgpPublicPrice;
    private String shopClass1;
    private String shopClass2;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

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

    public String getSgInventory() {
        return sgInventory;
    }

    public void setSgInventory(String sgInventory) {
        this.sgInventory = sgInventory;
    }

    public String getSgpPublicEprice() {
        return sgpPublicEprice;
    }

    public void setSgpPublicEprice(String sgpPublicEprice) {
        this.sgpPublicEprice = sgpPublicEprice;
    }

    public String getSgpPublicPrice() {
        return sgpPublicPrice;
    }

    public void setSgpPublicPrice(String sgpPublicPrice) {
        this.sgpPublicPrice = sgpPublicPrice;
    }

    public String getShopClass1() {
        return shopClass1;
    }

    public void setShopClass1(String shopClass1) {
        this.shopClass1 = shopClass1;
    }

    public String getShopClass2() {
        return shopClass2;
    }

    public void setShopClass2(String shopClass2) {
        this.shopClass2 = shopClass2;
    }
}
