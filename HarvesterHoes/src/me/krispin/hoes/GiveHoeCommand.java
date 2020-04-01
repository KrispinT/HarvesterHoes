package me.krispin.hoes;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveHoeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            //console command
            if(args.length == 0){
                sender.sendMessage(Utils.color("&cUsage: /harvesterhoe give <player> <amount>"));
                return true;
            }

            if(args.length == 1){
                sender.sendMessage(Utils.color("&cUsage: /harvesterhoe give <player> <amount>"));
                return true;
            }

            if(args.length == 3){
                Player target = Bukkit.getPlayer(args[1]);

                if(target != null && target.isOnline()){
                    try{

                        int number = Integer.valueOf(args[2]);

                        for(int i = 0; number > i; i++){
                            target.getInventory().addItem(Utils.getHoe());

                        }
                        if(number == 1){
                            sender.sendMessage(Utils.color("&aYou have given &2" + target.getName() + " &aa &6Harvester Hoe" + "&a."));
                            target.sendMessage(Utils.color("&aYou have been given a &6Harvester Hoe &aby &2" + sender.getName() + "&a."));
                            return true;
                        }else{
                            sender.sendMessage(Utils.color("&aYou have given &2" + target.getName() + " &a" + number + " &6Harvester Hoes" + "&a."));
                            target.sendMessage(Utils.color("&aYou have been given " + number + " &6Harvester Hoes &aby &2" + sender.getName() + "&a."));
                            return true;
                        }

                    }catch (NumberFormatException e){
                        sender.sendMessage(Utils.color("Please use digits for the amount!"));
                        e.printStackTrace();
                        return true;
                    }
                }else{
                    sender.sendMessage(Utils.color("&cCould not find player!"));
                    return true;
                }
            }
        }else{
            //in-game command

            final Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("harvesterhoe")){
                if(p.hasPermission("alohapvp.harvesterhoe.give")){
                    if(args.length == 0){
                        p.sendMessage(Utils.color("&cUsage: /harvesterhoe give <player> <amount>"));
                        return true;
                    }

                    if(args.length == 1){
                        p.sendMessage(Utils.color("&cUsage: /harvesterhoe give <player> <amount>"));
                        return true;
                    }

                    if(args.length == 3){
                        Player target = Bukkit.getPlayer(args[1]);

                        if(target != null && target.isOnline()){
                                try{

                                    int number = Integer.valueOf(args[2]);

                                    for(int i = 0; number > i; i++){
                                        target.getInventory().addItem(Utils.getHoe());

                                    }
                                    if(number == 1){
                                        p.sendMessage(Utils.color("&aYou have given &2" + target.getName() + " &aa &6Harvester Hoe" + "&a."));
                                        target.sendMessage(Utils.color("&aYou have been given a &6Harvester Hoe &aby &2" + p.getName() + "&a."));
                                        return true;
                                    }else{
                                        p.sendMessage(Utils.color("&aYou have given &2" + target.getName() + " &a" + number + " &6Harvester Hoes" + "&a."));
                                        target.sendMessage(Utils.color("&aYou have been given " + number + " &6Harvester Hoes &aby &2" + p.getName() + "&a."));
                                        return true;
                                    }

                                }catch (NumberFormatException e){
                                    p.sendMessage(Utils.color("Please use digits for the amount!"));
                                    e.printStackTrace();
                                    return true;
                                }
                        }else{
                            p.sendMessage(Utils.color("&cCould not find player!"));
                            return true;
                        }
                    }

                }else{
                    p.sendMessage(Utils.color("&cYou do not have permission to execute this command!"));
                    return true;
                }
                return true;
            }


        }


        return false;
    }
}
