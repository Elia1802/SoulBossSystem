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
 * @author Elia, Zopnote
 */
public class Item {

  public static void registerAll(@NotNull Plugin plugin) {
    new Zombie_Spawn_Egg(plugin);
    new Magic_Book(plugin);
  }

  /**
   * @author Zopnote
   * @description Gets a specify item.
   * @param item Requires the item
   * @return the item
   * @throws CloneNotSupportedException if the item cant cloned.
   */
  @Nullable
  public static ComplexItem get(Complex item) throws CloneNotSupportedException {
    if (ComplexItem.SAVED.containsKey((Object)item)) {
      return (ComplexItem)(ComplexItem.SAVED.get(item)).clone();
    }
    return null;
  }

  /**
   * @author Zopnote
   * @description Give the {@link Player} the Item.
   * @param player Requires a {@link Player}
   * @param item Requires a Item.
   * @param amount Requires the amount of the item to give the player.
   */
  public static void give(@NotNull Player player, @NotNull Complex item, int amount) {
    if (amount <= 0) {
      return;
    }
    if (ComplexItem.SAVED.containsKey(item)) {
      ((ComplexItem)ComplexItem.SAVED.get(item)).setAmount(Math.min(amount, 64)).giveItem(player);
    }
  }

  /**
   * @author Zopnote
   * @description Drop the item an a specify location in the world.
   * @param location Requires the {@link Location}.
   * @param item Requires the item.
   * @param amount Requires the amount of the item to drop.
   */
  public static void drop(@NotNull Location location, @NotNull Complex item, int amount) {
    if (ComplexItem.SAVED.containsKey(item)) {
      ((ComplexItem)ComplexItem.SAVED.get(item)).setAmount(amount).drop(location);
    }
  }
}
