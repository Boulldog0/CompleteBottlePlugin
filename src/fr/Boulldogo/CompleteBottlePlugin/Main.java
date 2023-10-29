package fr.Boulldogo.CompleteBottlePlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();

        String version = config.getString("version");
        
        VersionChecker versionChecker = new VersionChecker("Boulldog0", "CompleteBottlePlugin", version);
        versionChecker.checkForNewVersion();
        
        getLogger().info("Le plugin CompleteBottlePlugin v" + version + " a été chargé avec succès !");
        this.getCommand("bottle").setExecutor(new XpToBottleCommand(this));
        this.getCommand("xp-info").setExecutor(new XpCommand(this));
        this.getServer().getPluginManager().registerEvents(new XPBottleListener(this), this);
    }

    @Override
    public void onDisable() {

        getLogger().info("Le plugin CompleteBottlePlugin a été déchargé avec succès !");
    }
}
