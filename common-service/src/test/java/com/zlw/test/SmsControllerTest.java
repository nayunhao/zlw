package com.zlw.test;

import com.zlead.CommonServiceApplication;
import com.zlead.common.RedisClient;
import com.zlead.domain.ApiRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonServiceApplication.class)
public class SmsControllerTest {
    private MockMvc mvc;

    @Autowired
    private RedisClient redisClient;

    @Test
    public void testRedis() throws Exception{

        redisClient.set("ttt111",222);
        System.out.println(redisClient.get("ttt111"));

    }

    @Test
    public void testvalidateCode() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        ApiRequest request = new ApiRequest();
        HashMap<String, String> map = new HashMap<>();
        map.put("phone","19904837055");
        map.put("serviceId","1");
        request.setPlatform("pc");
        request.setData(map);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8085/getPhoneValidateCode",request,String.class);
        String body = responseEntity.getBody();
        System.out.println(body);
    }
    @Test
    public void checkCode() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, String> map = new HashMap<>();
        map.put("phone","19904837055");
        map.put("code","5407");
        ApiRequest request = new ApiRequest();
        request.setPlatform("pc");
        request.setData(map);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8085/common/checkSmsCode",request,String.class);
        String body = responseEntity.getBody();
        System.out.println(body);
    }

}
