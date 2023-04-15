package de.elia.soulboss.commands.items;

import de.elia.PluginInstances;
import de.elia.PluginMessages;
import de.elia.PluginMain;
import de.elia.api.Complex;
import de.elia.api.TheZepserAPI;
import de.elia.api.components.ComplexItem;
import de.elia.items.Item;
import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.spawn.SpawnEgg;
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
    PluginMessages message = new PluginMessages();
    SpawnEgg spawnEgg = new SpawnEgg(SoulBoss.main());
    var miniMessage = SoulBoss.soulBoss().miniMessage();
    if (sender instanceof Player commandSender) {
      if (commandSender.hasPermission("soulboss.give")) {
        //cmd [item] [amount] [target(player)]
        if (args.length == 3) {
          Complex complex = Complex.valueOf(args[0]);
          if (ComplexItem.SAVED.containsKey(complex)) {
            Player player = Bukkit.getPlayer(args[2]);
            if (player != null) {
              Item.give(player, Complex.valueOf(args[0]), Integer.parseInt(args[1]));
              commandSender.sendMessage(TheZepserAPI.Prefix.append(miniMessage.deserialize("<gray>Du hast dem Spieler</gray> <aqua>")).append(miniMessage.deserialize(player.getName())).append(miniMessage.deserialize("</aqua> <gray>das Item</gray> <aqua>")).append(miniMessage.deserialize(args[0])).append(miniMessage.deserialize("</aqua> <gray>gegeben!</gray>")));
              return true;
            }
          }
        }
      }else {
        message.messageWithPrefix(commandSender, message.red("Du hast keine Rechte für diesen Command!"));
        return false;
      }
    }else {
      PluginInstances.SOUL_BOSS_LOGGER.logWarning("You have to be a Player!");
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
    List<String> list = new ArrayList<>();
    if (args.length == 1) {
      for (Complex complex : ComplexItem.SAVED.keySet()) {
        list.add(complex.toString());
      }
    }
    return list;
  }
}
