package me.mailo.mcbriscola.langs;

import me.mailo.mcbriscola.MCBriscola;
import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LangLoader {
    HashMap<String, String> transMap = new HashMap<>();
    private Logger logger = MCBriscola.getInstance().getLogger();

    public LangLoader(Plugin plugin) {
        File langDir = new File(plugin.getDataFolder(), "langs/");
        File defaultLangFile = new File(plugin.getDataFolder(), "langs/en_US.yml");
        File itaLangFile = new File(plugin.getDataFolder(), "langs/it_IT.yml");
        File VENETOLANGFILE = new File(plugin.getDataFolder(), "langs/vec_VEC.yml");

        if (!langDir.isDirectory()) {
            logger.log(Level.WARNING,"Language Files don't exist, creating ones...");
            langDir.mkdir();
            try {
                InputStream streamEng = plugin.getResource("langs/en_US.yml");
                InputStream streamIta = plugin.getResource("langs/it_IT.yml");
                InputStream streamVec = plugin.getResource("langs/vec_VEC.yml");
                FileUtils.copyInputStreamToFile(streamEng, defaultLangFile);
                FileUtils.copyInputStreamToFile(streamIta, itaLangFile);
                FileUtils.copyInputStreamToFile(streamVec, VENETOLANGFILE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (langDir.isDirectory() && !defaultLangFile.isFile() && !itaLangFile.isFile() && !VENETOLANGFILE.isFile()) {
            logger.log(Level.WARNING,"Language Files don't exist, creating ones...");
            try {
                InputStream streamEng = plugin.getResource("langs/en_US.yml");
                InputStream streamIta = plugin.getResource("langs/it_IT.yml");
                InputStream streamVec = plugin.getResource("langs/vec_VEC.yml");
                FileUtils.copyInputStreamToFile(streamEng, defaultLangFile);
                FileUtils.copyInputStreamToFile(streamIta, itaLangFile);
                FileUtils.copyInputStreamToFile(streamVec, VENETOLANGFILE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (langDir.isDirectory() && defaultLangFile.isFile() && !VENETOLANGFILE.isFile() && !itaLangFile.isFile()) {
            logger.log(Level.WARNING,"Some Language Files don't exist, creating missing files...");
            try {
                InputStream streamIta = plugin.getResource("langs/it_IT.yml");
                InputStream streamVec = plugin.getResource("langs/vec_VEC.yml");
                FileUtils.copyInputStreamToFile(streamIta, itaLangFile);
                FileUtils.copyInputStreamToFile(streamVec, VENETOLANGFILE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (langDir.isDirectory() && defaultLangFile.isFile() && VENETOLANGFILE.isFile() && !itaLangFile.isFile()) {
            logger.log(Level.WARNING,"Some Language Files don't exist, creating missing files...");
            try {
                InputStream streamIta = plugin.getResource("langs/it_IT.yml");
                FileUtils.copyInputStreamToFile(streamIta, itaLangFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (langDir.isDirectory() && defaultLangFile.isFile() && !VENETOLANGFILE.isFile() && itaLangFile.isFile()) {
            logger.log(Level.WARNING,"Some Language Files don't exist, creating missing files...");
            try {
                InputStream streamVec = plugin.getResource("langs/vec_VEC.yml");
                FileUtils.copyInputStreamToFile(streamVec, VENETOLANGFILE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (langDir.isDirectory() && !defaultLangFile.isFile() && VENETOLANGFILE.isFile() && !itaLangFile.isFile()) {
            logger.log(Level.WARNING,"Some Language Files don't exist, creating missing files...");
            try {
                InputStream streamEng = plugin.getResource("langs/en_US.yml");
                InputStream streamIta = plugin.getResource("langs/it_IT.yml");
                FileUtils.copyInputStreamToFile(streamEng, defaultLangFile);
                FileUtils.copyInputStreamToFile(streamIta, itaLangFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (langDir.isDirectory() && defaultLangFile.isFile() && VENETOLANGFILE.isFile() && !itaLangFile.isFile()) {
            logger.log(Level.WARNING,"Some Language Files don't exist, creating missing files...");
            try {
                InputStream streamIta = plugin.getResource("langs/it_IT.yml");
                FileUtils.copyInputStreamToFile(streamIta, itaLangFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (langDir.isDirectory() && !defaultLangFile.isFile() && VENETOLANGFILE.isFile() && itaLangFile.isFile()) {
            logger.log(Level.WARNING,"Some Language Files don't exist, creating missing files...");
            try {
                InputStream streamEng = plugin.getResource("langs/en_US.yml");
                FileUtils.copyInputStreamToFile(streamEng, defaultLangFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (langDir.isDirectory() && !defaultLangFile.isFile() && !VENETOLANGFILE.isFile() && itaLangFile.isFile()) {
            logger.log(Level.WARNING,"Some Language Files don't exist, creating missing files...");
            try {
                InputStream streamEng = plugin.getResource("langs/en_US.yml");
                InputStream streamVec = plugin.getResource("langs/vec_VEC.yml");
                FileUtils.copyInputStreamToFile(streamEng, defaultLangFile);
                FileUtils.copyInputStreamToFile(streamVec, VENETOLANGFILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (langDir.isDirectory() && defaultLangFile.isFile() && !VENETOLANGFILE.isFile() && itaLangFile.isFile()) {
            logger.log(Level.WARNING,"Some Language Files don't exist, creating missing files...");
            try {
                InputStream streamVec = plugin.getResource("langs/vec_VEC.yml");
                FileUtils.copyInputStreamToFile(streamVec, VENETOLANGFILE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (langDir.isDirectory() && !defaultLangFile.isFile() && VENETOLANGFILE.isFile() && itaLangFile.isFile()) {
            logger.log(Level.WARNING,"Some Language Files don't exist, creating missing files...");
            try {
                InputStream streamEng = plugin.getResource("langs/en_US.yml");
                FileUtils.copyInputStreamToFile(streamEng, defaultLangFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (plugin.getConfig().getString("settings.locale") != null && plugin.getConfig().getString("settings.locale").equals("en_US")) {
            FileConfiguration trans = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "langs/" + plugin.getConfig().getString("locale") + ".yml"));
            for (String tran : trans.getKeys(false)) {
                transMap.put(tran, trans.getString(tran));
            }

        } else if (plugin.getConfig().getString("settings.locale") != null && plugin.getConfig().getString("settings.locale").equals("it_IT")) {
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
