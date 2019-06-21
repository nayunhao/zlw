package com.zlead.entity.goods;

    import java.io.Serializable;
    import java.util.List;

    import com.baomidou.mybatisplus.annotation.TableField;
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
    public class ZlwPlatformGoodsClass implements Serializable {

    private static final long serialVersionUID = 1L;

    private String pgcId;

    private String pgcName;

    private Integer pgcLevel;

    private String pgcParentId;

    private String pgcRemark;

    @TableField(exist = false)
    private List<ZlwPlatformGoodsClass> sonClassList;

}
