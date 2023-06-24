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
import com.sk89q.worldedit.world.World;
import de.elia.api.annotation.AnnotationChecker;
import de.elia.api.annotation.Beta;
import de.elia.api.logging.SaveError;
import de.elia.bossfightcreator.BossFightCreatorMain;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Elia, and Sinan
 * @description This class loaded and paste a Schematic in the world
 * @Beta This class is new and may have Bugs
 */
@Beta
public class SchematicBuilder {

  /**
   * @description Load a build of a schematic in the worldedit clipboard
   * @param path Requries the path of the schematic
   * @param schematicName Requires the name of the schematic file
   * @return The {@link Clipboard} of Worldedit with the build (arena)
   */
  @NotNull
  public static Clipboard schematic(File path, String schematicName) {
    Clipboard clipboard;
    AnnotationChecker.processAnnotations(SchematicBuilder.class);
    File arenaFile = new File(path, schematicName + ".schem");
    ClipboardFormat format = ClipboardFormats.findByFile((File)arenaFile);
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
   * @description paste a Schematic on a specify {@link Location}
   * @param location Requires the location where the schematic of the {@link Clipboard} is loaded
   * @param clipboard Requires a Clipboard with the schematic (For this project you can use {@link SchematicBuilder#schematic(File, String)})
   * @throws WorldEditException Print a Stacktrace if the Operation can not completed
   */
  public static void pasteSchematic(@NotNull Location location, Clipboard clipboard) throws WorldEditException {
    AnnotationChecker.processAnnotations(SchematicBuilder.class);
    EditSession session = WorldEdit.getInstance().newEditSession((World)new BukkitWorld(location.getWorld()));
    Operation operation = new ClipboardHolder(clipboard).createPaste(session).to(BlockVector3.at(location.x(), location.y(), location.z())).ignoreAirBlocks(false).copyEntities(false).build();
    Operations.complete((Operation)operation);
    session.close();
  }
}

