package de.elia.bossfightcreator.arena.utils;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;

import org.bukkit.Location;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SchematicBuilder {

  @Nullable
  public static Clipboard getSchematic(File path, String schematicFileName) {
    try {
      File arenaFile = new File(path, schematicFileName + ".schem");
      ClipboardReader reader = null;
      reader = ClipboardFormats.findByFile(arenaFile).getReader(new FileInputStream(arenaFile));
      return reader.read();
    }catch (IOException ignored) {}
    return null;
  }

  public static void pasteSchematic(@NotNull Location location, Clipboard clipboard) throws WorldEditException {
    EditSession session = WorldEdit.getInstance().newEditSession(new BukkitWorld(location.getWorld()));
    Operation operation = new ClipboardHolder(clipboard)
      .createPaste(session)
      .to(BlockVector3.at(
        location.getX(),
        location.getY(),
        location.getZ()
      ))
      .ignoreAirBlocks(false)
      .copyEntities(false)
      .build();
    Operations.complete(operation);
    session.close();
  }
}
