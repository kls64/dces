package com.hust.dces;

import java.io.File;

public class Test {

public static void main(String[] args) {

        File dir = new File("C:\\Users\\kls\\Desktop\\textprocessing");

String[] names =dir.list();

for(String name : names){
System.out.println(name);
}
    }

}
