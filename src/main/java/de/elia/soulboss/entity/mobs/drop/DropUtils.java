package de.elia.soulboss.entity.mobs.drop;

import de.elia.api.thezepserapi.Complex;
import de.elia.items.Item;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class DropUtils {
  public static void drop(@NotNull Location location) {
    Random random = new Random();
    double randomNumber = random.nextDouble(100.0);
    double roundedValue = roundToDecimalPlaces(randomNumber, 1);
    if (randomNumber <= 0.5D) {
      Item.drop(location, Complex.MAGIC_BOOK, 1);
    }else if (randomNumber > 0.5D) {
      if (randomNumber <= 8.0D) {
        location.getWorld().dropItem(location, enchantedBook());
      }else if (randomNumber > 8.0) {
        if (randomNumber <= 20.0) {
          int amount = random.nextInt(4, 11);
          location.getWorld().dropItem(location, new ItemStack(Material.NETHERITE_BLOCK, amount));
        }else if (randomNumber > 20.0) {
          if (randomNumber <= 40.0) {
            int amount = random.nextInt(4, 11);
            location.getWorld().dropItem(location, new ItemStack(Material.DIAMOND_BLOCK, amount));
          }else if (randomNumber > 40.0) {
            if (randomNumber <= 70.0) {
              int amount = random.nextInt(4, 11);
              location.getWorld().dropItem(location, new ItemStack(Material.NETHERITE_INGOT, amount));
            }else if (randomNumber > 70.0) {
              if (randomNumber <= 100.0) {
                int amount = random.nextInt(4, 11);
                location.getWorld().dropItem(location, new ItemStack(Material.DIAMOND, amount));
              }
            }
          }
        }
      }
    }
  }

  private static double roundToDecimalPlaces(double value, int decimalPlaces) {
    double scale = Math.pow(10, decimalPlaces);
    return Math.round(value * scale) / scale;
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
    Random random = new Random();
    int book = random.nextInt(4);
    if (book == 1) {
      ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
      EnchantmentStorageMeta meta = (EnchantmentStorageMeta)itemStack.getItemMeta();
      meta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
      itemStack.setItemMeta(meta);
      return itemStack;
    }else if (book == 2){
      ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
      EnchantmentStorageMeta meta = (EnchantmentStorageMeta)itemStack.getItemMeta();
      meta.addStoredEnchant(Enchantment.MENDING, 1, true);
      itemStack.setItemMeta(meta);
      return itemStack;
    }else {
      ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
      EnchantmentStorageMeta meta = (EnchantmentStorageMeta)itemStack.getItemMeta();
      meta.addStoredEnchant(Enchantment.LUCK, 5, true);
      itemStack.setItemMeta(meta);
      return itemStack;
    }
  }
}
