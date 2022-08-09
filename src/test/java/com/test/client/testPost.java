package com.test.client;
import com.alibaba.fastjson.JSONArray;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.test.client.restfulClient_post;
import com.test.client.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class testPost {
    restfulClient_post client;
    JSONObject responseBody;
    JSONParser jParser;
    int responseCode;
    String city;
    String url = "http://public63.noohle.com:1064/api/oa/weekly/plan/save";
    String postBody;
    @BeforeClass
    public void beforeClass() throws ClientProtocolException, IOException {
        client = new restfulClient_post();

//用NameValuePair的list来添加请求主体参数"Content-Type", "application/x-www-form-urlencoded"。 List<NameValuePair> params
/*        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", "Hello Kitty"));
        params.add(new BasicNameValuePair("status", "sold"));*/


//JSONObject来添加请求主体参数，同时json里面有jsonArray
        JSONObject params = new JSONObject();
        JSONObject json = new JSONObject();
        json.put("uuid","2aca5329-3b63-47bf-8730-7d2172dbcd03");
        json.put("nextJobContent","学习软件测试制度化222");
        json.put("id","621f34e77d16e4000104ba29");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(json);


        params.put("subDatas",jsonArray);
//        params.put("cname", "沈秀兰");




//用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/json");
        hashHead.put("Cookie", "paletteType=light; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2217e7a31a906a9f-01ba444c1841f4-f791539-2073600-17e7a31a907ca6%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%7D%2C%22%24device_id%22%3A%2217e7a31a906a9f-01ba444c1841f4-f791539-2073600-17e7a31a907ca6%22%7D; Qs_lvt_426693=1642727581%2C1645428608; Qs_pv_426693=3814116218338743300%2C4329048718471348700; Hm_lvt_0f9e8eeee8a4bf8a556a99eadd7d8255=1645428609; SESSION=67815493-b022-4ebe-8143-652372b16b32");

//传参发送post请求并接收反馈
        client.sendPost(url, params, hashHead);

        responseBody = client.getBodyInJSON();
        responseCode = client.getCodeInNumber();

        System.out.println(responseBody);
        jParser = new JSONParser();
        city = jParser.geturl(responseBody);
    }
    @Test
    public void testPostRequest() {
 //断言反馈中城市信息是否正确
//        Assert.assertEquals(city, "北京");
//断言反馈的状态码是否正确
        Assert.assertEquals(responseCode, 200);
    }

}
