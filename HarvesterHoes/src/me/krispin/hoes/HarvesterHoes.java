package me.krispin.hoes;

import me.krispin.hoes.upgrades.UpgradeInventory;
import me.krispin.hoes.upgrades.UpgradesCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HarvesterHoes extends JavaPlugin {

    private HarvesterHoes instance;

    @Override
    public void onEnable(){
        instance = this;

        //setups
        UpgradeInventory.getInstance().setup(this);

        //Listeners
        Bukkit.getServer().getPluginManager().registerEvents(new HoeListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(UpgradeInventory.getInstance(), this);

        //Commands
        getCommand("harvesterhoe").setExecutor(new GiveHoeCommand());
        getCommand("upgrades").setExecutor(new UpgradesCommand());

    }

    @Override
    public void onDisable(){

    }

    public HarvesterHoes getInstance(){
        return instance;
    }
}
