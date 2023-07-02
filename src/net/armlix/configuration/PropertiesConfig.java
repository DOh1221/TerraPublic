package net.armlix.configuration;

import java.io.*;
import java.util.Properties;

public class PropertiesConfig {

    private Properties properties;
    private File File;

    private String store;

    public PropertiesConfig(String folder, String path, String store) {
        this.File=new File(path);
        File Folder=new File(folder);

        try {
            if (!Folder.exists()) {
                Folder.mkdir();
            }
            if (!this.File.exists()) {
                this.File.createNewFile();
            }


            FileReader reader = new FileReader(path);
            this.properties = new Properties();

            properties.load(reader);

            properties.store(new FileWriter(path), store);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.store = store;

    }

    public void addDefault(String string, String value) {
        properties.setProperty(string, properties.getProperty(string, value));
        saveProperties(properties);
        loadProperties(properties);
    }

    public void setProperty(String string, String value) {
        properties.setProperty(string, value);
        saveProperties(properties);
        loadProperties(properties);
    }

    public String getString(String string) {
        return properties.getProperty(string);
    }

    public boolean getBoolean(String string) {
        return Boolean.parseBoolean(properties.getProperty(string));
    }

    public int getInt(String string) {
        return Integer.parseInt(properties.getProperty(string));
    }

    public byte getByte(String string) {
        return Byte.parseByte(properties.getProperty(string));
    }

    public Long getLong(String string) {
        return Long.parseLong(properties.getProperty(string));
    }

    public void saveProperties(Properties p)
    {
        try {
            FileOutputStream fr = new FileOutputStream(this.File);
            p.store(fr, store);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadProperties(Properties p)
    {
        try {
            FileInputStream fi = new FileInputStream(this.File);
            p.load(fi);
            fi.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

