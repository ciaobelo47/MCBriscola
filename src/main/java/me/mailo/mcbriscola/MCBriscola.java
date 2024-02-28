package me.mailo.mcbriscola;

import me.mailo.mcbriscola.Game.Gioco;
import me.mailo.mcbriscola.Game.Toa;
import me.mailo.mcbriscola.langs.LangLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class MCBriscola extends JavaPlugin {

    public static MCBriscola plugin;
    public static String pluginVersion = "v0.1-beta";

    @Override
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        plugin=this;
        getCommand("briscola").setExecutor(new Toa());
        getCommand("briscola").setTabCompleter(new BriscolaTabCompleter());
        LangLoader langLoader = new LangLoader(this);
        getServer().getPluginManager().registerEvents(new Gioco(),this);
        System.out.println(langLoader.get("welcome_mess"));

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
