package de.elia.items.commands.items;

import de.elia.api.logging.PluginLogger;
import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.components.ComplexItem;
import de.elia.items.Item;
import de.elia.items.ItemMain;
import de.elia.items.recipes.SpawnEggRecipe;
import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Elia
 * @description This command give a player a item.
 * @extends {@link Command}
 */
public class ItemGiveCommand extends Command {

  public ItemGiveCommand() {
    this("itemgive", "The itemgive command give the player a specify item", "Use /itemgive [ITEM] [AMOUNT] [PLAYER]", Arrays.asList("igive", "itemg"));
  }

  public ItemGiveCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    PluginMessages message = new PluginMessages();
    SpawnEggRecipe spawnEggRecipe = new SpawnEggRecipe();
    PluginLogger logger = ItemMain.itemMain().itemLogger();
    if (sender instanceof Player player) {
      if (player.hasPermission("soulbosssystem.items.give")) {
        if (args.length == 3) {
          Complex complex = Complex.valueOf((String)args[0]);
          if (ComplexItem.SAVED.containsKey((Object)complex)) {
            Player target = Bukkit.getPlayer((String)args[2]);
            if (target != null) {
              if (args[1].equalsIgnoreCase("...")) {
                message.message(player, message.red("... Ist ein Beispiel für hörere Zahlen als 9 und hat somit keine Funktion."));
                return false;
              }
              Item.give(target, Complex.valueOf((String)args[0]), Integer.parseInt(args[1]));
              message.messageWithPrefix(player, message.gray("Du hast dem Spieler ").append(message.aqua(target.getName())).append(message.gray(" das item ")).append(message.aqua(args[0])).append(message.gray(" gegeben")));
              return true;
            }
            message.message(player, message.red(args[2] + "ist nicht online oder existiert nicht!"));
            return false;
          }
          return false;
        }
        message.message(player, message.red("/itemgive [ITEM] [AMOUNT] [PLAYER]"));
        return false;
      }
      message.messageWithPrefix(player, message.red("Du hast keine Rechte für diesen Command!"));
      return false;
    }
    logger.logWarning("You have to be a Player");
    return false;
  }

  @NotNull
  public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
    ArrayList<String> tab1 = new ArrayList<>();
    ArrayList<String> tab2 = new ArrayList<>();
    ArrayList<String> tab3 = new ArrayList<>();
    if (args.length == 1) {
      ComplexItem.SAVED.keySet().forEach(complex -> tab1.add(complex.toString()));
      return tab1;
    }
    if (args.length == 2) {
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
    }
    if (args.length == 3) {
      Bukkit.getOnlinePlayers().forEach(player -> tab3.add(player.getName()));
      return tab3;
    }
    return null;
  }
}
