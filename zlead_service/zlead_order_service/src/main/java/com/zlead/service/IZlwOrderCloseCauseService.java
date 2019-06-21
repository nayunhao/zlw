package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.order.ZlwOrderCloseCause;

import java.util.List;

/**
 * <p>
 * 关闭订单原因表 服务类
 * </p>
 *
 * @author zlw
 * @since 2019-06-20
 */
public interface IZlwOrderCloseCauseService extends IService<ZlwOrderCloseCause> {

    List<ZlwOrderCloseCause> getUseableCloseOrderCause();

}
