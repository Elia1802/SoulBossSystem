package de.elia.items;

import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.components.ComplexItem;
import de.elia.items.items.magicbook.Magic_Book;
import de.elia.items.items.zombiespawnegg.Zombie_Spawn_Egg;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description This class register all items and has methodes to drop/give/get a item
 */
public class Item {

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Register all items
   * @param plugin  Requires a instance of the main class
   */
  public static void registerAll(@NotNull Plugin plugin){
    new Zombie_Spawn_Egg();
    new Magic_Book(plugin);
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets a item
   * @param item
   * @return The item was saved in a {@link ComplexItem#SAVED}
   * @throws CloneNotSupportedException Generate a exception if the object cannot cloned
   */
  @Nullable
  public static ComplexItem get(Complex item) throws CloneNotSupportedException {
    if (ComplexItem.SAVED.containsKey(item)) {
      return (ComplexItem) ComplexItem.SAVED.get(item).clone();
    }
    else {
      return null;
    }
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description give the player a item
   * @param player Requires a {@link Player}
   * @param item Requires a Item {@link Complex}
   * @param amount Requires the amount
   */
  public static void give(@NotNull Player player, @NotNull Complex item, int amount){
    if (amount <= 0)return;
    if (ComplexItem.SAVED.containsKey(item)) {
      ComplexItem.SAVED.get(item).setAmount(Math.min(amount, 64)).giveItem(player);
    }
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description drop the item on a specify {@link Location}
   * @param location Requires {@link Location}
   * @param item Requires a Item {@link Complex}
   * @param amount Requires the amount
   */
  public static void drop(@NotNull Location location, @NotNull Complex item, int amount){
    if (ComplexItem.SAVED.containsKey(item)) {
      ComplexItem.SAVED.get(item).setAmount(amount).drop(location);
    }
  }

}
