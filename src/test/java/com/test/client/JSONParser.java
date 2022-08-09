package com.test.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.test.client.restfulClient;
import com.test.client.JSONParser;

import java.io.IOException;
import java.net.URL;

import org.apache.http.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class JSONParser {
    static restfulClient client;
    static JSONObject responseBody;
    JSONParser jParser;
    int responseCode;
    String aaa;

    JSONObject internalJSON;

    public String geturl(JSONObject jo) {// 传入参数本身就是一个json对象
        String city = "";

        try {
//有data情况下，先后获取data下面的
/*            例如：
            {
                "status": 0,
                    "data": {
                "list": [
                {
                    "id": "28067118079",
                        "time": "202208081600",
                        "citycode": "307",
                        "district_type": "1",
                        "roadsegid": "河大路-1-xj20211105",
                        "speed": "25.81",
                        "yongdu_length": "0.00",
                        "road_type": "4",
                        "roadname": "河大路",
                        "index": "1.447",
                        "index_level": 1,
                        "length": "2.20",
                        "semantic": "从五四东路到前卫桥，东向西",
                        "links": "-",
                        "location": ",",
                        "nameadd": ""
                }
],
                "cityborder": "65",
                        "updatetime": "202208081600"
            },
                "message": "success"
            }*/
//            JSONObject internalJSON = jo.getJSONObject("data");
//            JSONArray bodyJsonArray = internalJSON.getJSONArray("list");//先获取到data，再获取到list json集合
//            JSONObject targetJsonObject = bodyJsonArray.getJSONObject(0); list只有一条数据，所以索引直接写死0。若有多条数据，用for循环。
//            String url = targetJsonObject.getString("url");


         /*   以下是没有data的情况下。直接获取:
            {
                "images": [
                {
                    "startdate": "20220807",
                        "fullstartdate": "202208071600",
                        "enddate": "20220808",...},{
                "title": "贴秋膘了吗？",
                        "quiz": "/search?...
                }
            }*/
            JSONArray bodyJsonArray = jo.getJSONArray("images");//采用getJSONArray方法， 定位到images json集合
            for (int i = 0; i < bodyJsonArray.size(); i++) {// 长度不用length（）。用.size()。
                JSONObject targetJsonObject = bodyJsonArray.getJSONObject(i);
                String url = targetJsonObject.getString("url");
                System.out.println("----------------------" + url + "----------------------");
            }






        }catch (Exception e){
            e.printStackTrace();
        }
        return city;
    }


    public static void main(String[] args) throws IOException {
        JSONParser JSONParser=new JSONParser();
        client = new restfulClient();
        client.getResponse("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=3");
        responseBody = client.getBodyInJSON();
        JSONParser.geturl(responseBody);
    }
}
