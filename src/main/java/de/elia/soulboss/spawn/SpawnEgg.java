package de.elia.soulboss.spawn;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the SpawnEgg class for the Boss
 */
public class SpawnEgg {
  private final Plugin plugin;
  private ShapedRecipe bossSpawnEggRecipe;
  private final ItemStack bossSpawnEgg = new ItemStack(Material.ZOMBIE_SPAWN_EGG);

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the constructor for this class.
   * @param plugin Requires the Main class of the Plugin
   */
  public SpawnEgg(Plugin plugin){
    this.plugin = plugin;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This Methode create the eggRecipe
   */
  public void eggRecipe(){
    ItemMeta itemMeta = bossSpawnEgg.getItemMeta();
    List<Component> lore = new ArrayList<>();
    itemMeta.displayName(Component.text("<dark_purple>Soul Boss</dark_purple> <dark_gray>Spawn Egg</dark_gray>"));
    lore.add(Component.text(""));
    lore.add(Component.text("<dark_gray>With this egg you can spawn the SoulBoss</dark_gray>"));
    lore.add(Component.text(""));
    itemMeta.lore(lore);
    itemMeta.setUnbreakable(true);
    itemMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, false);
    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    NamespacedKey uuid = new NamespacedKey(plugin, "v4j23hdu-df4e-36ta-r4sf-2n0c3n8cky9x");
    ShapedRecipe recipe = new ShapedRecipe(uuid, bossSpawnEgg);
    bossSpawnEgg.setItemMeta(itemMeta);
    recipe.shape("ZZZ", "WSW", "CSC");
    recipe.setIngredient('Z', Material.ZOMBIE_HEAD);
    recipe.setIngredient('W', Material.GREEN_WOOL);
    recipe.setIngredient('S', Material.SOUL_SAND);
    recipe.setIngredient('C', Material.CYAN_CONCRETE);
    bossSpawnEggRecipe = recipe;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This Methode register the EggRecipe
   */
  public void registerEggRecipe(){
    try {
      this.eggRecipe();
      plugin.getServer().addRecipe(this.bossSpawnEggRecipe());
    }catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This Methode gets the Egg
   */
  @NotNull
  public ItemStack bossSpawnEgg() {
    return bossSpawnEgg;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This Methode gets the Egg recipe
   */
  @NotNull
  public ShapedRecipe bossSpawnEggRecipe() {
    return bossSpawnEggRecipe;
  }
}
