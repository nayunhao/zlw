package com.zlead.entity.goods;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName 刘祎航
 * @Description TODO
 * @Author Administrator
 * @Date 2019/6/1211:39
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ZsgZpg {
    /**
     * 主键
     */
        private Integer zsgZpgId;
    /**
     * spu编码
     */
        private String  spuCode;
}
