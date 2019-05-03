package net.snapecraft.snapechat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static net.md_5.bungee.api.ProxyServer.getInstance;

public class SnapeChat extends Plugin {
    @Override
    public void onEnable() {
        System.out.println("SnapeChat loaded successfully!");
        setTimer();
    }

    private void setTimer() {
        ScheduledTask task = getInstance().getScheduler().schedule(getInstance().getPluginManager().getPlugin("SnapeChat"), () -> {
            for(ServerInfo serverInfo : ProxyServer.getInstance().getServers().values()) {
                for (ProxiedPlayer pp : serverInfo.getPlayers()) {
                    pp.sendMessage(getVoteMessage());

                    //Add other messages here
                }
            }
        }, 1, 15, TimeUnit.MINUTES);
    }

    private TextComponent getVoteMessage() {
        TextComponent message1 = new TextComponent("\n\n====================\nHeute schon " + ChatColor.GREEN + "gevoted?\n");
        TextComponent message2 = new TextComponent(ChatColor.RESET + "Wenn du regelmäßig votest, " + ChatColor.RED + ChatColor.BOLD + "unterstützt " + ChatColor.RESET + "du den Server nicht nur, sondern bekommst auch " + ChatColor.RED + ChatColor.BOLD + "tolle Belohnungen!\n");
        TextComponent message3 = new TextComponent(ChatColor.RESET + "Zum Voten hier klicken: ");
        TextComponent message4 = new TextComponent("" + ChatColor.BLUE + ChatColor.BOLD + ChatColor.UNDERLINE + "Vote\n\n");
        message4.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://snapecraft.net/voten"));

        message3.addExtra(message4);
        message2.addExtra(message3);
        message1.addExtra(message2);
        return message1;
    }
}
