package de.elia.soulboss.commands.idea;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.plugin.ThisPlugin;
import de.elia.soulmain.allplugins.messages.builder.MessageBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
    new MessageBuilder().message(sender, "If you have an idea send it to me on Discord. My Discord is:" + new ThisPlugin().contact());
    return true;
  }
}
