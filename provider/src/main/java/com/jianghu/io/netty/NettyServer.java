package com.jianghu.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * @description: netty服务器
 * @author: OF3848
 * @create: 2021-10-20 23:04
 */
public class NettyServer {
    public static void main(String[] args) {
        // bossGroup 只是处理连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 真正的和客户端业务处理，会交给 workerGroup完成
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 设置两个线程组
            bootstrap.group(bossGroup, workerGroup)
                    // 使用NioSocketChannel 作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    // 设置线程队列得到连接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 给 pipeline 设置处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 给 pipeline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            System.out.println("server is ready...");
            // 绑定一个端口并且同步, 生成了一个 ChannelFuture 对象
            ChannelFuture channelFuture = bootstrap.bind(6668).sync();

            // 给 channelFuture 注册监听器，监控我们关心的事件
            channelFuture.addListener(future -> {
                if(channelFuture.isSuccess()){
                    System.out.println("listener port 6668 success");
                } else {
                    System.out.println("listener port 6668 fail");
                }
            });

            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

/**
 * 自定义一个handler
 */
class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取实际数据
     * @param ctx 上下文对象, 含有 管道pipeline , 通道channel, 地址
     * @param msg 就是客户端发送的数据 默认Object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server thread :" + Thread.currentThread().getName() + " channel:" + ctx.channel());
        System.out.println("server ctx = " + ctx);
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();

        ByteBuf buf = (ByteBuf) msg;
        System.out.println("client send message: " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("client address: "+ channel.remoteAddress());
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello netty client", CharsetUtil.UTF_8));
    }

    /**
     * 异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
