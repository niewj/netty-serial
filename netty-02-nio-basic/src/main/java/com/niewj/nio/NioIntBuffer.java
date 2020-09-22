package com.niewj.nio;

import java.nio.IntBuffer;
import java.util.Random;

/**
 * Created by niewj on 2020/9/18 9:48
 */
public class NioIntBuffer {
    public static void main(String[] args) {
        // 1. 声明 capacity=缓冲区容量
        int capacity = 20;
        // 2. IntBuffer是指缓冲区存放都是int类型的, 缓冲区是一个int数组;
        /*
         * Buffer的子类: 基本类型的除了 boolean, 都有
         *  ByteBuffer/ ShortBuffer/IntBuffer/LongBuffer
         *  FloatBuffer/DoubleBuffer/CharBuffer
         */
        IntBuffer buffer = IntBuffer.allocate(capacity);
        // 3. 写数据-写模式
        for (int i = 0; i < capacity; i++) {
            buffer.put(new Random().nextInt(3000));
        }
        // 4. 写完数据, 要进入读模式, 需要调用 flip()
        buffer.flip();
        System.out.println("buffer中现有元素上限: "+ buffer.limit());

        // 5. 读取数据: 读模式
        int count = 0;
        while (buffer.hasRemaining()) {
            int bufferItem = buffer.get();
            System.out.println("buffer[" + count + "] = " + bufferItem);
            count++;
        }

        // 6. 如果后面还要写, 可以调用 clear() 进入写模式继续写
        //buffer.clear(); // 进入写模式
    }
}