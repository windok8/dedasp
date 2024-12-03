package com.dedasp.common.utils.http;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.util.List;

public class HttpClientUtils {
    /***
     * 发送Post请求
     * @param url 请求的地址
     * @param params 请求的参数
     * @return 相应结果
     */
    public static String sendPost(String url, List<NameValuePair> params) {
        //1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //2.创建Post请求对象
        HttpPost post = new HttpPost(url);

        //3.使用Post请求的参数
        post.setEntity(new UrlEncodedFormEntity(params));

        //4.执行请求
        CloseableHttpResponse response;
        String json;
        try {
            response = httpClient.execute(post);
            System.out.println("response:"+ response);
            json = EntityUtils.toString(response.getEntity());
            System.out.println("json:"+ json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return json;
    }
}
