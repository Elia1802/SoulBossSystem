package de.elia.systemclasses.register.commands;

import de.elia.achivements.commands.give.AchievementGiveCommand;
import de.elia.achivements.commands.help.AchievementHelpCommand;
import de.elia.bossfightcreator.game.executer.GameExecuter;
import de.elia.bossfightcreator.commands.help.BossFightCreatorHelpCommand;
import de.elia.bossfightcreator.commands.world.BossFightCreatorWorldCommand;
import de.elia.items.commands.help.ItemHelpCommand;
import de.elia.items.commands.items.ItemGiveCommand;
import de.elia.soulbosssystem.commands.help.SoulBossSystemHelpCommand;
import de.elia.soulbosssystem.commands.idea.SoulBossSystemIdeaCommand;
import de.elia.soulbosssystem.commands.plugin.SoulBossSystemCommand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description This is the Register class to register all Commands.
 */
public class CommandRegister {

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Register Commands
   * @param main Requires the main class
   */
  public static void registerCommand(@NotNull JavaPlugin main) {
    Bukkit.getPluginCommand("achievementgive").setExecutor(new AchievementGiveCommand());
    Bukkit.getPluginCommand("achievementhelp").setExecutor(new AchievementHelpCommand());
    Bukkit.getPluginCommand("bossfightcreatorhelp").setExecutor(new BossFightCreatorHelpCommand());
    Bukkit.getPluginCommand("bossfightcreatoraddplayer").setExecutor(new GameExecuter(main));
    Bukkit.getPluginCommand("bossfightcreatorworld").setExecutor(new BossFightCreatorWorldCommand());
    Bukkit.getPluginCommand("itemhelp").setExecutor(new ItemHelpCommand());
    Bukkit.getPluginCommand("itemgive").setExecutor(new ItemGiveCommand());
    Bukkit.getPluginCommand("soulbosssystemhelp").setExecutor(new SoulBossSystemHelpCommand());
    Bukkit.getPluginCommand("soulbosssystemidea").setExecutor(new SoulBossSystemIdeaCommand());
    Bukkit.getPluginCommand("soulbosssystem").setExecutor(new SoulBossSystemCommand());
  }
}
