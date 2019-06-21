package com.zlead.entity.order;

    import java.math.BigDecimal;
    import com.baomidou.mybatisplus.annotation.TableField;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 订单数据
    * </p>
*
* @author zlw
* @since 2019-06-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class ZlwOrderIndex implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 订单编号
            */
    private String oiId;

            /**
            * 线上网单：1:客户ID 线下门店：2:门店下单员工ID
            */
    private String userId;

            /**
            * 店铺编号
            */
    private String shopId;

            /**
            * 客户姓名
            */
    private String oiRealName;

            /**
            * 订单名称
            */
    private String oiName;

            /**
            * 下单时间
            */
    private Long oiCreateTime;

            /**
            * 订单有效时长：2000ms
            */
    private Integer oiExpireTimelong;

            /**
            * 订单总金额
            */
    private BigDecimal oiAmount;

            /**
            * 订单优惠金额
            */
    private BigDecimal oiDiscount;

            /**
            * 订单来源：1:线上网单   2:线下门店
            */
    private Integer oiFromSource;

            /**
            * 1:自取  2:配送
            */
    private Integer oiDispatching;

            /**
            * 商品种类
            */
    private Integer oiGoodsCategory;

            /**
            * 订单修改后的总金额
            */
    private String oiNewAmount;

            /**
            * 运费
            */
    private String oiFreight;

            /**
            * 收款备注
            */
    private String oiGatheringRemark;

            /**
            * 订单支付状态：1:待支付2:已支付3:已挂账
            */
    private Integer oiPayStatus;

            /**
            * 1:现金 2:WeiXin 3:aliPay  4:银行卡  5:挂账
            */
    private Integer oiPayType;

            /**
            * 支付时间
            */
    private Long oiPayTime;

            /**
            * 配单状态：1:已配单完成 2:待配单3:配单中
            */
    private Integer oiMatchStatus;

            /**
            * 配单员工编号
            */
    private String oiMatchUserId;

            /**
            * 配单时间
            */
    private Long oiMatchDatetime;

            /**
            * 配单总金额
            */
    private BigDecimal oiMatchAmount;

            /**
            * 订单签收状态：1:待签收2:已签收
            */
    private Integer oiReceiveStatus;

            /**
            * 签收人
            */
    private String oiReceiveName;

            /**
            * 签收时间
            */
    private Long oiReceiveDatetime;

            /**
            * 物流状态：1:待发货2:已发货3:退货中
            */
    private Integer oiLogisticsStatus;

            /**
            * 物流公司
            */
    private String oiLogisticsCompany;

            /**
            * 发货时间
            */
    private Long oiLogisticsDatetime;

            /**
            * 发货条码
            */
    private String oiLogisticsCodebar;

            /**
            * 退货标识：1:全部退货2:部分退货
            */
    private Integer oiRegoodsStatus;

            /**
            * 售后编号（退货，退款，补发货，维修，安装，其它）
            */
    private String asId;

            /**
            * 退款标识：1:已退款2:未退款3:退款中
            */
    private Integer oiRefundTatus;

            /**
            * 订单状态：1:已完成2:未完成3:已取消4:已关闭
            */
    private Integer oiStatus;

            /**
            * 订单取消原因1:重新下单 
2:缺货
3:客户不需要
4:订单信息错误
5:未收到货款
6:无法赊销
7:其他原因
            */
    private Integer oiCancelCause;

            /**
            * 订单锁定 1:锁定 2:未锁
            */
        @TableField("oi_isLock")
    private Integer oiIslock;

            /**
            * 订单删除 1:已删除 2:未删除
            */
        @TableField("oi_isDelete")
    private Integer oiIsdelete;

            /**
            * 网单：买家留言 门单：卖家备注
            */
    private String oiRemark;


}
