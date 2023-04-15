package de.elia.soulboss.commands.idea;

import de.elia.PluginMessages;
import de.elia.soulboss.plugin.load.start.register.configuation.ConfigurationLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link CommandExecutor}
 * @description This is the Command for ideas of other Players for new Mobs and other thinks for this Plugin
 */
public class IdeaCommand extends ConfigurationLoader implements CommandExecutor {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create the Command
   * @param sender Source of the command
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args Passed command arguments
   * @return true
   */
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    PluginMessages message = new PluginMessages();
    if (sender instanceof Player player) {
      if (args.length > 1) {
        int i;
        StringBuilder idea = new StringBuilder();
        for (i = 0; i < args.length; i++) {
          idea.append(args[i]).append(" ");
        }
        if (this.ideasStorage().get(player.getName()) == null) {
          String string = idea.toString();
          this.ideasStorage().set(player.getName(), string);
          message.messageWithPrefix(player, message.gold("Du hast die Idee erfolgreich abgesendet!"));
          message.messageWithPrefix(player, message.red("Deine Idee: " + string));
        }else {
          message.messageWithPrefix(player, message.red("Du hast eine Idee schon abgegeben!"));
          message.messageWithPrefix(player, message.gold("Eine neue Idee kannst du erst eingeben, wenn ein Teammitglied dein Antrag aus der Configuration ausgelesen hat!"));
        }
      }
    }else {
      message.log(Level.WARNING, "You have to be a Player!");
      return false;
    }
    return true;
  }
}
