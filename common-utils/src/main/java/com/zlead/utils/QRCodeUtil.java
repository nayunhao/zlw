package com.zlead.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * @author Administrator
 * 生成二维码
 */
public class QRCodeUtil {
	 //二维码颜色  
    private static final int BLACK = 0xFF000000;  
    //二维码颜色  
    private static final int WHITE = 0xFFFFFFFF;  
    
    /** 
     * <span style="font-size:18px;font-weight:blod;">ZXing 方式生成二维码</span> 
     * @param codeName 下方输出：编码+名称
     * @param text    <a href="javascript:void();">二维码内容</a> 
     * @param width    二维码宽 
     * @param height    二维码高 
     * @param outPutPath    二维码生成保存路径 
     * @param imageType        二维码生成格式 
     */
    public static void zxingCodeCreate(String codeName, String text, int width, int height, String outPutPath, String imageType){  
        Map<EncodeHintType, String> his = new HashMap<EncodeHintType, String>();  
        //设置编码字符集  
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        try {  
            //1、生成二维码  
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, his);  
              
            //2、获取二维码宽高  
            int codeWidth = encode.getWidth();  
            int codeHeight = encode.getHeight();  
            int textHeight = 0;
            if(!codeName.isEmpty())
            	textHeight = 25;
            //3、将二维码放入缓冲流  TYPE_INT_ARGB：生成透明图形
            BufferedImage image = new BufferedImage(codeWidth, codeHeight+textHeight, BufferedImage.TYPE_INT_RGB);  
            Graphics g = image.getGraphics();          
            
            if(!codeName.isEmpty())
            {          
            	int fontSize = 14;
                Font font = new Font("SIMSUN", Font.PLAIN, fontSize);
                g.setFont(font);
                g.setColor(new Color(255, 255, 255));
                g.fillRect(0, 0, codeWidth, codeHeight+textHeight);            	
            }         
            for (int i = 0; i < codeWidth; i++) {  
                for (int j = 0; j < codeHeight; j++) {  
                    //4、循环将二维码内容定入图片  
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);  
                }  
            }  
            if(!codeName.isEmpty())
            {   
            	int strWidth = g.getFontMetrics().stringWidth(codeName);
            	g.setColor(new Color(0, 0, 0));  
            	g.drawString(codeName, (codeWidth - strWidth)/2, codeHeight-20);
            }
            File outPutImage = new File(outPutPath);  
            //如果图片不存在创建图片  
            if(!outPutImage.exists())  
                outPutImage.createNewFile();  
            //5、将二维码写入图片  
            ImageIO.write(image, imageType, outPutImage);  
            g.dispose();
        } catch (WriterException e) {  
            e.printStackTrace();  
            System.out.println("二维码生成失败");  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("生成二维码图片失败");  
        }  
    }  
    /** 
     * <span style="font-size:18px;font-weight:blod;">二维码解析</span> 
     * @param analyzePath    二维码路径 
     * @return 
     * @throws IOException 
     */  
    @SuppressWarnings({ "rawtypes", "unchecked" })  
    public static Object zxingCodeAnalyze(String analyzePath) throws Exception{  
        MultiFormatReader formatReader = new MultiFormatReader();  
        Object result = null;  
        try {  
            File file = new File(analyzePath);  
            if (!file.exists())  
            {  
                return "二维码不存在";  
            }  
            BufferedImage image = ImageIO.read(file);  
            LuminanceSource source = new BufferedImageLuminanceSource(image);  
            Binarizer binarizer = new HybridBinarizer(source);    
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
            Map hints = new HashMap();  
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
            result = formatReader.decode(binaryBitmap, hints);  
        } catch (NotFoundException e) {  
            e.printStackTrace();  
        }    
        return result;  
    } 
}
