package com.basics.socket1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName Client
 * @Description  简单的IM客户端————socket实现
 * @Author Guo
 * @Date 2019/11/27 19:12
 * @Version 1.0
 **/
public class Client {

    private static Logger logger = LogManager.getLogger(Client.class);
    private final static  String IP = "localhost";
    private final static int PORT = 8888;

    public static void main(String[] args) {

        Socket socket = null;

        try {
            socket=new Socket(IP,PORT);
            logger.info("client 连接成功"+socket.getPort());
            //创建服务器客户端IO相关的输入输出
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            //获取console输入内容的流
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            String requestMessage ;
            while ((requestMessage=consoleReader.readLine())!=null){
                writer.write(requestMessage+"\n");
                writer.flush();
            }

            String responseMessage ;
            while ((responseMessage=reader.readLine())!=null){
                logger.info("服务器发来的消息------>  " +responseMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if (socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
