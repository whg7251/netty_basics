package com.basics.socket1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName Server
 * @Description TODO  ServerSocket
 * @Author Guo
 * @Date 2019/11/27 15:22
 * @Version 1.0
 **/
public class Server {

    private final static int PORT = 8888;

    private final static Logger logger = LogManager.getLogger(Server.class);
    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);

            logger.info("启动服务器  监听端口为" + PORT);
            //TODO 循环等待，客户端连接
            for (;;){
                Socket socket = serverSocket.accept();
                logger.info("客户端[" + socket.getPort() + "]已启动");
                //创建IO相关的输入输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out\\socket_out1.txt"), StandardCharsets.UTF_8));

                String message;
                if ((message = bufferedReader.readLine()) != null) {
                    System.out.println("client " + socket.getPort() + "  send data --> " + message);
                    bufferedWriter.write("服务端发来的消息---> " + message);
                    bufferedWriter.flush();
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
