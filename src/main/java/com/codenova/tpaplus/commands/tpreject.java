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

public class tpreject implements CommandExecutor{

    private final TpaPlus plugin;

    public tpreject(TpaPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpreject").setExecutor(this);
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
                        p.sendMessage(Utils.chat("&cYou cant reject tpa from yourself"));
                    } else {
                        if (teleports.get().get("tpa."+tname+".to."+pname) == null) {
                            p.sendMessage(Utils.chat("&cThis player didnt sent you tpa"));
                        } else {
                            if (Objects.equals(teleports.get().getString("tpa." + tname + ".to."+pname), "yes")) {
                                String rejectp = Objects.requireNonNull(this.plugin.getConfig().getString("tpa-reject-message")).replace("%Player%",tname);
                                p.sendMessage(Utils.chat(rejectp));
                                String rejectt = Objects.requireNonNull(this.plugin.getConfig().getString("tpa-reject-message-target")).replace("%Player%",pname);
                                target.sendMessage(Utils.chat(rejectt));
                                teleports.get().set("tpa." + tname+".to."+pname, null);
                                teleports.save();
                            } else {
                                p.sendMessage(Utils.chat("&cThis player didnt sent you tpa"));
                            }
                        }
                    }


                }else{
                    p.sendMessage(Utils.chat("&cThis player is not online"));
                }

            }else{
                p.sendMessage(Utils.chat("&c/tpreject <player>"));
            }

        }

        return false;
    }
}
