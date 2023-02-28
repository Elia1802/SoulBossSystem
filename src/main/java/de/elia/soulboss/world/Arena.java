package de.elia.soulboss.world;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import de.elia.soulboss.world.worldedit.BossArena;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

/**
 * @author Zopnote, Elia
 * @version 1.0
 * @since 1.0
 * @description The Interface of {@link BossArena}
 * @implementationOf {@link BossArena}
 */
public interface Arena {

  /**
   * @author Zopnote
   * @version 1.0
   * @since 1.0
   * @description Set or Reset the structure.
   * @implementationOf {@link BossArena#onChangeWorld(PlayerChangedWorldEvent)}
   * @param event Requires a {@link PlayerChangedWorldEvent}
   */
  void onChangeWorld(PlayerChangedWorldEvent event);

  /**
   * @author Elia, Zopnote
   * @version 1.0
   * @since 1.0
   * @description Set or Reset the structure.
   * @implementationOf {@link BossArena#onPlayerJoin(PlayerJoinEvent)}
   * @param event Requires a {@link PlayerJoinEvent}
   */
  void onPlayerJoin(PlayerJoinEvent event);

  /**
   * @author Elia, Zopnote
   * @version 1.0
   * @since 1.0
   * @description Load a schem of Worldedit of the file "arena.schem"
   * @implementationOf {@link BossArena#loadSchem()}
   * @return a {@link Clipboard} if the {@link File} could be read and if not then null
   */
  Clipboard loadSchem();

  /**
   * @author Elia, Zopnote
   * @version 1.0
   * @since 1.0
   * @description Paste a schem of a {@link Clipboard} in the {@link BukkitWorld}
   * @implementationOf {@link BossArena#pasteSchem(BukkitWorld, Clipboard)}
   * @param world Requires a {@link BukkitWorld}
   * @param clipboard Requires a {@link Clipboard}
   */
  void pasteSchem(BukkitWorld world, Clipboard clipboard);

}
