package com.basics.io.chapter1;

import java.io.*;

/**
 * @ClassName BufferedApp
 * @Description TODO  字节字符转换App
 * @Author Guo
 * @Date 2019/11/27 9:32
 * @Version 1.0
 **/
public class BufferedApp {
    public static void main(String[] args) {
//        read();
//        write();
       
    }

    /**
    * @Description: TODO 输出流
    * @Param:
    * @return: void
    * @Author: Guo
    * @Date: 9:57
    */
    private static void write() {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out\\data-out1.txt")));
            bufferedWriter.write("buffer输出流");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    /** 
    * @Description: TODO 输入字节流转换输入字符流  并打印    InputStreamReader->字节转字符类   BufferedReader  ->buffer形式的字符输入流，继承自Reader
    * @Param:  
    * @return: void 
    * @Author: Guo
    * @Date: 9:42 
    */ 
    private static void read() {


        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("data\\wc.txt")));

            String message = null;
            while ((message=bufferedReader.readLine() )!=null){

                System.out.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
