package me.krispin.hoes;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveHoeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("givehoe")) {
            if (p.hasPermission("perms")){
                if (args.length == 0) {
                    p.sendMessage(Utils.color("&cUsage: /givehoe <playername> <amount>"));
                    return true;
                }


                else if(args.length == 2){
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null && target.isOnline()){
                        try{

                            int number = Integer.valueOf(args[1]);

                            for(int i = 0; number > i; i++){
                                target.getInventory().addItem(Utils.getHoe(0,0,0));

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

               else if(args.length == 5){
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null && target.isOnline()){
                        try{
                            int amount = Integer.valueOf(args[1]);
                            int hustlerLevel = Integer.valueOf(args[2]);
                            if(hustlerLevel > 3){
                                p.sendMessage(Utils.color("&cHustler Level can not exceed 3!"));
                                return true;
                            }
                            int invigLevel = Integer.valueOf(args[3]);
                            if(invigLevel > 3){
                                p.sendMessage(Utils.color("&cInvigoration Level can not exceed 3!"));
                                return true;
                            }
                            int fortuneLevel = Integer.valueOf(args[4]);
                            if(fortuneLevel > 3){
                                p.sendMessage(Utils.color("&cFortune Level can not exceed 3!"));
                                return true;
                            }

                            for(int i = 0; i < amount; i++){
                                target.getInventory().addItem(Utils.getHoe(hustlerLevel, invigLevel, fortuneLevel));
                            }
                            if(amount == 1){
                                p.sendMessage(Utils.color("&aYou have given &2" + target.getName() + " &aa &6Harvester Hoe" + "&a."));
                                target.sendMessage(Utils.color("&aYou have been given a &6Harvester Hoe &aby &2" + p.getName() + "&a."));
                                return true;
                            }else{
                                p.sendMessage(Utils.color("&aYou have given &2" + target.getName() + " &a" + amount + " &6Harvester Hoes" + "&a."));
                                target.sendMessage(Utils.color("&aYou have been given " + amount + " &6Harvester Hoes &aby &2" + p.getName() + "&a."));
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
                }else{
                    p.sendMessage(Utils.color("&cUsage: /givehoe <playername> <amount>"));
                    return true;
                }


        }else{
                p.sendMessage(Utils.color("&cNo permission!"));
                return true;
            }

        }
        return false;
    }
}