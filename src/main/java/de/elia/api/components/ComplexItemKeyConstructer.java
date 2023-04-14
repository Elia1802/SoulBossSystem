package de.elia.api.components;

import de.elia.PluginMain;
import de.elia.api.Complex;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ComplexItemKeyConstructer {
  public String getKey(ItemStack itemStack) {
    ItemMeta meta = itemStack.getItemMeta();
    return meta.getPersistentDataContainer().getKeys().toString();
  }
  public boolean hasKey(ItemStack itemStack, String key) {
    ItemMeta meta = itemStack.getItemMeta();
    if (meta != null) {
      if (meta.getPersistentDataContainer().has(new NamespacedKey(PluginMain.main(), key))) {
        return true;
      }
      else {
        return false;
      }
    }
    return false;
  }
  public String createKey(Complex item) {
    return String.valueOf(item);
  }
}
