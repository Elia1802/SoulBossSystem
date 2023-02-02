package de.elia.soulboss.entity.equipment.tools.axe;

import de.elia.soulboss.utils.random.RandomUtils;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Axe for the Boss
 */
public class Axe {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Give the {@link Zombie} a diamond or netherite axe.
   * @param zombie Requires the Zombie
   */
  public void axe(Zombie zombie){
    ItemStack diamond = new ItemStack(Material.DIAMOND_AXE);
    ItemMeta diamondMeta = diamond.getItemMeta();
    diamondMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
    diamondMeta.setUnbreakable(true);
    diamond.setItemMeta(diamondMeta);
    ItemStack netherite = new ItemStack(Material.NETHERITE_AXE);
    ItemMeta netheriteMeta = netherite.getItemMeta();
    netheriteMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
    netheriteMeta.setUnbreakable(true);
    netherite.setItemMeta(netheriteMeta);
    new RandomUtils().randomItem(zombie, 0.5F, diamond, netherite);
  }

}
