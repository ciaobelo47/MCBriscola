package com.mailo.mcbriscola;

import org.bukkit.plugin.java.JavaPlugin;

public final class MCBriscola extends JavaPlugin {

    public static MCBriscola plugin;
    public static String pluginVersion = "v0.1-beta";

    @Override
    public void onEnable() {
        plugin=this;
        getCommand("briscola").setExecutor(new Banco());
    }

    public static MCBriscola getInstance(){
        return plugin;
    }

    @Override
    public void onDisable() {
        // Just Like My Other Plugin I Added a Little Troll for my Friend
        if (true == false){
            System.out.println("Massimo è bello");
        }
    }
}
