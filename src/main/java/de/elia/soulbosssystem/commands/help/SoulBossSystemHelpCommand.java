package de.elia.soulbosssystem.commands.help;

import de.elia.api.logging.PluginLogger;
import de.elia.soulbosssystem.SoulBossSystemMain;
import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SoulBossSystemHelpCommand extends Command {

  public SoulBossSystemHelpCommand() {
    this("soulbosssystemhelp", "Send a help about all plugins in this plugin", "/soulbosssystemhelp [HELP]", Arrays.asList("sbshelp", "sbsh"));
  }

  public SoulBossSystemHelpCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    PluginLogger logger = SoulBossSystemMain.soulBossSystemMain().soulBossSystemLogger();
    PluginMessages message = new PluginMessages();
    if (sender instanceof Player player) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("achievementplugin")) {
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/achievementgive [PLAYER] [ACHIEVEMENT]")).append(message.gray("gibt einen Spieler ein bestimmtes achievement.")));
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/achievementhelp [COMMAND]")).append(message.gray("gibt dir eine Hilfe über dieses Plugin.")));
          return true;
        }
        if (args[0].equalsIgnoreCase("bossfightcreatorplugin")) {
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/bossfightcreatorworld [PLAYER] [WORLD]")).append(message.gray("teleportiert einen Spieler in die Welten vom SoulBossSystem und zurück.")));
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/bossfightcreatorhelp [COMMAND]")).append(message.gray("gibt dir eine Hilfe über dieses Plugin.")));
          return true;
        }
        if (args[0].equalsIgnoreCase("itemsplugin")) {
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/itemgive [ITEM] [AMOUNT] [PLAYER]")).append(message.gray(" gibt dir eine bestimmte Anzahl an Items an einen bestimmten Spieler.")));
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/itemhelp [COMMAND]")).append(message.gray("gibt dir eine Hilfe über dieses Plugin.")));
          return true;
        }
        if (args[0].equalsIgnoreCase("soulbosssystemplugin")) {
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("soulbosssystem [HELP]")).append(message.gray(" gibt dir diese Hilfe")));
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/soulbosssystemidea [IDEA]")).append(message.gray("ermöglicht dir eine Idee abzusenden.")));
          message.messageWithPrefix(player, message.gray("Der command").append(message.aqua("/soulbosssystemplugin help")).append(message.gray(" gibt dir Hilfe für den command selbst.")));
          return true;
        }
        message.messageWithPrefix(player, message.red("Dieser Command exsistiert nicht!"));
        return false;
      }
      message.messageWithPrefix(player, message.red("Dieser Command exsistiert nicht!"));
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
      tab.add("achievementplugin");
      tab.add("bossfightcreatorplugin");
      tab.add("itemsplugin");
      tab.add("soulbosssystemplugin");
      return tab;
    }
    return null;
  }
}
