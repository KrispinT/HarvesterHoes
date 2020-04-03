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


    public static ItemStack getHoe(int hustler, int invigoration, int fortune){
        ItemStack i = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta m = i.getItemMeta();
        List<String> lore = new ArrayList<>();
        m.setDisplayName(color("&6Harvester Hoe"));
        lore.add(color("&7&m-------------------"));
        lore.add(color("&fHustler: &e" + hustler + "/3" ));
        lore.add(color("&fInvigoration: &e" + invigoration + "/3"));
        lore.add(color("&fFortune: &e" + fortune + "/3"));
        lore.add(color("&7&m-------------------"));
        m.setLore(lore);
        i.setItemMeta(m);
        return i;
    }


    public static ItemStack getinvFiller(){
        ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(" ");
        i.setItemMeta(m);
        return i;
    }

    private static List<String> hustlerLore = new ArrayList<>();
    public static List<String> getHustlerLore(){
        hustlerLore.clear();
        hustlerLore.add(color("&7&m----------------"));
        hustlerLore.add(color("&6Current Upgrades:"));
        hustlerLore.add(color("&8• &fLevel: "));
        hustlerLore.add(color("&7&m----------------"));
        hustlerLore.add(color("&6Next Upgrades:"));
        hustlerLore.add(color("&8• &fLevel: "));
        hustlerLore.add(color("&8• &fCost: "));
        hustlerLore.add(color("&7&m----------------"));
        return hustlerLore;
    }
    public static void setHustlerLore(List<String> lore){
        hustlerLore = lore;
    }

    public static ItemStack getHustler(){
        ItemStack i = new ItemStack(Material.DIAMOND);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Utils.color("&bHustler"));
        m.setLore(hustlerLore);
        i.setItemMeta(m);
        return i;
    }

    private static List<String> fortuneLore = new ArrayList<>();
    public static List<String> getFortuneLore(){
        fortuneLore.clear();
        fortuneLore.add(color("&7&m----------------"));
        fortuneLore.add(color("&6Current Upgrades:"));
        fortuneLore.add(color("&8• &fLevel: "));
        fortuneLore.add(color("&7&m----------------"));
        fortuneLore.add(color("&6Next Upgrades:"));
        fortuneLore.add(color("&8• &fLevel: "));
        fortuneLore.add(color("&8• &fCost: "));
        fortuneLore.add(color("&7&m----------------"));
        return fortuneLore;
    }

    public static void setFortuneLore(List<String> lore){
        fortuneLore = lore;
    }

    public static ItemStack getFortune(){
        ItemStack i = new ItemStack(Material.SUGAR_CANE);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(color("&aFortune"));
        m.setLore(fortuneLore);
        i.setItemMeta(m);
        return i;
    }

    private static List<String> invigorationLore = new ArrayList<>();
    public static List<String> getInvigorationLore(){
        invigorationLore.clear();
        invigorationLore.add(color("&7&m----------------"));
        invigorationLore.add(color("&6Current Upgrades:"));
        invigorationLore.add(color("&8• &fLevel: "));
        invigorationLore.add(color("&7&m----------------"));
        invigorationLore.add(color("&6Next Upgrades:"));
        invigorationLore.add(color("&8• &fLevel: "));
        invigorationLore.add(color("&8• &fCost: "));
        invigorationLore.add(color("&7&m----------------"));
        return invigorationLore;
    }

    public static void setInvigorationLore(List<String> lore){
        invigorationLore = lore;
    }

    public static ItemStack getInvigoration(){
        ItemStack i = new ItemStack(Material.SULPHUR);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(color("&cInvigoration"));
        m.setLore(invigorationLore);
        i.setItemMeta(m);
        return i;
    }
}
