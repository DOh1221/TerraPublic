package net.armlix;

import net.armlix.configuration.PropertiesConfig;
import net.armlix.logger.CraftLogger;
import net.armlix.logger.LoggerFile;
import net.armlix.network.MinecraftServer;
import net.armlix.network.NetHandler;
import net.armlix.network.NetLoginHandler;

public class Core {

    public static CraftLogger logger;
    public static LoggerFile loggerFile;
    public static PropertiesConfig propertiesConfig;

    public static String level_name;

    public static NetHandler netHandler = new NetLoginHandler();

    public static int max_players;
    public static int port;
    public static boolean white_list;
    public static boolean FESupporting;
    public static boolean use_auth;

    public static MinecraftServer server;

    public static void main(String[] args) {
        logger = new CraftLogger("Server");
        //loggerFile = new LoggerFile(logger);

        logger.info("This server is running TerraClassic version 1.0 (MC: Classic 0.30)");
        logger.info("Loading server properties...");
        propertiesConfig = new PropertiesConfig("", "server.properties", "Minecraft server properties");
        propertiesConfig.addDefault("level-name", "world");
        propertiesConfig.addDefault("online-mode", "false");
        propertiesConfig.addDefault("max-players", "20");
        propertiesConfig.addDefault("server-port", "25565");
        propertiesConfig.addDefault("white-list", "false");
        propertiesConfig.addDefault("support-FE-packets", "true");
        propertiesConfig.addDefault("use-authsystem", "true");

        level_name = propertiesConfig.getString("level-name");
        max_players = propertiesConfig.getInt("max-players");
        port = propertiesConfig.getInt("server-port");
        white_list = propertiesConfig.getBoolean("white-list");
        FESupporting = propertiesConfig.getBoolean("support-FE-packets");
        use_auth = propertiesConfig.getBoolean("use-authsystem");
        logger.info("Server properties loaded. Starting netty socket...");

        server = new MinecraftServer(port);

        try {
            server.run();
        } catch (Exception e) {
            logger.severe("Netty caused java exception:" + e.getCause() + "\n" + e.getMessage());
        }
    }

}