package de.elia.bossfightcreator.arena;

import de.elia.PluginMain;
import de.elia.bossfightcreator.BossFightCreatorMain;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ArenaHandler {
  public static final File FILE_PATH = new File(PluginMain.main().getDataFolder() + "/arenas/");// arena game path
  private static final int AREANS_TO_GENERATE = 3;
  private static final int ARENAS_DISTANCE = 50;
  private static final int ARENAS_Z_DISTANCE = 50;

  private static HashMap<String, Set<Arena>> arenas = new HashMap<>();  // K: schematicName, V: Array<Arena>

  public static int ARENA_OFFSET = 0;

  public static Optional<Arena> getFreeArena() {
    return getArenaWithType(ArenaState.FREE);
  }
  public static Optional<Arena> getArenaWithType(ArenaState arenaState) {
    return Optional.ofNullable(getArenasWithType(arenaState).get(0));
  }

  @NotNull
  public static List<Arena> getArenasWithType(ArenaState arenaState) {
    List<Arena> collectedArenas = new ArrayList<>();
    arenas.values().forEach(arenas1 -> collectedArenas.addAll(arenas1.stream().filter((arena -> arena.getState() == arenaState)).toList()));
    return collectedArenas;
  }

  public static void init() {
    if(!FILE_PATH.exists()) FILE_PATH.mkdir();
    generateArenas("arena_1", 0*ARENAS_Z_DISTANCE);
    generateArenas("arena_2", 1*ARENAS_Z_DISTANCE);
    generateArenas("arena_3", 2*ARENAS_Z_DISTANCE);
  }

  public static void generateArenas(String schem, int z) {
    HashSet<Arena> setArena = new HashSet<>();
    for (int i = 0; i < AREANS_TO_GENERATE; i++) {
      setArena.add(generateArena(schem, z, schem + "." + i));
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("ARENA ID: " + schem + "." + i);
      ARENA_OFFSET++;
    }
    arenas.put(schem, setArena);
    ARENA_OFFSET = 0;
  }


  @NotNull
  private static Arena generateArena(@NotNull String schem, int z, String arenaID) {
    Arena arena = new Arena(schem, new Location(Bukkit.getWorld("world_bossfight"), ARENA_OFFSET * ARENAS_DISTANCE, 100, z), arenaID);
    return arena;
  }

}
