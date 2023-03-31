package de.elia.soulboss.spawn;

import de.elia.Keys;
import de.elia.api.Complex;
import de.elia.items.Item;
import de.elia.soulboss.SoulBoss;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the SpawnEgg class for the Boss
 */
public class SpawnEgg {
  
  private final Plugin plugin;
  private ShapedRecipe bossSpawnEggRecipe;
  public static Component lore = SoulBoss.soulBoss().miniMessage().deserialize("<dark_gray>With this egg you can spawn the SoulBoss</dark_gray>");

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the constructor for this class.
   * @param plugin Requires the Main class of the Plugin
   */
  public SpawnEgg(Plugin plugin){
    this.plugin = plugin;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the methode for the spawnegg of the Zombie
   * @return itemStack
   */
  @Deprecated //A new methode to create items
  public ItemStack spawnEgg(){
    Material material = Material.ZOMBIE_HEAD;
    ItemStack itemStack = new ItemStack(material);
    ItemMeta itemMeta = itemStack.getItemMeta();
    ArrayList<Component> lores = new ArrayList<>();
    MiniMessage miniMessage = SoulBoss.soulBoss().miniMessage();
    Component name = miniMessage.deserialize("<dark_purple>Soul Boss</dark_purple> <dark_gray>Spawn Egg</dark_gray>");
    itemMeta.displayName(name);
    lores.add(lore);
    itemMeta.lore(lores);
    itemMeta.setUnbreakable(true);
    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This Methode create the eggRecipe
   */
  public void eggRecipe(){
    NamespacedKey uuid = Keys.RECIPE_KEY.key();
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
   * @version 1.0
   * @since 1.0
   * @description This Methode gets the Egg recipe
   */
  @NotNull
  public ShapedRecipe bossSpawnEggRecipe() {
    return bossSpawnEggRecipe;
  }
}
