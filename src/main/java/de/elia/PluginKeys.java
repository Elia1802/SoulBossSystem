package de.elia;

import org.bukkit.NamespacedKey;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Save all keys for the bosses and recipes
 */
public enum PluginKeys {

  ZOMBIE_KEY(new NamespacedKey(PluginMain.main(), "680035753")),
  RECIPE_KEY(new NamespacedKey(PluginMain.main(), "v4j23hdu-df4e-36ta-r4sf-2n0c3n8cky9x"));

  private final NamespacedKey key;

  PluginKeys(NamespacedKey key){
    this.key = key;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the Key
   * @return {@link NamespacedKey}
   */
  public NamespacedKey key(){
    return this.key;
  }

}