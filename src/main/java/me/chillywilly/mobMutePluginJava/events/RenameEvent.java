package me.chillywilly.mobMutePluginJava.events;

import me.chillywilly.mobMutePluginJava.MobMute;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class RenameEvent implements Listener {
    @EventHandler
    public void interact(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        Boolean main = player.getInventory().getItemInMainHand().getType() == Material.NAME_TAG;
        Boolean off = player.getInventory().getItemInOffHand().getType() == Material.NAME_TAG;

        String item_name = "";

        MiniMessage MM = MiniMessage.miniMessage();

        if (main) {
            item_name = MM.serialize(player.getInventory().getItemInMainHand().displayName()).toLowerCase();
        } else if (off) {
            item_name = MM.serialize(player.getInventory().getItemInOffHand().displayName()).toLowerCase();
        }

        String mute_name = MobMute.plugin.getConfig().getString("mute-name");
        String unmute_name = MobMute.plugin.getConfig().getString("unmute-name");
        boolean cancel = MobMute.plugin.getConfig().getBoolean("cancel-rename-event");
        if (mute_name == null) {
            mute_name = "[mute]";
            MobMute.plugin.getConfig().set("mute-name", mute_name);
            MobMute.plugin.saveConfig();
        }
        if (unmute_name == null) {
            unmute_name = "[unmute]";
            MobMute.plugin.getConfig().set("unmute-name", unmute_name);
            MobMute.plugin.saveConfig();
        }

        if (item_name.contains(mute_name)) {
            entity.setSilent(true);
            player.sendMessage(MobMute.plugin.getMMMessage("mute"));
            event.setCancelled(cancel);
        } else if (item_name.contains(unmute_name)) {
            entity.setSilent(false);
            player.sendMessage(MobMute.plugin.getMMMessage("unmute"));
            event.setCancelled(cancel);
        }

    }
}
