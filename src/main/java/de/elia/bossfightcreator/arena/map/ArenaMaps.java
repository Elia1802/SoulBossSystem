package de.elia.bossfightcreator.arena.map;

import de.elia.bossfightcreator.arena.Arenas;

import java.lang.Integer;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Create for all Arenas a Map to save the Status
 */
public class ArenaMaps {

  public static final Map<Arenas, Integer> ARENA_1_STATUS_MAP = new HashMap<>();//For Arena 1
  public static final Map<Arenas, Integer> ARENA_2_STATUS_MAP = new HashMap<>();//For Arena 2
  public static final Map<Arenas, Integer> ARENA_3_STATUS_MAP = new HashMap<>();//For Arena 3
  public static final Map<Arenas, Integer> ARENA_4_STATUS_MAP = new HashMap<>();//For Arena 4
  public static final Map<Arenas, Integer> ARENA_5_STATUS_MAP = new HashMap<>();//For Arena 5
  public static final Map<Arenas, Integer> ARENA_6_STATUS_MAP = new HashMap<>();//For Arena 6
  public static final Map<Arenas, Integer> ARENA_7_STATUS_MAP = new HashMap<>();//For Arena 7
  public static final Map<Arenas, Integer> ARENA_8_STATUS_MAP = new HashMap<>();//For Arena 8
  public static final Map<Arenas, Integer> ARENA_9_STATUS_MAP = new HashMap<>();//For Arena 9
  public static final Map<Arenas, Integer> ARENA_10_STATUS_MAP = new HashMap<>();//For Arena 10

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description load all Maps with status 0 (ready)
   */
  //TODO: To release set the status of the arenas 5-10 to 0
  public static void load(){
    ARENA_1_STATUS_MAP.put(Arenas.ARENA_1, 0);
    ARENA_2_STATUS_MAP.put(Arenas.ARENA_2, 0);
    ARENA_3_STATUS_MAP.put(Arenas.ARENA_3, 0);
    ARENA_4_STATUS_MAP.put(Arenas.ARENA_4, 0);
    ARENA_5_STATUS_MAP.put(Arenas.ARENA_5, 1);
    ARENA_6_STATUS_MAP.put(Arenas.ARENA_6, 1);
    ARENA_7_STATUS_MAP.put(Arenas.ARENA_7, 1);
    ARENA_8_STATUS_MAP.put(Arenas.ARENA_8, 1);
    ARENA_9_STATUS_MAP.put(Arenas.ARENA_9, 1);
    ARENA_10_STATUS_MAP.put(Arenas.ARENA_10, 1);
  }
}
