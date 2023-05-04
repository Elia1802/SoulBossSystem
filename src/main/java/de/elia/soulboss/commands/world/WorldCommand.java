package de.elia.soulboss.commands.world;

import de.elia.soulboss.SoulBoss;
import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 /**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link CommandExecutor}, {@link TabCompleter}
 * @description This is the Command class to teleport the player in a other world
 */
public class WorldCommand implements CommandExecutor, TabCompleter {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Command to teleport the player in a other world
   * @param sender Source of the command
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args Passed command arguments
   * @return boolean
   */
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    PluginMessages messages = new PluginMessages();
    if (sender instanceof Player player) {
      if (player.hasPermission("soulboss.world")) {
        if (args.length == 1) {
          if (args[0].equalsIgnoreCase("world_bossfight")) {
            player.teleport(Bukkit.getWorld("world_bossfight").getSpawnLocation());
            messages.messageWithPrefix(player, messages.gray("Du wurdest in die Welt ")
              .append(messages.aqua("world_bossfight"))
              .append(messages.gray(" teleportiert!")));
          }else if (args[0].equalsIgnoreCase("world")) {
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            messages.messageWithPrefix(player, messages.gray("Du wurdest in die Welt ")
              .append(messages.aqua("world"))
              .append(messages.gray(" teleportiert!")));
          }else {
            messages.messageWithPrefix(player, messages.red("Dieser Command existiert nicht!"));
          }
        }else {
          messages.messageWithPrefix(player, messages.red("/tpworld world_bossfight"));
          messages.messageWithPrefix(player, messages.red("/tpworld world"));
        }
      }else {
        messages.messageWithPrefix(player, messages.red("Du hast keine Rechte für diesen Command!"));
      }
    }else {
      SoulBoss.soulBoss().soulBossLogger().logWarning("You have to be a Player!");
    }
    return true;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the tab completer for this command.
   * @param sender Source of the command.  For players tab-completing a
   *     command inside of a command block, this will be the player, not
   *     the command block.
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args The arguments passed to the command, including final
   *     partial argument to be completed
   * @return
   */
  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    return Arrays.asList("world", "world_bossfight");
  }
}
