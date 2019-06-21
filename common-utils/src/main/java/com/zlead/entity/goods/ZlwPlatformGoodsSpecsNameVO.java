package com.zlead.entity.goods;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ZlwPlatformGoodsSpecsNameVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String pgsnId;

    private String pgsnName;

    private String pgcId;

    private String pgsgId;

    private String pgsnRemark;

    private List<ZlwPlatformGoodsSpecsValue> zlwPlatformGoodsSpecsValueList;
}
