package de.elia.bossfightcreator.arena;

import com.sk89q.worldedit.WorldEditException;

import de.elia.api.logging.error.SaveError;

import de.elia.PluginMain;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.bossfightcreator.arena.utils.SchematicBuilder;
import de.elia.bossfightcreator.game.Game;
import de.elia.systemclasses.PluginInstances;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * @author Sinan, D1p4k, Elia
 * @description This is the Arena class. This class gives for all schematics.
 * @beta Because: This is unstable!!!
 */
public class Arena {

  private final Location location;
  private final String name;
  private final String arenaID;
  private ArenaState state = ArenaState.UNINITIALIZED;
  private Game game;

  /**
   * @description This Constructer create a new Arena
   * @param name Requires a name for the arena.
   * @param location Requires a {@link Location} of the arena
   * @param arenaID Requires a id for the arena.
   */
  public Arena(String name, Location location, String arenaID) {
    this.name = name;
    this.location = location;
    this.arenaID = arenaID;
    this.buildArena();
  }

  /**
   * @author Elia
   * @description This gets the spawn location for the arena.
   * @return Return a {@link Location} if the arena id the same as a exist id, else null.
   */
  public Location getSpawnLocation() {
    switch (this.arenaID) {
      case "arena_1.0" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), -102.0, 68.0, 176.0);
      }
      case "arena_1.1" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 98.0, 68.0, 176.0);
      }
      case "arena_1.2" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 298.0, 68.0, 176.0);
      }
      case "arena_1.3" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 498.0, 68.0, 176.0);
      }
      case "arena_1.4" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 698.0, 68.0, 176.0);
      }
      case "arena_1.5" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 898.0, 68.0, 176.0);
      }
      case "arena_1.6" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1098.0, 68.0, 176.0);
      }
      case "arena_1.7" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1298.0, 68.0, 176.0);
      }
      case "arena_1.8" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1498.0, 68.0, 176.0);
      }
      case "arena_1.9" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1698.0, 68.0, 176.0);
      }
      case "arena_2.0" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1830, 94, 235);
      }
      case "arena_2.1" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1630, 94, 235);
      }
      case "arena_2.2" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1430, 94, 235);
      }
      case "arena_2.3" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1230, 94, 235);
      }
      case "arena_2.4" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 1030, 94, 235);
      }
      case "arena_2.5" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 830, 94, 235);
      }
      case "arena_2.6" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 630, 94, 235);
      }
      case "arena_2.7" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 430, 94, 235);
      }
      case "arena_2.8" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 230, 94, 235);
      }
      case "arena_2.9" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 30, 94, 235);
      }
    }
    return null;
  }

  /**
   * @deprecated Use {@link de.elia.party.Party#addPlayer(Player)}
   * @description This methode add a {@link Player} to the {@link Game} of the arena.
   * @param player Requires a player.
   */
  @Deprecated
  public void addPlayer(@NotNull Player player) {
    BossFightCreatorMain.playerStatusMap().replace(player, 1);
    this.game.party.members().add(player);
  }

  /**
   * @description This methode build the arena.
   */
  private void buildArena() {
    PluginInstances.BOSS_FIGHT_CREATOR_LOGGER.logInfo(this.name + " is being build at " + this.location + "!");
    try {
      this.state = ArenaState.LOADING;
      PluginInstances.BOSS_FIGHT_CREATOR_LOGGER.logInfo("Paste the schematic of the arena");
      SchematicBuilder.pasteSchematic(this.location, SchematicBuilder.schematic(ArenaHandler.FILE_PATH, this.name));
      File arenaFile = new File(ArenaHandler.FILE_PATH, this.name + ".schem");
      PluginInstances.BOSS_FIGHT_CREATOR_LOGGER.logInfo(arenaFile + "  !  " + arenaFile.exists());
      this.state = ArenaState.FREE;
      PluginInstances.BOSS_FIGHT_CREATOR_LOGGER.logInfo("The arena " + this.arenaID + " is " + this.state);
    } catch (WorldEditException exception) {
      new SaveError().saveError(PluginMain.main(), exception, "Arena-buildArena-line_139=worldedit");
      exception.printStackTrace();
      this.state = ArenaState.ERROR_BUILDING;
    }
    PluginInstances.BOSS_FIGHT_CREATOR_LOGGER.logInfo(this.name + " was build at " + this.location + "! And now the State is " + this.state);
  }

  /**
   * @description This methode rebuild the arena.
   * @param arena Requires the arena.
   */
  public void reBuildArena(@NotNull Arena arena) {
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo(arena.getName() + " is being build at " + arena.getLocation() + "!");
    try {
      arena.setState(ArenaState.LOADING);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Paste the schematic of the arena");
      SchematicBuilder.pasteSchematic(arena.getLocation(), SchematicBuilder.schematic(ArenaHandler.FILE_PATH, arena.getName()));
      arena.setState(ArenaState.USED);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("The arena " + arena.getArenaID() + " is " + arena.getState());
    } catch (WorldEditException exception) {
      new SaveError().saveError(PluginMain.main(), exception, "Arena-buildArena-line_131=worldedit");
      exception.printStackTrace();
      this.state = ArenaState.ERROR_BUILDING;
    }
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo(arena.getName() + " was build at " + arena.getLocation() + "! And now the State is " + arena.getState());
  }

  /**
   * @description Gets the location of the Arena.
   * @return Return the {@link Location} where the arena is generated
   */
  public Location getLocation() {
    return this.location;
  }

  /**
   * @description Gets the name of the Arena.
   * @return Return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @description Gets the arena id of the Arena.
   * @return Return the arena id
   */
  public String getArenaID() {
    return this.arenaID;
  }

  /**
   * @description Gets the game of the Arena.
   * @return Return the game
   */
  public Game getGame() {
    return this.game;
  }


  /**
   * @description Gets the state of the Arena.
   * @return Return the state
   */
  public ArenaState getState() {
    return this.state;
  }

  /**
   * @description This set the game to the arena.
   * @param game Requires the game.
   */
  public void setGame(@NotNull Game game) {
    this.game = game;
  }

  /**
   * @description This set the state of the arena.
   * @param state Requires the state
   */
  public void setState(@NotNull ArenaState state) {
    this.state = state;
  }

}
