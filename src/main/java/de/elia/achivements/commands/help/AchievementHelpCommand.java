package de.elia.achivements.commands.help;

import de.elia.achivements.AchievementMain;
import de.elia.api.logging.PluginLogger;
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
 * @description A command to give the {@link Player} a help about the achievement plugin.
 * @extends {@link Command}
 */
public class AchievementHelpCommand extends Command {

  public AchievementHelpCommand() {
    this("achievementhelp", "The achievementhelp command send the player a help about the achievement plugin in this plugin", "/achievementhelp [HELP]", Arrays.asList("achievementh", "ahelp"));
  }

  public AchievementHelpCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    PluginLogger logger = AchievementMain.achievementMain().achievementPluginLogger();
    PluginMessages message = new PluginMessages();
    if (sender instanceof Player player) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("givecommand")) {
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/achievementgive [PLAYER] [ACHIEVEMENT]")).append(message.gray("gibt einen Spieler ein bestimmtes achievement.")));
          return true;
        }
        if (args[0].equalsIgnoreCase("helpcommand")) {
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/achievementhelp [COMMAND]")).append(message.gray("gibt dir eine Hilfe über dieses Plugin.")));
          return true;
        }
        if (args[0].equalsIgnoreCase("allcommands")) {
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/achievementgive [PLAYER] [ACHIEVEMENT]")).append(message.gray("gibt einen Spieler ein bestimmtes achievement.")));
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/achievementhelp [COMMAND]")).append(message.gray("gibt dir eine Hilfe über dieses Plugin.")));
          return true;
        }
        message.messageWithPrefix(player, message.red("Dieser Command exsistiert nicht!"));
        return false;
      }
      message.messageWithPrefix(player, message.red("/achievementhelp [HELP]"));
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
      tab.add("givecommand");
      tab.add("helpcommand");
      tab.add("allcommands");
      return tab;
    }
    return null;
  }
}

