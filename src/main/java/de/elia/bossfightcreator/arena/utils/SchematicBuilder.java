package de.elia.bossfightcreator.arena.utils;

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

import de.elia.api.annotation.AnnotationChecker;
import de.elia.api.annotation.Beta;
import de.elia.api.logging.error.SaveError;

import de.elia.bossfightcreator.BossFightCreatorMain;

import org.bukkit.Location;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Sinan
 * @description This class load and paste schematic file of worldedit.
 * @beta This function can create errors!!!
 */
@Beta
public class SchematicBuilder {

  /**
   * @author Sinan, Elia
   * @param path Requires the path of the schematic file.
   * @param schematicName Requires the schematic name for the file.
   * @return Return the {@link Clipboard} with the schematic.
   */
  @NotNull
  public static Clipboard schematic(@NotNull File path, @NotNull String schematicName) {
    AnnotationChecker.processAnnotations(SchematicBuilder.class);
    Clipboard clipboard;
    File arenaFile = new File(path, schematicName + ".schem");
    ClipboardFormat format = ClipboardFormats.findByFile(arenaFile);
    try (ClipboardReader reader = format.getReader(new FileInputStream(arenaFile))) {
      return clipboard = reader.read();
    }catch (IOException exception) {
      //Start Elia
      new SaveError().saveError(BossFightCreatorMain.bossFightCreator().main(), exception, "SchematicBuilder-schematic-line_47-48=ioexception");
      exception.printStackTrace();
      return null;
      //End Elia
    }
  }

  /**
   * @description This methode paste a schematic of a {@link Clipboard} on specify {@link Location}.
   * @param location Requires the {@link Location} to place the Schematic.
   * @param clipboard Requires the {@link Clipboard} with the schemeatic.
   * @throws WorldEditException The Operation can create an error.
   */
  public static void pasteSchematic(@NotNull Location location, @NotNull Clipboard clipboard) throws WorldEditException {
    AnnotationChecker.processAnnotations(SchematicBuilder.class);
    EditSession session = WorldEdit.getInstance().newEditSession(new BukkitWorld(location.getWorld()));
    Operation operation = new ClipboardHolder(clipboard).createPaste(session).to(BlockVector3.at(location.x(), location.y(), location.z())).ignoreAirBlocks(false).copyEntities(false).build();
    Operations.complete(operation);
    session.close();
  }
}
