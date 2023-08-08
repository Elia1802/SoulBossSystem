package de.elia.systemclasses.register.events;

import de.elia.achivements.events.bossfight.AchievementBossFightEvent;
import de.elia.bossfightcreator.events.connect.ConnectionEvent;
import de.elia.bossfightcreator.game.executer.NewGameExecuter;
import de.elia.items.events.join.RecipeEvent;
import de.elia.items.events.magicbook.MagicBookEvent;
import de.elia.soulboss.entity.events.DropEvent;
import de.elia.soulboss.entity.mobs.boss.mob.ZombieBoss;
import de.elia.soulboss.entity.mobs.boss.mob.ZombieBoss.ZombieBossEvent;
import de.elia.soulboss.events.AttackEvent;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class EventRegister {
  public static void registerEvents(@NotNull PluginManager pluginManager, @NotNull JavaPlugin main) {
    pluginManager.registerEvents(new AchievementBossFightEvent(), main);
    pluginManager.registerEvents(new ConnectionEvent(), main);
    pluginManager.registerEvents(new MagicBookEvent(main), main);
    pluginManager.registerEvents(new AttackEvent(), main);
    pluginManager.registerEvents(new NewGameExecuter(main), main);
    pluginManager.registerEvents(new RecipeEvent(), main);
    pluginManager.registerEvents(new DropEvent(), main);
    pluginManager.registerEvents(new ZombieBossEvent(), main);
  }
}
