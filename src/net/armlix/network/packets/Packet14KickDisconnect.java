package net.armlix.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.armlix.Core;

import java.io.IOException;

public class Packet14KickDisconnect extends Packet {

    public String message = "";

    public Packet14KickDisconnect() {

    }

    public Packet14KickDisconnect(String message) {
        this.message = message;
    }

    @Override
    public void writeData(ByteBuf out) throws IOException {
        writeString(out, message);
    }

    @Override
    public void handle(ChannelHandlerContext ctx) {
        Core.netHandler.handle(this, ctx);
    }

    @Override
    public byte getPacketID() {
        return 0x0E;
    }
}