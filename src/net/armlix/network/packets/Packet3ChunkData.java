package net.armlix.network.packets;

import io.netty.buffer.ByteBuf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class Packet3ChunkData extends Packet {

    public short ChunkLength;
    public byte[] ChunkData;
    public byte percent;

    public Packet3ChunkData() {

    }

    public Packet3ChunkData(short chunkLength, byte[] chunkData, byte percent) {
        this.ChunkLength = chunkLength;
        this.ChunkData = chunkData;
        this.percent = percent;
    }

    @Override
    public void writeData(ByteBuf out) throws IOException {
        byte[] compressedData = compressByteArray(this.ChunkData);
        out.writeShort(compressedData.length);
        out.writeBytes(compressedData);
        out.writeByte(percent);
    }

    public static byte[] compressByteArray(byte[] byteArray) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = new GZIPOutputStream(byteOut);
        gzipOut.write(byteArray);
        gzipOut.finish();
        return byteOut.toByteArray();
    }

    @Override
    public byte getPacketID() {
        return 0x03;
    }
}