package com.codenova.tpaplus.files;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import java.io.File;
import java.io.IOException;
public class teleports {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup(){

        file = new File(Bukkit.getServer().getPluginManager().getPlugin("TpaPlus").getDataFolder(), "teleports.yml");

        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                System.out.println("Couldn't create a file.");
            }
        }

        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void save(){
        try{
            customFile.save(file);
        } catch(IOException e){
            System.out.println("Couldn't save file");
        }
    }

    public static void reload(){

        customFile = YamlConfiguration.loadConfiguration(file);

    }

}
