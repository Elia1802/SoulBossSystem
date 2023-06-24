package de.elia.soulboss.utils.random;

import net.minecraft.world.entity.monster.Zombie;

import java.util.Random;

/**
 * @author Elia
 * @description This class give the zombie coincidentally a item.
 */
public class RandomUtils {

  public void randomItem(Zombie zombie, float chance, org.bukkit.inventory.ItemStack item1, org.bukkit.inventory.ItemStack item2) {
    Random random = new Random();
    float x = random.nextFloat();
    if (x<chance){zombie.equipItemIfPossible(net.minecraft.world.item.ItemStack.fromBukkitCopy(item1));}
    else{zombie.equipItemIfPossible(net.minecraft.world.item.ItemStack.fromBukkitCopy(item2));}
  }
}
