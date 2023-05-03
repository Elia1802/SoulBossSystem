package de.elia.soulboss.entity.equipment.tools.sword;

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
 * @description This is the Sword for the Boss
 */
public class Sword {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Give the {@link Zombie} a diamond or netherite sword.
   * @param zombie Requires the Zombie
   */
  public void sword(Zombie zombie) throws SoulBossSystemNullException {
    if (!new CheckVariable().check(zombie, "Sword#sword(Zombie)"))return;
    ItemStack diamond = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta diamondMeta = diamond.getItemMeta();
    diamondMeta.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
    diamondMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
    diamondMeta.setUnbreakable(true);
    diamond.setItemMeta(diamondMeta);
    ItemStack netherite = new ItemStack(Material.NETHERITE_SWORD);
    ItemMeta netheriteMeta = netherite.getItemMeta();
    netheriteMeta.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
    netheriteMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
    netheriteMeta.setUnbreakable(true);
    netherite.setItemMeta(netheriteMeta);
    new RandomUtils().randomItem(zombie, 0.5F, diamond, netherite);
  }

}
