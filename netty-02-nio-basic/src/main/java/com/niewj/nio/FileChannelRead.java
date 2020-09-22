package com.niewj.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by niewj on 2020/9/18 22:33
 */
public class FileChannelRead {

    public static void main(String[] args) {
        // 1. 对channel的操作要通过 Buffer 来操作;
        ByteBuffer byteBuffer= ByteBuffer.allocate(1024);

        // 2. 文件输出流
        try(FileInputStream fileInputStream = new FileInputStream(NioConstant.FILE)){
            // 3. 拿到文件输入流的 FileChannel;
            FileChannel fileChannel = fileInputStream.getChannel();

            // 4. 将数据字节数组化, 放入Buffer中
            int read = fileChannel.read(byteBuffer);

            if(read != -1){
                // 5. 调用 Buffer的array方法, 把写入的Buffer数据转为 byte[] 返回
                System.out.println(new String(byteBuffer.array()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("读文件完毕!");
    }
}
