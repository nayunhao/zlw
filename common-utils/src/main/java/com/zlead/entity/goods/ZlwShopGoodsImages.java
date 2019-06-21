package com.zlead.entity.goods;

    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author zlw
* @since 2019-05-31
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class ZlwShopGoodsImages implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
    private String sgImageId;

            /**
            * 店铺商品编号
            */
    private String sgkId;

            /**
            * 店铺id
            */
    private String shopId;

            /**
            * 图片名称
            */
    private String sgImageName;

            /**
            * 图片类型 1：png 2：bmp 3：gif
            */
    private Integer sgImageType;

            /**
            * 排序
            */
    private Integer sgImageSort;

            /**
            * 备注
            */
    private String sgImageRemark;


}
