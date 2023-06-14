package de.elia.achivements.commands.help;

import de.elia.achivements.AchievementMain;
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
 * @description A command to send a {@link Player} help about the achievement plugin.
 * @implements {@link CommandExecutor}, {@link TabCompleter}
 */
public class AchievementHelpCommand implements CommandExecutor, TabCompleter {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    var logger = AchievementMain.achievementMain().achievementPluginLogger();
    PluginMessages message = new PluginMessages();
    if (sender instanceof Player player) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("givecommand")) {
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/achievementgive [PLAYER] [ACHIEVEMENT]"))
            .append(message.gray("gibt einen Spieler ein bestimmtes achievement."))
          );
          return true;
        }else if (args[0].equalsIgnoreCase("helpcommand")) {
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/achievementhelp [COMMAND]"))
            .append(message.gray("gibt dir eine Hilfe über dieses Plugin."))
          );
          return true;
        }else if (args[0].equalsIgnoreCase("allcommands")) {
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/achievementgive [PLAYER] [ACHIEVEMENT]"))
            .append(message.gray("gibt einen Spieler ein bestimmtes achievement."))
          );
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/achievementhelp [COMMAND]"))
            .append(message.gray("gibt dir eine Hilfe über dieses Plugin."))
          );
          return true;
        }else {
          message.messageWithPrefix(player, message.red("Dieser Command exsistiert nicht!"));
          return false;
        }
      }else {
        message.messageWithPrefix(player, message.red("/achievementhelp [HELP]"));
        return false;
      }
    }else {
      logger.logWarning("You have to be a Player!");
      return false;
    }
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    List<String> tab = new ArrayList<>();
    if (args.length == 1) {
      tab.add("givecommand");
      tab.add("helpcommand");
      tab.add("allcommands");
      return tab;
    }
    return null;
  }
}
