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
    public class ZlwShopGoodsSpecsGroup implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号 
            */
    private String sgsgId;

            /**
            * 商品分类编号
            */
    private String sgcId;

            /**
            * 规格组名称
            */
    private String sgsgName;

            /**
            * 店铺id
            */
    private String shopId;

            /**
            * 备注
            */
    private String sgsnRemark;


}
