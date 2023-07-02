package net.armlix.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Packet {

    private static ArrayList<Integer> ClientSidePackets = new ArrayList<Integer>();
    private static ArrayList<Integer> ServerSidePackets = new ArrayList<Integer>();
    private static HashMap<Object, Class> PacketClassToID = new HashMap<>();

    public static void registerPacket(int id, boolean clientSide, boolean serverSide, Class clazz) throws Exception {
        if(!(PacketClassToID.containsKey(id) && PacketClassToID.get(id) != null)) {
            PacketClassToID.put(id, clazz);
        } else {
            throw new Exception("Packet with id " + id + " already registered!");
        }
        if(clientSide) {
            if(!ClientSidePackets.contains(id)) {
                ClientSidePackets.add(id);
            }
        }
        if(serverSide) {
            if(!ServerSidePackets.contains(id)) {
                ServerSidePackets.add(id);
            }
        }
    }

    public static Packet getPacketByID(int i) throws InstantiationException, IllegalAccessException {
        return (Packet) PacketClassToID.get(i).newInstance();
    }

    public void readData(ByteBuf in) throws IOException {

    }

    public abstract void writeData(ByteBuf out) throws IOException;

    public static void writePacket(Packet packet, ByteBuf out) throws IOException {
        out.writeByte(packet.getPacketID());
        packet.writeData(out);
    }

    public void handle(ChannelHandlerContext ctx) {

    };

    public byte getPacketID() {
        return 0;
    }

    static {
        try {
            registerPacket(0x00, true, true, Packet0Identification.class);
            registerPacket(0x01, false, true, Packet1Ping.class);
            registerPacket(0x02, false, true, Packet2ChunkInit.class);
            registerPacket(0x03, false, true, Packet3ChunkData.class);
            registerPacket(0x04, false, true, Packet4ChunkEnd.class);
            registerPacket(0x05, true, false, Packet5SetBlock.class);
            registerPacket(0x06, false, true, Packet6SetBlock.class);
            registerPacket(0x07, false, true, Packet6SetBlock.class);
            registerPacket(0x08, true, true, Packet8PlayerPosition.class);
            registerPacket(0x09, false, true, Packet9PositionOrientationUpdate.class);
            registerPacket(0x0A, false, true, Packet10PositionUpdate.class);
            registerPacket(0x0B, false, true, Packet11OrientationUpdate.class);
            registerPacket(0x0C, false, true, Packet12DestroyPlayer.class);
            registerPacket(0x0D, true, true, Packet13ChatMessage.class);
            registerPacket(0x0E, false, true, Packet14KickDisconnect.class);
            registerPacket(0x0F, false, true, Packet15ChangePermissions.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeString(ByteBuf out, String value) throws IOException {
        byte[] ascii = value.getBytes("US-ASCII");
        byte[] bytes = new byte[64];
        System.arraycopy(ascii, 0, bytes, 0, Math.min(ascii.length, 64));
        for (int i = ascii.length; i < 64; i++) {
            bytes[i] = 0x20;
        }
        out.writeBytes(bytes);
    }

    public static String readString(ByteBuf in) throws IOException {
        byte[] bytes = new byte[64];
        in.readBytes(bytes);
        int endIndex = 64;
        for (int i = 63; i >= 0; i--) {
            if (bytes[i] != 0x20) {
                endIndex = i + 1;
                break;
            }
        }
        return new String(bytes, 0, endIndex, "US-ASCII");
    }

    public static void writeFShort(ByteBuf out, float fshortValue) throws IOException {
        int intValue = (int) (fshortValue * 32);
        short shortValue = (short) (intValue & 0xFFFF);
        out.writeShort(shortValue);
    }

    public static float readFShort(ByteBuf in) throws IOException {
        short shortValue = in.readShort();
        int intValue = shortValue & 0xFFFF;
        return (float) intValue / 32;
    }

    public static void writeFByte(ByteBuf out, float fbyteValue) throws IOException {
        byte byteValue = (byte) (fbyteValue * 32);
        out.writeByte(byteValue);
    }

    public static float readFByte(ByteBuf in) throws IOException {
        byte byteValue = in.readByte();
        return (float) byteValue / 32;
    }

    public static void writeByteArray(ByteBuf out, byte[] byteArray) throws IOException {
        byte[] paddedArray = new byte[1024];
        System.arraycopy(byteArray, 0, paddedArray, 0, Math.min(byteArray.length, 1024));
        out.writeBytes(paddedArray);
    }

    public static byte[] readByteArray(ByteBuf in) throws IOException {
        byte[] byteArray = new byte[1024];
        in.readBytes(byteArray);
        return byteArray;
    }

}
