package de.elia.achivements.commands.give;

import de.elia.achivements.AchievementMain;
import de.elia.achivements.achievement.process.Achievement;
import de.elia.achivements.achievement.storage.Achievements;
import de.elia.systemclasses.messages.PluginMessages;

import de.elia.api.logging.PluginLogger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Elia
 * @description This command give a {@link Player} a achievement.
 * @extends {@link Command}
 */
public class AchievementGiveCommand extends Command {

  public AchievementGiveCommand() {
    this("achievementgive", "The achievementgive command give the player a Achievement.", "Use /achievementhelp [PLAYER] [ACHIEVEMENT]", Arrays.asList("achievementg", "agive"));
  }

  public AchievementGiveCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String subCommand, String[] args) {
    PluginMessages message = new PluginMessages();
    PluginLogger logger = AchievementMain.achievementMain().achievementPluginLogger();
    Achievement achievement = new Achievement();
    if (sender instanceof Player player) {
      if (player.hasPermission("soulbosssystem.achievement.give")) {
        if (args.length == 2) {
          Player target = Bukkit.getPlayer(args[0]);
          if (args[1].equalsIgnoreCase("soulbosssystem:plugin:achievement:BOSSFIGHT")) {
            if (target.getName() == null) {
              message.message(player, message.gold("Dieser Spieler existiert nicht oder ist nicht online!"));
              return false;
            }
            achievement.giveAchievement(target, Achievements.BOSSFIGHT);
            message.messageWithPrefix(target, message.gray("Du hast vom Teammitglied ").append(message.aqua(player.getName())).append(message.gray(" den Erfolg ")).append(message.aqua(Achievements.BOSSFIGHT.getName())).append(message.gray(" bekommen!")));
            message.messageWithPrefix(player, message.gray("Du hast dem Spieler ").append(message.aqua(target.getName())).append(message.gray(" den Erfolg ")).append(message.aqua(Achievements.BOSSFIGHT.getName())).append(message.gray(" gegeben!")));
            logger.logInfo("Das Teammitglied " + player.getName() + " hat den Spieler " + target.getName() + " den Erfolg " + Achievements.BOSSFIGHT.getName() + " gegeben");
            return true;
          }
          if (args[1].equalsIgnoreCase("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE")) {
            if (target.getName() == null) {
              message.message(player, message.gold("Dieser Spieler existiert nicht oder ist nicht online!"));
              return false;
            }
            achievement.giveAchievement(target, Achievements.BOSSFIGHT_ZOMBIE);
            message.messageWithPrefix(target, message.gray("Du hast vom Teammitglied ").append(message.aqua(player.getName())).append(message.gray(" den Erfolg ")).append(message.aqua(Achievements.BOSSFIGHT_ZOMBIE.getName())).append(message.gray(" bekommen!")));
            message.messageWithPrefix(player, message.gray("Du hast dem Spieler ").append(message.aqua(target.getName())).append(message.gray(" den Erfolg ")).append(message.aqua(Achievements.BOSSFIGHT_ZOMBIE.getName())).append(message.gray(" gegeben!")));
            logger.logInfo("Das Teammitglied " + player.getName() + " hat den Spieler " + target.getName() + " den Erfolg " + Achievements.BOSSFIGHT_ZOMBIE.getName() + " gegeben");
            return true;
          }
          if (args[1].equalsIgnoreCase("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE_END")) {
            if (target.getName() == null) {
              message.message(player, message.gold("Dieser Spieler existiert nicht oder ist nicht online!"));
              return false;
            }
            achievement.giveAchievement(target, Achievements.BOSSFIGHT_ZOMBIE_END);
            message.messageWithPrefix(target, message.gray("Du hast vom Teammitglied ").append(message.aqua(player.getName())).append(message.gray(" den Erfolg ")).append(message.aqua(Achievements.BOSSFIGHT_ZOMBIE_END.getName())).append(message.gray(" bekommen!")));
            message.messageWithPrefix(player, message.gray("Du hast dem Spieler ").append(message.aqua(target.getName())).append(message.gray(" den Erfolg ")).append(message.aqua(Achievements.BOSSFIGHT_ZOMBIE_END.getName())).append(message.gray(" gegeben!")));
            logger.logInfo("Das Teammitglied " + player.getName() + " hat den Spieler " + target.getName() + " den Erfolg " + Achievements.BOSSFIGHT_ZOMBIE_MINI.getName() + " gegeben");
            return true;
          }
          if (args[1].equalsIgnoreCase("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE_MINI")) {
            if (target.getName() == null) {
              message.message(player, message.gold("Dieser Spieler existiert nicht oder ist nicht online!"));
              return false;
            }
            achievement.giveAchievement(target, Achievements.BOSSFIGHT_ZOMBIE_MINI);
            message.messageWithPrefix(target, message.gray("Du hast vom Teammitglied ").append(message.aqua(player.getName())).append(message.gray(" den Erfolg ")).append(message.aqua(Achievements.BOSSFIGHT_ZOMBIE_MINI.getName())).append(message.gray(" bekommen!")));
            message.messageWithPrefix(player, message.gray("Du hast dem Spieler ").append(message.aqua(target.getName())).append(message.gray(" den Erfolg ")).append(message.aqua(Achievements.BOSSFIGHT_ZOMBIE_MINI.getName())).append(message.gray(" gegeben!")));
            logger.logInfo("Das Teammitglied " + player.getName() + " hat den Spieler " + target.getName() + " den Erfolg " + Achievements.BOSSFIGHT_ZOMBIE_MINI.getName() + " gegeben");
            return true;
          }
          message.message(player, message.red("Das Achievement ").append(message.aqua(args[1])).append(message.red(" gibt es nicht")));
          return false;
        }
        message.message(player, message.red("/achievementgive [PLAYER] [ACHIEVEMENT]"));
        return false;
      }
      message.message(player, message.red("Du hast keine Rechte für diesen Command"));
      return false;
    }
    message.message(sender, message.red("You have to be a Player!"));
    return false;
  }

  @Override
  @NotNull
  public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String subCommand, String[] args) {
    ArrayList<String> tab1 = new ArrayList<>();
    ArrayList<String> tab2 = new ArrayList<>();
    if (args.length == 1) {
      Bukkit.getOnlinePlayers().forEach(player -> tab1.add(player.getName()));
      return tab1;
    }
    if (args.length == 2) {
      tab2.add("soulbosssystem:plugin:achievement:BOSSFIGHT");
      tab2.add("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE");
      tab2.add("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE_END");
      tab2.add("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE_MINI");
      return tab2;
    }
    return null;
  }
}
