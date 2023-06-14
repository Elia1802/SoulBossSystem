package de.elia.items.recipes.loader;

import de.elia.items.ItemMain;
import de.elia.items.recipes.SpawnEggRecipe;

import org.bukkit.plugin.Plugin;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description This is the Register class to register all Recipes.
 */
public class RecipeLoader {

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This is the Register class to register all the Spawn Egg Recipe.
   */
  public static class SpawnEggLoader {

    /**
     * @author Elia
     * @version 1.0.0.pre1
     * @since 1.0.0.pre1
     * @description This is the load methode to load all Recipes.
     */
    public void loadRecipe(Plugin plugin){
      this.registerEggRecipe(plugin);
    }

    /**
     * @author Elia
     * @version 1.0.0.pre1
     * @since 1.0.0.pre1
     * @description This Methode register the EggRecipe
     */
    private void registerEggRecipe(Plugin plugin){
      SpawnEggRecipe spawnEggRecipe = new SpawnEggRecipe();
      try {
        spawnEggRecipe.eggRecipe();
        plugin.getServer().addRecipe(spawnEggRecipe.bossSpawnEggRecipe());
      }catch (Exception exception) {
        ItemMain.itemMain().itemLogger().stacktrace(exception);
      }
    }
  }

}
