package com.zlead.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public class RequestPramToJson {

	public static JSONObject getParameter(HttpServletRequest request) throws UnsupportedEncodingException {
		BufferedReader reader = null;
		StringBuilder result = null;
		try {
			reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		
			String jsonStr = null;		
			result = new StringBuilder();
			while ((jsonStr = reader.readLine()) != null) {
				result.append(jsonStr);				
			}
			reader.close();// 关闭输入流
		} catch (IOException e) {
			e.printStackTrace();
		}		
		if(result.length()>0)
		{
			String s = URLDecoder.decode(result.toString(), "UTF-8");
			if(s != null && !s.isEmpty())
				return JSONObject.fromObject(s);
		}
		return null; 
	}
}
