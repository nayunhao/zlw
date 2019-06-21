package com.zlead.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	
	public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            "bmp", "gif", "jpg", "jpeg", "png"
    };
	
	public static final String getFileName(String filename){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		Date date=new Date();
		String dateStr=sdf.format(date);
		String suffix=filename.substring(filename.lastIndexOf("."),filename.length());
		String randomStr=getRandomNumber();//随机生成六位数
		String refilename=dateStr.concat(randomStr).concat(suffix);
		return refilename;
	}

	
	private static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
	    for(String str : allowedExtension) {
	        if(str.equalsIgnoreCase(extension)) {
	           return true;
	         }
	       }
	       return false;
	 }	
	 
	public static final boolean isErrorSuffixFile(String filename){
		String extension = FilenameUtils.getExtension(filename);
		if(isAllowedExtension(extension,DEFAULT_ALLOWED_EXTENSION)){
			return false;
		}else{
			return true;
		}		
	}

	private static String getRandomNumber(){
		String str = "";
		str += (int)(Math.random()*9+1);
		for(int i = 0; i < 5; i++){
			str += (int)(Math.random()*10);
		}
		return str;
	}

	public static String getPictureName() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		Date date=new Date();
		String dateStr=sdf.format(date);
		String randomStr=getRandomNumber();//随机生成六位数
		return dateStr + randomStr + ".jpg";
	}


	public static final byte[] getThumPicture(MultipartFile imageFile) throws Exception{
		Thumbnails.Builder builder  = Thumbnails.of(imageFile.getInputStream()).outputFormat("jpg");
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //字节输出流（写入到内存）
		builder.size(400,500);
		builder.toOutputStream(baos);
		return  baos.toByteArray();
	}
}
