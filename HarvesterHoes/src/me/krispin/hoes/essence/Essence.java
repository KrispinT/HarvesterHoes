package me.krispin.hoes.essence;

import me.krispin.hoes.ConfigManager;
import me.krispin.hoes.HarvesterHoes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Essence implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(!(ConfigManager.getInstance().getUserdata().contains("Essence." + e.getPlayer().getUniqueId()))){
            HarvesterHoes.getInstance().getEssence().put(e.getPlayer().getUniqueId(), 0);
        }else{
            int amount = ConfigManager.getInstance().getUserdata().getInt("Essence." + e.getPlayer().getUniqueId());
            HarvesterHoes.getInstance().getEssence().put(e.getPlayer().getUniqueId(), amount);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        int amount = HarvesterHoes.getInstance().getEssence().get(e.getPlayer().getUniqueId());
        ConfigManager.getInstance().getUserdata().set("Essence." + e.getPlayer().getUniqueId(), amount);
        ConfigManager.getInstance().saveUserdata();
    }
}
