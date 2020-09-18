package com.niewj.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by niewj on 2020/9/17 16:25
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        // 1. 处理请求的线程池
        ExecutorService requestPool = Executors.newFixedThreadPool(20);

        // 2. 启动ServerSocket, 监听在7777端口
        ServerSocket serverSocket = new ServerSocket(7777);
        while (true) {
            System.out.println("##@@==> 等待连接...");
            Socket socket = serverSocket.accept();
            System.out.println("##@@==> 收到客户端: [" + Thread.currentThread().getName() + "]");
            // 3. 来一个客户端, 启动一个线程处理
            requestPool.execute(() -> handle(socket));
        }
    }

    /**
     * 处理客户端的线程执行的功能
     * @param socket
     */
    private static void handle(Socket socket) {
        String threadName = Thread.currentThread().getName();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println("[" + threadName + "]==>\t" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("[" + threadName + " 处理的socket关闭]");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}