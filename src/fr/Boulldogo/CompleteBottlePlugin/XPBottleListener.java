package fr.Boulldogo.CompleteBottlePlugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class XPBottleListener implements Listener {

    private final Main plugin;

    public XPBottleListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
    	String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        
        if (item != null && item.getType() == Material.EXP_BOTTLE) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.hasLore() && meta.hasDisplayName()) {
                String displayName = meta.getDisplayName();

                String expectedName10 = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("item.bottle-lvl-10-name"));
                String expectedName20 = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("item.bottle-lvl-20-name"));
                String expectedName30 = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("item.bottle-lvl-30-name"));
                String expectedName50 = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("item.bottle-lvl-50-name"));

                if (displayName.equals(expectedName10) || displayName.equals(expectedName20) || displayName.equals(expectedName30)|| displayName.equals(expectedName50)) {
                    event.setCancelled(true);

                    int currentLevels = player.getLevel();
                    int xpToAdd = 0;
                    
                    if (displayName.equals(expectedName10)) {
                        xpToAdd = 10;
                    } else if (displayName.equals(expectedName20)) {
                        xpToAdd = 20;
                    } else if (displayName.equals(expectedName30)) {
                        xpToAdd = 30;
                    } else if (displayName.equals(expectedName50)) {
                        xpToAdd = 50;
                    }

                    
                    int maxLvl = plugin.getConfig().getInt("max-level");
                    String maxLvlString = String.valueOf(maxLvl);
                    String maxlvlmessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("max-level-message").replace("%maxlevel%", maxLvlString));

                    if (currentLevels + xpToAdd > maxLvl) {
                        player.sendMessage(prefix + maxlvlmessage);
                        return;
                    }

                    player.setLevel(currentLevels + xpToAdd);

                    String xpToAddString = String.valueOf(xpToAdd);
                    String successfullmessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("success-addxp-message"));
                    successfullmessage = successfullmessage.replace("%level%", xpToAddString).replace("%bottle-name%", displayName);
                    player.sendMessage(prefix + successfullmessage);
                    
                    player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);

                }
                
            }
            
        }
        
    }
}
