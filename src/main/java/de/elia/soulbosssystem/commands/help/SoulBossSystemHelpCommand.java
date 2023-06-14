package de.elia.soulbosssystem.commands.help;

import de.elia.soulbosssystem.SoulBossSystemMain;
import de.elia.systemclasses.messages.PluginMessages;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @implements {@link CommandExecutor}, {@link TabCompleter}
 * @description This is the Command for help
 */
public class SoulBossSystemHelpCommand implements CommandExecutor, TabCompleter {

  /**
   * Executes the given command, returning its success.
   * <br>
   * If false is returned, then the "usage" plugin.yml entry for this command
   * (if defined) will be sent to the player.
   *
   * @param sender  Source of the command
   * @param command Command which was executed
   * @param label   Alias of the command which was used
   * @param args    Passed command arguments
   * @return true if a valid command, otherwise false
   */
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    var logger = SoulBossSystemMain.soulBossSystemMain().soulBossSystemLogger();
    PluginMessages message = new PluginMessages();
    if (sender instanceof Player player) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("achievementplugin")){
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/achievementgive [PLAYER] [ACHIEVEMENT]"))
            .append(message.gray("gibt einen Spieler ein bestimmtes achievement."))
          );
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/achievementhelp [COMMAND]"))
            .append(message.gray("gibt dir eine Hilfe über dieses Plugin."))
          );
          return true;
        }else if (args[0].equalsIgnoreCase("bossfightcreatorplugin")){
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/bossfightcreatorworld [PLAYER] [WORLD]"))
            .append(message.gray("teleportiert einen Spieler in die Welten vom SoulBossSystem und zurück."))
          );
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/bossfightcreatorhelp [COMMAND]"))
            .append(message.gray("gibt dir eine Hilfe über dieses Plugin."))
          );
          return true;
        }else if (args[0].equalsIgnoreCase("itemsplugin")){
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/itemgive [ITEM] [AMOUNT] [PLAYER]"))
            .append(message.gray(" gibt dir eine bestimmte Anzahl an Items an einen bestimmten Spieler."))
          );
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/itemhelp [COMMAND]"))
            .append(message.gray("gibt dir eine Hilfe über dieses Plugin."))
          );
          return true;
        }else if (args[0].equalsIgnoreCase("soulbosssystemplugin")){
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("soulbosssystem [HELP]"))
            .append(message.gray(" gibt dir diese Hilfe"))
          );
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/soulbosssystemidea [IDEA]"))
            .append(message.gray("ermöglicht dir eine Idee abzusenden."))
          );
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/soulbosssystemplugin help"))
            .append(message.gray(" gibt dir Hilfe für den command selbst."))
          );
          return true;
        }else {
          message.messageWithPrefix(player, message.red("Dieser Command exsistiert nicht!"));
          return false;
        }
      }else {
        message.messageWithPrefix(player, message.red("Dieser Command exsistiert nicht!"));
        return false;
      }
    }else {
      logger.logWarning("You have to be a Player!");
      return false;
    }
  }

  /**
   * Requests a list of possible completions for a command argument.
   *
   * @param sender  Source of the command.  For players tab-completing a
   *                command inside of a command block, this will be the player, not
   *                the command block.
   * @param command Command which was executed
   * @param label   Alias of the command which was used
   * @param args    The arguments passed to the command, including final
   *                partial argument to be completed
   * @return A List of possible completions for the final argument, or null
   * to default to the command executor
   */
  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    List<String> tab = new ArrayList<>();
    if (args.length == 1) {
      tab.add("achievementplugin");
      tab.add("bossfightcreatorplugin");
      tab.add("itemsplugin");
      tab.add("soulbosssystemplugin");
      return tab;
    }
    return null;
  }
}
