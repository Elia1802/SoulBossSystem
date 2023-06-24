package de.elia.systemclasses.register.commands;

import de.elia.achivements.commands.give.AchievementGiveCommand;
import de.elia.achivements.commands.help.AchievementHelpCommand;
import de.elia.bossfightcreator.commands.help.BossFightCreatorHelpCommand;
import de.elia.bossfightcreator.commands.world.BossFightCreatorWorldCommand;
import de.elia.items.commands.help.ItemHelpCommand;
import de.elia.items.commands.items.ItemGiveCommand;
import de.elia.soulbosssystem.commands.help.SoulBossSystemHelpCommand;
import de.elia.soulbosssystem.commands.idea.SoulBossSystemIdeaCommand;
import de.elia.soulbosssystem.commands.plugin.SoulBossSystemCommand;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;

import java.util.HashMap;
import java.util.Map;
/**
 * @author Elia
 * @description This class register all commands.
 */
public class CommandRegister {
  
  private static final Map<String, Command> COMMANDS = new HashMap<>();

  public static void registerCommands(Server server) {
    COMMANDS.forEach((s, command) -> server.getCommandMap().register(s, "Soulbosssytem", command));
  }

  static {
    COMMANDS.put("achievementgive", new AchievementGiveCommand());
    COMMANDS.put("achievementhelp", new AchievementHelpCommand());
    COMMANDS.put("bossfightcreatorhelp", new BossFightCreatorHelpCommand());
    COMMANDS.put("bossfightcreatoworld", new BossFightCreatorWorldCommand());
    COMMANDS.put("itemhelp", new ItemHelpCommand());
    COMMANDS.put("itemgive", new ItemGiveCommand());
    COMMANDS.put("soulbosssystemhelp", new SoulBossSystemHelpCommand());
    COMMANDS.put("soulbosssystemidea", new SoulBossSystemIdeaCommand());
    COMMANDS.put("soulbosssystem", new SoulBossSystemCommand());
  }

  public static void registerCommands() {
    CommandRegister.registerCommands(Bukkit.getServer());
  }
}
