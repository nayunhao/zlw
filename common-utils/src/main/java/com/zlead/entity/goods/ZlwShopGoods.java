package com.zlead.entity.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

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
public class ZlwShopGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String sguId;

    /**
     * SPU编码
     */
    private String spuCode;

    /**
     * 自定义编码
     */
    private String sgCustomCode;

    /**
     * 商品名称
     */
    private String sgName;

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
     * 商品所有图片文件夹链接
     */
    private String sgImagesUrl;

    /**
     * 发布渠道 1：门店  2：网络
     */
    private Integer sgFromSource;

    /**
     * 状态： -1：全部，1：销售中，2：下架中，3：违规下架
     */
    private Integer sgSpuStatus;

    /**
     * 创建/更新时间
     */
    private Long sgOptionTime;

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
     * 1：锁定 2：解锁
     */
    private Integer sgIsLock;

    /**
     * 1：删除 2：没删除
     */
    private Integer sgIsDelete;

    /**
     * 备注
     */
    private String sgRemark;

    /**
     * 1平台，2店铺
     */
    private String sgSalesTarget;
    /**
     * 平台 品牌ID
     */
    private String pgbId;

    /**
     * 添加 店铺品牌 id
     */
    private String sgbId;

    //品牌名称
    @TableField(exist = false)
    private  String brandName;

    //SPU下包含的SKU
    @TableField(exist = false)
    private List<ZlwShopGoodsSku> skus;

    @TableField(exist = false)
    private Integer zsgZpgId;
}
