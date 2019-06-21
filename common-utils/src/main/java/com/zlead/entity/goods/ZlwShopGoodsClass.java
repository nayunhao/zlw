package com.zlead.entity.goods;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.List;

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
    public class ZlwShopGoodsClass implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
    @TableId
    private String sgcId;

            /**
            * 编码
            */
    private String sgcCode;

            /**
            * 名称
            */
    private String sgcName;

            /**
            * 等级： 1 ， 2
            */
    private Integer sgcLevel;

            /**
            * 店铺id
            */
    private String shopId;

            /**
            * 备注
            */
    private String sgcRemark;

            /**
             * pid
             */
    private String sgcParentId;

            /* *
             *
             *  伪删除字段   1删除   2未删除
             */
    private Integer  sgcIsDelete;

    private List<ZlwShopGoodsClass> children = new ArrayList<ZlwShopGoodsClass>();


}
