package me.krispin.hoes;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HarvesterHoes extends JavaPlugin {

    private HarvesterHoes instance;

    @Override
    public void onEnable(){
        instance = this;

        Bukkit.getServer().getPluginManager().registerEvents(new HoeListener(), this);

        getCommand("harvesterhoe").setExecutor(new GiveHoeCommand());

    }

    @Override
    public void onDisable(){

    }

    public HarvesterHoes getInstance(){
        return instance;
    }
}
