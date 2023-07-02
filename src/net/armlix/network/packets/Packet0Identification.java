package net.armlix.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.armlix.Core;

import java.io.IOException;

public class Packet0Identification extends Packet {

    public byte version;
    public String username;
    public String key;

    public Packet0Identification() {

    }

    public Packet0Identification(byte protocol_version, String username, String key) {
        this.version = protocol_version;
        this.username = username;
        this.key = key;
    }

    @Override
    public void readData(ByteBuf in) throws IOException {
        this.version = in.readByte();
        this.username = readString(in);
        this.key = readString(in);
        Core.logger.info(String.valueOf(this.version));
        Core.logger.info(this.username);
        Core.logger.info(this.key);
    }

    @Override
    public void writeData(ByteBuf out) throws IOException {
        out.writeByte(0x07);
        writeString(out, "Minecraft Server");
        writeString(out, "Message of the day");
        out.writeByte(0x00);
    }

    @Override
    public void handle(ChannelHandlerContext ctx) {
        Core.netHandler.handle(this, ctx);
    }

    @Override
    public byte getPacketID() {
        return 0x00;
    }
}
