package de.elia.items;

import de.elia.Main;
import de.elia.api.Complex;
import de.elia.api.components.ComplexItem;
import de.elia.items.magic.Magic_Book;
import de.elia.items.other.Zombie_Spawn_Egg;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Item {

  public static void registerAll(Plugin plugin){
    new Zombie_Spawn_Egg();
    new Magic_Book(plugin);
  }

  public static ComplexItem get(Complex item) {
    if (ComplexItem.SAVED.containsKey(item)) {
      try {
        return (ComplexItem) ComplexItem.SAVED.get(item).clone();
      }
      catch (CloneNotSupportedException exception) {
        Main.THE_ZEPSER_API_LOGGER.logError("An error occurred when getting an item!");
        exception.printStackTrace();
        return null;
      }
    }
    else {
      return null;
    }
  }

  public static void give(Player player, Complex item, int amount) {
    if (ComplexItem.SAVED.containsKey(item)) {
      ComplexItem.SAVED.get(item).setAmount(amount).giveItem(player);
    }
  }

  public static void drop(Location location, Complex item, int amount) {
    if (ComplexItem.SAVED.containsKey(item)) {
      ComplexItem.SAVED.get(item).setAmount(amount).drop(location);
    }
  }

}
