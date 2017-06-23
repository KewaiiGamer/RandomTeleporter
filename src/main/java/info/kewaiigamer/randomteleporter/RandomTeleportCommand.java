package info.kewaiigamer.randomteleporter;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by KewaiiGamer on 6/23/2017.
 */
public class RandomTeleportCommand implements CommandExecutor {

    SettingsManager settings = SettingsManager.getInstance();
    Player p;
    GameMode gameMode;
    boolean failed = true;

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            while (failed) {
                if (strings.length >= 1 && strings[0].equalsIgnoreCase("help")) {
                    failed = false;
                } else {
                    int x = ThreadLocalRandom.current().nextInt(settings.getConfig().getInt("min.x"), settings.getConfig().getInt("max.x"));
                    int y = ThreadLocalRandom.current().nextInt(settings.getConfig().getInt("min.y"), settings.getConfig().getInt("max.y"));
                    int z = ThreadLocalRandom.current().nextInt(settings.getConfig().getInt("min.z"), settings.getConfig().getInt("max.z"));

                    Location location = new Location(((Player) sender).getWorld(), x, y, z);
                    if (strings.length == 2) {
                        String player = strings[0];
                        p = Bukkit.getServer().getPlayer(player);
                        p.getLocation().getBlock().setType(Material.STONE);
                        p.teleport(location);
                        String gamemode = strings[1];
                        if (gamemode.equalsIgnoreCase("0")) {
                            gameMode = GameMode.SURVIVAL;
                        } else if (gamemode.equalsIgnoreCase("1")) {
                            gameMode = GameMode.CREATIVE;
                        } else if (gamemode.equalsIgnoreCase("2")) {
                            gameMode = GameMode.ADVENTURE;
                        } else if (gamemode.equalsIgnoreCase("3")) {
                            gameMode = GameMode.SPECTATOR;
                        }
                        p.setGameMode(gameMode);

                        if (settings.getLanguage().equalsIgnoreCase("fr")) {
                            p.sendMessage("Vous avez été téléporté(e)s pour X: " + x + " Y: " + y + " Z: " + z);
                            p.sendMessage("Votre gamemode a été changé(e)s pour " + gameMode.name());
                        } else if (settings.getLanguage().equalsIgnoreCase("pt")) {
                            p.sendMessage("Foste teleportado para X: " + x + " Y: " + y + " Z: " + z);
                            p.sendMessage("O teu modo de jogo foi mudado para " + gameMode.name());
                        } else {
                            p.sendMessage("You have been teleported to X: " + x + " Y: " + y + " Z: " + z);
                            p.sendMessage("Your gamemode has been changed to " + gameMode.name());
                        }
                    } else {
                        p = ((Player) sender).getPlayer();
                        p.getLocation().getBlock().setType(Material.STONE);
                        p.teleport(location);
                        if (settings.getLanguage().equalsIgnoreCase("en")) {
                            p.sendMessage("You have been teleported to X: " + x + " Y: " + y + " Z: " + z);
                        } else if (settings.getLanguage().equalsIgnoreCase("fr")) {
                            p.sendMessage("Vous avez été téléporté pour X: " + x + " Y: " + y + " Z: " + z);
                        } else if (settings.getLanguage().equalsIgnoreCase("pt")) {
                            p.sendMessage("Foste teleportado para X: " + x + " Y: " + y + " Z: " + z);
                        } else {
                            p.sendMessage("You have been teleported to X: " + x + " Y: " + y + " Z: " + z);
                        }
                    }
                    if (p.getLocation().getBlock().getType() == Material.AIR) {
                        failed = false;
                    } else {
                        failed = true;
                    }
                }
            }
            failed = true;
            if (strings.length == 1 && strings[0].equalsIgnoreCase("help")) {
                p = ((Player) sender).getPlayer();
                p.sendMessage("Usage: /tpran <player> <gamemode>");
                p.sendMessage("Usage: /tpran");
            }
        } else {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            console.sendMessage("Usage: /tpran <player> <gamemode>");
            console.sendMessage("Usage: /tpran");
        }

        return true;
    }
}
