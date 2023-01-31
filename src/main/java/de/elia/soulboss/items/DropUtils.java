package de.elia.soulboss.items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the class to set drops if the boss die.
 */
public class DropUtils {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This set the drop if the boss die.
   * @param location Requires the Location of the Boss.
   */
  public void drop(@NotNull Location location){
    ItemStack itemStack;
    Random random = new Random();
    float x = random.nextFloat();
    if (x < 0.25) {
      itemStack = this.randomAmount(Material.DIAMOND, 4);
    }else if (x < 0.5) {
      itemStack = this.randomAmount(Material.ENDER_PEARL, 3);
    }else if (x < 0.75) {
      itemStack = this.randomAmount(Material.IRON_INGOT, 5);
    }else {
      itemStack = this.randomAmount(Material.NETHERITE_INGOT, 3);
    }
    location.getWorld().dropItem(location, itemStack);
    int y = random.nextInt(100);
    if (y == 1) {
      location.getWorld().dropItem(location, this.enchantedBook());
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This method sets the number of items to drop.
   * @param material Requires the Item.
   * @param bound Requires the highest number of items.
   * @return itemStack
   */
  @NotNull
  private ItemStack randomAmount(Material material, int bound){
    ItemStack itemStack = new ItemStack(material);
    Random random = new Random();
    int x = random.nextInt(bound);
    itemStack.setAmount(x);
    return itemStack;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This method create the enchanted book.
   * @return itemStack
   */
  @NotNull
  private ItemStack enchantedBook(){
    ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
    EnchantmentStorageMeta meta = (EnchantmentStorageMeta) itemStack.getItemMeta();
    meta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
    itemStack.setItemMeta(meta);
    return itemStack;
  }

}
