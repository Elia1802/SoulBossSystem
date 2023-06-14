package de.elia.bossfightcreator.commands.help;

import de.elia.bossfightcreator.BossFightCreatorMain;
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

public class BossFightCreatorHelpCommand implements CommandExecutor, TabCompleter {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    var logger = BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger();
    PluginMessages message = new PluginMessages();
    if (sender instanceof Player player) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("tpworldcommand")) {
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/bossfightcreatorworld [PLAYER] [WORLD]"))
            .append(message.gray("teleportiert einen Spieler in die Welten vom SoulBossSystem und zurück."))
          );
          return true;
        } else if (args[0].equalsIgnoreCase("helpcommand")) {
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/bossfightcreatorhelp [COMMAND]"))
            .append(message.gray("gibt dir eine Hilfe über dieses Plugin."))
          );
          return true;
        } else if (args[0].equalsIgnoreCase("allcommands")) {
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/bossfightcreatorworld [PLAYER] [WORLD]"))
            .append(message.gray("teleportiert einen Spieler in die Welten vom SoulBossSystem und zurück."))
          );
          message.messageWithPrefix(player, message.gray("Der command")
            .append(message.aqua("/bossfightcreatorhelp [COMMAND]"))
            .append(message.gray("gibt dir eine Hilfe über dieses Plugin."))
          );
          return true;
        } else {
          message.messageWithPrefix(player, message.red("Dieser Command exsistiert nicht!"));
          return false;
        }
      }else {
        message.messageWithPrefix(player, message.red("/bossfighthelp [HELP]"));
        return false;
      }
    } else {
      logger.logWarning("You have to be a Player!");
      return false;
    }
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    List<String> tab  = new ArrayList<>();
    if (args.length == 1) {
      tab.add("tpworldcommand");
      tab.add("helpcommand");
      tab.add("allcommands");
      return tab;
    }
    return null;
  }
}
