package de.elia.bossfightcreator;

import de.elia.PluginMain;
import de.elia.PluginLogger;
import de.elia.bossfightcreator.builder.save.SaveGame;
import de.elia.bossfightcreator.load.start.StartPlugin;
import de.elia.bossfightcreator.load.stop.StopPlugin;
import de.elia.bossfightcreator.world.WorldMain;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
public class BossFightCreator {

  private static BossFightCreator bossFightCreator;
  private static WorldMain worldMain;
  private final MiniMessage miniMessage = MiniMessage.miniMessage();//Gets the MiniMessage API.
  private static final Map<Player, Integer> playerStatusMap = new HashMap<>();//Save the status of the player for the bossfight.

  public BossFightCreator(){
    //...
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load the Plugin
   * @param plugin Requires the main Main class
   */
  public void enable(JavaPlugin plugin){
    bossFightCreator = this;
    worldMain = new WorldMain(plugin);
    StartPlugin.start(plugin);
    SaveGame.loadMap();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Unload the Plugin
   */
  public void disable(){
    StopPlugin.stop();
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
   * @description Gets the MiniMessage
   * @return {@link MiniMessage}
   */
  @NotNull
  public MiniMessage miniMessage() {
    return this.miniMessage;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description A instance of this Main
   * @return {@link BossFightCreator}
   */
  public static BossFightCreator bossFightCreator() {
    return bossFightCreator;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description A instance of the main Main class
   * @return {@link PluginMain}
   */
  @NotNull
  public static PluginMain main(){
    return PluginMain.main();
  }

  @NotNull
  public PluginLogger bossFightCreatorLogger(){
    return PluginMain.BOSS_FIGHT_CREATOR_LOGGER;
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
}
