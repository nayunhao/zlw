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
    public class ZlwShopGoodsSpec implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
    private String sgsId;

            /**
            * 店铺商品编码
            */
    private Long sgCode;

            /**
            * 规格id
            */
    private String sgsnId;

            /**
            * 规格值id
            */
    private String sgsvId;

            /**
            * 店铺编号
            */
    private String shopId;

            /**
            * 备注
            */
    private String sgsRemark;


}
