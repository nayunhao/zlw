package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.order.ZlwOrderCloseCause;
import com.zlead.entity.pay.ZlwPayChanel;

import java.util.List;

/**
 * <p>
 * 支付通道 服务类
 * </p>
 *
 * @author zlw
 * @since 2019-06-20
 */
public interface IZlwPayChanelService extends IService<ZlwPayChanel> {

    List<ZlwPayChanel> getUseablePayChanel();

}
