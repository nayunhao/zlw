package com.zlead.entity.order;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 售后服务
    * </p>
*
* @author zlw
* @since 2019-06-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class ZlwAfterService implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "as_id", type = IdType.AUTO)
    private Long asId;

            /**
            * 售后类型：1:退货，2退款，3补发货，4维修，5安装，6其它
            */
    private Integer asType;

            /**
            * 售后名称
            */
    private String asName;

            /**
            * 开始时间
            */
    private Long asBeginDatetime;

            /**
            * 结束时间
            */
    private Long asEndDatetime;

            /**
            * 申请内容
            */
    private String asContent;

            /**
            * 联系人
            */
    private String asContact;

            /**
            * 联系电话
            */
    private String asPhone;


}
