package com.codenova.tpaplus.commands;

import com.codenova.tpaplus.TpaPlus;
import com.codenova.tpaplus.files.teleports;
import com.codenova.tpaplus.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Objects;

public class tpaPlus implements CommandExecutor {

    private final TpaPlus plugin;

    public tpaPlus(TpaPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpaPlus").setExecutor(this);
        plugin.getCommand("tpaPlus").setTabCompleter(new MyTabCompleter());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        Player p = (Player) sender;

        if (args.length == 0){

            p.sendMessage(Utils.chat(
                    "&a= &a[&2&lTpaPlus&a] &fmade by &aT0m1keu\n"+
                    "&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=\n"+
                    "&a= &f/tpa <player> &a- send a teleport request.\n"+
                    "&a= &f/tpaccept &a- accept a teleport request.\n"+
                    "&a= &f/tpreject &a- reject a teleport request.\n"+
                    "&a= &f/tpcancel &a- cancel a teleport request.\n"+
                    "&a= &f/tpignore <player> &a- ignore teleport requests from player.\n"+
                    "&a= &f/tpaPlus reload &a- reloads config.\n"+
                    "&a= &f/tpaPlus &a- shows the help menu\n"+
                    "&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a=&f=&a="));



        } else if (args.length==1) {
            if(Objects.equals(args[0], "reload")){
                if (p.hasPermission("TpaPlus.reload")){


                    teleports.reload();


                    p.sendMessage(Utils.chat("&7Reloading..."));
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            p.sendMessage(Utils.chat("&aReloaded"));
                        }
                    }.runTaskLater(plugin,40);

                }else{
                    p.sendMessage(Utils.chat("&cYou dont have permissions for this command"));
                }
            }
        }else {
            p.sendMessage(Utils.chat("&c/tpaPlus"));
            p.sendMessage(Utils.chat("&c/tpaPlus reload"));
        }



        return false;
    }

    private class MyTabCompleter implements TabCompleter {

        @Override
        public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
            if (args.length == 1) {
                List<String> completions = new ArrayList<>();
                completions.add("reload");
                return completions;
            }
            return Collections.emptyList();
        }
    }
}
