package fr.Boulldogo.CompleteBottlePlugin;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XpCommand implements CommandExecutor {

    private final Main plugin;

    public XpCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
        	String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
            Player player = (Player) sender;
            int xp = player.getTotalExperience();
            String xpString = String.valueOf(xp);
            String xpMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("xp-message").replace("%xp%", xpString));
            player.sendMessage(prefix + xpMessage);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        }
        return true;
    }
}
