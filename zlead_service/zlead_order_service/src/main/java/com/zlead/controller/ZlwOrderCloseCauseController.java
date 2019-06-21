package com.zlead.controller;


import com.zlead.entity.order.ZlwOrderCloseCause;
import com.zlead.service.IZlwOrderCloseCauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 关闭订单原因表 前端控制器
 * </p>
 *
 * @author dmy
 * @since 2019-06-20
 */
@Controller
@RequestMapping("/ZlwOrder")
public class ZlwOrderCloseCauseController {
    @Autowired
    IZlwOrderCloseCauseService zlwOrderCloseCauseService;

    /**
     * 获取可用的关闭比订单原因
     */
    @PostMapping("/getUseableCloseOrderCause")
    public List<ZlwOrderCloseCause> getUseableCloseOrderCause(){
        List<ZlwOrderCloseCause> list = null;
        try {
            list = zlwOrderCloseCauseService.getUseableCloseOrderCause();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
