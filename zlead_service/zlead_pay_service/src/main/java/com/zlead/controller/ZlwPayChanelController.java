package com.zlead.controller;

import com.zlead.entity.pay.ZlwPayChanel;
import com.zlead.service.IZlwPayChanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 支付通道 前端控制器
 * </p>
 *
 * @author dmy
 * @since 2019-06-20
 */
@RestController
@RequestMapping("/ZlwPay")
public class ZlwPayChanelController {
    @Autowired
    IZlwPayChanelService zlwPayChanelService;

    /**
     * 获取支付通道
     */
    @PostMapping("/getPayChanel")
    private List<ZlwPayChanel> getPayChanel(){
        List<ZlwPayChanel> list = null;
        try {
            list = zlwPayChanelService.getUseablePayChanel();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
