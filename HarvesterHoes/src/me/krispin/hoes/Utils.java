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
    public static ItemStack getinvFiller(){
        ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(" ");
        i.setItemMeta(m);
        return i;
    }

    public static ItemStack getHustler(){
        ItemStack i = new ItemStack(Material.DIAMOND);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Utils.color("&bHustler"));
        List<String> lore = new ArrayList<>();
        lore.add(color("&6&m----------------"));
        lore.add(color("&eCurrent Upgrades:"));
        lore.add(color("&8• &fLevel: "));
        lore.add(color("&8• &fRate: "));
        lore.add(color("&6&m----------------"));
        lore.add(color("&eNext Upgrades:"));
        lore.add(color("&8• &fLevel: "));
        lore.add(color("&8• &fRate: "));
        lore.add(color("&8• &fCost: "));
        lore.add(color("&6&m----------------"));
        m.setLore(lore);
        i.setItemMeta(m);
        return i;
    }

    public static ItemStack getFortune(){
        ItemStack i = new ItemStack(Material.SUGAR_CANE);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(color("&aFortune"));
        List<String> lore = new ArrayList<>();
        lore.add(color("&6&m----------------"));
        lore.add(color("&eCurrent Upgrades:"));
        lore.add(color("&8• &fLevel: "));
        lore.add(color("&8• &fRate: "));
        lore.add(color("&6&m----------------"));
        lore.add(color("&eNext Upgrades:"));
        lore.add(color("&8• &fLevel: "));
        lore.add(color("&8• &fRate: "));
        lore.add(color("&8• &fCost: "));
        lore.add(color("&6&m----------------"));
        m.setLore(lore);
        i.setItemMeta(m);
        return i;
    }

    public static ItemStack getInvigoration(){
        ItemStack i = new ItemStack(Material.SULPHUR);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(color("&cInvigoration"));
        List<String> lore = new ArrayList<>();
        lore.add(color("&6&m----------------"));
        lore.add(color("&eCurrent Upgrades:"));
        lore.add(color("&8• &fLevel: "));
        lore.add(color("&8• &fRate: "));
        lore.add(color("&6&m----------------"));
        lore.add(color("&eNext Upgrades:"));
        lore.add(color("&8• &fLevel: "));
        lore.add(color("&8• &fRate: "));
        lore.add(color("&8• &fCost: "));
        lore.add(color("&6&m----------------"));

        m.setLore(lore);
        i.setItemMeta(m);
        return i;
    }
}
