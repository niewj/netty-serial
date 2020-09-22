package com.niewj.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer
 * 内存映射文件中的部分, 然后直接修改映射部分;
 *
 * MappedByteBuffer的子类还有个DirectByteBuffer和DirectByteBufferR
 * Created by niewj on 2020/9/21 11:09
 */
public class NioMappedByteBuffer {

    public static void main(String[] args) {
        String fileName = "E:\\map_modify.txt";
        // 1. 声明访问流以及访问模式
        try(RandomAccessFile raf = new RandomAccessFile(fileName, "rw")){
            /*
             * map_modify.txt原来内容:
             * 01234567890
             * 不说了, 都是泪水~
             * 精彩的!
             */
            // 2. 从IO流获取访问文件
            FileChannel channel = raf.getChannel();
            // 3. 把文件中的偏移量5(索引从0开始然后索引=5的地方), 5个字节长度, 映射到内存
            MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 5, 5);
            mappedByteBuffer.put(0, (byte)'N');
            mappedByteBuffer.put(1, (byte)'i');
            mappedByteBuffer.put(2, (byte)'e');
            mappedByteBuffer.put(3, (byte)'w');
            mappedByteBuffer.put(4, (byte)'j');

            // 4. 超过 size的长度, 就会异常:
            // java.lang.IndexOutOfBoundsException
            //mappedByteBuffer.put(5, (byte)'E');

            /**
             * 修改后:
             * 01234Niewj0
             * 不说了, 都是泪水~
             * 精彩的!
             */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("修改完成!");
    }
}
