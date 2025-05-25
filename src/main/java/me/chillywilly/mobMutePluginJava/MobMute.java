package me.chillywilly.mobMutePluginJava;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class MobMute extends JavaPlugin {

    public static MobMute plugin;

    @Override
    public void onEnable() {
        MobMute.plugin = this;
        saveResource("messages.yml", false);
    }

    public void getMMMessage(String path) {
        File file = new File(getDataFolder() + "/messages.yml");
        if (!file.exists()) {
            saveResource("messages.yml", false);
            file = new File(getDataFolder() + "/messages.yml");
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        String unparsedMsg = config.getString(path);
        String unparsedPrefix = config.getString("prefix");
    }

    public String convertLegacytoMM(String msg) {
        return msg.replace("&0", "<black>0")
                .replace("&1", "<dark_blue>")
                .replace("&2", "<dark_green>")
                .replace("&3", "<dark_aqua>")
                .replace("&4", "<dark_red>")
                .replace("&5", "<dark_purple>")
                .replace("&6", "<gold>")
                .replace("&7", "<gray>")
                .replace("&8", "<dark_gray>")
                .replace("&9", "<blue>")
                .replace("&a", "<green>")
                .replace("&b", "<aqua>")
                .replace("&c", "<red>")
                .replace("&d", "<light_purple>")
                .replace("&e", "<yellow>")
                .replace("&f", "<white>")
                .replace("&l", "<bold>")
                .replace("&n", "<underline>")
                .replace("&o", "<italic>")
                .replace("&m", "<strikethrough>")
                .replace("&k", "<magic>")
                .replace("&r", "<reset>")
    }
}
