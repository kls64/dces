package com.hust.dces.Utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class JavaRunPython {
    public static String textGraph() {
        String result = "";
        try {
            // 输入指令执行python脚本
            Process process = Runtime.getRuntime().exec("python C:\\Users\\kunkun\\Desktop\\TextGrapher-master\\test.py");
            // 获取返回值的方式是需要用python打印输出，然后java去获取命令行的输出，再用java返回
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            result = input.readLine();
            input.close();
            ir.close();
            int re = process.waitFor();
            System.out.println(result);
        } catch (IOException | InterruptedException e) {
            System.out.println("调用python脚本并读取结果时出错: " + e.getMessage());
        }
        return result;
    }
}
