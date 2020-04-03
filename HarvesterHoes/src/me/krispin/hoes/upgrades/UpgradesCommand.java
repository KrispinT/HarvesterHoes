package me.krispin.hoes.upgrades;

import me.krispin.hoes.HarvesterHoes;
import me.krispin.hoes.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UpgradesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can execute this command!");
        }else{
            final Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("upgrades")){
                if(args.length == 0){
                    if(p.getItemInHand().getType() == Material.DIAMOND_HOE && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.color("&6Harvester Hoe"))){
                        HarvesterHoes.getInstance().getItemInHand().put(p.getUniqueId(), p.getItemInHand());
                        UpgradeInventory.getInstance().openInventory(p);
                        return true;
                    }
                }
                return true;
            }

        }
        return false;
    }
}
