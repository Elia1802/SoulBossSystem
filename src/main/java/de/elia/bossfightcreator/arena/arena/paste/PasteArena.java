package de.elia.bossfightcreator.arena.arena.paste;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Paste a arena in the World of a schematic
 */
public class PasteArena {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Paste a arena in the World of a schematic
   * @param world Requires a {@link BukkitWorld}
   * @param clipboard Requires a {@link Clipboard}
   */
  public void pasteArena(BukkitWorld world, Clipboard clipboard){
    try (EditSession session = WorldEdit.getInstance().newEditSession(world)) {
      Operation operation = new ClipboardHolder(clipboard)
        .createPaste(session)
        .to(BlockVector3.at(
          clipboard.getOrigin().getX(),
          clipboard.getOrigin().getY(),
          clipboard.getOrigin().getZ()
        ))
        .ignoreAirBlocks(false)
        .build();
      Operations.complete(operation);
    }catch (WorldEditException exception) {
      exception.printStackTrace();
    }
  }
}
