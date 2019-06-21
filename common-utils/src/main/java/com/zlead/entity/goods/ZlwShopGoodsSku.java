package com.zlead.entity.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zlw
 * @since 2019-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ZlwShopGoodsSku implements Serializable {

    private static final long serialVersionUID = 1L;


    //库存
    @TableField(exist = false)
    private String inventoryValue;
    //规格名称
    @TableField(exist = false)
    private String goodSpecName;
    //规格值
    @TableField(exist = false)
    private String goodSpecValue;
    //商品名称
    @TableField(exist = false)
    private String sgName;
    //分类名称
    @TableField(exist = false)
    private String sgClassName1;
    @TableField(exist = false)
    private String sgClassName2;
    //价格表
    @TableField(exist = false)
    private ZlwShopGoodsPrice shopGoodsPrice;
    /**
     * 编号
     */
    @TableId
    private String sgkId;

    /**
     * 编号
     */
    private String sguId;

    /**
     * SKU编码
     */
    private String sgCode;

    /**
     * SPU编码
     */
    private String spuCode;

    /**
     * 自定义编码
     */
    private String sgCustomCode;

    /**
     * 商品自定义名称
     */
    private String sgCustomName;

    /**
     * 店铺编号
     */
    private String shopId;

    /**
     * 分类1
     */
    private Long sgClass1;

    /**
     * 分类2
     */
    private Long sgClass2;

    /**
     * 规格
     */
    private String sgSpecs;

    /**
     * 价格编号
     */
    private String sgpId;

    /**
     * 商品所有图片文件夹链接
     */
    private String sgImagesUrl;

    /**
     * 发布渠道 1：门店 2：网络
     */
    private Integer sgFromSource;

    /**
     * 状态 -1：全部 1：销售中 2：下架 3：违规下架
     */
    private Integer sgStatus;

    /**
     * 商品创建时间
     */
    private Long sgCreateTime;

    /**
     * 商品结束时间
     */
    private Long sgEndTime;

    /**
     * 单位
     */
    private String sgUnit;

    /**
     * 型号
     */
    private String sgModel;

    /**
     * 商品描述
     */
    private String sgDesc;

    /**
     * 包装清单
     */
    private String sgPackageList;

    /**
     * 店铺库存编号
     */
    private String sgiId;

    /**
     * 1：锁定 2：解锁
     */
    private Integer sgIsLock;

    /**
     * 1：删除 2：未删除
     */
    private Integer sgIsDelete;

    /**
     * 备注
     */
    private String sgRemark;

    /**
     * 置顶排序
     */
    private Long sgSort;

    /**
     * 置顶时间
     */
    private Date sgSortTime;

    private Integer sgSalesTarget;//1平台，2店铺
}
