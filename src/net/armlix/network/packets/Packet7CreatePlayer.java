package net.armlix.network.packets;

import io.netty.buffer.ByteBuf;

public class Packet7CreatePlayer extends Packet {
    @Override
    public void writeData(ByteBuf out) {

    }

    @Override
    public byte getPacketID() {
        return 0x07;
    }
}