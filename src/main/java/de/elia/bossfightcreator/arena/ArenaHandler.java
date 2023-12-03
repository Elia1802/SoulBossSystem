package de.elia.bossfightcreator.arena;

import de.elia.PluginMain;
import de.elia.systemclasses.PluginInstances;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.*;

/**
 * @description This class initialized the arenas and gets arenas with specify states.
 */
public class ArenaHandler {

  public static final File FILE_PATH = new File(PluginMain.main().getDataFolder() + "/arenas/");
  private static final int AREANS_TO_GENERATE = 10;
  private static final int ARENAS_DISTANCE = 200;
  private static final int ARENAS_Z_DISTANCE = 200;
  private static boolean isInit = false;
  private static HashMap<String, Set<Arena>> arenas = new HashMap();
  public static int ARENA_OFFSET = 0;

  /**
   * @description Gets all free arenas.
   * @return Return all free arenas.
   */
  public static Optional<Arena> getFreeArena() {
    return ArenaHandler.getArenaWithType(ArenaState.FREE);
  }

  /**
   * @description Get the first {@link Arena} with a specify {@link ArenaState} of a list.
   * @param arenaState Requires the {@link ArenaState} of the arena
   * @return The first {@link Arena} with the specify {@link ArenaState}.
   */
  @NotNull
  public static Optional<Arena> getArenaWithType(@NotNull ArenaState arenaState) {
    Optional<ArrayList<Arena>> t = Optional.of(ArenaHandler.getArenasWithType(arenaState));
    Random random = new Random();
    int x = random.nextInt(t.get().size());
    System.out.println(x);
    return Optional.ofNullable(ArenaHandler.getArenasWithType(arenaState).get(x));
  }

  /**
   * @description Gets all {@link Arena}s with a specify {@link ArenaState} of a list.
   * @param arenaState Requires the {@link ArenaState} of the arenas
   * @return Return The {@link Arena}s with the specify {@link ArenaState}.
   */
  @NotNull
  public static ArrayList<Arena> getArenasWithType(@NotNull ArenaState arenaState) {
    ArrayList<Arena> collectedArenas = new ArrayList<>();
    arenas.values().forEach(arenas1 -> collectedArenas.addAll(arenas1.stream().filter(arena -> arena.getState() == arenaState).toList()));
    return collectedArenas;
  }

  /**
   * @description Initialized the arenas by server start.
   */
  public static void init() {
    if (!FILE_PATH.exists()) {
      FILE_PATH.mkdir();
    }
    ArenaHandler.generateArenas("arena_1", 200);
    ArenaHandler.generateArenas("arena_2", 20);
    isInit = true;
  }

  /**
   * @description Generate the arenas.
   * @param schem Requires the schemmatic file name.
   * @param z Requires the z cordinate.
   */
  public static void generateArenas(String schem, int z) {
    HashSet<Arena> setArena = new HashSet<>();
    for (int i = 0; i < 10; ++i) {
      setArena.add(ArenaHandler.generateArena(schem, z, schem + "." + i));
      PluginInstances.BOSS_FIGHT_CREATOR_LOGGER.logInfo("ARENA ID: " + schem + "." + i);
      ++ARENA_OFFSET;
    }
    arenas.put(schem, setArena);
    ARENA_OFFSET = 0;
  }

  /**
   * @description Generate a {@link Arena}
   * @param schem Requires the schemmatic file name for the {@link Arena}.
   * @param z Requires the z cordinate for the {@link Arena}.
   * @param arenaID Requires the id for the {@link Arena}.
   * @return
   */
  @NotNull
  private static Arena generateArena(@NotNull String schem, int z, String arenaID) {
    Arena arena = new Arena(schem, new Location(Bukkit.getWorld("world_bossfight"), (200 * ARENA_OFFSET), 100.0, z), arenaID);
    return arena;
  }

  /**
   * @deprecated if the boolean false can the server crash
   * @description Checks if the arenas initialized.
   * @return Return true if arenas initialized and if not false.
   */
  @Deprecated
  public static boolean isInit() {
    return isInit;
  }
}
