package com.codenova.tpaplus.commands;

import com.codenova.tpaplus.TpaPlus;
import com.codenova.tpaplus.files.teleports;
import com.codenova.tpaplus.utils.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Time;
import java.util.Objects;

public class tpa implements CommandExecutor {

    private final TpaPlus plugin;

    public tpa(TpaPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpa").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player p){



                if (args.length == 1) {
                    String tname = args[0];
                    Player target = Bukkit.getPlayerExact(tname);
                    String pname = p.getDisplayName();




                    if (tname.equalsIgnoreCase(p.getDisplayName())){
                        p.sendMessage(Utils.chat("&cYou cant send tpa to yourself"));
                    } else if (target == null) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis player is not online"));
                    } else {


                        if(Objects.equals(teleports.get().getString("tpa." + tname + ".ignored." + pname), "yes")){
                            p.sendMessage(Utils.chat("&c&lThis player has ignored you"));
                        } else {
                            if (Objects.equals(teleports.get().getString("tpa." + pname + ".ignored." + tname), "yes")) {
                                p.sendMessage(Utils.chat("&c&lYou have ignored this player"));
                            } else {
                                if (Objects.equals(teleports.get().getString("tpa." + pname + ".to."+tname), "yes")) {
                                    p.sendMessage(Utils.chat("&cYou already sent this player tpa"));
                                    p.sendMessage(Utils.chat("&cwait for them to accept/reject"));
                                    p.sendMessage(Utils.chat("&cor you can type /tpcancel <player>"));
                                } else {

                                    teleports.get().set("tpa." + pname + ".to."+tname, "yes");
                                    teleports.save();


                                    TextComponent message = new TextComponent(Utils.chat("&e&l" + pname + " &6wants to teleport to you"));
                                    TextComponent confirm = new TextComponent(Utils.chat(" &a&l[✔]"));
                                    confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept " + pname));
                                    confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.chat("&a&lconfirm")).create()));
                                    TextComponent reject = new TextComponent(Utils.chat("&c&l[✖]"));
                                    reject.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpreject " + pname));
                                    reject.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.chat("&c&lreject")).create()));
                                    TextComponent tmessage = new TextComponent(Utils.chat("&7Tpa sent to &a&l" + target.getDisplayName()));
                                    TextComponent cancel = new TextComponent(Utils.chat(" &c&l[✖]"));
                                    cancel.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpcancel  " + tname));
                                    cancel.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.chat("&c&lcancel")).create()));

                                    p.spigot().sendMessage(tmessage, cancel);
                                    target.spigot().sendMessage(message, confirm, reject);

                                }
                            }
                        }



                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/tpa <player>"));
                }

        }

        return false;
    }
}
