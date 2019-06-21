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
    public class ZlwPlatformGoodsManufacturer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String pgmId;

    private String pgmCode;

    private String pgmName;

    private String pgcRemark;


}
