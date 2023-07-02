package net.armlix.network;

import io.netty.channel.ChannelHandlerContext;
import net.armlix.Core;
import net.armlix.network.packets.*;

import java.util.ArrayList;

public abstract class NetHandler {

    private static ArrayList<String> loggedUsers = new ArrayList<>();

    public void handle(Packet packet, ChannelHandlerContext ctx) {

    }

    public abstract void handle(Packet0Identification packet, ChannelHandlerContext ctx);

    public abstract void handle(Packet5SetBlock packet, ChannelHandlerContext ctx);

    public abstract void handle(Packet8PlayerPosition packet, ChannelHandlerContext ctx);

    public abstract void handle(Packet13ChatMessage packet, ChannelHandlerContext ctx);

    public static void add() {

    }

}
