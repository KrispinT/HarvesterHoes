package me.krispin.hoes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

   private static List<String> lore = new ArrayList<>();

    public  List<String> getLore(){
        return lore;
    }

    public void setLore(List<String> lore){
        this.lore = lore;
    }

    public static ItemStack getHoe(){
        ItemStack i = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(color("&6Harvester Hoe"));
        m.setLore(lore);
        i.setItemMeta(m);
        return i;
    }
}
