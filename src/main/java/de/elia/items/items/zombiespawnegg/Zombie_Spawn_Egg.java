package de.elia.items.items.zombiespawnegg;

import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.TheZepserAPI;
import de.elia.api.thezepserapi.components.ComplexItem;
import de.elia.items.ItemMain;
import java.util.ArrayList;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

/**
 * @author Zopnote, Elia
 * @description This class create the item zombie spawn egg.
 */
public class Zombie_Spawn_Egg {

  private static ComplexItem ZSE;

  public Zombie_Spawn_Egg() {
    if (ZSE == null) {
      ArrayList<Component> list = new ArrayList<>();
      list.add(ItemMain.MINI_MESSAGE.deserialize("<gray>With this egg you can spawn the SoulBoss</gray>"));
      Component name = ItemMain.MINI_MESSAGE.deserialize("<green>Soul Boss</green> <gray>Spawn Egg</gray>");
      ZSE = TheZepserAPI.item.create(Material.ZOMBIE_SPAWN_EGG,
        name,
        list)
        .setCustomModelData(1)
        .setAmount(1)
        .setKey(Complex.ZOMBIE_SPAWN_EGG)
        .save();
    }
  }
}
