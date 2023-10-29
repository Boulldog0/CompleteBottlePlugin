package fr.Boulldogo.CompleteBottlePlugin;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.RegisteredServiceProvider;

public class XpToBottleCommand implements CommandExecutor {

    private final Main plugin;
    private Economy economy; 

    public XpToBottleCommand(Main plugin) {
        this.plugin = plugin;
        this.economy = null; 
        setupEconomy(); 
    }

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            this.economy = economyProvider.getProvider();
        } else {
            this.economy = null;
            plugin.getLogger().warning("Vault (économie) n'a pas été trouvé. Le plugin ne prendra pas en compte les coûts en économie.");
        }
    }

	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
        String level10 = "10";
        String level20 = "20";
        String level30 = "30";
        String level50 = "50";

        if (!(sender instanceof Player)) {
            sender.sendMessage("Seuls les joueurs peuvent exécuter cette commande !");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1 || (!args[0].equals("10") && !args[0].equals("20") && !args[0].equals("30") && !args[0].equals("50"))) {
        	String correctUse = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("correct-usage-message"));
            player.sendMessage(prefix + correctUse );
            return true;
        }

        
        if (args[0].equals("10")) {
        
        if (!player.hasPermission("completebottle.bottle.10")) {
            String noPermissionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission"));
            player.sendMessage(prefix + noPermissionMessage);
            return true;
        }

        if (player.getLevel() < 10) {
            String noXp = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("xp-not-high-level").replace("%level%", level10));
            player.sendMessage(prefix + noXp);
            return true;
        }

        double cost = plugin.getConfig().getDouble("economy.cost-level-10-command");
        String price = String.valueOf(cost);

        if (economy != null && cost > 0 && !economy.has(player, cost)) {
        	String notEnoughMoney = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("economy.no-enough-money"));
        	notEnoughMoney = notEnoughMoney.replace("%price%", price).replace("%level%", level10);
            player.sendMessage(prefix + notEnoughMoney);
            return true;
        }

        if (economy != null && cost > 0) {
            economy.withdrawPlayer(player, cost);
            String paymentSuccessful = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("economy.payment-successful").replace("%price%", price));
            player.sendMessage(prefix + paymentSuccessful);
        }

        ItemStack xpBottle = new ItemStack(Material.EXP_BOTTLE);
        ItemMeta meta = xpBottle.getItemMeta();
        String bottleName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("item.bottle-lvl-10-name"));
        meta.setDisplayName(bottleName);

        List<String> lore = new ArrayList<>();
        String[] bottleLoreLines = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("item.bottle-lvl-10-lore")).split("\\\\n");
        lore.addAll(Arrays.asList(bottleLoreLines));
        meta.setLore(lore);

        xpBottle.setItemMeta(meta);
        player.getInventory().addItem(xpBottle);

        player.setLevel(player.getLevel() - 10);

        String successMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("success-message").replace("%level%", level10));
        player.sendMessage(prefix + successMessage);

        }
        
	if (args[0].equals("20")) {
		
        if (!player.hasPermission("completebottle.bottle.20")) {
            String noPermissionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission"));
            player.sendMessage(prefix + noPermissionMessage);
            return true;
        }

        if (player.getLevel() < 20) {
            String noXp = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("xp-not-high-level").replace("%level%", level20));
            player.sendMessage(prefix + noXp);
            return true;
        }

        double cost = plugin.getConfig().getDouble("economy.cost-level-20-command");
        String price = String.valueOf(cost);

        if (economy != null && cost > 0 && !economy.has(player, cost)) {
        	String notEnoughMoney = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("economy.no-enough-money"));
        	notEnoughMoney = notEnoughMoney.replace("%price%", price).replace("%level%", level20);
            player.sendMessage(prefix + notEnoughMoney);
            return true;
        }

        if (economy != null && cost > 0) {
            economy.withdrawPlayer(player, cost);
            String paymentSuccessful = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("economy.payment-successful").replace("%price%", price));
            player.sendMessage(prefix + paymentSuccessful);
        }

        ItemStack xpBottle = new ItemStack(Material.EXP_BOTTLE);
        ItemMeta meta = xpBottle.getItemMeta();
        String bottleName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("item.bottle-lvl-20-name"));
        meta.setDisplayName(bottleName);

        List<String> lore = new ArrayList<>();
        String[] bottleLoreLines = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("item.bottle-lvl-20-lore")).split("\\\\n");
        lore.addAll(Arrays.asList(bottleLoreLines));
        meta.setLore(lore);

        xpBottle.setItemMeta(meta);
        player.getInventory().addItem(xpBottle);

        player.setLevel(player.getLevel() - 20);

        String successMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("success-message").replace("%level%", level20));
        player.sendMessage(prefix + successMessage);

        }	
    	
     if (args[0].equals("30")) {
    	 
         if (!player.hasPermission("completebottle.bottle.30")) {
             String noPermissionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission"));
             player.sendMessage(prefix + noPermissionMessage);
             return true;
         }

         if (player.getLevel() < 30) {
             String noXp = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("xp-not-high-level").replace("%level%", level30));
             player.sendMessage(prefix + noXp);
             return true;
         }

         double cost = plugin.getConfig().getDouble("economy.cost-level-30-command");
         String price = String.valueOf(cost);

         if (economy != null && cost > 0 && !economy.has(player, cost)) {
         	String notEnoughMoney = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("economy.no-enough-money"));
         	notEnoughMoney = notEnoughMoney.replace("%price%", price).replace("%level%", level30);
             player.sendMessage(prefix + notEnoughMoney);
             return true;
         }

         if (economy != null && cost > 0) {
             economy.withdrawPlayer(player, cost);
             String paymentSuccessful = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("economy.payment-successful").replace("%price%", price));
             player.sendMessage(prefix + paymentSuccessful);
         }

         ItemStack xpBottle = new ItemStack(Material.EXP_BOTTLE);
         ItemMeta meta = xpBottle.getItemMeta();
         String bottleName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("item.bottle-lvl-30-name"));
         meta.setDisplayName(bottleName);

         List<String> lore = new ArrayList<>();
         String[] bottleLoreLines = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("item.bottle-lvl-30-lore")).split("\\\\n");
         lore.addAll(Arrays.asList(bottleLoreLines));
         meta.setLore(lore);

         xpBottle.setItemMeta(meta);
         player.getInventory().addItem(xpBottle);

         player.setLevel(player.getLevel() - 30);

         String successMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("success-message").replace("%level%", level30));
         player.sendMessage(prefix + successMessage);

         }
     
     if (args[0].equals("50")) {
    	 
         if (!player.hasPermission("completebottle.bottle.50")) {
             String noPermissionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission"));
             player.sendMessage(prefix + noPermissionMessage);
             return true;
         }

         if (player.getLevel() < 50) {
             String noXp = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("xp-not-high-level").replace("%level%", level50));
             player.sendMessage(prefix + noXp);
             return true;
         }

         double cost = plugin.getConfig().getDouble("economy.cost-level-50-command");
         String price = String.valueOf(cost);

         if (economy != null && cost > 0 && !economy.has(player, cost)) {
         	String notEnoughMoney = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("economy.no-enough-money"));
         	notEnoughMoney = notEnoughMoney.replace("%price%", price).replace("%level%", level30);
             player.sendMessage(prefix + notEnoughMoney);
             return true;
         }

         if (economy != null && cost > 0) {
             economy.withdrawPlayer(player, cost);
             String paymentSuccessful = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("economy.payment-successful").replace("%price%", price));
             player.sendMessage(prefix + paymentSuccessful);
         }

         ItemStack xpBottle = new ItemStack(Material.EXP_BOTTLE);
         ItemMeta meta = xpBottle.getItemMeta();
         String bottleName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("item.bottle-lvl-50-name"));
         meta.setDisplayName(bottleName);

         List<String> lore = new ArrayList<>();
         String[] bottleLoreLines = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("item.bottle-lvl-50-lore")).split("\\\\n");
         lore.addAll(Arrays.asList(bottleLoreLines));
         meta.setLore(lore);

         xpBottle.setItemMeta(meta);
         player.getInventory().addItem(xpBottle);

         player.setLevel(player.getLevel() - 50);

         String successMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("success-message").replace("%level%", level50));
         player.sendMessage(prefix + successMessage);

         }

    return true;
	}
}

