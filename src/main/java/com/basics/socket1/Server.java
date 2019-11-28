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
                //此时是阻塞式的，只能连接一个客户端
                Socket socket = serverSocket.accept();
                logger.info("客户端[" + socket.getPort() + "]已启动");
                //创建IO相关的输入输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

                String message;
                while((message = bufferedReader.readLine()) != null) {
                    logger.info("client " + socket.getPort() + "  send data --> " + message);
                    if (message.equalsIgnoreCase(Constants.QUIT)){
                        logger.info("客户端[" + socket.getPort() + "]已注销");
                    }
                    bufferedWriter.write(message+"\n");
                    bufferedWriter.flush();
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    logger.error("server stopped...");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
