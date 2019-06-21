package com.zlead.entity.goods;

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
* @since 2019-05-31
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class ZlwPlatformGoodsSpecsValue implements Serializable {

    private static final long serialVersionUID = 1L;

    private String pgsvId;

    private String pgsnId;

    private String pgsvValue;

    private String pgcId;

    private String pgsgId;

    private String gsnRemark;


}
