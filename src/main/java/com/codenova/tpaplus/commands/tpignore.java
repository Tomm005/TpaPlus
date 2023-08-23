package com.codenova.tpaplus.commands;

import com.codenova.tpaplus.TpaPlus;
import com.codenova.tpaplus.files.teleports;
import com.codenova.tpaplus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class tpignore implements CommandExecutor {

    private final TpaPlus plugin;

    public tpignore(TpaPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpignore").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player p){

            if (args.length == 1){
                String pname = p.getDisplayName();
                String tname = args[0];
                Player target = Bukkit.getPlayerExact(tname);

                if (target != null){

                    if (pname.equals(tname)){
                        p.sendMessage(Utils.chat("&cYou cant ignore yourself"));
                    } else {
                        if (!Objects.equals(teleports.get().getString("tpa." + pname + ".ignored." + tname), "yes")){
                            p.sendMessage(Utils.chat("&cYou added &c&l"+tname+" &cto your ignore list"));
                            teleports.get().set("tpa."+pname+".ignored."+tname,"yes");
                            teleports.get().set("tpa."+pname+".to."+tname,null);
                            teleports.get().set("tpa."+tname+".to."+pname,null);
                            teleports.save();
                    } else {
                            p.sendMessage(Utils.chat("&aYou removed &a&l"+tname+" &afrom your ignore list"));
                            teleports.get().set("tpa."+pname+".ignored."+tname,null);
                            teleports.save();
                        }
                    }


                }else{
                    p.sendMessage(Utils.chat("&cThis player is not online"));
                }

            }else{
                p.sendMessage(Utils.chat("&c/tpignore <player>"));
            }

        }

        return false;
    }
}
