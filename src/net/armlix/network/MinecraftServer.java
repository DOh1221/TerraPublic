package net.armlix.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import net.armlix.Core;

public class MinecraftServer {
    private int port;

    public MinecraftServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new PacketDataEncoder())
                                    .addLast(new PacketDataDecoder())
                                    .addLast(new PacketDataHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Запускаем сервер
            Channel ch = b.bind(this.port).sync().channel();
            Core.logger.info("Starting Minecraft server on " + this.port);

            // Ожидаем закрытия сервера
            ch.closeFuture().sync();
        } finally {
            // Освобождаем ресурсы
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
