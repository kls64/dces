package com.hust.dces.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaRunPython {
    public static String textGraph(String docPath,String docId) {
        String result = "";
        try {
            // 输入指令执行python脚本
            Process process = Runtime.getRuntime().exec("python C:\\Users\\kls\\Desktop\\TextGrapher-master\\test.py"+" "+docPath+" "+docId);
            // 获取返回值的方式是需要用python打印输出，然后java去获取命令行的输出，再用java返回
//            InputStreamReader ir = new InputStreamReader(process.getInputStream());
//            LineNumberReader input = new LineNumberReader(ir);
//            result = input.readLine();
//            System.out.println(result);
//            input.close();
//            ir.close();
            System.out.println(docId);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "gb2312"));
            String line = null;
            while ((line = in.readLine()) != null) {
                 System.out.println(line);

            }
            in.close();

            int re = process.waitFor();

        } catch (IOException | InterruptedException e) {
            System.out.println("调用python脚本并读取结果时出错: " + e.getMessage());
        }
        return result;
    }
}
