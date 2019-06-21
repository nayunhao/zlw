package com.zlead.entity.order;

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
* @since 2019-06-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class ZlwOrderShoppingCartData implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 购物车数据编号
            */
    private String oscdId;

            /**
            * 商品编号
            */
    private String sgId;

            /**
            * 购物车编号
            */
    private String osciId;

            /**
            * 下单时间
            */
    private Long oscdCreateTime;

            /**
            * 店铺商品编码
            */
    private String sgCode;

            /**
            * 店铺编号
            */
    private String shopId;

            /**
            * 生产企业编号
            */
    private String gmId;

            /**
            * 店铺商品价格编号
            */
    private String sgpId;

            /**
            * 商品购买数量
            */
    private Integer ocsdCount;

            /**
            * 订单金额
            */
    private String ocsdAmount;

            /**
            * 订单状态:  1:销售中2:仓库中3:违规下架
            */
    private Integer gsStatus;

            /**
            * 备注
            */
    private String sgsnRemark;


}
