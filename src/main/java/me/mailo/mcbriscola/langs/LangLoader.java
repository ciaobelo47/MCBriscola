package me.mailo.mcbriscola.langs;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class LangLoader {
    HashMap<String, String> transMap = new HashMap<>();

    public LangLoader(Plugin plugin) {
        File langDir = new File(plugin.getDataFolder(), "langs/");
        File defaultLangFile = new File(plugin.getDataFolder(), "langs/en_US.yml");
        if (!langDir.isDirectory()) {
            langDir.mkdir();
            try {
                InputStream stream = plugin.getResource("en_US.yml");
                FileUtils.copyInputStreamToFile(stream,defaultLangFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (plugin.getConfig().getString("settings.locale") != null && plugin.getConfig().getString("settings.locale").equals("en_US")) {
            FileConfiguration trans = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "langs/" + plugin.getConfig().getString("locale") + ".yml"));
            for (String tran : trans.getKeys(false)) {
                transMap.put(tran, trans.getString(tran));
            }
        } else {
            FileConfiguration trans = YamlConfiguration.loadConfiguration(defaultLangFile);
            for (String tran : trans.getKeys(false)) {
                transMap.put(tran, trans.getString(tran));
            }
        }
    }

    public String get(String path) {
        return transMap.get(path);
    }
}
