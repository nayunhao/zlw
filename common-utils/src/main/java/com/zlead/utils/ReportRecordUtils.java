package com.zlead.utils;

import java.util.HashMap;
import java.util.Map;

public class ReportRecordUtils {
	 /**
     * 获取举报表名
     * @param province
     * @return
     * @throws Exception
     */
	public static String getTableName(String province)throws Exception{
		String bkTableName="";
		Map<String,String> tableMap=new HashMap<String,String>();
	//"北京市-BJ", "天津市-TJ", "上海市-SH", "重庆市-CQ", "河北省-HB",
		tableMap.put("北京市", "BJ_report_record");
		tableMap.put("天津市", "TJ_report_record");
		tableMap.put("上海市", "SH_report_record");
		tableMap.put("重庆市", "CQ_report_record");
		tableMap.put("河北省", "HB_report_record");
	//"山西省-SX", "内蒙古-NMG", "辽宁省-LN", "吉林省-JL", "黑龙江省-HLJ",
		tableMap.put("山西省", "SX_report_record");
		tableMap.put("内蒙古自治区", "NMG_report_record");
		tableMap.put("辽宁省", "LN_report_record");
		tableMap.put("吉林省", "JL_report_record");
		tableMap.put("黑龙江省", "HLJ_report_record");
		
	//"江苏省-JS", "浙江省-ZJ", "安徽省-AH", "福建省-FJ", "江西省-JX",	
		tableMap.put("江苏省", "JS_report_record");
		tableMap.put("浙江省", "ZJ_report_record");
		tableMap.put("安徽省", "AH_report_record");
		tableMap.put("福建省", "FJ_report_record");
		tableMap.put("江西省", "JX_report_record");
		
	//"山东省-SD", "河南省-HN", "湖北省-HBS", "湖南省-HNS", "广东省-GD",
		tableMap.put("山东省", "SD_report_record");
		tableMap.put("河南省", "HN_report_record");
		tableMap.put("湖北省", "HBS_report_record");
		tableMap.put("湖南省", "HNS_report_record");
		tableMap.put("广东省", "GD_report_record");
		
	//"广西-GX", "海南省-HNA", "四川省-SC", "贵州省-GZ", "云南省-YN", 
		tableMap.put("广西壮族自治区", "GX_report_record");
		tableMap.put("海南省", "HNA_report_record");
		tableMap.put("四川省", "SC_report_record");
		tableMap.put("贵州省", "GZ_report_record");
		tableMap.put("云南省", "YN_report_record");
    
    //"西藏-XZ", "陕西省-SXS", "甘肃省-GS", "青海省-QH", "宁夏-NX", 
		tableMap.put("西藏自治区", "XZ_report_record");
		tableMap.put("陕西省", "SXS_report_record");
		tableMap.put("甘肃省", "GS_report_record");
		tableMap.put("青海省", "QH_report_record");
		tableMap.put("宁夏回族自治区", "NX_report_record");
		
	//"新疆-XJ", "香港-XG", "澳门-AM", "台湾省-TW"};
		tableMap.put("新 疆维吾尔自治区", "XJ_report_record");
		tableMap.put("香港特别行政区", "XG_report_record");
		tableMap.put("澳门特别行政区", "AM_report_record");
		tableMap.put("台湾省", "TW_report_record");
		if(tableMap.containsKey(province)){
			bkTableName=tableMap.get(province);
		}	
		return bkTableName;
	}
	
	
	 /**
     * 获取举报公司表明或者举报产品表名
     * @param province
     * @return
     * @throws Exception
     */
	public static String getCompanyTableName(String reportRecordTableName,String type)throws Exception{
		String prefix=reportRecordTableName.substring(0, reportRecordTableName.indexOf("_"));
		String bkTableName=null;
		if("company".equalsIgnoreCase(type)){
			bkTableName=prefix+"_"+"company_record";
		}else if("product".equalsIgnoreCase(type)){
			bkTableName=prefix+"_"+"products_record";
		}
		return bkTableName;
	}
}
