package com.codenova.tpaplus;

import com.codenova.tpaplus.commands.*;
import com.codenova.tpaplus.files.teleports;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class TpaPlus extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        teleports.setup();
        teleports.get().options().copyDefaults(true);
        teleports.save();

        //config.yml
        saveDefaultConfig();



        registerCommands();
        getServer().getPluginManager().registerEvents(this,this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void registerCommands() {
        new tpa(this);
        new tpaccept(this);
        new tpreject(this);
        new tpcancel(this);
        new tpignore(this);
        new tpaPlus(this);
    }

}
