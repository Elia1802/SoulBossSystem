package de.elia.soulboss.entity.equipment.armors.chestplate;

import de.elia.soulboss.utils.random.RandomUtils;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException.CheckVariable;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Chestplate for the Boss
 */
public class Chestplate {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Give the {@link Zombie} a diamond or netherite chestplate.
   * @param zombie Requires the Zombie
   */
  public void chestplate(Zombie zombie) throws SoulBossSystemNullException {
    if (!new CheckVariable().check(zombie, "Chestplate#chestplate(Zombie)"))return;
    ItemStack diamond = new ItemStack(Material.DIAMOND_CHESTPLATE);
    ItemMeta diamondMeta = diamond.getItemMeta();
    diamondMeta.addEnchant(Enchantment.PROTECTION_FIRE, 5, true);
    diamondMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
    diamondMeta.addEnchant(Enchantment.THORNS, 5, true);
    diamondMeta.setUnbreakable(true);
    diamond.setItemMeta(diamondMeta);
    ItemStack netherite = new ItemStack(Material.NETHERITE_CHESTPLATE);
    ItemMeta netheriteMeta = netherite.getItemMeta();
    netheriteMeta.addEnchant(Enchantment.PROTECTION_FIRE, 5, true);
    netheriteMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
    netheriteMeta.addEnchant(Enchantment.THORNS, 5, true);
    netheriteMeta.setUnbreakable(true);
    netherite.setItemMeta(netheriteMeta);
    new RandomUtils().randomItem(zombie, 0.5F, diamond, netherite);
  }

}
