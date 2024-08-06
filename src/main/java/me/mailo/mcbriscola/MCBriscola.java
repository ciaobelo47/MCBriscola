package me.mailo.mcbriscola;

import me.mailo.mcbriscola.Game.Gioco;
import me.mailo.mcbriscola.Game.Toa;
import me.mailo.mcbriscola.langs.LangLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MCBriscola extends JavaPlugin {

    public static MCBriscola plugin;
    private static final String pluginVersion = "v0.1-beta";

    @Override
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        plugin = this;
        getCommand("briscola").setExecutor(new Toa());
        getCommand("briscola").setTabCompleter(new BriscolaTabCompleter());
        getServer().getPluginManager().registerEvents(new Gioco(), this);

    }

    public static MCBriscola getInstance() {return plugin;}

    @Override
    public void onDisable() {
        // Just Like My Other Plugin I Added a Little Troll for my Friend
        if (true == false) {
            MCBriscola.getInstance().getLogger().log(Level.SEVERE,"Massimo è bello!");
        }
    }
}
