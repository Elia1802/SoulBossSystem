package de.elia.items.recipes;

import de.elia.api.thezepserapi.Complex;

import de.elia.items.Item;
import de.elia.systemclasses.keys.PluginKeys;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description  This class create all recipes.
 */
public class SpawnEggRecipe {

  private ShapedRecipe bossSpawnEggRecipe;

  /**
   * @description This methode create the zombie spawn egg recipe.
   * @throws CloneNotSupportedException if the item cant cloned.
   */
  public void eggRecipe() throws CloneNotSupportedException {
    ShapedRecipe recipe = new ShapedRecipe(PluginKeys.RECIPE_KEY.key(), Item.get(Complex.ZOMBIE_SPAWN_EGG).getItem());
    recipe.shape("ZZZ", "WSW", "CSC");
    recipe.setIngredient('Z', Material.ZOMBIE_HEAD);
    recipe.setIngredient('W', Material.GREEN_WOOL);
    recipe.setIngredient('S', Material.SOUL_SAND);
    recipe.setIngredient('C', Material.CYAN_CONCRETE);
    this.bossSpawnEggRecipe = recipe;
  }

  @NotNull
  public ShapedRecipe bossSpawnEggRecipe() {
    return this.bossSpawnEggRecipe;
  }
}
