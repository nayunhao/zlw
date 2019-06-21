package com.zlw.tests;

import com.zlead.GoodsServiceApplication;
import com.zlead.dao.mapper.ZlwShopGoodsMapper;
import com.zlead.domain.ApiRequest;
import com.zlead.entity.goods.ZlwShopGoods;
import com.zlead.utils.JsonMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest(classes = GoodsServiceApplication.class)
@RunWith(SpringRunner.class)
public class ZlwGoodsControllerTests {

    @Autowired
    private ZlwShopGoodsMapper zlwShopGoodsMapper;

    @Test
    public void testSpringCacheWithRedis() throws Exception{

        System.out.println(("----- testSpringCacheWithRedis ------"));
        ZlwShopGoods zlwShopGoods = zlwShopGoodsMapper.getShopGoods("1111");

        System.out.println(zlwShopGoods);

        //from redis
        zlwShopGoods = zlwShopGoodsMapper.getShopGoods("1111");

        System.out.println(zlwShopGoods);

    }
}
