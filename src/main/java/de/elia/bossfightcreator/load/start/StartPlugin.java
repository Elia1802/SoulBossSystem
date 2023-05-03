package de.elia.bossfightcreator.load.start;

import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import de.elia.PluginMain;
import de.elia.bossfightcreator.Instances;
import de.elia.bossfightcreator.arena.Arenas;
import de.elia.bossfightcreator.arena.arena.load.LoadArena;
import de.elia.bossfightcreator.arena.arena.paste.PasteArena;
import de.elia.bossfightcreator.arena.map.ArenaMaps;
import de.elia.bossfightcreator.builder.fight.events.DisconnectEvent;
import de.elia.bossfightcreator.builder.fight.executer.GameExecuter;
import de.elia.bossfightcreator.builder.save.Saver;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Start Methode to load the Plugin
 */
public class StartPlugin {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Start Methode to load the Plugin
   * @param plugin Requires the main main class of this Plugin
   */
  public static void start(@NotNull Plugin plugin) throws IOException, WorldEditException, FileNotFoundException {
    Instances.Plugin.instance = plugin;
    var world_status = Instances.Files.WORLD_STATUS;
    world_status.copyDefaults(true);
    world_status.save();
    PluginMain.main().getDefaultWorldGenerator("bossfight", "world_bossfight");
    Bukkit.getPluginCommand("trustplayer").setExecutor(new GameExecuter(plugin));
    Bukkit.getPluginManager().registerEvents(new  GameExecuter(plugin), plugin);
    Bukkit.getPluginManager().registerEvents(new DisconnectEvent(), plugin);
    ArenaMaps.load();
    Saver.SaveGame.loadMap();
    Saver.SaveGameBuilder.loadMap();
    loadArenas();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load the Arenas if the server started
   */
  private static void loadArenas() throws IOException, WorldEditException, FileNotFoundException {
    PasteArena arenaLoader = new PasteArena();
    arenaLoader.pasteArena(new BukkitWorld(Bukkit.getWorld("world_bossfight")), new LoadArena(Arenas.ARENA_1.getName()).loadArena());
    arenaLoader.pasteArena(new BukkitWorld(Bukkit.getWorld("world_bossfight")), new LoadArena(Arenas.ARENA_2.getName()).loadArena());
    arenaLoader.pasteArena(new BukkitWorld(Bukkit.getWorld("world_bossfight")), new LoadArena(Arenas.ARENA_3.getName()).loadArena());
    arenaLoader.pasteArena(new BukkitWorld(Bukkit.getWorld("world_bossfight")), new LoadArena(Arenas.ARENA_4.getName()).loadArena());
    //arenaLoader.pasteArena(new BukkitWorld(Bukkit.getWorld("world_bossfight")), new LoadArena(Arenas.ARENA_5.getName()).loadArena());
    //arenaLoader.pasteArena(new BukkitWorld(Bukkit.getWorld("world_bossfight")), new LoadArena(Arenas.ARENA_6.getName()).loadArena());
    //arenaLoader.pasteArena(new BukkitWorld(Bukkit.getWorld("world_bossfight")), new LoadArena(Arenas.ARENA_7.getName()).loadArena());
    //arenaLoader.pasteArena(new BukkitWorld(Bukkit.getWorld("world_bossfight")), new LoadArena(Arenas.ARENA_8.getName()).loadArena());
    //arenaLoader.pasteArena(new BukkitWorld(Bukkit.getWorld("world_bossfight")), new LoadArena(Arenas.ARENA_9.getName()).loadArena());
    //arenaLoader.pasteArena(new BukkitWorld(Bukkit.getWorld("world_bossfight")), new LoadArena(Arenas.ARENA_10.getName()).loadArena());
  }
}
