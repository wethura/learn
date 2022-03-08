package net.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * @author wethura
 * @date 7/16/21 12:31 AM
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private static Logger log = LoggerFactory.getLogger(NettyClientHandler.class);

    public NettyClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //        super.channelActive(ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("Client's message", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //        super.channelRead(ctx, msg);
        ByteBuf buf = (ByteBuf) msg;
        log.info("Message Received From Server: \nMessage: \n{}", buf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //        super.exceptionCaught(ctx, cause);
        log.info("Server Was Error, Exiting!");
    }
}