package com.test.api.com.test;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.test.client.restfulClient;
import com.test.client.JSONParser;

import java.io.IOException;
import java.net.URL;

import org.apache.http.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class testGet {
    restfulClient client;
    JSONObject responseBody;
    JSONParser jParser;
    int responseCode;
    String aaa;
    String url = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";



    @BeforeClass
    public void beforeClass() throws ParseException, IOException {
//发送请求，获取反馈
        client = new restfulClient();
        client.getResponse(url);
        responseBody = client.getBodyInJSON();
        responseCode = client.getCodeInNumber();

//调用JSONParser获取反馈中的城市信息
        jParser = new JSONParser();
        aaa = jParser.geturl(responseBody);
        System.out.println(aaa);
    }

    @Test
    public void TestGetRequest() {
//断言反馈中城市是否正确
        Assert.assertEquals(aaa, "北京");
//断言反馈中的状态码是否正确
        Assert.assertEquals(responseCode, 200);
    }




}
