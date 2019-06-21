package com.zlead.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class ConvertList {
	private static final String SEP1 = ",";
	
	/**
	 * List转换String
	 * 
	 * @param list
	 *            :需要转换的List
	 * @return String转换后的字符串
	 */
	 public static String ListToString(List<?> list) {
	     StringBuffer sb = new StringBuffer();
	     if (list != null && list.size() > 0) {
	        for (int i = 0; i < list.size(); i++) {
	            if (list.get(i) == null || list.get(i) == "") {
	                      continue;
	            }
	            // 如果值是list类型则调用自己
	            if (list.get(i) instanceof List) {
	                sb.append(ListToString((List<?>) list.get(i)));
	                if(i != list.size()-1)
	                {
	                	sb.append(SEP1);
	                }
	            } else if(list.get(i) instanceof Map){
	            	String s = ConvertJson.map2json((Map<?, ?>)list.get(i));
	            	sb.append(s);
	            	if(i !=list.size()-1)
	                {
	                	sb.append(SEP1);
	                }
	            } else {
	            	JSONObject jSONObject = JSONObject.fromObject(list.get(i));   
	                sb.append(jSONObject.toString());
	                if(i != list.size()-1)
	                {
	                	sb.append(SEP1);
	                }
	            }
	         }
	     }
	     return sb.toString();
	}
	/**
	 * List to String
	 * @param list 
	 * @param split 分隔符
	 * @return
	 */
	public static String ListToString(List<String> list, String split)
	{
	      if(list==null)
	          return null;
	      if(split.isEmpty())
	    	  split = ",";
	      StringBuilder result = new StringBuilder();
	      boolean first = true;
	      //第一个前面不拼接","
	      for(String string :list) {
	          if(first) {
	             first=false;
	          }else{
	             result.append(split);
	          }
	          result.append(string);
	       }
	       return result.toString();
	}	    
	/**
	 * String to List
	 * @param strs
	 * @param split
	 * @return
	 */
	public List<String> StringToList(String strs, String split)
	{
		 if(split.isEmpty())
	    	  split = ",";
		 String str[] = strs.split(split);
		 return Arrays.asList(str);
	}
	/**
     * 拆分集合
     * @param <T>
     * @param resList  要拆分的集合
     * @param count    每个集合的元素个数
     * @return  返回拆分后的各个集合
     */
    public static  <T> List<List<T>> split(List<T> resList,int count){
        
        if(resList==null ||count<1)
            return  null ;
        List<List<T>> ret=new ArrayList<List<T>>();
        int size=resList.size();
        if(size<=count){ //数据量不足count指定的大小
            ret.add(resList);
        }else{
            int pre=size/count;
            int last=size%count;
            //前面pre个集合，每个大小都是count个元素
            for(int i=0;i<pre;i++){
                List<T> itemList=new ArrayList<T>();
                for(int j=0;j<count;j++){
                    itemList.add(resList.get(i*count+j));
                }
                ret.add(itemList);
            }
            //last的进行处理
            if(last>0){
                List<T> itemList=new ArrayList<T>();
                for(int i=0;i<last;i++){
                    itemList.add(resList.get(pre*count+i));
                }
                ret.add(itemList);
            }
        }
        return ret;
        
    }
}
