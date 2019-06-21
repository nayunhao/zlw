package com.zlead.entity.goods;

    import java.io.Serializable;

    import com.baomidou.mybatisplus.annotation.TableField;
    import com.baomidou.mybatisplus.annotation.TableId;
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
    public class ZlwPlatformGoods implements Serializable {

    private static final long serialVersionUID = 1L;
            /**
             * 编号
             */
            @TableId
    private String pgId;
            /**
             * SPU编码
             */
    private String spuCode;
            /**
            * 条形码
            */
    private String pgCodebar;
            /**
             * 商品名称
             */
    private String pgName;
            /**
             * 类别1
             */
    private String pgcId1;
            /**
             * 类别2
             */
    private String pgcId2;
            /**
             * 类别3
             */
    private String pgcId3;
            /**
             * 生产企业编号
             */
    private String pgmId;
            /**
             * 商品品牌
             */
    private String pgRemark;
            /**
             * 商品品牌
             */
    private String pgbId;

    @TableField(exist = false)
    private String pgImageUrl;

    @TableField(exist = false)
    private String pgImageRemark;


}
