package de.elia;

import org.bukkit.NamespacedKey;

public enum Keys {

  ZOMBIE_KEY(new NamespacedKey(Main.main(), "680035753")),
  RECIPE_KEY(new NamespacedKey(Main.main(), "v4j23hdu-df4e-36ta-r4sf-2n0c3n8cky9x"));

  private final NamespacedKey key;

  Keys(NamespacedKey key){
    this.key = key;
  }

  public NamespacedKey key(){
    return this.key;
  }

}
