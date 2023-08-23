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

public class tpcancel implements CommandExecutor {

    private final TpaPlus plugin;

    public tpcancel(TpaPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpcancel").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player p){

            if (args.length == 1){
                String pname = p.getDisplayName();
                String tname = args[0];
                Player target = Bukkit.getPlayerExact(tname);

                if (target != null){

                    if (tname.equals(pname)){
                        p.sendMessage(Utils.chat("&cYou cant cancel tpa to yourself"));
                    } else {
                        if (teleports.get().get("tpa."+pname+".to."+tname) == null) {
                            p.sendMessage(Utils.chat("&cYou didnt sent this player tpa"));
                        } else {
                            if (Objects.equals(teleports.get().getString("tpa." + pname + ".to."+tname), "yes")) {
                                p.sendMessage(Utils.chat("&6&lYou cancelled tpa to "+tname));
                                target.sendMessage(Utils.chat("&c&l"+pname+" cancelled tpa to you"));
                                teleports.get().set("tpa." + pname+".to."+tname, null);
                                teleports.save();
                            } else {
                                p.sendMessage(Utils.chat("&cYou didnt sent this player tpa"));
                            }
                        }
                    }


                }else{
                    p.sendMessage(Utils.chat("&cThis player is not online"));
                }

            }else{
                p.sendMessage(Utils.chat("&c/tpcancel <player>"));
            }

        }

        return false;
    }
}
