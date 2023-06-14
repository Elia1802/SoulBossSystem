package de.elia.bossfightcreator.commands.world;

import de.elia.api.logging.PluginLogger;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.systemclasses.messages.PluginMessages;

import org.bukkit.Bukkit;
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
 /**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link CommandExecutor}, {@link TabCompleter}
 * @description This is the Command class to teleport the player in a other world
 */
public class BossFightCreatorWorldCommand implements CommandExecutor, TabCompleter {

  /**
   * Executes the given command, returning its success.
   *
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
    var logger = BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger();
    PluginMessages messages = new PluginMessages();
    if (sender instanceof Player player) {
      if (player.hasPermission("soulbosssystem.bossfightgenerator.tpworld")) {
        if (args.length == 2) {
          Player target = Bukkit.getPlayer(args[0]);
          if (target.getName() == null){
            try {
              throw new NullPointerException("The Player " + target.getName() + " is not online/exsist!");
            }catch (NullPointerException exception){
              new PluginLogger.SaveError().saveError(BossFightCreatorMain.bossFightCreator().main(), exception, "BossFightCreatorWorldCommand-onCommand-line_48=null");
              logger.stacktrace(exception);
            }
            return false;
          }
          if (args[1].equalsIgnoreCase("world_bossfight")) {
            target.teleport(Bukkit.getWorld("world_bossfight").getSpawnLocation());
            messages.messageWithPrefix(target, messages.gray("Du wurdest in die Welt ")
              .append(messages.aqua("world_bossfight"))
              .append(messages.gray(" teleportiert!"))
            );
            messages.messageWithPrefix(player, messages.gray("Du hast den Spieler ")
              .append(messages.aqua(target.getName()))
              .append(messages.gray(" in die Welt "))
              .append(messages.aqua("world_bossfight"))
              .append(messages.gray(" teleportiert!"))
            );
            return true;
          }else if (args[1].equalsIgnoreCase("world")) {
            target.teleport(Bukkit.getWorld("world").getSpawnLocation());
            messages.messageWithPrefix(target, messages.gray("Du wurdest in die Welt ")
              .append(messages.aqua("world"))
              .append(messages.gray(" teleportiert!"))
            );
            messages.messageWithPrefix(player, messages.gray("Du hast den Spieler ")
              .append(messages.aqua(target.getName()))
              .append(messages.gray(" in die Welt "))
              .append(messages.aqua("world"))
              .append(messages.gray(" teleportiert!"))
            );
            return true;
          }else {
            messages.messageWithPrefix(player, messages.red("Dieser Command existiert nicht!"));
            return false;
          }
        }else {
          messages.messageWithPrefix(player, messages.red("/tpworld world_bossfight"));
          messages.messageWithPrefix(player, messages.red("/tpworld world"));
          return false;
        }
      }else {
        messages.messageWithPrefix(player, messages.red("Du hast keine Rechte für diesen Command!"));
        return false;
      }
    }else {
      logger.logWarning("You have to be a Player!");
      return false;
    }
  }

  /**
   * Requests a list of possible completions for a command argument.
   *
   * @param sender  Source of the command.  For players tab-completing a
   *                command inside of a command block, this will be the player, not
   *                the command block.
   * @param command Command which was executed
   * @param label   Alias of the command which was used
   * @param args    The arguments passed to the command, including final
   *                partial argument to be completed
   * @return A List of possible completions for the final argument, or null
   * to default to the command executor
   */
  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    List<String> tab = new ArrayList<>();
    if (args.length == 2) {
      tab.add("world");
      tab.add("world_bossfight");
      return tab;
    }
    return null;
  }
}
