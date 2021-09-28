package com.jianghu.io.nio.buffer;

import java.nio.IntBuffer;

/**
 * @description: Buffer的使用
 * @author: OF3848
 * @create: 2021-09-28 23:21
 */
public class BasicBuffer {
    public static void main(String[] args) {
        //创建一个 Buffer，大小为 5，即可以存放 5 个 int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向buffer存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        //将 buffer 转换，读写切换(!!!)
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
