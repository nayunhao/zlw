package com.zlead.utils;

/** 
 * 分页管理通用函数
 * 
 * @author yangting
 */
public class PageUtil {
	/**
	 * 分页管理临时备用
	 * @param i
	 * @return
	 */
	public static int getCurrentPageSize(int i){
		if(i == 1){
			i = 0;
		}else{
			i = (i - 1) * 10;
		}
		return i;
	}
	
	/**
	 * 分页管理临时备用
	 * @param i
	 * @return
	 */
	public static int getCurrentPageSizeBig(int i,int nums){
		if(i == 1){
			i = 0;
		}else if(nums == 50){
			i = (i - 1) * 50;
		}else if(nums == 100){
			i = (i - 1) * 100;
		}else if(nums == 150){
			i = (i - 1) * 150;
		}else if(nums == 200){
			i = (i - 1) * 200;
		}
		return i;
	}
}
