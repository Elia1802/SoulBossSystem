package de.elia.soulboss.entity.equipment.armors.boots;

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
 * @description This is the boots for the Boss
 */
public class Boots {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Give the {@link Zombie} diamond or netherite boots.
   * @param zombie Requires the Zombie
   */
  public void boots(Zombie zombie){
    ItemStack diamond = new ItemStack(Material.DIAMOND_BOOTS);
    ItemMeta diamondMeta = diamond.getItemMeta();
    diamondMeta.addEnchant(Enchantment.PROTECTION_FIRE, 5, true);
    diamondMeta.addEnchant(Enchantment.FROST_WALKER, 5, true);
    diamondMeta.addEnchant(Enchantment.PROTECTION_FALL, 5, true);
    diamondMeta.addEnchant(Enchantment.THORNS, 5, true);
    diamondMeta.setUnbreakable(true);
    diamond.setItemMeta(diamondMeta);
    ItemStack netherite = new ItemStack(Material.NETHERITE_BOOTS);
    ItemMeta netheriteMeta = netherite.getItemMeta();
    netheriteMeta.addEnchant(Enchantment.PROTECTION_FIRE, 5, true);
    netheriteMeta.addEnchant(Enchantment.FROST_WALKER, 5, true);
    netheriteMeta.addEnchant(Enchantment.PROTECTION_FALL, 5, true);
    netheriteMeta.addEnchant(Enchantment.THORNS, 5, true);
    netheriteMeta.setUnbreakable(true);
    netherite.setItemMeta(netheriteMeta);
    new RandomUtils().randomItem(zombie, 0.5F, diamond, netherite);
  }

}
