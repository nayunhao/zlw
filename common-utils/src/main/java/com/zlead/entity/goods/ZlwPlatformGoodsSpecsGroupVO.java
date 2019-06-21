package com.zlead.entity.goods;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ZlwPlatformGoodsSpecsGroupVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String pgsgId;

    private String pgsgName;

    private String spuCode;

    private String pgsnRemark;

    private List<ZlwPlatformGoodsSpecsNameVO> zlwPlatformGoodsSpecsNameVOList;

}
