package de.elia.bossfightcreator.world.arena.load;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import de.elia.bossfightcreator.Instances.Plugin;
import de.elia.bossfightcreator.world.WorldMain;
import org.jetbrains.annotations.NotNull;

import java.lang.String;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Load a Arena of a Schematic
 */
public class LoadArena {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description
   * @param arena Requires the Arena name
   * @return Load a Arena of a Schematic
   */
  @NotNull
  public Clipboard loadArena(String arena){
    WorldMain worldMain = new WorldMain(Plugin.instance);
    File arenaSchem = new File(worldMain.arenaFolder(), arena + ".schem");
    ClipboardFormat format = ClipboardFormats.findByFile(arenaSchem);
    try (ClipboardReader reader = format.getReader(new FileInputStream(arenaSchem))) {
      return reader.read();
    }catch (IOException exception) {
      exception.printStackTrace();
    }
    return null;
  }
}
