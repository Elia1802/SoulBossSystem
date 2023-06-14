package de.elia.items.items.zombiespawnegg;

import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.TheZepserAPI;
import de.elia.api.thezepserapi.components.ComplexItem;

import net.kyori.adventure.text.Component;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

import static de.elia.items.ItemMain.MINI_MESSAGE;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description Create the zombie spawnegg
 */
public class Zombie_Spawn_Egg {

  private static ComplexItem ZSE;

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description The zombie spawnegg builder
   */
  public Zombie_Spawn_Egg(){
    if (ZSE == null) {
      List<Component> list = new ArrayList<>();
      list.add(MINI_MESSAGE.deserialize("<gray>With this egg you can spawn the SoulBoss</gray>"));
      Component name = MINI_MESSAGE.deserialize("<green>Soul Boss</green> <gray>Spawn Egg</gray>");
      ZSE =
        TheZepserAPI.item.create(Material.ZOMBIE_SPAWN_EGG, name, list)
          .setCustomModelData(1)
          .setAmount(1)
          .setKey(Complex.ZOMBIE_SPAWN_EGG)
          .save();
    }
  }

}
