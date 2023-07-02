package net.armlix.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class CraftLogger {
    private String name;

    public CraftLogger(String logger_name) {
        this.name = " [" + logger_name + "] ";
    }

    public void info(String msg) {
        String date = new SimpleDateFormat("hh:mm:ss").format(new Date());
        System.out.println(date + name + msg);
    }

    public void warn(String msg) {
        String date = new SimpleDateFormat("hh:mm:ss").format(new Date());
        System.out.println(date + name + msg);
    }

    public void severe(String msg) {
        String date = new SimpleDateFormat("hh:mm:ss").format(new Date());
        System.out.println(date + name + msg);
    }
}

