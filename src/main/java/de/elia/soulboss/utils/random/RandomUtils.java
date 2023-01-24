package de.elia.soulboss.utils.random;

import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Random class for the Zombie tools
 */
public class RandomUtils {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Decides whether item1 or item2 will be given to the zombie.
   * @param zombie Requires the zombie
   * @param chance Requires the chance
   * @param item1 Requires the Item 1
   * @param item2 Requires the item 2
   */
  public void randomItem(Zombie zombie, float chance, ItemStack item1, ItemStack item2){
    Random random = new Random();
    float x = random.nextFloat();
    if (x<chance){zombie.equipItemIfPossible(net.minecraft.world.item.ItemStack.fromBukkitCopy(item1));}
    else{zombie.equipItemIfPossible(net.minecraft.world.item.ItemStack.fromBukkitCopy(item2));}
  }

}
