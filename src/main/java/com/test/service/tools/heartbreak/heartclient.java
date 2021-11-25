package com.test.service.tools.heartbreak;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class heartclient {

    private final String host;
    private final int port;

    public heartclient(String host, int port){
        this.host = host;
        this.port = port;
    }

    EventLoopGroup group = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap();
    Channel channel;
    public void bs() throws Exception{
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                        pipeline.addLast("decoder", new StringDecoder());
                        pipeline.addLast("encoder", new StringEncoder());
                    }
                });
        channel = bootstrap.connect(host,port).sync().channel();

    }


    public String run() {

        try{

            if (channel.isActive()){
                channel.writeAndFlush("456");
                return "s1";
            }else{
                System.out.println(111111);
                this.sto();
                return "s2";
            }

        }catch(Exception e){
            e.printStackTrace();
            return "s3";
        }
    }

    public void sto(){
        group.shutdownGracefully();
    }


}
