package com.zlead.entity.order;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 关闭订单原因表
    * </p>
*
* @author zlw
* @since 2019-06-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class ZlwOrderCloseCause implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "occ_id", type = IdType.AUTO)
    private Long occId;

            /**
            * 关闭类型
            */
    private Integer occType;

            /**
            * 关闭类型名称
            */
    private String occName;

            /**
            * 状态 1可用 2不可用
            */
    private Integer occStatus;


}
