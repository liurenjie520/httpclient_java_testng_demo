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
public class test_test {

    @Test
    public void Str2JsonObject(){
        String jsonObjectStr = "{'zhangxy':'张学友','liudh':'刘德华','lim':'黎明','guofc':'郭富城'}" ;
        //做5次测试
        for(int i=0,j=5;i<j;i++)  //多次测试，无序
        {
            JSONObject jsonObject = JSONObject.parseObject(jsonObjectStr) ;

            for(java.util.Map.Entry<String,Object> entry:jsonObject.entrySet()){
                System.out.print(entry.getKey()+"-"+entry.getValue()+"\t");
            }
            System.out.println();//用来换行
        }
    }

}
