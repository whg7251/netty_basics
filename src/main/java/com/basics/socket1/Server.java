package com.basics.socket1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName Server
 * @Description TODO  ServerSocket
 * @Author Guo
 * @Date 2019/11/27 15:22
 * @Version 1.0
 **/
public class Server {

    private final static int PORT = 8888;

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);

            System.out.println("启动服务器  监听端口为" + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户端[" + socket.getPort() + "]已启动");

                //创建IO相关的输入输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out\\socket_out1.txt"),"UTF-8"));

                String message = null;
                if ((message = bufferedReader.readLine()) != null) {
                    System.out.println("client " + socket.getPort() + "send data --> " + message);
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
