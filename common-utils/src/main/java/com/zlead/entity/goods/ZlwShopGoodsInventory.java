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
    public class ZlwShopGoodsInventory implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 店铺商品库存编号
            */
    private String sgiId;

            /**
            * 库存
            */
    private String sgiValue;


}
