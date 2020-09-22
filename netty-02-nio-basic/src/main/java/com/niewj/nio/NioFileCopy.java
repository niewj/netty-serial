package com.niewj.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by niewj on 2020/9/21 10:23
 */
public class NioFileCopy {
    public static void main(String[] args) {
        String srcFile = "E:\\piantouqu.mp3";
        String destFile = "E:\\piantouqu_copy.mp3";

        try (FileInputStream inputStream = new FileInputStream(srcFile);
             FileOutputStream outputStream = new FileOutputStream(destFile);
             FileChannel inChannel = inputStream.getChannel();
             FileChannel outChannel = outputStream.getChannel()
        ) {
            // copy 文件
            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("copy 完成!");
    }
}
