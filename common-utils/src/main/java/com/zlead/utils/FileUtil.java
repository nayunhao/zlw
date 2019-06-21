package com.zlead.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 文件操作类
 * @author yangting
 *
 */
public class FileUtil {
	
	/**
     * 目录是否存在
     * @param dir_path
     */
    public static boolean existsDir(String dir_path) {
    	boolean bRet = false;
        File myFolderPath = new File(dir_path);   
        try {   
            if (myFolderPath.exists()) {   
               bRet = true;
            }   
        } catch (Exception e) {   
        	System.out.println("新建目录" +dir_path + "失败！");
            e.printStackTrace(); 
            bRet = false;
        }  
        return bRet;
    }
    
	/**
     * 创建目录
     * @param dir_path
     */
    public static boolean mkDir(String dir_path) {
    	boolean bRet = false;
        File myFolderPath = new File(dir_path);   
        try {   
            if (!myFolderPath.exists()) {   
               bRet = myFolderPath.mkdirs();   
            }   
        } catch (Exception e) {   
        	System.out.println("新建目录" +dir_path + "失败！");
            e.printStackTrace(); 
            bRet = false;
        }  
        return bRet;
    }
    
    /**
     * 创建文件
     * @param file_path
     */
    public static void createNewFile(String file_path) {  
        File myFilePath = new File(file_path);   
        try {   
            if (!myFilePath.exists()) {   
                myFilePath.createNewFile();   
            } 
        }   
        catch (Exception e) {   
        	System.out.println("新建文件" +file_path + "失败！");
            e.printStackTrace();   
        }  
    }
    
    /**
     * 递归删除文件或者目录
     * @param file_path
     */
    public static void deleteEveryThing(String file_path) {
	    try{
	        File file=new File(file_path);
	        if(!file.exists()){
	            return ;
	        }
	        if(file.isFile()){
	            file.delete();
	        }else{
	            File[] files = file.listFiles();
	            for(int i=0;i<files.length;i++){
	                String root=files[i].getAbsolutePath();//得到子文件或文件夹的绝对路径
	                deleteEveryThing(root);
	            }
	            file.delete();
	        }
	    } catch(Exception e) {
	    	System.out.println("删除文件" +file_path + "失败！");
	    }
    }
    
    /*
     * 得到一个文件夹下所有文件
     */
    public static List<String> getAllFileNameInFold(String fold_path) {
        List<String> file_paths = new ArrayList<String>();
        
        LinkedList<String> folderList = new LinkedList<String>();   
        folderList.add(fold_path);   
        while (folderList.size() > 0) {   
            File file = new File(folderList.peekLast());   
            folderList.removeLast();   
            File[] files = file.listFiles();   
            ArrayList<File> fileList = new ArrayList<File>();   
            for (int i = 0; i < files.length; i++) {   
                if (files[i].isDirectory()) {   
                    folderList.add(files[i].getPath());   
                } else {   
                    fileList.add(files[i]);   
                }   
            }   
            for (File f : fileList) {   
                file_paths.add(f.getAbsoluteFile().getPath());   
            }   
        }   
        return file_paths;
    }
    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName
     *            要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }
}
