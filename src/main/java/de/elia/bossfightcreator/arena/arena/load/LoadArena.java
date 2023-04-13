package de.elia.bossfightcreator.arena.arena.load;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import de.elia.bossfightcreator.BossFightCreator;

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

  private final String arenaName;

  public LoadArena(String arenaName){
    this.arenaName = arenaName;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description
   * @return Load a Arena of a Schematic
   */
  public Clipboard loadArena(){
    var worldMain = BossFightCreator.worldMain();
    File arenaFile = new File(worldMain.arenaFolder(), arenaName + ".schem");
    ClipboardFormat format = ClipboardFormats.findByFile(arenaFile);
    try (ClipboardReader reader = format.getReader(new FileInputStream(arenaFile))) {
      return reader.read();
    }catch (IOException exception) {
      exception.printStackTrace();
    }
    return null;
  }
}
