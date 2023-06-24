package de.elia.bossfightcreator.arena;

import com.sk89q.worldedit.WorldEditException;
import de.elia.PluginMain;
import de.elia.api.annotation.AnnotationChecker;
import de.elia.api.annotation.Beta;
import de.elia.api.logging.SaveError;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.bossfightcreator.arena.utils.SchematicBuilder;
import de.elia.bossfightcreator.game.Game;
import de.elia.systemclasses.PluginInstances;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * @author Elia, D1p4k and Sinan
 * @description This class create a Arena for the {@link Game}s.
 * @beta There may still be errors when loading the arenas
 */
@Beta
public class Arena {

  private final Location location;
  private final String schematicName;
  private final String arenaID;
  private ArenaState state = ArenaState.UNINITIALIZED;
  private Game game;

  /**
   * @description Create a new Arena.
   * @param schematicName Requires a schematic name.
   * @param location Requires a {@link Location}.
   * @param arenaID Requires a id.
   */
  public Arena(String schematicName, Location location, String arenaID) {
    this.schematicName = schematicName;
    this.location = location;
    this.arenaID = arenaID;
    this.buildArena();
    //Start Elia
    AnnotationChecker.processAnnotations(Arena.class);
    //End Elia
  }

  /**
   * @author Elia
   * @description Gets the spawn location of the arena.
   * @return The {@link Location} of the Arena or if the id none exist null.
   */
  public Location getSpawnLocation() {
    switch (this.arenaID) {
      case "arena_2.0" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), -16.0, 68.0, -28.0);
      }
      case "arena_2.1" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 184.0, 68.0, -28.0);
      }
      case "arena_2.2" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 384.0, 68.0, -28.0);
      }
      case "arena_2.3" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 584.0, 68.0, -28.0);
      }
      case "arena_2.4" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 784.0, 68.0, -28.0);
      }
      case "arena_2.5" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 984.0, 68.0, -28.0);
      }
      case "arena_2.6" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1184.0, 68.0, -28.0);
      }
      case "arena_2.7" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1384.0, 68.0, -28.0);
      }
      case "arena_2.8" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1584.0, 68.0, -28.0);
      }
      case "arena_2.9" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1784.0, 68.0, -28.0);
      }
      case "arena_1.0" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 117.0, 21.0, 259.0);
      }
      case "arena_1.1" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 317.0, 21.0, 259.0);
      }
      case "arena_1.2" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 517.0, 21.0, 259.0);
      }
      case "arena_1.3" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 158.0, 21.0, 259.0);
      }
      case "arena_1.4" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 208.0, 21.0, 259.0);
      }
      case "arena_1.5" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 717.0, 21.0, 259.0);
      }
      case "arena_1.6" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 917.0, 21.0, 259.0);
      }
      case "arena_1.7" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1117.0, 21.0, 259.0);
      }
      case "arena_1.8" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1317.0, 21.0, 259.0);
      }
      case "arena_1.9" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1527.0, 21.0, 259.0);
      }
    }
    return null;
  }

  /**
   * @description add a Player to the {@link Game}.
   * @param player Requires a {@link Player}
   */
  public void addPlayer(@NotNull Player player) {
    //Start Elia
    BossFightCreatorMain.playerStatusMap().replace(player, 1);
    //End Elia
    this.game.addPlayer(player);
  }

  /**
   * @author Elia, D1p4k and Sinan
   * @description This Methode build a arena if a new arena created.
   */
  private void buildArena() {
    PluginInstances.BOSS_FIGHT_CREATOR_LOGGER.logInfo(this.schematicName + " is being build at " + this.location + "!");
    try {
      this.state = ArenaState.LOADING;
      PluginInstances.BOSS_FIGHT_CREATOR_LOGGER.logInfo("Paste the schematic of the arena");
      SchematicBuilder.pasteSchematic(this.location, SchematicBuilder.schematic(ArenaHandler.FILE_PATH, this.schematicName));
      File arenaFile = new File(ArenaHandler.FILE_PATH, this.schematicName + ".schem");
      PluginInstances.BOSS_FIGHT_CREATOR_LOGGER.logInfo(arenaFile + "  !  " + arenaFile.exists());
      this.state = ArenaState.FREE;
      PluginInstances.BOSS_FIGHT_CREATOR_LOGGER.logInfo("The arena " + this.arenaID + " is " + this.state);
    }catch (WorldEditException exception) {
      new SaveError().saveError((JavaPlugin)PluginMain.main(), (Exception)((Object)exception), "Arena-buildArena-line_108=worldedit");
      exception.printStackTrace();
      this.state = ArenaState.ERROR_BUILDING;
    }
    PluginInstances.BOSS_FIGHT_CREATOR_LOGGER.logInfo(this.schematicName + " was build at " + this.location + "! And now the State is " + this.state);
  }

  /**
   * @author Elia
   * @description Rebuild a arena.
   * @param arena Requires the arena to be reloaded..
   */
  public void reBuildArena(@NotNull Arena arena) {
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo(arena.getSchematicName() + " is being build at " + arena.getLocation() + "!");
    try {
      arena.setState(ArenaState.LOADING);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Paste the schematic of the arena");
      SchematicBuilder.pasteSchematic(arena.getLocation(), SchematicBuilder.schematic(ArenaHandler.FILE_PATH, arena.getSchematicName()));
      File arenaFile = new File(ArenaHandler.FILE_PATH, arena.getSchematicName() + ".schem");
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo(arenaFile + "  !  " + arenaFile.exists());
      arena.setState(ArenaState.USED);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("The arena " + arena.getArenaID() + " is " + arena.getState());
    } catch (WorldEditException exception) {
      new SaveError().saveError((JavaPlugin)PluginMain.main(), (Exception)((Object)exception), "Arena-buildArena-line_131=worldedit");
      exception.printStackTrace();
      this.state = ArenaState.ERROR_BUILDING;
    }
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo(arena.getSchematicName() + " was build at " + arena.getLocation() + "! And now the State is " + arena.getState());
  }

  /**
   * @description Gets the {@link Location} of the Arena.
   * @return a {@link Location}
   */
  public Location getLocation() {
    return this.location;
  }

  /**
   * @description Gets the schematic name of the Arena.
   * @return the name of the schematic
   */
  public String getSchematicName() {
    return this.schematicName;
  }

  /**
   * @description Gets the id of the Arena.
   * @return the id of the arena
   */
  public String getArenaID() {
    return this.arenaID;
  }

  /**
   * @description Gets the {@link Game} of the Arena.
   * @return a {@link Game}
   */
  public Game getGame() {
    return this.game;
  }

  /**
   * @description Set a Game to the arena.
   * @param game Requires a {@link Game}
   */
  public void setGame(@NotNull Game game) {
    this.game = game;
  }

  /**
   * @description Gets the state of the Arena.
   * @return a {@link ArenaState}
   */
  public ArenaState getState() {
    return this.state;
  }

  /**
   * @description Set a state of the Arena.
   * @param state Requires a state for the Arena
   */
  public void setState(@NotNull ArenaState state) {
    this.state = state;
  }
}

