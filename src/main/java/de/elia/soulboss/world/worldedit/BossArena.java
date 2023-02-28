package de.elia.soulboss.world.worldedit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.world.Arena;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Zopnote, Elia
 * @version 1.0
 * @since 1.0
 * @description Set the structure of a {@link Clipboard} in world
 * @implements {@link Listener}, {@link Arena}
 */
public class BossArena implements Listener, Arena {

  /**
   * @author Zopnote
   * @version 1.0
   * @since 1.0
   * @description Set or Reset the structure.
   * @param event Requires a {@link PlayerChangedWorldEvent}
   */
  @EventHandler
  public void onChangeWorld(@NotNull PlayerChangedWorldEvent event) {
    if (event.getPlayer().getWorld() == Bukkit.getServer().getWorld("world_bossfight")) {
      int Count = 0;
      for (Player player : Bukkit.getServer().getOnlinePlayers()) {
        if (player != event.getPlayer()) {
          if (player.getWorld() == Bukkit.getServer().getWorld("world_bossfight")) {
            Count++;
          }
        }
      }
      if (Count == 0) {
        this.pasteSchem(new BukkitWorld(Bukkit.getServer().getWorld("world_bossfight")), this.loadSchem());
      }
    }
  }

  /**
   * @author Elia, Zopnote
   * @version 1.0
   * @since 1.0
   * @description Set or Reset the structure.
   * @param event Requires a {@link PlayerJoinEvent}
   */
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event){
    if (event.getPlayer().getWorld() == Bukkit.getServer().getWorld("world_bossfight")) {
      int Count = 0;
      for (Player player : Bukkit.getServer().getOnlinePlayers()) {
        if (player != event.getPlayer()) {
          if (player.getWorld() == Bukkit.getServer().getWorld("world_bossfight")) {
            Count++;
          }
        }
      }
      if (Count == 0) {
        this.pasteSchem(new BukkitWorld(Bukkit.getServer().getWorld("world_bossfight")), this.loadSchem());
      }
    }
  }

  /**
   * @author Elia, Zopnote
   * @version 1.0
   * @since 1.0
   * @description Load a schem of Worldedit of the file "arena.schem"
   * @return a {@link Clipboard} if the {@link File} could be read and if not then null
   */
  public Clipboard loadSchem() {
    File file = new File(SoulBoss.soulBoss().getDataFolder(), "arena.schem");
    ClipboardFormat format = ClipboardFormats.findByFile(file);
    try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
      Clipboard clipboard = reader.read();
      return clipboard;
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  /**
   * @author Elia, Zopnote
   * @version 1.0
   * @since 1.0
   * @description Paste a schem of a {@link Clipboard} in the {@link BukkitWorld}
   * @param world Requires a {@link BukkitWorld}
   * @param clipboard Requires a {@link Clipboard}
   */
  public void pasteSchem(BukkitWorld world, Clipboard clipboard) {
    try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1)) {
      Operation operation = new ClipboardHolder(clipboard).createPaste(editSession).to(BlockVector3.at(clipboard.getOrigin().getX(), clipboard.getOrigin().getY(), clipboard.getOrigin().getZ())).ignoreAirBlocks(false).build();
      Operations.complete(operation);
    } catch (WorldEditException exception) {
      exception.printStackTrace();
    }
  }
}
