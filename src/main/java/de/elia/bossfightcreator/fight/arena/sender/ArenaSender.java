package de.elia.bossfightcreator.fight.arena.sender;

import de.elia.bossfightcreator.fight.arena.Arenas;
import de.elia.bossfightcreator.fight.arena.map.ArenaMaps;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Checks if a Arena ready
 */
public class ArenaSender {

  public ArenaSender(){
    //...
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Checks if a Arena ready
   * @return {@link Arenas}
   */
  public Arenas arena(){
    if (ArenaMaps.ARENA_1_STATUS_MAP.get(Arenas.ARENA_1) == 0) {
      return Arenas.ARENA_1;
    }else if (ArenaMaps.ARENA_2_STATUS_MAP.get(Arenas.ARENA_2) == 0) {
      return Arenas.ARENA_2;
    }else if (ArenaMaps.ARENA_3_STATUS_MAP.get(Arenas.ARENA_3) == 0) {
      return Arenas.ARENA_3;
    }else if (ArenaMaps.ARENA_4_STATUS_MAP.get(Arenas.ARENA_4) == 0) {
      return Arenas.ARENA_4;
    }else if (ArenaMaps.ARENA_5_STATUS_MAP.get(Arenas.ARENA_5) == 0) {
      return Arenas.ARENA_5;
    }else if (ArenaMaps.ARENA_6_STATUS_MAP.get(Arenas.ARENA_6) == 0) {
      return Arenas.ARENA_6;
    }else if (ArenaMaps.ARENA_7_STATUS_MAP.get(Arenas.ARENA_7) == 0) {
      return Arenas.ARENA_7;
    }else if (ArenaMaps.ARENA_8_STATUS_MAP.get(Arenas.ARENA_8) == 0) {
      return Arenas.ARENA_8;
    }else if (ArenaMaps.ARENA_9_STATUS_MAP.get(Arenas.ARENA_9) == 0) {
      return Arenas.ARENA_9;
    }else if (ArenaMaps.ARENA_10_STATUS_MAP.get(Arenas.ARENA_10) == 0) {
      return Arenas.ARENA_10;
    }else {
      return null;
    }
  }
}
