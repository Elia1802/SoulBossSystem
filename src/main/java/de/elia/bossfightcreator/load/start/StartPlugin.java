package de.elia.bossfightcreator.load.start;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import de.elia.Main;
import de.elia.bossfightcreator.Instances;
import de.elia.bossfightcreator.arena.Arenas;
import de.elia.bossfightcreator.arena.arena.load.LoadArena;
import de.elia.bossfightcreator.arena.arena.paste.PasteArena;
import de.elia.bossfightcreator.arena.map.ArenaMaps;
import de.elia.bossfightcreator.builder.fight.events.DisconnectEvent;
import de.elia.bossfightcreator.builder.fight.executer.GameExecuter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

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
  public static void start(@NotNull Plugin plugin){
    Instances.Plugin.instance = plugin;
    Main main = (Main) Instances.Plugin.instance;
    var world_status = Instances.Files.WORLD_STATUS;
    world_status.copyDefaults(true);
    world_status.save();
    main.getDefaultWorldGenerator("bossfight", "world_bossfight");
    Bukkit.getPluginCommand("trustplayer").setExecutor(new GameExecuter(plugin));
    Bukkit.getPluginManager().registerEvents(new  GameExecuter(plugin), plugin);
    Bukkit.getPluginManager().registerEvents(new DisconnectEvent(), plugin);
    ArenaMaps.load();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load the Arenas if the server started
   */
  public static void loadArenas(){
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
