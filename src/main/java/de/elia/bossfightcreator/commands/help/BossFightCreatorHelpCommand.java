package de.elia.bossfightcreator.commands.help;

import de.elia.api.logging.PluginLogger;

import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.systemclasses.messages.PluginMessages;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Elia
 * @description This command send a help for the player of the bossfightcreator plugin.
 * @extends {@link Command}
 */
public class BossFightCreatorHelpCommand extends Command {

  public BossFightCreatorHelpCommand() {
    this("bossfightcreatorhelp", "The bossfightcreatorhelp command send the player a help about the bossfightcreator plugin in this Plugin", "Use /bossfightcreatorhelp [HELP]", Arrays.asList("bossfightcreatorh", "bfchelp"));
  }

  public BossFightCreatorHelpCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    PluginLogger logger = BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger();
    PluginMessages message = new PluginMessages();
    if (sender instanceof Player player) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("tpworldcommand")) {
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/bossfightcreatorworld [PLAYER] [WORLD]")).append(message.gray("teleportiert einen Spieler in die Welten vom SoulBossSystem und zurück.")));
          return true;
        }
        if (args[0].equalsIgnoreCase("helpcommand")) {
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/bossfightcreatorhelp [COMMAND]")).append(message.gray("gibt dir eine Hilfe über dieses Plugin.")));
          return true;
        }
        if (args[0].equalsIgnoreCase("allcommands")) {
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/bossfightcreatorworld [PLAYER] [WORLD]")).append(message.gray("teleportiert einen Spieler in die Welten vom SoulBossSystem und zurück.")));
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/bossfightcreatorhelp [COMMAND]")).append(message.gray("gibt dir eine Hilfe über dieses Plugin.")));
          return true;
        }
        message.messageWithPrefix(player, message.red("Dieser Command exsistiert nicht!"));
        return false;
      }
      message.messageWithPrefix(player, message.red("/bossfighthelp [HELP]"));
      return false;
    }
    logger.logWarning("You have to be a Player!");
    return false;
  }

  @Override
  @NotNull
  public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
    ArrayList<String> tab = new ArrayList<>();
    if (args.length == 1) {
      tab.add("tpworldcommand");
      tab.add("helpcommand");
      tab.add("allcommands");
      return tab;
    }
    return null;
  }
}
