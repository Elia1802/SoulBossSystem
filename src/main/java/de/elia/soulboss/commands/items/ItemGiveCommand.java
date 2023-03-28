package de.elia.soulboss.commands.items;

import de.elia.CustomMessages;
import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.spawn.SpawnEgg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Level;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link CommandExecutor}, {@link TabCompleter}
 * @description This is the Command to get all custom items of this Plugin.
 */
public class ItemGiveCommand implements CommandExecutor, TabCompleter {
  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create the Command
   * @param sender Source of the command
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args Passed command arguments
   * @return false
   */
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    CustomMessages message = new CustomMessages();
    SpawnEgg spawnEgg = new SpawnEgg(SoulBoss.main());
    if (sender instanceof Player player) {
      if (player.hasPermission("soulboss.give")) {
        if (args.length == 2) {
          if (args[0].equalsIgnoreCase(player.getName())) {
            if (args[1].equalsIgnoreCase("soulboss_zombie")) {
              player.getInventory().setItemInMainHand(spawnEgg.spawnEgg());
              message.messageWithPrefix(player, message.green("Du hast das Spawn Ei von dem Zombie Boss geholt!"));
            }
          }
        }else {
          message.messageWithPrefix(player, message.red("/bossgive [player] [Item]"));
          return false;
        }
      }else {
        message.messageWithPrefix(player, message.red("Du hast keine Rechte für diesen Command!"));
      }
    }else {
      message.log(Level.WARNING, "You have to be a Player!");
      return false;
    }
    return true;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create the TabCompleter
   * @param sender Source of the command.  For players tab-completing a
   *     command inside a command block, this will be the player, not
   *     the command block.
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args The arguments passed to the command, including final
   *     partial argument to be completed
   * @return null
   */
  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
    Player player = (Player) sender;
    if (args.length == 2) {
      return List.of("soulboss_zombie");
    }
    return null;
  }
}
