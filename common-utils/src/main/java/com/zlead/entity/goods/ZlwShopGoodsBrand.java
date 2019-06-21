package com.zlead.entity.goods;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ZlwShopGoodsBrand {
    private Long sgbId;//品牌ID

    private Long pgbId;//平台品牌ID

    private Long sgmId;//生产企业id

    private String sgbName;//品牌名称

    private String sgbNamePinyin;//品牌名称拼音

    private String sgbNamePyFirst;//品牌名称拼音首字母

    private String sgbRemark;//备注

}