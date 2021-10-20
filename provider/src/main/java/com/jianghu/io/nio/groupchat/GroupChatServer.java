package com.jianghu.io.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @description: 群聊服务器
 * @author: OF3848
 * @create: 2021-10-17 22:48
 */
public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final int PORT = 6667;

    public static void main(String[] args) {
        GroupChatServer chatServer = new GroupChatServer();
        chatServer.listen();
    }

    /**
     * 构造器
     * 初始化工作
     */
    public GroupChatServer() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            // 绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            // 设置非阻塞
            listenChannel.configureBlocking(false);
            // 注册到selector，监听连接事件
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 监听
     */
    public void listen(){
        try{
            while (true) {
                int count = selector.select();
                if(count > 0){
                    // 遍历得到的selectionKey集合
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        // 监听到accept
                        if(key.isAcceptable()){
                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            // 将链接注册到selector
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + " is online");
                        }

                        // 监听到read事件
                        if(key.isReadable()){
                            readData(key);
                        }

                        iterator.remove();
                    }
                } else {
                    System.out.println("waiting...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 读取客户端消息
     * @param selectionKey
     */
    private void readData(SelectionKey selectionKey){
        // 定义一个SocketChannel
        SocketChannel channel = null;
        try{
            channel = (SocketChannel) selectionKey.channel();
            // 创建buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = channel.read(byteBuffer);
            // 读取到数据
            if(count > 0){
                String msg = new String(byteBuffer.array());
                System.out.println("from client msg:" + msg.trim());

                // 向其它客户端转发消息
                sendInfoToOtherClient(msg, channel);
            }
        } catch (Exception e) {
            try {
                System.out.println(channel.getRemoteAddress() + " offline");
                selectionKey.cancel();
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 转发消息给其他客户端
     * @param msg
     * @param selfChannel
     */
    private void sendInfoToOtherClient(String msg, SocketChannel selfChannel) throws IOException {
        System.out.println("server resend message");
        for (SelectionKey selectionKey : selector.keys()) {
            Channel channel = selectionKey.channel();
            if(channel instanceof SocketChannel && channel != selfChannel){
                SocketChannel socketChannel = (SocketChannel) channel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                socketChannel.write(byteBuffer);
            }
        }
    }
}
