package de.elia.items.recipes.loader;

import de.elia.api.logging.SaveError;
import de.elia.items.ItemMain;
import de.elia.items.recipes.SpawnEggRecipe;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description This class load all recipes of the items.
 */
public class RecipeLoader {

  /**
   * @description This load the recipe of the spawn eggs
   */
  public static class SpawnEggLoader {

    public void loadRecipe(Plugin plugin) {
      this.registerEggRecipe(plugin);
    }

    private void registerEggRecipe(@NotNull Plugin plugin) {
      SpawnEggRecipe spawnEggRecipe = new SpawnEggRecipe();
      try {
        spawnEggRecipe.eggRecipe();
        plugin.getServer().addRecipe((Recipe)spawnEggRecipe.bossSpawnEggRecipe());
      }catch (CloneNotSupportedException exception) {
        new SaveError().saveError(ItemMain.itemMain().main(), exception, "RecipeLoader-registerEggRecipe-line_28=clonenotsupported");
        exception.printStackTrace();
      }
    }
  }
}
