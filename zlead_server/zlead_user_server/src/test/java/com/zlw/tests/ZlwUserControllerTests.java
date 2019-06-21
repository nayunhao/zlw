package com.zlw.tests;

import com.zlead.domain.ApiRequest;

import com.zlead.UserServerApplication;
import com.zlead.utils.JsonMapper;
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

@SpringBootTest(classes = UserServerApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ZlwUserControllerTests {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void before() {
        //获取mockmvc对象实例
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testUser() throws Exception{

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setPlatform("111");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/ZlwUser/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapper.getInstance().toJson(apiRequest))
                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
