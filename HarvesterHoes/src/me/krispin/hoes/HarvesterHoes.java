package me.krispin.hoes;

import me.krispin.hoes.essence.Essence;
import me.krispin.hoes.essence.EssenceCommand;
import me.krispin.hoes.upgrades.UpgradeInventory;
import me.krispin.hoes.upgrades.UpgradesCommand;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HarvesterHoes extends JavaPlugin {

    private static HarvesterHoes instance;
    private Map<UUID, ItemStack> ItemInHand;
    private Map<UUID, Integer> essence;

    @Override
    public void onEnable(){
        instance = this;

        ItemInHand = new HashMap<>();
        essence = new HashMap<>();

        //setups
        ConfigManager.getInstance().setup(this);

        //Listeners
        Bukkit.getServer().getPluginManager().registerEvents(new HoeListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(UpgradeInventory.getInstance(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Essence(), this);

        //Commands
        getCommand("givehoe").setExecutor(new GiveHoeCommand());
        getCommand("upgrades").setExecutor(new UpgradesCommand());
        getCommand("essence").setExecutor(new EssenceCommand());
        getCommand("harvesterhoe").setExecutor(new SaveAllCommand());

    }

    @Override
    public void onDisable(){

    }

    public static HarvesterHoes getInstance(){
        return instance;
    }

    public Map<UUID, ItemStack> getItemInHand(){ return ItemInHand;}

    public Map<UUID, Integer> getEssence(){return essence;}

    public String getNumberFormatted(int format){
        DecimalFormat dform = new DecimalFormat("#,###");
        return dform.format(format);
    }
}
