package de.elia.items.recipes;

import de.elia.api.thezepserapi.Complex;
import de.elia.systemclasses.keys.PluginKeys;
import de.elia.items.Item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description This is the SpawnEgg class for the Boss
 */
public class SpawnEggRecipe {

  private ShapedRecipe bossSpawnEggRecipe;

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This Methode create the eggRecipe
   */
  public void eggRecipe() throws CloneNotSupportedException {
    NamespacedKey uuid = PluginKeys.RECIPE_KEY.key();
    ShapedRecipe recipe = new ShapedRecipe(uuid, Item.get(Complex.ZOMBIE_SPAWN_EGG).getItem());
    recipe.shape("ZZZ", "WSW", "CSC");
    recipe.setIngredient('Z', Material.ZOMBIE_HEAD);
    recipe.setIngredient('W', Material.GREEN_WOOL);
    recipe.setIngredient('S', Material.SOUL_SAND);
    recipe.setIngredient('C', Material.CYAN_CONCRETE);
    bossSpawnEggRecipe = recipe;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This Methode gets the Egg recipe
   */
  @NotNull
  public ShapedRecipe bossSpawnEggRecipe() {
    return bossSpawnEggRecipe;
  }
}
