package net.armlix.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.armlix.Core;
import net.armlix.network.packets.Packet;
import net.armlix.network.packets.Packet14KickDisconnect;

public class PacketDataHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        packet.handle(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.write(new Packet14KickDisconnect("Unexpected error while processing request"));
        ctx.close();
    }
}