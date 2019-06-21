package com.zlead.entity.goods;

public class ZlwPlatformGoodsImages {
    private String pgImageId;

    private String pgId;

    private String pgImageUrl;

    private String pgImageType;

    private String pgApplicationType;

    private String pgImageDesc;

    private String pgImageSort;

    private String pgImageRemark;

    public String getPgImageId() {
        return pgImageId;
    }

    public void setPgImageId(String pgImageId) {
        this.pgImageId = pgImageId == null ? null : pgImageId.trim();
    }

    public String getPgId() {
        return pgId;
    }

    public void setPgId(String pgId) {
        this.pgId = pgId == null ? null : pgId.trim();
    }

    public String getPgImageUrl() {
        return pgImageUrl;
    }

    public void setPgImageUrl(String pgImageUrl) {
        this.pgImageUrl = pgImageUrl == null ? null : pgImageUrl.trim();
    }

    public String getPgImageType() {
        return pgImageType;
    }

    public void setPgImageType(String pgImageType) {
        this.pgImageType = pgImageType == null ? null : pgImageType.trim();
    }

    public String getPgApplicationType() {
        return pgApplicationType;
    }

    public void setPgApplicationType(String pgApplicationType) {
        this.pgApplicationType = pgApplicationType == null ? null : pgApplicationType.trim();
    }

    public String getPgImageDesc() {
        return pgImageDesc;
    }

    public void setPgImageDesc(String pgImageDesc) {
        this.pgImageDesc = pgImageDesc == null ? null : pgImageDesc.trim();
    }

    public String getPgImageSort() {
        return pgImageSort;
    }

    public void setPgImageSort(String pgImageSort) {
        this.pgImageSort = pgImageSort == null ? null : pgImageSort.trim();
    }

    public String getPgImageRemark() {
        return pgImageRemark;
    }

    public void setPgImageRemark(String pgImageRemark) {
        this.pgImageRemark = pgImageRemark == null ? null : pgImageRemark.trim();
    }
}