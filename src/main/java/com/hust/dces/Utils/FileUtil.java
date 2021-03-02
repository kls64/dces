package com.hust.dces.Utils;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

public class FileUtil {

    //文件上传工具类服务方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception{

        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(targetFile.getAbsolutePath() + "/" + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    // 获取文件上传的存储路径
    public static String getUpLoadFilePath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) path = new File("");
        File filePath = new File(path.getAbsolutePath(), "static/files/");
        return filePath.getAbsolutePath();
    }

    // 根据文件路径删除指定文件
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    public static void storeAsTxT(String filePath,List<String> list){
        File outFile = new File(filePath);
        Writer out;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile, true), "utf-8"), 10240);
            for (int i = 0; i < list.size(); i++) {
                out.write(list.get(i));
                out.write("\r\n");
            }
            out.flush();
            out.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static String readTxt(String docPath){
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(docPath));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result = result + "\n" + s;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
