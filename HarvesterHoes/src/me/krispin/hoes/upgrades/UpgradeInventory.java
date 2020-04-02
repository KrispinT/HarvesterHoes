package me.krispin.hoes.upgrades;

import me.krispin.hoes.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;

public class UpgradeInventory implements Listener {
    private UpgradeInventory(){}
    private static UpgradeInventory instance = new UpgradeInventory();
    public static UpgradeInventory getInstance(){
        return instance;
    }

    private Inventory inv;

    public void setup(Plugin pl){
        inv = Bukkit.createInventory(new UpgradeHolder(), 27, Utils.color("&eUpgrades"));
    }

    public void resfreshInv(){
        inv.clear();
        for(int i = 0; i < 27; i++){
            inv.setItem(i, Utils.getinvFiller());
        }
        inv.setItem(11, Utils.getHustler());
        inv.setItem(13, Utils.getInvigoration());
        inv.setItem(15, Utils.getFortune());
    }

    public void openInventory(Player p){
        this.resfreshInv();
        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        //might be fix for npe
        if (!(e.getInventory().getHolder() instanceof UpgradeHolder))return;

        if(e.getInventory().getHolder() instanceof UpgradeHolder){
            e.setCancelled(true);
        }
    }

}
class UpgradeHolder implements InventoryHolder{

    @Override
    public Inventory getInventory() {
        return null;
    }
}

