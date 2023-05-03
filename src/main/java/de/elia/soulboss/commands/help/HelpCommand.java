package de.elia.soulboss.commands.help;

import de.elia.soulboss.SoulBoss;
import de.elia.systemclasses.messages.PluginMessages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
 * @description This is the Command for the help
 */
public class HelpCommand implements CommandExecutor, TabCompleter {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Command for the help
   * @param sender Source of the command
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args Passed command arguments
   * @return true
   */
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    PluginMessages message = new PluginMessages();
    MiniMessage miniMessage = SoulBoss.soulBoss().miniMessage();
    if (sender instanceof Player player) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("bosses")) {
          Component component1 = miniMessage.deserialize("<click:open_url:https://github.com/Elia1802/Bossfight-Information/blob/main/Erkl%C3%A4rungen/Bosse/Zombie.txt><green>Öffne</green></click>");
          Component component2 = miniMessage.deserialize("<aqua> um die Informationen der Bosse zu bekommen.</aqua>");
          message.messageWithPrefix(player, component1.append(component2));
        }else if (args[0].equalsIgnoreCase("commands")) {
          Component component1 = miniMessage.deserialize("<click:open_url:https://github.com/Elia1802/Bossfight-Information/blob/main/Erkl%C3%A4rungen/Commands/Commands.txt><green>Öffne</green></click>");
          Component component2 = miniMessage.deserialize("<aqua> um die Informationen über die Commands zu bekommen.</aqua>");
          message.messageWithPrefix(player, component1.append(component2));
        }else if (args[0].equalsIgnoreCase("items")) {
          Component component1 = miniMessage.deserialize("<click:open_url:https://github.com/Elia1802/Bossfight-Information/blob/main/Erkl%C3%A4rungen/Items/Items.txt><green>Öffne</green></click>");
          Component component2 = miniMessage.deserialize("<aqua> um die Informationen über die Items zu bekommen.</aqua>");
          message.messageWithPrefix(player, component1.append(component2));
        }else if (args[0].equalsIgnoreCase("recipes")) {
          Component component1 = miniMessage.deserialize("<click:open_url:https://github.com/Elia1802/Bossfight-Information/blob/main/Erkl%C3%A4rungen/Rezept/Zombie%20SpawnEgg%20Rezept.png><green>Öffne</green></click>");
          Component component2 = miniMessage.deserialize("<aqua> um die Rezepte für die Items zu bekommen.</aqua>");
          message.messageWithPrefix(player, component1.append(component2));
        }else {
          message.messageWithPrefix(player, message.red("Dieser Command existiert nicht!"));
          return false;
        }
      }else {
        message.messageWithPrefix(player, message.red("Der Command is /bosshelp bosses"));
        message.messageWithPrefix(player, message.red("Der Command is /bosshelp commands"));
        message.messageWithPrefix(player, message.red("Der Command is /bosshelp items"));
        message.messageWithPrefix(player, message.red("Der Command is /bosshelp recipes"));
        return false;
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
   * @description This is the Tabcompleter for this command.
   * @param sender Source of the command.  For players tab-completing a
   *     command inside of a command block, this will be the player, not
   *     the command block.
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args The arguments passed to the command, including final
   *     partial argument to be completed
   * @return
   */
  @Override
  @Nullable
  public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
    if (args.length == 1) {
      return Arrays.asList("bosses", "commands", "items", "recipes");
     }else return null;
  }
}
