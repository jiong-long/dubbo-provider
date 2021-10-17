package com.jianghu.io.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @description: 群聊客户端
 * @author: OF3848
 * @create: 2021-10-17 23:18
 */
public class GroupChatClient {

    private final String HOST = "127.0.0.1";
    private final Integer PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;

    public static void main(String[] args) throws IOException {
        GroupChatClient chatClient = new GroupChatClient();
        // 每隔3秒读取服务器数据
        new Thread(() -> {
            while (true) {
                chatClient.readInfo();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 发送数据给服务端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            chatClient.sendInfo(msg.trim());
        }
    }

    /**
     * 初始化工作
     */
    public GroupChatClient() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        userName = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(userName + " is ok...");
    }

    /**
     * 向服务器发送消息
     * @param info
     */
    public void sendInfo(String info){
        info = userName + ":" + info;
        try{
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 从服务器读取消息
     */
    public void readInfo(){
        try{
            int count = selector.select();
            if(count > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isReadable()){
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        String msg = new String(byteBuffer.array());
                        System.out.println(msg.trim());
                    }
                    iterator.remove();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
