package de.elia.soulboss.plugin.load.start.register.recipes;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.spawn.SpawnEgg;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException.CheckVariable;
import org.bukkit.plugin.Plugin;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Register class to register all Recipes.
 */
public class RecipeLoader {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Register class to register all the Spawn Egg Recipe.
   */
  public static class SpawnEggLoader {

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description This is the load methode to load all Recipes.
     */
    public void loadRecipe(Plugin plugin) throws SoulBossSystemNullException {
      if (!new CheckVariable().check(plugin, "RecipeLoader#loadRecipe(Plugin)"))return;
      this.registerEggRecipe(plugin);
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description This Methode register the EggRecipe
     */
    private void registerEggRecipe(Plugin plugin) throws SoulBossSystemNullException {
      if (!new CheckVariable().check(plugin, "RecipeLoader#registerEggRecipe(Plugin)"))return;
      SpawnEgg spawnEgg = new SpawnEgg(plugin);
      try {
        spawnEgg.eggRecipe();
        plugin.getServer().addRecipe(spawnEgg.bossSpawnEggRecipe());
      }catch (Exception exception) {
        SoulBoss.soulBoss().soulBossLogger().stackstrace(exception);
      }
    }
  }

}
