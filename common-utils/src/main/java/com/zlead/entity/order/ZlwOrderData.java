package com.zlead.entity.order;

    import java.math.BigDecimal;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 订单中商品数据
    * </p>
*
* @author zlw
* @since 2019-06-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class ZlwOrderData implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 订单数据编号
            */
    private String odId;

            /**
            * 商品编号
            */
    private String sgId;

            /**
            * 订单编号
            */
    private String osciId;

            /**
            * 下单时间
            */
    private Long odCreateTime;

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
    private Integer odCount;

            /**
            * 商品金额
            */
    private BigDecimal odAmount;

            /**
            * 降价优惠金额
            */
    private BigDecimal odDiscount;

            /**
            * 商品状态:  1:销售中2:已下架3:违规下架4:已降价
            */
    private Integer gsStatus;

            /**
            * 退货标识：1:已退货2:退货中
            */
    private Integer odRegoodsStatus;

            /**
            * 退货申请时间
            */
    private Long odRegoodsDatetime;

            /**
            * 支付状态：1:已支付2:待支付3:已挂账
            */
    private Integer odPayStatus;

            /**
            * 备注
            */
    private String odRemark;


}
