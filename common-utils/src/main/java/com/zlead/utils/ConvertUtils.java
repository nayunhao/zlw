package com.zlead.utils;

public class ConvertUtils {
	/***
	 * 根据地值截取拼接 省-市-区
	 * @param addr
	 * @return
	 */
	public static String convertProvinceCityCounty(String addr) throws Exception{
		//1、北京、上海、天津、重庆 直辖市
		if(null!=addr&&!"".equals(addr)){
			String province=null;
			String city=null;
			String county=null;
			if(addr.indexOf("北京")!=-1||addr.indexOf("上海")!=-1||addr.indexOf("天津")!=-1||addr.indexOf("重庆")!=-1){
				province=addr.substring(0,addr.indexOf("市"));
				if(addr.indexOf("区")!=-1&&addr.indexOf("县")==-1){
					county=addr.substring(addr.indexOf("市")+1,addr.indexOf("区"));
					if(null!=province&&null!=county){
						return province+"市-"+province+"市-"+county+"区";
					}
				}else if(addr.indexOf("县")!=-1&&addr.indexOf("区")==-1){
					county=addr.substring(addr.indexOf("市")+1,addr.indexOf("县"));
					if(null!=province&&null!=county){
						return province+"市-"+province+"市-"+county+"县";
					}
				} 
			}
			
			//2、常规的 
			if((addr.indexOf("省")!=-1&&addr.indexOf("市")!=-1&&addr.indexOf("区")!=-1)||(addr.indexOf("省")!=-1&&addr.indexOf("市")!=-1&&addr.indexOf("县")!=-1)){
				province=addr.substring(0,addr.indexOf("省"));
				if(addr.indexOf("市")!=-1){
					city=addr.substring(addr.indexOf("省"),addr.indexOf("市"));
					if(addr.indexOf("区")!=-1&&addr.indexOf("县")==-1){
						county=addr.substring(addr.indexOf("省"),addr.indexOf("区"));
						if(null!=province&&null!=county){
							return province+"省-"+city+"市-"+county+"区";
						}
					}else if(addr.indexOf("县")!=-1&&addr.indexOf("区")==-1){
						county=addr.substring(addr.indexOf("省"),addr.indexOf("县"));
						if(null!=province&&null!=county){
							return province+"省-"+city+"市-"+county+"县";
						}
					} 
				}
			}
			
			//3、特别的(内蒙古自治区、广西壮族自治区、新疆维吾尔自治区、宁夏回族自治区、西藏自治区、澳门特别行政区、香港特别行政区） 暂时先不做
			if(addr.indexOf("内蒙古自治")!=-1||addr.indexOf("广西壮族自治")!=-1||addr.indexOf("新疆维吾尔自治")!=-1||addr.indexOf("宁夏回族自治")!=-1||addr.indexOf("西藏自治")!=-1||addr.indexOf("澳门特别行政")!=-1||addr.indexOf("香港特别行政")!=-1){
				return "特殊自治区/行政区";
			}
		}
		return null;	
	}
	
	
	/***
	 * 根据省市区获取in() 查询所需要的条件
	 * @param addr
	 * @return
	 */
	public static String[] convertProvinceCityCountys(String provinceCityCounty) throws Exception{
		String [] str=provinceCityCounty.split("-");
		String [] bt= new String [3];
		bt[0]=str[0];
		bt[1]=str[0]+"-"+str[1];
		bt[2]=str[0]+"-"+str[1]+"-"+str[2];
		return bt;	
	}		
}
