package de.elia.soulboss.commands.idea;

import de.elia.soulboss.functions.register.Register;
import de.elia.soulboss.messages.message.CustomMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link CommandExecutor}
 * @description This is the Command for ideas of other Players for new Mobs and other thinks for this Plugin
 */
public class IdeaCommand implements CommandExecutor {

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
    CustomMessages message = new CustomMessages();
    Register.Configuration configuration = new Register.Configuration();
    if (sender instanceof Player player) {
      if (args.length > 1) {
        StringBuilder idea = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
          idea.append(args[i]).append(" ");
        }
        String string = idea.toString();
        configuration.ideasConfiguration().set(player.getName(), string);
        message.messageWithPrefix(player, message.gold("Du hast die Idee erfolgreich abgesendet!"));
        message.messageWithPrefix(player, message.red("Deine Idee: " + string));
      }
    }
    return true;
  }
}
