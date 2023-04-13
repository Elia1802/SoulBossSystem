package de.elia.bossfightcreator.arena.sender;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import de.elia.bossfightcreator.arena.Arenas;
import de.elia.bossfightcreator.arena.arena.load.LoadArena;
import de.elia.bossfightcreator.arena.arena.paste.PasteArena;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

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
    if (Arenas.ARENA_1.arenaStatusMap().get(Arenas.ARENA_1) == 0) {
      Arenas.ARENA_1.setStatus(Arenas.ARENA_1, false);
      return Arenas.ARENA_1;
    }else if (Arenas.ARENA_2.arenaStatusMap().get(Arenas.ARENA_2) == 0) {
      Arenas.ARENA_2.setStatus(Arenas.ARENA_2, false);
      return Arenas.ARENA_2;
    }else if (Arenas.ARENA_3.arenaStatusMap().get(Arenas.ARENA_3) == 0) {
      Arenas.ARENA_3.setStatus(Arenas.ARENA_3, false);
      return Arenas.ARENA_3;
    }else if (Arenas.ARENA_4.arenaStatusMap().get(Arenas.ARENA_4) == 0) {
      Arenas.ARENA_4.setStatus(Arenas.ARENA_4, false);
      return Arenas.ARENA_4;
    }else if (Arenas.ARENA_5.arenaStatusMap().get(Arenas.ARENA_5) == 0) {
      Arenas.ARENA_5.setStatus(Arenas.ARENA_5, false);
      return Arenas.ARENA_5;
    }else if (Arenas.ARENA_6.arenaStatusMap().get(Arenas.ARENA_6) == 0) {
      Arenas.ARENA_6.setStatus(Arenas.ARENA_6, false);
      return Arenas.ARENA_6;
    }else if (Arenas.ARENA_7.arenaStatusMap().get(Arenas.ARENA_7) == 0) {
      Arenas.ARENA_7.setStatus(Arenas.ARENA_7, false);
      return Arenas.ARENA_7;
    }else if (Arenas.ARENA_8.arenaStatusMap().get(Arenas.ARENA_8) == 0) {
      Arenas.ARENA_8.setStatus(Arenas.ARENA_8, false);
      return Arenas.ARENA_8;
    }else if (Arenas.ARENA_9.arenaStatusMap().get(Arenas.ARENA_9) == 0) {
      Arenas.ARENA_9.setStatus(Arenas.ARENA_9, false);
      return Arenas.ARENA_9;
    }else if (Arenas.ARENA_10.arenaStatusMap().get(Arenas.ARENA_10) == 0) {
      Arenas.ARENA_10.setStatus(Arenas.ARENA_10, false);
      return Arenas.ARENA_10;
    }else {
      return null;
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Reset the arena
   * @param arena Requires a {@link Arenas}
   */
  public void resetArena(@NotNull Arenas arena){
    PasteArena  arenaLoader = new PasteArena();
    arenaLoader.pasteArena(new BukkitWorld(Bukkit.getWorld("world_bossfight")), new LoadArena(arena.getName()).loadArena());
    arena.setStatus(arena, true);
  }

}
