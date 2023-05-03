package de.elia.items;

import de.elia.api.Complex;
import de.elia.api.components.ComplexItem;
import de.elia.items.magicbook.Magic_Book;
import de.elia.items.zombiespawnegg.Zombie_Spawn_Egg;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemIntOutOfRangeException;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemIntOutOfRangeException.Check;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException.CheckVariable;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This class register all items and has methodes to drop/give/get a item
 */
public class Item {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Register all items
   * @param plugin  Requires a instance of the main class
   * @throws SoulBossSystemNullException Generate a exception if plugin null of Magic_Book
   */
  public static void registerAll(@NotNull Plugin plugin) throws SoulBossSystemNullException {
    new Zombie_Spawn_Egg();
    new Magic_Book(plugin);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets a item
   * @param item
   * @return The item was saved in a {@link ComplexItem#SAVED}
   * @throws SoulBossSystemNullException Generate a exception if item null
   * @throws CloneNotSupportedException Generate a exception if the object cannot cloned
   */
  @Nullable
  public static ComplexItem get(Complex item) throws SoulBossSystemNullException, CloneNotSupportedException {
    if (!new CheckVariable().check(item, "Item#get(Complex)"))return null;
    if (ComplexItem.SAVED.containsKey(item)) {
      return (ComplexItem) ComplexItem.SAVED.get(item).clone();
    }
    else {
      return null;
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description give the player a item
   * @param player Requires a {@link Player}
   * @param item Requires a Item {@link Complex}
   * @param amount Requires the amount
   * @throws SoulBossSystemNullException Generate a exception if player and/or item null
   * @throws SoulBossSystemIntOutOfRangeException Generate a exception if amount smaller than 0 or bigger than 65
   */
  public static void give(Player player, Complex item, int amount) throws SoulBossSystemNullException, SoulBossSystemIntOutOfRangeException {
    if (!new CheckVariable().check(player, "Item#give(Player, Complex, int)"))return;
    if (!new CheckVariable().check(item, "Item#give(Player, Complex, int)"))return;
    if (!new Check(0, 65, amount).check() == true)return;
    if (ComplexItem.SAVED.containsKey(item)) {
      ComplexItem.SAVED.get(item).setAmount(amount).giveItem(player);
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description drop the item on a specify {@link Location}
   * @param location Requires {@link Location}
   * @param item Requires a Item {@link Complex}
   * @param amount Requires the amount
   * @throws SoulBossSystemNullException Generate a exception if player and/or item null
   * @throws SoulBossSystemIntOutOfRangeException Generate a exception if amount smaller than 0 or bigger than 65
   */
  public static void drop(Location location, Complex item, int amount) throws SoulBossSystemNullException, SoulBossSystemIntOutOfRangeException {
    if (!new CheckVariable().check(location, "Item#drop(Location, Complex, int)"))return;
    if (!new CheckVariable().check(item, "Item#drop(Location, Complex, int)"))return;
    if (!new Check(0, 31, amount).check() == true)return;
    if (ComplexItem.SAVED.containsKey(item)) {
      ComplexItem.SAVED.get(item).setAmount(amount).drop(location);
    }
  }

}
