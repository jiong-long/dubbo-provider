package com.jianghu.io.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description: 从文件中服务内容
 * @author: OF3848
 * @create: 2021-10-05 22:16
 */
public class FileChannel02 {
    public static void main(String[] args) throws IOException {
        File file = new File("1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        // 从channel读取内容到buffer
        channel.read(byteBuffer);
        byteBuffer.flip();
        System.out.println(new String(byteBuffer.array()));
    }
}
