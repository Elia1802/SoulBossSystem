package de.elia.bossfightcreator.fight.arena;

import de.elia.bossfightcreator.fight.arena.map.ArenaMaps;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.lang.Integer;
import java.lang.String;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static de.elia.bossfightcreator.Instances.Plugin.world_bossfight;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This class save all Arenas
 */
public enum Arenas {

  ARENA_1("arena_1", 1386403, new Location(world_bossfight, -84D, 74D, 216D), ArenaMaps.ARENA_1_STATUS_MAP),
  ARENA_2("arena_2", 9650569, new Location(world_bossfight, 4D, 74D, 181D), ArenaMaps.ARENA_2_STATUS_MAP),
  ARENA_3("arena_3", 1620334, new Location(world_bossfight, 88D, 74D, 133), ArenaMaps.ARENA_3_STATUS_MAP),
  ARENA_4("arena_4", 4440724, new Location(world_bossfight, 150D, 74D, 4D), ArenaMaps.ARENA_4_STATUS_MAP),
  ARENA_5("arena_5", 2820715, new Location(world_bossfight, 79D, 67D, -117D), ArenaMaps.ARENA_5_STATUS_MAP),
  ARENA_6("arena_6", 3193567, new Location(world_bossfight, 12D, 67D, -143D), ArenaMaps.ARENA_6_STATUS_MAP),
  ARENA_7("arena_7", 5567116, new Location(world_bossfight, -59D, 67D, 83D), ArenaMaps.ARENA_7_STATUS_MAP),
  ARENA_8("arena_8", 2326107, new Location(world_bossfight, -150D, 67D, 26D), ArenaMaps.ARENA_8_STATUS_MAP),
  ARENA_9("arena_9", 9378406, new Location(world_bossfight, -70D, 67D, -58D), ArenaMaps.ARENA_9_STATUS_MAP),
  ARENA_10("arena_10", 9613245, new Location(world_bossfight, 7D, 68D, -16D), ArenaMaps.ARENA_10_STATUS_MAP);

  private final @NotNull String name;//A name of the Arena
  private final Integer id;//A id of the Arena
  private final @NotNull Location location;//A location of the Arena
  private final Map<Arenas, Integer> arenaStatusMap;//A status map for the Arena

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create a new Arena
   * @param name Requires a name for the Arena
   * @param id Requires a id for the Arena
   * @param location Requires a location for the Arena
   * @param arenaStatusMap Requires a status map for the Arena
   */
  Arenas(@NotNull String name, Integer id, @NotNull Location location, Map<Arenas, Integer> arenaStatusMap){
    this.name = name;
    this.id = id;
    this.location = location;
    this.arenaStatusMap = arenaStatusMap;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the name of the Arena
   * @return {@link String}
   */
  @NotNull
  public String getName(){
    return this.name;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the id of the Arena
   * @return {@link Integer}
   */
  @NotNull
  public Integer id(){
    return this.id;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the location of the Arena
   * @return {@link Location}
   */
  @NotNull
  public Location location(){
    return this.location;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the status map of the Arena
   * @return {@link Map<Arenas, Integer>}
   */
  @NotNull
  public Map<Arenas, Integer> arenaStatusMap(){
    return this.arenaStatusMap;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets a specify Arena
   * @param arena Requires the Arena
   * @return {@link Arenas}
   */
  @NotNull
  public Arenas get(Arenas arena){
    return arena;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets a list of Arenas
   * @param arenas Requires the list of Arenas
   * @return {@link List<Arenas>}
   */
  @NotNull
  public List<Arenas> getArenas(@NotNull List<Arenas> arenas){
    return arenas;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This class save all Arenas in one value
   */
  public enum AllArenas {

    ALL_ARENAS(Arrays.asList(Arenas.ARENA_1, Arenas.ARENA_2, Arenas.ARENA_3, Arenas.ARENA_4, Arenas.ARENA_5, Arenas.ARENA_6, Arenas.ARENA_7, Arenas.ARENA_8,  Arenas.ARENA_9,  Arenas.ARENA_10));

    private final List<Arenas> arenas;

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Create a new list of Arenas
     * @param arenas Requires the new list of Arenas
     */
    AllArenas(@NotNull List<Arenas> arenas){
      this.arenas  = arenas;
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Gets a list of Arenas
     * @return {@link List<Arenas>}
     */
    @NotNull
    public List<Arenas> getArenas() {
      return arenas;
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Get a specify arena of the list
     * @param arena Requires the specify Arena
     * @return {@link Arenas}
     */
    @NotNull
    public Arenas getArenaOfList(@NotNull Arenas arena){
      if (arenas.contains(arena)) {
        return arena;
      }
      return null;
    }
  }
}
