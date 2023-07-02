package net.armlix.network.packets;

import io.netty.buffer.ByteBuf;

public class Packet15ChangePermissions extends Packet {
    @Override
    public void writeData(ByteBuf out) {

    }

    @Override
    public byte getPacketID() {
        return 0x0F;
    }
}