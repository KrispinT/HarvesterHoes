package me.krispin.hoes;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("harvesterhoe")){
            if(sender.hasPermission("alohapvp.harvesterhoe.saveall")){
                if(args[0].equalsIgnoreCase("saveall")){
                    for(Player p : Bukkit.getOnlinePlayers()){
                        int amount = HarvesterHoes.getInstance().getEssence().get(p.getUniqueId());
                        ConfigManager.getInstance().getUserdata().set("Essence." + p.getUniqueId(), amount);
                        ConfigManager.getInstance().saveUserdata();
                    }
                    sender.sendMessage(Utils.color("&8[&2HarvesterHoes&8] &cSuccessfully saved all harvester hoe data!"));
                    return true;
                }
            }
            return true;
        }
        return false;
    }
}
