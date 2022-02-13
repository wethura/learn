package net.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wethura
 * @date 7/16/21 12:26 AM
 */
public class NettyClient {
    private static Logger log = LoggerFactory.getLogger(NettyClient.class);

    public void execute() {
        EventLoopGroup executors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(executors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            log.info("Netty Client prepared!");
            ChannelFuture future = bootstrap.connect("", 8888).sync();
            CompletableFuture.runAsync(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException ignored) { }

                    future.channel().pipeline().writeAndFlush(Unpooled.copiedBuffer("Heart Beating...", Charset.defaultCharset()));
                }

            });
            future.channel().closeFuture().sync();
        } catch (Exception ignored) {
        } finally {
            executors.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyClient().execute();
    }
}
