package de.elia.items.other;

import de.elia.api.Complex;
import de.elia.api.TheZepserAPI;
import de.elia.api.components.ComplexItem;
import de.elia.items.Item;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

import static de.elia.items.ItemMain.MINI_MESSAGE;

public class Zombie_Spawn_Egg extends Item {

  private static ComplexItem ZSE;

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
