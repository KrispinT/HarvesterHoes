package me.krispin.hoes.essence;

import me.krispin.hoes.HarvesterHoes;
import me.krispin.hoes.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EssenceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players execute this command!");
        }else{
            final Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("essence")){
                if(args.length == 0){
                    int amount = HarvesterHoes.getInstance().getEssence().get(p.getUniqueId());
                    p.sendMessage(Utils.color("&eEssence: ❖" + HarvesterHoes.getInstance().getNumberFormatted(amount)));
                    return true;
                }
                if(p.hasPermission("reefmc.harvesterhoe.essence")){
                    if(args.length < 2){
                        p.sendMessage(Utils.color("&cUsage: /essence add/set <playername> <amount>"));
                        return true;
                    }
                    //check command
                    if(args.length == 2){
                        if(args[0].equalsIgnoreCase("check")){
                            Player target = Bukkit.getPlayer(args[1]);
                            if(target != null && target.isOnline()){
                                int amount = HarvesterHoes.getInstance().getEssence().get(target.getUniqueId());
                                    p.sendMessage(Utils.color("&6" + target.getName()+"'s &eEssence: ❖" + HarvesterHoes.getInstance().getNumberFormatted(amount)));
                                    return true;
                            }else{
                                sender.sendMessage(Utils.color("&cCould not find player!"));
                                return true;
                            }
                        }

                    }

                    if(args.length == 3) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if(args[0].equalsIgnoreCase("add")){

                            if(target != null && target.isOnline()) {
                                try {
                                    int amount = Integer.valueOf(args[2]);
                                    int newAmount = HarvesterHoes.getInstance().getEssence().get(target.getUniqueId()) + amount;
                                    HarvesterHoes.getInstance().getEssence().put(target.getUniqueId(), newAmount);
                                    sender.sendMessage(Utils.color("&aYou have added &6❖" + amount + " &aessence to &2" + target.getName() + "'s &abalance."));
                                    Bukkit.getServer().broadcast("alohapvp.staff", Utils.color("&7(&9" + sender.getName() + " &bupdated &3" + target.getName() + " &bbalance to &f" + newAmount + "&7)"));
                                    target.sendMessage(Utils.color("&2" + sender.getName() + " &aadded &6❖" + amount + " &aessence to your balance."));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                    sender.sendMessage(Utils.color("&cPlease use digits for amount!"));
                                    return true;
                                }
                            }else {
                                sender.sendMessage(Utils.color("&cCould not find player!"));
                                return true;
                            }
                        }else if(args[0].equalsIgnoreCase("set")){
                            if(target != null && target.isOnline()) {
                                try {
                                    int amount = Integer.valueOf(args[2]);
                                    HarvesterHoes.getInstance().getEssence().put(target.getUniqueId(), amount);
                                    sender.sendMessage(Utils.color("&aYou have set &2" + target.getName() + " &aessence balance to &6❖" + amount + "&a."));
                                    target.sendMessage(Utils.color("&2" + sender.getName() + " &ahas set your essence balance to &6❖" + amount + "&a."));
                                    Bukkit.getServer().broadcast("alohapvp.staff", Utils.color("&7(&9" + sender.getName() + " &bupdated &3" + target.getName() + " &bbalance to &f" + amount + "&b.&7)"));
                                    return true;

                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                    sender.sendMessage(Utils.color("&cPlease use digits for amount!"));
                                    return true;
                                }
                            }else{
                                sender.sendMessage(Utils.color("&cCould not find player!"));
                                return true;
                            }
                        }else{
                            p.sendMessage(Utils.color("&cUsage: /essence add/set <playername> <amount>"));
                            return true;
                        }
                    }
                }else{
                    p.sendMessage(Utils.color("&cNo permission!"));
                    return true;
                }
                return true;
            }
        }
        return false;
    }
}
