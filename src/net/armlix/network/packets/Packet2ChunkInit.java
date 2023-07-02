package net.armlix.network.packets;

import io.netty.buffer.ByteBuf;

public class Packet2ChunkInit extends Packet {
    @Override
    public void writeData(ByteBuf out) {

    }

    @Override
    public byte getPacketID() {
        return 0x02;
    }
}
