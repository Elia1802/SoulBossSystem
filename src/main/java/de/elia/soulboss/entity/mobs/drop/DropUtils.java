package de.elia.soulboss.entity.mobs.drop;

import de.elia.api.thezepserapi.Complex;
import de.elia.items.Item;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author Elia
 * @description Thats are the drops of the zombie boss.
 */
public class DropUtils {

  public static void drop(@NotNull Location location) {
    Random random = new Random();
    float x = random.nextFloat();
    ItemStack itemStack = (double)x < 0.25 ? DropUtils.randomAmount(Material.DIAMOND, 4) : ((double)x < 0.5 ? DropUtils.randomAmount(Material.ENDER_PEARL, 3) : ((double)x < 0.75 ? DropUtils.randomAmount(Material.IRON_INGOT, 5) : DropUtils.randomAmount(Material.NETHERITE_INGOT, 3)));
    location.getWorld().dropItem(location, itemStack);
    int y = random.nextInt(100);
    int z = random.nextInt(1000000);
    if (y == 1) {
      location.getWorld().dropItem(location, DropUtils.enchantedBook());
    }else if (z == 1) {
      Item.drop(location, Complex.MAGIC_BOOK, 1);
    }
  }

  @NotNull
  private static ItemStack randomAmount(@NotNull Material material, int bound) {
    ItemStack itemStack = new ItemStack(material);
    Random random = new Random();
    int x = random.nextInt(bound);
    itemStack.setAmount(x);
    return itemStack;
  }

  @NotNull
  private static ItemStack enchantedBook() {
    ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
    EnchantmentStorageMeta meta = (EnchantmentStorageMeta)itemStack.getItemMeta();
    meta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
    itemStack.setItemMeta((ItemMeta)meta);
    return itemStack;
  }
}
