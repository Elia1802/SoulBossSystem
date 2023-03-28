package de.elia.soulboss.plugin.load.stop.disable;

import de.elia.CustomMessages;
import de.elia.soulboss.plugin.load.start.register.configuation.ConfigurationLoader;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
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
    log.infoLog("Stop SoulBoss...");
    log.infoLog("Save all Configurations...");
    this.save(plugin);
    log.infoLog("Configuration saved");
    log.infoLog("Remove Bosses...");
    Bukkit.getServer().getWorld("world").getEntities().forEach((entity) ->{
      if (entity.getPersistentDataContainer().has(new NamespacedKey(plugin, "680035753"))) {
        entity.remove();
      }
    });
    Bukkit.getServer().getWorld("world_nether").getEntities().forEach((entity) ->{
      if (entity.getPersistentDataContainer().has(new NamespacedKey(plugin, "680035753"))) {
        entity.remove();
      }
    });
    Bukkit.getServer().getWorld("world_the_end").getEntities().forEach((entity) ->{
      if (entity.getPersistentDataContainer().has(new NamespacedKey(plugin, "680035753"))) {
        entity.remove();
      }
    });
    log.infoLog("Bosses removed!");
    log.infoLog("Remove Recipes...");
    Bukkit.removeRecipe(new NamespacedKey(plugin, "v4j23hdu-df4e-36ta-r4sf-2n0c3n8cky9x"));
    log.infoLog("Recipes Removed!");
    log.infoLog("SoulBoss stopped!");
  }
}
