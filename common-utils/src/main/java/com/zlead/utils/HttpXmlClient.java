package com.zlead.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpXmlClient {
    private static Logger logger = LoggerFactory.getLogger(HttpXmlClient.class);
    
    // public static void main(String[] args) {
    //
    // String loginUrl =
    // "http://172.16.0.126:8080/irs-iface/om/inf/hotel/v1/updRankByHidAndVal";
    // Map<String, String> params = new HashMap<String, String>();
    // params.put("hid", "20585");
    // params.put("code", "00002");
    // params.put("ranking", "0");
    //
    // String xml = HttpXmlClient.post(loginUrl, params);
    //
    // }
    
    public static String post(String url, Map<String, String> params) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        
        HttpParams httpParams = httpclient.getParams();
        if (httpParams == null) {
            httpParams = new BasicHttpParams();
        }
        HttpConnectionParams.setConnectionTimeout(httpParams, 2 * 1000);
        HttpConnectionParams.setSoTimeout(httpParams, 2 * 1000);
        httpclient.setParams(httpParams);
        
        String body = null;
        
        // logger.debug("create http post:" + url);
        HttpPost post = postForm(url, params);
        
        body = invoke(httpclient, post);
        
        httpclient.getConnectionManager().shutdown();
        
        return body;
    }
    
    public static String get(String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;
        
        logger.debug("create httppost:" + url);
        HttpGet get = new HttpGet(url);
        body = invoke(httpclient, get);
        
        httpclient.getConnectionManager().shutdown();
        
        return body;
    }
    
    private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost) {
        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);
        
        return body;
    }
    
    private static String paseResponse(HttpResponse response) {
        // logger.debug("get response from http server..");
        HttpEntity entity = response.getEntity();
        
        // logger.debug("response status: " + response.getStatusLine());
        // String charset =
        // ContentType.getOrDefault(entity).getCharset().name();
        // logger.debug(charset);
        
        String body = null;
        try {
            body = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return body;
    }
    
    private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost) {
        // logger.debug("execute post...");
        HttpResponse response = null;
        
        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            logger.debug(ExceptionUtils.getStackTraceAsString(e));
        } catch (IOException e) {
            logger.debug(ExceptionUtils.getStackTraceAsString(e));
        }
        return response;
    }
    
    private static HttpPost postForm(String url, Map<String, String> params) {
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        
        try {
            // logger.debug("set utf-8 form entity to httppost");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, Charset.availableCharsets().get("UTF-8").name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        return httpost;
    }
}
