package me.krispin.hoes;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private ConfigManager(){}
    public static ConfigManager instance = new ConfigManager();
    public static ConfigManager getInstance(){
        return instance;
    }

    File configF, userdataF;
    FileConfiguration config, userdata;

    public void setup(Plugin pl)  {

        configF = new File(pl.getDataFolder(), "config.yml");
        if(!(configF.exists())){
            configF.getParentFile().mkdir();
            pl.saveDefaultConfig();
            pl.saveResource("config.yml", false);
        }
        config = new YamlConfiguration();

        try{
            config.load(configF);
        }catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }

        userdataF = new File(pl.getDataFolder(), "userdata.yml");
        if(!(userdataF.exists())){
            userdataF.getParentFile().mkdir();
            pl.saveResource("userdata.yml", false);
        }
        userdata = new YamlConfiguration();

        try{
            userdata.load(userdataF);
        }catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig(){ return config; }

    public FileConfiguration getUserdata(){return userdata;}

    public void saveConfig() {
        try{
            config.save(configF);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void saveUserdata(){
        try{
            userdata.save(userdataF);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
