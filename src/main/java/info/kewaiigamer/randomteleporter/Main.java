package info.kewaiigamer.randomteleporter;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by KewaiiGamer on 6/22/2017.
 */
public class Main extends JavaPlugin implements Listener {

    private static Main instance;
    SettingsManager settings = SettingsManager.getInstance();

    public Main() {
        instance = this;
    }

    public static Main getPlugin() {
        return instance;
    }

    public void onDisable() {
        //Prevents configurations from getting deleted on reload
        reloadConfig();

        //Logging
        if (settings.getLanguage().equalsIgnoreCase("en")) {
            getLogger().info(getName() + " " + getDescription().getVersion() + " has been disabled");
        } else if (settings.getLanguage().equalsIgnoreCase("fr")) {
            getLogger().info(getName() + " " + getDescription().getVersion() + " a été désactivée");
        } else if (settings.getLanguage().equalsIgnoreCase("pt")) {
            getLogger().info(getName() + " " + getDescription().getVersion() + " foi desativado");
        } else {
            getLogger().info(getName() + " " + getDescription().getVersion() + " has been disabled");
        }
    }

    public void onEnable() {
        getCommand("tpran").setExecutor(new RandomTeleportCommand());
        settings.setup(this);
        //Logging
        if (settings.getLanguage().equalsIgnoreCase("en")) {
            getLogger().info(getName() + " " + getDescription().getVersion() + " has been enabled");
        } else if (settings.getLanguage().equalsIgnoreCase("fr")) {
            getLogger().info(getName() + " " + getDescription().getVersion() + " a été activée");
        } else if (settings.getLanguage().equalsIgnoreCase("pt")) {
            getLogger().info(getName() + " " + getDescription().getVersion() + " foi ativado");
        } else {
            getLogger().info(getName() + " " + getDescription().getVersion() + " has been enabled");
        }
    }
}

