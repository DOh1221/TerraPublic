package net.armlix.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.armlix.Core;
import net.armlix.network.packets.*;

public class NetLoginHandler extends NetHandler {
    @Override
    public void handle(Packet0Identification packet, ChannelHandlerContext ctx) {
        Core.logger.info(packet.username + " joined the game.");
        /*ctx.write(new Packet14KickDisconnect("Username: " + packet.username + " Key: " + packet.key + " Protocol: " + packet.version));*/

        ctx.write(new Packet0Identification());

        ctx.write(new Packet2ChunkInit());

        byte[] chunkData = new byte[1024]; // Создаем массив размером 1024 для чанка
        byte blockType = 1; // Задаем тип блока для плоской территории (например, 1 для земли)

        for (int i = 0; i < 256; i++) { // Проходим по первым 256 элементам массива (16x16 блоков)
            chunkData[i] = blockType; // Задаем тип блока для текущего индекса
        }

        ctx.write(new Packet3ChunkData((short) 1024, chunkData, (byte) 100));
        ctx.write(new Packet4ChunkEnd((short) 64, (short) 64, (short) 64));
    }

    @Override
    public void handle(Packet5SetBlock packet, ChannelHandlerContext ctx) {

    }

    @Override
    public void handle(Packet8PlayerPosition packet, ChannelHandlerContext ctx) {

    }

    @Override
    public void handle(Packet13ChatMessage packet, ChannelHandlerContext ctx) {

    }
}
