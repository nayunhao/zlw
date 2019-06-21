package com.zlead.entity.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName 刘祎航
 * @Description TODO
 * @Author Administrator
 * @Date 2019/6/1019:53
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ZlwOrderShoppingCartData implements Serializable  {
    /**
     * 订单数据编号
     */
    private  String  oscdId;
    /**
     * 商品编号
     */

    private  String  sgId;
    /**
     * 订单编号
     */
    private  String  osciId;
    /**
     * 下单时间
     */
    //private  Integer  oscdCreateTime;
    /**
     * 店铺商品编码
     */
    //private  String  shopId;
    /**
     * 生产企业编号
     */
    //private  String  gmId;
    /**
     * 店铺商品价格编号
     */
    //private  String  sgpId;
    /**
     * 商品购买数量
     */
    private  Integer  ocsdCount;
    /**
     * 订单金额
     */
    //private  String  ocsdAmount;
    /**
     * 订单状态:  1:销售中2:仓库中3:违规下架
     */
    //private  Integer  gsStatus;
    /**
     * 备注
     */
    //private  String  sgsnRemark;


}
