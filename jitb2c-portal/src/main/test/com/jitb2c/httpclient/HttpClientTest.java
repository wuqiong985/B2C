package com.jitb2c.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuqiong6 on 2018/3/26.
 */
public class HttpClientTest {

    @Test
    public void doGet() throws Exception{
        //创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个Get对象
        HttpGet get = new HttpGet("http://localhost:8082/rest/content/list/89");
        //做请求
        CloseableHttpResponse response = httpClient.execute(get);
        //取响应的结果
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String entity1 = EntityUtils.toString(entity,"utf-8");
        System.out.println(statusCode+" "+entity1);
        //关闭httpClient
        response.close();
        httpClient.close();
    }

    /**
     * 带参数的Get
     * @throws Exception
     */
    @Test
    public void doGetWithParam() throws  Exception{
        //创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个Get对象
        URIBuilder uriBuilder = new URIBuilder("http://www.sougou.com/web");
        //用URIBuilder来封装uri
        uriBuilder.addParameter("query","欢乐颂2");
        HttpGet get = new HttpGet(uriBuilder.build());
        //做请求
        CloseableHttpResponse response = httpClient.execute(get);
        //取响应的结果
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String entity1 = EntityUtils.toString(entity,"utf-8");
        System.out.println(statusCode+" "+entity1);
        //关闭httpClient
        response.close();
        httpClient.close();
    }

    @Test
    public void doPost() throws Exception{
        //创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个post请求 (只有*.html的才会被拦截，项目设置)
        HttpPost post = new HttpPost("http://localhost:8083/httpclient/post.html");
        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        String entity = EntityUtils.toString(response.getEntity());
        System.out.println(entity);
        response.close();
        httpClient.close();
    }

    @Test
    public void doPostWithParam() throws Exception{
        //创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个post请求 (只有*.html的才会被拦截，项目设置)
        HttpPost post = new HttpPost("http://localhost:8083/httpclient/post1.html");

        //创建一个entity，模拟一个表单
        List<NameValuePair> kvList = new ArrayList<>();
        kvList.add(new BasicNameValuePair("username","张三"));
        kvList.add(new BasicNameValuePair("password","123"));

        //包装成一个entity对象
        StringEntity entity = new UrlEncodedFormEntity(kvList);
        //设置请求的内容
        post.setEntity(entity);
        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        String entity1 = EntityUtils.toString(response.getEntity());
        System.out.println(entity1);
        response.close();
        httpClient.close();
    }

}
