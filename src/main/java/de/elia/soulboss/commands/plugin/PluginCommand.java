package de.elia.soulboss.commands.plugin;

import de.elia.Keys;
import de.elia.soulboss.SoulBoss;
import de.elia.CustomMessages;
import de.elia.soulboss.plugin.ThisPlugin;
import de.elia.soulboss.plugin.load.start.register.configuation.ConfigurationLoader;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link CommandExecutor}, {@link TabCompleter}
 * @description This is the Command to reload the Plugin and other thinks
 */
public class PluginCommand extends ConfigurationLoader implements CommandExecutor, TabCompleter {
  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create the Command
   * @param sender Source of the command
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args Passed command arguments
   * @return boolean
   */
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    MiniMessage miniMessage = SoulBoss.soulBoss().miniMessage();
    CustomMessages message = new CustomMessages();
    ThisPlugin thisPlugin = new ThisPlugin();
    if (sender instanceof Player player) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("author")) {
          message.messageWithPrefix(player, message.aqua("Die Authoren dieses Plugins sind ").append(message.green(thisPlugin.author())));
        }else if (args[0].equalsIgnoreCase("description")) {
          message.messageWithPrefix(player, message.aqua(thisPlugin.description()));
        }else if (args[0].equalsIgnoreCase("github")) {
          message.messageWithPrefix(player, message.red("/soulboss github code"));
          message.messageWithPrefix(player, message.red("/soulboss github help"));
        }else if (args[0].equalsIgnoreCase("permissions")) {
          if (player.hasPermission("soulboss.plugin")) {
            message.messageWithPrefix(player, message.green("soulboss.plugin").append(message.gray(" = ")).append(message.aqua("Gibt alle Berechtigungen um das Plugin zu steuern.")));
            message.messageWithPrefix(player, message.green("soulboss.give").append(message.gray(" = ")).append(message.aqua("Gibt die Berechtigung den /bossgive Command zu benutzen.")));
            message.messageWithPrefix(player, message.green("soulboss.fight").append(message.gray(" = ")).append(message.aqua("Gibt die Berechtigung den /bossfight Command zu benutzen.")));
          }else {
            message.messageWithPrefix(player, message.red("Du hast keine Berechtigung für diesen Command!"));
            return false;
          }
        }else if (args[0].equalsIgnoreCase("reload")) {
          if (player.hasPermission("soulboss.plugin")) {
            message.messageWithPrefix(player, message.red("/soulboss reload configuration"));
            message.messageWithPrefix(player, message.red("/soulboss reload plugin"));
          }else {
            message.messageWithPrefix(player, message.red("Du hast keine Berechtigung für diesen Command!"));
            return false;
          }
        }else if (args[0].equalsIgnoreCase("stop")) {
          if (player.hasPermission("soulboss.plugin")) {
            message.messageWithPrefix(player, message.red("/soulboss stop fights"));
            message.messageWithPrefix(player, message.red("/soulboss stop plugin"));
          }else {
            message.messageWithPrefix(player, message.red("Du hast keine Berechtigung für diesen Command!"));
            return false;
          }
        }
      }else if (args.length == 2) {
        if (args[0].equalsIgnoreCase("github")) {
          if (args[1].equalsIgnoreCase("code")) {
            Component component1 = miniMessage.deserialize("<click:open_url:https://github.com/Elia1802/BossFight><green>Clicke hier</green></click>");
            Component component2 = miniMessage.deserialize("<aqua> den Open-Source-Code von Soulboss zu sehen!</aqua>");
            message.messageWithPrefix(player, component1.append(component2));
          } else if (args[1].equalsIgnoreCase("help")) {
            Component component1 = miniMessage.deserialize("<click:open_url:https://github.com/Elia1802/Bossfight-Help><green>Clicke hier</green></click>");
            Component component2 = miniMessage.deserialize("<aqua> die Hilfs-Reposity von Soulboss zu sehen!</aqua>");
            message.messageWithPrefix(player, component1.append(component2));
          }
        }else if (args[0].equalsIgnoreCase("reload")) {
          if (player.hasPermission("soulboss.plugin")) {
            message.messageWithPrefix(player, message.red("/soulboss reload configuration"));
            message.messageWithPrefix(player, message.red("/soulboss reload plugin"));
            if (args[1].equalsIgnoreCase("configuration")) {
              message.messageWithPrefix(player, message.gold("Alle Configurations werden neu geladen!"));
              SoulBoss.soulBoss().reloadConfiguration(SoulBoss.main());
              message.messageWithPrefix(player, message.green("Alle Configurations wurden neu geladen!"));
            } else if (args[1].equalsIgnoreCase("plugin")) {
              message.messageWithPrefix(player, message.gold("Das Plugin wird neu geladen!"));
              SoulBoss.soulBoss().reload(SoulBoss.main());
              message.messageWithPrefix(player, message.green("Das Plugin wurde neu geladen!"));
            }
          }else {
            message.messageWithPrefix(player, message.red("Du hast keine Berechtigung für diesen Command!"));
            return false;
          }
        }else if (args[0].equalsIgnoreCase("stop")) {
          if (player.hasPermission("soulboss.plugin")) {
            if (args[1].equalsIgnoreCase("fights")) {
              message.messageWithPrefix(player, message.gold("Alle BossFights werden beendet!"));
              Bukkit.getServer().getWorld("world").getEntities().forEach((entity) -> {
                if (entity.getPersistentDataContainer().has(Keys.ZOMBIE_KEY.key())) {
                  entity.remove();
                }
              });
              Bukkit.getServer().getWorld("world_nether").getEntities().forEach((entity) -> {
                if (entity.getPersistentDataContainer().has(Keys.ZOMBIE_KEY.key())) {
                  entity.remove();
                }
              });
              Bukkit.getServer().getWorld("world_the_end").getEntities().forEach((entity) -> {
                if (entity.getPersistentDataContainer().has(Keys.ZOMBIE_KEY.key())) {
                  entity.remove();
                }
              });
              //this.playerRegisterStorage().clear();
              message.messageWithPrefix(player, message.green("Alle BossFights wurden beendet!"));
              message.broadcastWithPrefix(message.gold("!ACHTUNG! Alle Bossfights wurden von einem Teammitglied beendet!"));
            } else if (args[1].equalsIgnoreCase("plugin")) {
              message.messageWithPrefix(player, message.gold("Das Plugin wird beendet!"));
              SoulBoss.main().disable();
            }
          }else {
            message.messageWithPrefix(player, message.red("Du hast keine Berechtigung für diesen Command!"));
            return false;
          }
        }
      }
    }else {
      message.log(Level.WARNING, "You have to be a Player!");
      return false;
    }
    return true;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create the tab completer for this command
   * @param sender Source of the command. For players tab-completing a
   *     command inside a command block, this will be the player, not
   *     the command block.
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args The arguments passed to the command, including final
   *     partial argument to be completed
   * @return null
   */
  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
    if (args.length == 1) {
      return Arrays.asList("author", "description", "github", "permissions", "reload", "stop");
    }else if (args.length == 2) {
      if (args[0].equalsIgnoreCase("github")) {
        return Arrays.asList("code", "help");
      }else if (args[0].equalsIgnoreCase("reload")) {
        return Arrays.asList("configuration", "plugin");
      }else if (args[0].equalsIgnoreCase("stop")) {
        return Arrays.asList("fights", "plugin");
      }
    }
    return null;
  }
}
