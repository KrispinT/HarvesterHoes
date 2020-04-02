package me.krispin.hoes.upgrades;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UpgradesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can execute this command!");
        }else{
            final Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("upgrades")){
                if(args.length == 0){
                    if(p.getItemInHand().getType() == Material.DIAMOND_HOE){
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
