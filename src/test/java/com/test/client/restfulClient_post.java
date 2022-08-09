package com.test.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class restfulClient_post {

    /*getResponse：发送请求并获取反馈；
    getBodyInJSON：获取JSON格式的反馈主体；
    getCodeInNumber：获取反馈状态码；
    getHeaderInHash：获取哈希图形式的反馈头；*/


    CloseableHttpClient httpClient;
    HttpGet httpGet;
    HttpPost httpPost;
    CloseableHttpResponse httpResponse;
    int responseCode;
    JSONObject responseBody;
    HashMap<String, String> responseHeads;

//通过httpclient获取请求的反馈

    public void sendPost(String url, Object jsonObject, HashMap<String, String> headers) throws ClientProtocolException, IOException{
        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet(url);
        //创建post请求对象
        httpPost = new HttpPost(url);
        //设置请求主体格式
        //这里考虑用List来发送NameValuePair键值对来设置请求的主体。
        //httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

//        httpResponse = httpClient.execute(httpGet);

        httpPost.setEntity(new StringEntity(jsonObject.toString(), "UTF-8"));

//设置头部信息
        Set<String> set = headers.keySet();
        for(Iterator<String> iterator = set.iterator(); iterator.hasNext();){
            String key = iterator.next();
            String value = headers.get(key);
            httpPost.addHeader(key, value);
        }
        httpResponse = httpClient.execute(httpPost);

    }

    //以JSON格式获取到反馈的主体
    public JSONObject getBodyInJSON() throws ParseException, IOException{
        HttpEntity entity;
        String entityToString;
        entity = httpResponse.getEntity();
        entityToString = EntityUtils.toString(entity);
        responseBody = JSON.parseObject(entityToString);
        System.out.println("This is your response body" + responseBody);

        return responseBody;
    }

    //以哈希图的方式获取到反馈头部
    public HashMap<String, String> getHeaderInHash(){
        Header[] headers;
        headers = httpResponse.getAllHeaders();

        responseHeads = new HashMap<String, String>();

        for(Header header:headers){
            responseHeads.put(header.getName(), header.getValue());
        }

        System.out.println("This is your response header" + responseHeads);

        return    responseHeads;
    }
    //获取反馈状态码
    public int getCodeInNumber(){
        responseCode = httpResponse.getStatusLine().getStatusCode();

        System.out.println("This is your response code" + responseCode);

        return responseCode;
    }

}
