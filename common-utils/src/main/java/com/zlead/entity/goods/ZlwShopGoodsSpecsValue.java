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
    public class ZlwShopGoodsSpecsValue implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
    private String sgsvId;

            /**
            * 规格名称编号
            */
    private String sgsnId;

            /**
            * 规格值
            */
    private String sgsnValue;

            /**
            * 店铺id
            */
    private String shopId;

            /**
            * 备注
            */
    private String sgsnRemark;


}
