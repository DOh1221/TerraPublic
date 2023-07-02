package net.armlix.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.armlix.Core;
import net.armlix.network.packets.Packet;

public class PacketDataEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        Packet.writePacket(packet, out);
        Core.logger.info(packet.getClass().getSimpleName());
    }
}
