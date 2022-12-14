package com.test.client;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestGet {

    public static void main(String[] args) throws ClientProtocolException, IOException {
        String url = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";
        CloseableHttpClient httpClient;
        HttpGet httpGet;
        CloseableHttpResponse httpResponse;
        HttpEntity responseBody;
        int responseCode;
        Header[] responseHeader;

        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet(url);
        httpResponse = httpClient.execute(httpGet);

        responseCode = httpResponse.getStatusLine().getStatusCode();
        responseBody = httpResponse.getEntity();
        responseHeader = httpResponse.getAllHeaders();

        String responseBodyString = EntityUtils.toString(responseBody,"utf-8");

        HashMap<String,String> hashMap = new HashMap<String,String>();
        for(Header header:responseHeader){
            hashMap.put(header.getName(), header.getValue());
        }


        System.out.println("This is the response code:" + responseCode);
        System.out.println("This is the response body:" + responseBodyString);
        System.out.println("This is the response header in hash" + hashMap);

    }

}