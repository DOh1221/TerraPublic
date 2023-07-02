package net.armlix.network.packets;

import io.netty.buffer.ByteBuf;

public class Packet4ChunkEnd extends Packet {

    public short XSize;
    public short YSize;
    public short ZSize;

    public Packet4ChunkEnd() {

    }

    public Packet4ChunkEnd(short XSize, short YSize, short ZSize) {
        this.XSize = XSize;
        this.YSize = YSize;
        this.ZSize = ZSize;
    }

    @Override
    public void writeData(ByteBuf out) {
        out.writeShort(XSize);
        out.writeShort(YSize);
        out.writeShort(ZSize);
    }

    @Override
    public byte getPacketID() {
        return 0x04;
    }
}