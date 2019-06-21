package com.zlead.entity.pay;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 支付通道
    * </p>
*
* @author zlw
* @since 2019-06-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class ZlwPayChanel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pc_id", type = IdType.AUTO)
    private Long pcId;

    /**
    * 支付通道类型
    */
    private Integer pcType;

    /**
    * 支付通道名称
    */
    private String pcName;

    /**
    * 状态 1可用 2不可用
    */
    private Integer pcStatus;

    /**
     * 支付通道图标
     */
    private String pcIcon;


}
