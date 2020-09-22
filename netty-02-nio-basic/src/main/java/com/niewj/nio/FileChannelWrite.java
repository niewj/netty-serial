package com.niewj.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by niewj on 2020/9/18 13:33
 */
public class FileChannelWrite {

    public static void main(String[] args) {
        // 1. 将content的字符串数据写入到 文件 file-channel-write-file.txt 中
        String content = "BIO以流的方式处理数据; 而NIO以块的方式处理数据, 块IO比流高效";

        // 2. 对channel的操作要通过 Buffer 来操作;
        ByteBuffer byteBuffer= ByteBuffer.allocate(1024);

        // 3. 将数据字节数组化, 放入Buffer中
        byteBuffer.put(content.getBytes());

        // 4. 将 Buffer 缓冲区的模式从 写模式转为读模式:
        byteBuffer.flip();

        // 5. 文件输出流
        try(FileOutputStream fileOutputStream = new FileOutputStream(NioConstant.FILE)){
            // 6. 拿到文件输出流的 FileChannel;
            FileChannel fileChannel = fileOutputStream.getChannel();

            fileChannel.write(byteBuffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("写文件完毕!");
    }
}
