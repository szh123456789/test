package com.test.tools.heartbreak;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class heart extends SimpleChannelInboundHandler<String> {

    private int unRecPingTimes = 0;
    private String userid;

    private static final int MAX_UN_REC_PING_TIMES = 2;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("----->msg=" + msg);
//        String[] args = msg.split(",");
//        String msg_operation = args[0];
//        String msg_userid = args[1];
//        if ("LOGIN".equals(msg_operation)){
//            if (msg_userid != null && !msg.equals("")){
//                userid = msg_userid;
//            }
//            setUserOnlineStatus(userid,true);
//        }else if ("HEARTBEAT".equals(msg_operation)){
           ctx.channel().writeAndFlush("success");

            unRecPingTimes = 0;
//        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE){
                System.out.println("===服务端==读超时");
                if (unRecPingTimes == MAX_UN_REC_PING_TIMES){
                    System.out.println("===服务端===读超时关闭channel");

                    ctx.channel().close();
                }else{
                    unRecPingTimes++;
                }
            }else if (event.state() == IdleState.WRITER_IDLE){
                System.out.println("===服务端===写超时");
            }else if (event.state() == IdleState.ALL_IDLE){
                System.out.println("===服务端===总超时");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("错误原因: "+cause.getMessage());
        ctx.channel().close();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
        System.out.println("===服务端===(客户端失效)");

    }

    private void setUserOnlineStatus(String userid, boolean isOnline){
        if (userid != null && !userid.equals("")){

        }
    }
}
