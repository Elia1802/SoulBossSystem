package de.elia.items.commands.items;

import de.elia.api.logging.PluginLogger.SaveError;
import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.components.ComplexItem;
import de.elia.items.ItemMain;
import de.elia.systemclasses.messages.PluginMessages;
import de.elia.items.Item;
import de.elia.items.recipes.SpawnEggRecipe;

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
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @implements {@link CommandExecutor}, {@link TabCompleter}
 * @description This is the Command to get all custom items of this Plugin.
 */
public class ItemGiveCommand implements CommandExecutor, TabCompleter {

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
    SpawnEggRecipe spawnEggRecipe = new SpawnEggRecipe();
    var logger = ItemMain.itemMain().itemLogger();
    if (sender instanceof Player player) {
      if (player.hasPermission("soulbosssystem.items.give")) {
        //cmd [item] [amount] [target(player)]
        if (args.length == 3) {
          Complex complex = Complex.valueOf(args[0]);
          if (ComplexItem.SAVED.containsKey(complex)) {
            Player target = Bukkit.getPlayer(args[2]);
            if (target != null) {
              if (args[1].equalsIgnoreCase("...")) {
                message.message(player, message.red("... Ist ein Beispiel für hörere Zahlen als 9 und hat somit keine Funktion."));
                return false;
              }
              Item.give(target, Complex.valueOf(args[0]), Integer.parseInt(args[1]));
              message.messageWithPrefix(player,
                message.gray("Du hast dem Spieler ")
                  .append(message.aqua(target.getName()))
                  .append(message.gray(" das item "))
                  .append(message.aqua(args[0]))
                  .append(message.gray(" gegeben")
                  )
              );
              return true;
            }else {
              try {
                throw new NullPointerException("The Player " + target.getName() + " is not online/exsist!");
              }catch (NullPointerException exception){
                new SaveError().saveError(ItemMain.itemMain().main(), exception, "ItemGiveCommand-onCommand-line_77=null");
                logger.stacktrace(exception);
                return false;
              }
            }
          }else return false;
        }else {
          message.message(player, message.red("/itemgive [ITEM] [AMOUNT] [PLAYER]"));
          return false;
        }
      }else {
        message.messageWithPrefix(player, message.red("Du hast keine Rechte für diesen Command!"));
        return false;
      }
    }else {
      logger.logWarning("You have to be a Player");
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
    List<String> tab1 = new ArrayList<>();
    List<String> tab2 = new ArrayList<>();
    List<String> tab3 = new ArrayList<>();
    if (args.length == 1) {
      ComplexItem.SAVED.keySet().forEach(complex -> tab1.add(complex.toString()));
      return tab1;
    }else if (args.length == 2) {
      tab2.add("1");
      tab2.add("2");
      tab2.add("3");
      tab2.add("4");
      tab2.add("5");
      tab2.add("6");
      tab2.add("7");
      tab2.add("8");
      tab2.add("9");
      tab2.add("...");
      return tab2;
    }else if (args.length == 3) {
      Bukkit.getOnlinePlayers().forEach(player -> tab3.add(player.getName()));
      return tab3;
    }
    return null;
  }
}
