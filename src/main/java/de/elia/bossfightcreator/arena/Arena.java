package de.elia.bossfightcreator.arena;

import com.sk89q.worldedit.WorldEditException;

import de.elia.PluginMain;
import de.elia.api.logging.PluginLogger;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.bossfightcreator.arena.utils.SchematicBuilder;
import de.elia.bossfightcreator.game.Game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class Arena {
  private final Location location;// location in arena world
  private final String schematicName;// schematic file name (e.g. fire_arena, ice_arena, etc...)
  private final String arenaID;
  private ArenaState state = ArenaState.UNINITIALIZED;// state of arena in map
  private Game game;// corresponding game instance


  public Arena(String schematicName, Location location, String arenaID) {
    this.schematicName = schematicName;
    this.location = location;
    this.arenaID = arenaID;
    buildArena();
  }

  public Location getSpawnLocation(){
    switch (arenaID) {
      case "arena_1.0" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 11D, 96D, -11D);
      }
      case "arena_1.1" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 61D, 96D, -11D);
      }
      case "arena_1.2" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 111D, 96D, -11D);
      }
      case "arena_2.0" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 8D, 93D, 42D);
      }
      case "arena_2.1" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 58D, 93D, 42D);
      }
      case "arena_2.2" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 108D, 96D, 42D);
      }
      case "arena_3.0" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 111D, 94D, 110D);
      }
      case "arena_3.1" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 61D, 94D, 110D);
      }
      case "arena_3.2" -> {
        return new Location(Bukkit.getWorld("world_bossfight"), 11D, 94D, 110D);
      }
    }
    return null;
  }

  public void addPlayer(@NotNull Player player) {
    BossFightCreatorMain.playerStatusMap().replace(player, 1);
    game.addPlayer(player);
  }

  public void buildArena() {  // also use for rebuilding
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo(schematicName + " is being build at " + location + "!");
    try {
      state = ArenaState.LOADING;
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Paste the schematic of the arena");
      SchematicBuilder.pasteSchematic(location, SchematicBuilder.getSchematic(ArenaHandler.FILE_PATH, schematicName));
      File arenaFile = new File(ArenaHandler.FILE_PATH, schematicName + ".schem");
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo(arenaFile + "  !  " + arenaFile.exists());
      state = ArenaState.FREE;
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("The arena " + arenaID + " is " + state);
    } catch (WorldEditException exception) {
      new PluginLogger.SaveError().saveError(PluginMain.main(), exception, "Arena-buildArena-line_38=worldedit");
      exception.printStackTrace();
      state = ArenaState.ERROR_BUILDING;
    }
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo(schematicName + " was build at " + location + "!" + " And now the State is " + state);
  }

  public void reBuildArena(@NotNull Arena arena) {  // also use for rebuilding
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo(arena.getSchematicName() + " is being build at " + arena.getLocation() + "!");
    try {
      arena.setState(ArenaState.LOADING);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Paste the schematic of the arena");
      SchematicBuilder.pasteSchematic(arena.getLocation(), SchematicBuilder.getSchematic(ArenaHandler.FILE_PATH, arena.getSchematicName()));
      File arenaFile = new File(ArenaHandler.FILE_PATH, arena.getSchematicName() + ".schem");
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo(arenaFile + "  !  " + arenaFile.exists());
      arena.setState(ArenaState.FREE);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("The arena " + arena.getArenaID() + " is " + arena.getState());
    } catch (WorldEditException exception) {
      new PluginLogger.SaveError().saveError(PluginMain.main(), exception, "Arena-buildArena-line_38=worldedit");
      exception.printStackTrace();
      state = ArenaState.ERROR_BUILDING;
    }
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo(arena.getSchematicName() + " was build at " + arena.getLocation() + "!" + " And now the State is " + arena.getState());
  }

  // Getter/Setter
  public Location getLocation() {
    return location;
  }
  public String getSchematicName() {
    return schematicName;
  }

  public String getArenaID() {
    return arenaID;
  }

  public Game getGame() {
    return game;
  }
  public void setGame(Game game) {
    this.game = game;
  }
  public ArenaState getState() {
    return state;
  }
  public void setState(ArenaState state) {
    this.state = state;
  }
}
