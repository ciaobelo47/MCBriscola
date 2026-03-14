package me.mailo.mcbriscola;

import me.mailo.mcbriscola.Game.Gioco;
import me.mailo.mcbriscola.Game.Toa;
import me.mailo.mcbriscola.langs.LangLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class MCBriscola extends JavaPlugin {

    public static MCBriscola plugin;
    public static LangLoader langLoader;
    private static final String pluginVersion = "v0.5-beta";

    @Override
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        plugin = this;
        getCommand("briscola").setExecutor(new Toa());
        getCommand("briscola").setTabCompleter(new BriscolaTabCompleter());
        getServer().getPluginManager().registerEvents(new Gioco(), this);
        langLoader = new LangLoader(plugin);

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
