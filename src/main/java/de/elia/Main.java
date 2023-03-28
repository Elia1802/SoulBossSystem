package de.elia;

import de.elia.bossfightcreator.BossFightCreator;
import de.elia.bossfightcreator.Constants.Files;
import de.elia.bossfightcreator.Constants.Plugin;
import de.elia.bossfightcreator.world.WorldMain;
import de.elia.bossfightcreator.world.creator.CustomChunkGenerator;
import de.elia.soulboss.SoulBoss;
import de.elia.soulmain.SoulMain;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.Exception;
import java.lang.String;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @extends {@link JavaPlugin}
 * @description This is the Main Class of the Plugin SoulBoss and BossFightCreator.
 */
public class Main extends JavaPlugin {

  private static Main main;//Instance of this Plugin
  private final SoulMain soulMain = (SoulMain) Bukkit.getPluginManager().getPlugin("SoulMain");//Gets the Soul API of Elia
  private static final BossFightCreator BOSS_FIGHT_CREATOR = BossFightCreator.bossFightCreator();
  private static final SoulBoss SOUL_BOSS = SoulBoss.soulBoss();//Gets the Main class of the bossfightcreator Plugin
  private World world;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load all thinks in this Plugin.
   */
  @Override
  public void onEnable(){
    if (soulMain == null)return;
    main = this;
    try {
      BOSS_FIGHT_CREATOR.enable(this);
    }catch (Exception exception) {
      exception.printStackTrace();
    }
    try {
      SOUL_BOSS.enable(this);
    }catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Unload all thinks in this Plugin.
   */
  @Override
  public void onDisable(){
    if (soulMain == null)return;
    try {
      BOSS_FIGHT_CREATOR.disable();
    }catch (Exception exception) {
      exception.printStackTrace();
    }
    try {
      SOUL_BOSS.disable(this);
    }catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load a custom World
   * @param worldName Name of the world that this will be applied to
   * @param id Unique ID, if any, that was specified to indicate which
   *     generator was requested
   * @return null
   */
  @Override
  public @Nullable ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id){
    var log_progress = WorldMain.LOGGER_PROGRESS;
    log_progress.logInfo("0%");
    var log = WorldMain.LOGGER;  //The logger for the status (Example: )
    var world_status = Files.WORLD_STATUS;
    if (world_status.get("status") == null|| world_status.get("status", false)) {
      log_progress.logInfo("0%");
      log.logInfo("Create a new World... (" + worldName + ":" + id + ")");
      log.logInfo("Load org.bukkit.WorldGenerator...");
      WorldCreator creator = new WorldCreator(id);
      log.logInfo("org.bukkit.WorldGenerator loaded!");
      log_progress.logInfo("25%");
      log.logInfo("Load de.elia.bossfightcreator.world.creator.CustomChunkGenerator...");
      CustomChunkGenerator generator = new CustomChunkGenerator();
      log.logInfo("de.elia.bossfightcreator.world.creator.CustomChunkGenerator loaded!");
      log_progress.logInfo("50%");
      log.logInfo("Set the custom generator to the WorldGenerator...");
      creator.generator(generator);
      log.logInfo("The custom generator to the WorldGenerator sets!");
      log_progress.logInfo("75%");
      log.logInfo("Create a new org.bukkit.World...");
      Plugin.world_bossfight = Bukkit.createWorld(creator);
      log.logInfo("A new org.bukkit.World is created!");
      log.logInfo("Ending world creation progress...");
      world_status.set("status", true);
      world_status.save();
      log.logInfo("world creation progress ended!");
      log_progress.logInfo("100%");
    }else {
      log.logInfo("World " + worldName + ":" + id + " exist!");
      log_progress.logInfo("100%");
    }
    return null;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the bossfight world.
   * @return the world of this plugin
   */
  @NotNull
  public World world() {
    if (world == null) {
      this.getDefaultWorldGenerator("Bossfight-World", "world_bossfight");
      return world;
    }
    return world;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Is the Instance of this Plugin.
   * @return {@link Main}
   */
  @NotNull
  public static Main main() {
    return main;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the SoulMain Plugin.
   * @return {@link SoulMain}
   */
  @NotNull
  public SoulMain soulMain() {
    return soulMain;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Disable this Plugin.
   */
  public void disable(){
    Bukkit.getPluginManager().disablePlugin(this);
  }

}
