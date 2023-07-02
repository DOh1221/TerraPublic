package net.armlix.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.armlix.Core;
import net.armlix.network.packets.Packet;

import java.util.List;

public class PacketDataDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (!in.isReadable()) {
            return;
        }
        int packetId = in.readByte();
        Packet packet = Packet.getPacketByID(packetId);
        packet.readData(in);
        out.add(packet);
        Core.logger.info(packet.getClass().getSimpleName());
    }
}
