package de.elia.items;

import de.elia.api.Complex;
import de.elia.api.components.ComplexItem;
import de.elia.items.magic.Magic_Book;
import de.elia.items.other.Zombie_Spawn_Egg;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemIntOutOfRangeException;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemIntOutOfRangeException.Check;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException.CheckVariable;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

public class Item {

  public static void registerAll(Plugin plugin) throws SoulBossSystemNullException {
    new Zombie_Spawn_Egg();
    new Magic_Book(plugin);
  }

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

  public static void give(Player player, Complex item, int amount) throws SoulBossSystemNullException, SoulBossSystemIntOutOfRangeException {
    if (!new CheckVariable().check(player, "Item#give(Player, Complex, int)"))return;
    if (!new CheckVariable().check(item, "Item#give(Player, Complex, int)"))return;
    if (!new Check(0, 65, amount).check() == true)return;
    if (ComplexItem.SAVED.containsKey(item)) {
      ComplexItem.SAVED.get(item).setAmount(amount).giveItem(player);
    }
  }

  public static void drop(Location location, Complex item, int amount) throws SoulBossSystemNullException, SoulBossSystemIntOutOfRangeException {
    if (!new CheckVariable().check(location, "Item#drop(Location, Complex, int)"))return;
    if (!new CheckVariable().check(item, "Item#drop(Location, Complex, int)"))return;
    if (!new Check(0, 31, amount).check() == true)return;
    if (ComplexItem.SAVED.containsKey(item)) {
      ComplexItem.SAVED.get(item).setAmount(amount).drop(location);
    }
  }

}
