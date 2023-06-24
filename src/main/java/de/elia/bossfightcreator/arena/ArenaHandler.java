package de.elia.bossfightcreator.arena;

import de.elia.PluginMain;
import de.elia.api.annotation.AnnotationChecker;
import de.elia.api.annotation.Beta;
import de.elia.systemclasses.PluginInstances;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author D1p4k and Sinan, modified by Elia
 * @description This class is sorts {@link Arena}s by state and loads all {@link Arena}s.
 * @beta There may still be errors when loading the {@link Arena}s.
 */
@Beta
public class ArenaHandler {

  public static final File FILE_PATH = new File(PluginMain.main().getDataFolder() + "/arenas/");//The path of the schematic files
  private static final int AREANS_TO_GENERATE = 10;//The amount of arenas to be loaded by each schematic
  private static final int ARENAS_DISTANCE = 200;//The Distance to the next Arena on the x achse
  private static final int ARENAS_Z_DISTANCE = 200;//The Distance to the next Arena on the z achse
  private static boolean isInit = false;
  private static HashMap<String, Set<Arena>> arenas = new HashMap();
  public static int ARENA_OFFSET = 0;

  /**
   * @description Gets all free {@link Arena}s.
   * @return all arenas with the {@link ArenaState#FREE}
   */
  public static Optional<Arena> getFreeArena() {
    return ArenaHandler.getArenaWithType(ArenaState.FREE);
  }

  /**
   * @description Gets all {@link Arena}s (which are not null) with a specify {@link ArenaState}.
   * @param arenaState Requires a state.
   * @return all {@link Arena}s with the specify state and which are not null.
   */
  public static Optional<Arena> getArenaWithType(@NotNull ArenaState arenaState) {
    return Optional.ofNullable(ArenaHandler.getArenasWithType(arenaState).get(0));
  }

  /**
   * @description Gets all {@link Arena}s with a specify {@link ArenaState}.
   * @param arenaState Requires a state.
   * @return all {@link Arena}s with the specify state.
   */
  @NotNull
  public static ArrayList<Arena> getArenasWithType(@NotNull ArenaState arenaState) {
    ArrayList<Arena> collectedArenas = new ArrayList<>();
    arenas.values().forEach(arenas1 -> collectedArenas.addAll(arenas1.stream().filter(arena -> arena.getState() == arenaState).toList()));
    return collectedArenas;
  }

  /**
   * @description This methode load the file paths and generate the {@link Arena}s.
   */
  public static void init() {
    AnnotationChecker.processAnnotations(ArenaHandler.class);
    if (!FILE_PATH.exists()) {
      FILE_PATH.mkdir();
    }
    ArenaHandler.generateArenas("arena_1", ARENAS_Z_DISTANCE);
    ArenaHandler.generateArenas("arena_2", AREANS_TO_GENERATE*2);
    isInit = true;
  }

  /**
   * @description Generate the {@link Arena}s.
   * @param schem Requires the schematic name of the arena.
   * @param z Gets the z cordinate of the {@link Arena}s.
   */
  public static void generateArenas(String schem, int z) {
    HashSet<Arena> setArena = new HashSet<>();
    for (int i = 0; i < AREANS_TO_GENERATE; ++i) {
      setArena.add(ArenaHandler.generateArena(schem, z, schem + "." + i));
      PluginInstances.BOSS_FIGHT_CREATOR_LOGGER.logInfo("ARENA ID: " + schem + "." + i);
      ++ARENA_OFFSET;
    }
    arenas.put(schem, setArena);
    ARENA_OFFSET = 0;
  }

  /**
   * @description Generate the {@link Arena}.
   * @param schem Requires the schematic name of the arena.
   * @param z Gets the z cordinate of the {@link Arena}.
   * @param arenaID Requires the id of the {@link Arena}.
   * @return the generated {@link Arena}
   */
  @NotNull
  private static Arena generateArena(@NotNull String schem, int z, String arenaID) {
    Arena arena = new Arena(schem, new Location(Bukkit.getWorld("world_bossfight"), ARENAS_DISTANCE * ARENA_OFFSET , 100.0, z), arenaID);
    return arena;
  }

  /**
   * @author Elia
   * @description Checks if all {@link Arena}s init.
   * @return if all {@link Arena}s intit true else false..
   */
  public static boolean isInit() {
    return isInit;
  }
}

