package de.elia.systemclasses.keys;

import de.elia.PluginMain;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public enum PluginKeys {
  ZOMBIE_KEY(new NamespacedKey(PluginMain.main(), "680035753")),
  RECIPE_KEY(new NamespacedKey(PluginMain.main(), "v4j23hdu-df4e-36ta-r4sf-2n0c3n8cky9x"));
  private final NamespacedKey key;

  private PluginKeys(NamespacedKey key) {
    this.key = key;
  }

  public NamespacedKey key() {
    return this.key;
  }
}
