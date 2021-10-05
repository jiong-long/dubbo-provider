package com.jianghu.io.nio.channel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description: 字符串写入到文件中
 * @author: OF3848
 * @create: 2021-10-05 22:16
 */
public class FileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello world";
        FileOutputStream fileOutputStream = new FileOutputStream("1.txt");
        /**
         * 通过 fileOutputStream 获取对应的 FileChannel
         * 这个 channel 真实类型是 FileChannelImpl
         */
        FileChannel channel = fileOutputStream.getChannel();
        // 创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 将 str 放入 byteBuffer
        byteBuffer.put(str.getBytes());
        // 对 byteBuffer 进行 flip
        byteBuffer.flip();
        // 将 byteBuffer 数据写入到 fileChannel
        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
