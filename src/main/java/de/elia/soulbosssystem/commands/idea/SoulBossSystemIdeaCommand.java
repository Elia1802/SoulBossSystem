package de.elia.soulbosssystem.commands.idea;

import de.elia.soulbosssystem.SoulBossSystemMain;
import de.elia.systemclasses.messages.PluginMessages;
import de.elia.soulbosssystem.configuation.SoulBossSystemConfigurationLoader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @implements {@link CommandExecutor}
 * @description This is the Command for ideas of other Players for new Mobs and other thinks for this Plugin
 */
public class SoulBossSystemIdeaCommand implements CommandExecutor {

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
    PluginMessages message = new PluginMessages();
    SoulBossSystemConfigurationLoader soulBossSystemConfigurationLoader = new SoulBossSystemConfigurationLoader();
    var logger = SoulBossSystemMain.soulBossSystemMain().soulBossSystemLogger();
    if (sender instanceof Player player) {
      if (args.length > 1) {
        int i;
        StringBuilder idea = new StringBuilder();
        for (i = 0; i < args.length; i++) {
          idea.append(args[i]).append(" ");
        }
        if (soulBossSystemConfigurationLoader.ideasStorage().get(player.getName()) == null) {
          String string = idea.toString();
          soulBossSystemConfigurationLoader.ideasStorage().set(player.getName(), string);
          message.messageWithPrefix(player, message.gray("Du hast die Idee erfolgreich abgesendet!"));
          message.messageWithPrefix(player, message.gray("Deine Idee: "));
          message.messageWithPrefix(player, message.aqua(string));
          return true;
        }else {
          message.messageWithPrefix(player, message.red("Du hast eine Idee schon abgegeben!"));
          message.messageWithPrefix(player, message.gold("Eine neue Idee kannst du erst eingeben, wenn ein Teammitglied dein Antrag aus der Configuration ausgelesen hat!"));
          return false;
        }
      }else {
        message.messageWithPrefix(player, message.red("/soulbosssystemidea [IDEA]"));
        return false;
      }
    }else {
      logger.logWarning("You have to be a Player!");
      return false;
    }
  }
}
