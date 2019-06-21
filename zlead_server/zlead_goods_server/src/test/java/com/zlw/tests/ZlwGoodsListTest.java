package com.zlw.tests;

import com.zlead.domain.ApiRequest;
import com.zlead.good.GoodsServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ricky on 2019/6/10
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoodsServerApplication.class)
public class ZlwGoodsListTest {

    private Logger logger = LoggerFactory.getLogger(ZlwGoodsListTest.class);

    @Test
    public void getGoodList(){

        RestTemplate rest =new RestTemplate();
        ApiRequest apiRequest = new ApiRequest();
        Map<String,Object> map=new HashMap();
        map.put("sgStatus",2);
        map.put("shopId","1");
        map.put("currentPage","1");
        map.put("sizePage","1");
        apiRequest.setPlatform("pc");
        apiRequest.setData(map);
        ResponseEntity<String> responseEntity = rest.postForEntity("http://localhost:7001/ZlwGoods/getGoodsListByStatus", apiRequest, String.class);
        String body = responseEntity.getBody();
        logger.info("goodList:{}",body);

    }


}
