package de.elia.soulboss.plugin.load.stop.disable;

import de.elia.CustomMessages;
import de.elia.Keys;
import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.plugin.load.start.register.configuation.ConfigurationLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Disable Class to stop and save every thinks.
 */
public class Disable extends ConfigurationLoader {

  private final CustomMessages log = new CustomMessages(); //Gets the Message class of SoulMain

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Disable and save all thinks
   */
  public void disable(Plugin plugin){
    var log = SoulBoss.soulBoss().soulBossLogger();
    log.logInfo("Stop SoulBoss...");
    log.logInfo("Save all Configurations...");
    this.save(plugin);
    log.logInfo("Configuration saved");
    log.logInfo("Remove Bosses...");
    Bukkit.getServer().getWorld("world").getEntities().forEach((entity) ->{
      if (entity.getPersistentDataContainer().has(Keys.ZOMBIE_KEY.key())) {
        entity.remove();
      }
    });
    Bukkit.getServer().getWorld("world_nether").getEntities().forEach((entity) ->{
      if (entity.getPersistentDataContainer().has(Keys.ZOMBIE_KEY.key())) {
        entity.remove();
      }
    });
    Bukkit.getServer().getWorld("world_the_end").getEntities().forEach((entity) ->{
      if (entity.getPersistentDataContainer().has(Keys.ZOMBIE_KEY.key())) {
        entity.remove();
      }
    });
    log.logInfo("Bosses removed!");
    log.logInfo("Remove Recipes...");
    Bukkit.removeRecipe(Keys.RECIPE_KEY.key());
    log.logInfo("Recipes Removed!");
    log.logInfo("SoulBoss stopped!");
  }
}
