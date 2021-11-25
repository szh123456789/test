package com.test.service.tools.heartbreak;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;


public class heartbreakserver {

    private int port;

    public heartbreakserver(int port){ this.port = port;}

    ChannelFuture channelFuture;

    ServerBootstrap serverBootstrap;

    private static final int READ_WAIT_SECONDS = 10;

    public void startServer(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
             serverBootstrap = new ServerBootstrap();
             serverBootstrap.group(bossGroup,workerGroup)
               .channel(NioServerSocketChannel.class)
               .childHandler(new HeartBeatServerInitializer());

               channelFuture = serverBootstrap.bind(port).sync();

               channelFuture.channel().closeFuture().sync();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            stopServer();
        }
    }

    private class HeartBeatServerInitializer extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            ChannelPipeline pipeline = socketChannel.pipeline();

            pipeline.addLast("decoder", new StringDecoder())
                    .addLast("encoder", new StringEncoder())
                    .addLast("pong", new IdleStateHandler(READ_WAIT_SECONDS,0,0, TimeUnit.SECONDS))
                    .addLast("handler" , new heart());
        }
    }

    public void stopServer(){
        if(channelFuture != null){
            System.out.println("终止");
            channelFuture.channel().close();
        }
    }
}
