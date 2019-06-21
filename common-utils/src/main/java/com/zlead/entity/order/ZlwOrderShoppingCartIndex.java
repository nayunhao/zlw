package com.zlead.entity.order;

    import java.sql.Blob;
    import com.baomidou.mybatisplus.annotation.TableField;
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
    public class ZlwOrderShoppingCartIndex implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 购物车编号
            */
    private String osciId;

            /**
            * 客户ID
            */
    private String userId;

            /**
            * 订单名称
            */
    private String osciName;

            /**
            * 下单时间
            */
    private Long osciCreateTime;

            /**
            * 订单有效时长：2000ms
            */
    private Integer osciExpireTimelong;

            /**
            * 订单总金额
            */
    private String osciAmount;

            /**
            * 订单状态：
1:待支付2:已支付3:待发货4:已发货5:已完成
7:已取消8:已关闭 9:退货中10:已退款11:未退款12:部分退款
            */
    private Integer osciStatus;

            /**
            * 1:现金 2:WeiXin 3:aliPay  4:银行卡  5:挂账
            */
    private Integer ocsiPayType;

            /**
            * 支付时间
            */
    private Long ocsiPayTime;

            /**
            * 1：线上   2：线下
            */
    private Integer ocsiFromSource;

            /**
            * 1:自取  2:配送
            */
    private Blob ocsiDispatching;

            /**
            * 商品种类
            */
    private Integer ocsiGoodsCategory;

            /**
            * 订单锁定 1:锁定 2:未锁
            */
        @TableField("ocsi_isLock")
    private Integer ocsiIslock;

            /**
            * 订单删除 1:已删除 2:未删除
            */
        @TableField("osci_isDelete")
    private Integer osciIsdelete;

            /**
            * 网单：买家留言 门单：卖家备注
            */
    private String osciRemark;

            /**
            * 订单修改后的总金额
            */
    private String osciNewAmount;

            /**
            * 运费
            */
    private String osciFreight;

            /**
            * 订单取消原因1:重新下单 
2:缺货
3:客户不需要
4:订单信息错误
5:未收到货款
6:无法赊销
7:其他原因
            */
    private Integer osciCancelCause;

            /**
            * 收款备注
            */
    private String osciGatheringRemark;


}
