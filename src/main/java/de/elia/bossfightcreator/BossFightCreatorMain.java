package de.elia.bossfightcreator;

import de.elia.PluginInstances;
import de.elia.PluginMain;
import de.elia.api.configuration.SoulConfiguration;
import de.elia.api.logging.PluginLogger;
import de.elia.bossfightcreator.world.WorldMain;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description The Main class of the BossFightCreator
 */
public class BossFightCreatorMain {

  private static BossFightCreatorMain bossFightCreatorMain;
  private static WorldMain worldMain;
  private static final Map<Player, Integer> playerStatusMap = new HashMap<>();//Save the status of the player for the bossfight.

  public BossFightCreatorMain(){
    //...
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load the Plugin
   * @param main Requires the main Main class
   */
  public void enable(@NotNull JavaPlugin main){
    this.bossFightCreatorLogger().logInfo("Load BossFightCreator plugin...");
    bossFightCreatorMain = this;
    this.bossFightCreatorLogger().logInfo("Load WorldMain...");
    worldMain = new WorldMain(main);
    this.bossFightCreatorLogger().logInfo("WorldMain loaded!");

    this.bossFightCreatorLogger().logInfo("BossFightCreator plugin loaded!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Unload the Plugin
   */
  public void disable(){
    this.bossFightCreatorLogger().logInfo("Disable BossFightCreator...");
    this.bossFightCreatorLogger().logInfo("BossFightCreator disabled!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the status Map. ({@link Map<Player, Integer>})
   * @return a new {@link Map<Player, Integer>}
   */
  @NotNull
  public static Map<Player, Integer> playerStatusMap() {
    return playerStatusMap;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description A instance of this Main
   * @return {@link BossFightCreatorMain}
   */
  public static BossFightCreatorMain bossFightCreator() {
    return bossFightCreatorMain;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description A instance of the main Main class
   * @return {@link PluginMain}
   */
  @NotNull
  public PluginMain main(){
    return PluginMain.main();
  }

  @NotNull
  public PluginLogger bossFightCreatorLogger(){
    return PluginInstances.BOSS_FIGHT_CREATOR_LOGGER;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the {@link WorldMain}
   * @return {@link WorldMain}
   */
  public static WorldMain worldMain() {
    return worldMain;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description A class to crate a bossfight file
   */
  public static class CreateBossfightFile {

    private final SoulConfiguration bossfightFile;

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Create a bossfightfile
     * @param fileName Requires the name of the file
     */
    public CreateBossfightFile(String fileName){
      bossfightFile = new SoulConfiguration(bossFightCreator().main(), "bossfights/", fileName + ".yml");
      bossfightFile.copyDefaults(true);
      bossfightFile.save();
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Gets the File
     * @return {@link SoulConfiguration}
     */
    @NotNull
    public SoulConfiguration getFile() {
      return this.bossfightFile;
    }
  }
}
