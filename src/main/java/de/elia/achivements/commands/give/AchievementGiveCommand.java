package de.elia.achivements.commands.give;

import de.elia.achivements.AchievementMain;
import de.elia.achivements.achievement.process.AchievementClass;
import de.elia.achivements.achievement.storage.Achievements;
import de.elia.api.logging.PluginLogger;
import de.elia.systemclasses.messages.PluginMessages;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description A command to give a {@link Player} a {@link Achievements}.
 * @implements {@link CommandExecutor}, {@link TabCompleter}
 */
public class AchievementGiveCommand implements CommandExecutor, TabCompleter {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    var logger = AchievementMain.achievementMain().achievementPluginLogger();
    PluginMessages message = new PluginMessages();
    AchievementClass achievement = new AchievementClass(AchievementMain.achievementMain().main());
    if (sender instanceof Player player) {
      if (player.hasPermission("soulbosssystem.achievement.give")) {
        if (args.length == 2){
          Player target = Bukkit.getPlayer(args[0]);
          if (target.getName() == null){
            try {
              throw new NullPointerException("The Player " + target.getName() + " is not online/exsist!");
            }catch (NullPointerException exception){
              new PluginLogger.SaveError().saveError(AchievementMain.achievementMain().main(), exception, "AchievementGiveCommand-onCommand-line_27=null");
              logger.stacktrace(exception);
              return false;
            }
          }
          if (args[1].equalsIgnoreCase("soulbosssystem:plugin:achievement:BOSSFIGHT")) {
            achievement.giveAchievement(target, Achievements.BOSSFIGHT);
            message.messageWithPrefix(target, message.gray("Du hast vom Teammitglied ")
              .append(message.aqua(player.getName()))
              .append(message.gray(" den Erfolg "))
              .append(message.aqua(Achievements.BOSSFIGHT.getName()))
              .append(message.gray(" bekommen!"))
            );
            message.messageWithPrefix(player, message.gray("Du hast dem Spieler ")
              .append(message.aqua(target.getName()))
              .append(message.gray(" den Erfolg "))
              .append(message.aqua(Achievements.BOSSFIGHT.getName()))
              .append(message.gray(" gegeben!"))
            );
            logger.logInfo("Das Teammitglied "
              + player.getName()
              + " hat den Spieler "
              + target.getName()
              + " den Erfolg "
              + Achievements.BOSSFIGHT.getName()
              + " gegeben"
            );
            return true;
          }else if (args[1].equalsIgnoreCase("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE")) {
            achievement.giveAchievement(target, Achievements.BOSSFIGHT_ZOMBIE);
            message.messageWithPrefix(target, message.gray("Du hast vom Teammitglied ")
              .append(message.aqua(player.getName()))
              .append(message.gray(" den Erfolg "))
              .append(message.aqua(Achievements.BOSSFIGHT_ZOMBIE.getName()))
              .append(message.gray(" bekommen!"))
            );
            message.messageWithPrefix(player, message.gray("Du hast dem Spieler ")
              .append(message.aqua(target.getName()))
              .append(message.gray(" den Erfolg "))
              .append(message.aqua(Achievements.BOSSFIGHT_ZOMBIE.getName()))
              .append(message.gray(" gegeben!"))
            );
            logger.logInfo("Das Teammitglied "
              + player.getName()
              + " hat den Spieler "
              + target.getName()
              + " den Erfolg "
              + Achievements.BOSSFIGHT_ZOMBIE.getName()
              + " gegeben"
            );
            return true;
          }else if (args[1].equalsIgnoreCase("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE_END")) {
            achievement.giveAchievement(target, Achievements.BOSSFIGHT_ZOMBIE_END);
            message.messageWithPrefix(target, message.gray("Du hast vom Teammitglied ")
              .append(message.aqua(player.getName()))
              .append(message.gray(" den Erfolg "))
              .append(message.aqua(Achievements.BOSSFIGHT_ZOMBIE_END.getName()))
              .append(message.gray(" bekommen!"))
            );
            message.messageWithPrefix(player, message.gray("Du hast dem Spieler ")
              .append(message.aqua(target.getName()))
              .append(message.gray(" den Erfolg "))
              .append(message.aqua(Achievements.BOSSFIGHT_ZOMBIE_END.getName()))
              .append(message.gray(" gegeben!"))
            );
            logger.logInfo("Das Teammitglied "
              + player.getName()
              + " hat den Spieler "
              + target.getName()
              + " den Erfolg "
              + Achievements.BOSSFIGHT_ZOMBIE_MINI.getName()
              + " gegeben"
            );
            return true;
          }else if (args[1].equalsIgnoreCase("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE_MINI")) {
            achievement.giveAchievement(target, Achievements.BOSSFIGHT_ZOMBIE_MINI);
            message.messageWithPrefix(target, message.gray("Du hast vom Teammitglied ")
              .append(message.aqua(player.getName()))
              .append(message.gray(" den Erfolg "))
              .append(message.aqua(Achievements.BOSSFIGHT_ZOMBIE_MINI.getName()))
              .append(message.gray(" bekommen!"))
            );
            message.messageWithPrefix(player, message.gray("Du hast dem Spieler ")
              .append(message.aqua(target.getName()))
              .append(message.gray(" den Erfolg "))
              .append(message.aqua(Achievements.BOSSFIGHT_ZOMBIE_MINI.getName()))
              .append(message.gray(" gegeben!"))
            );
            logger.logInfo("Das Teammitglied "
              + player.getName()
              + " hat den Spieler "
              + target.getName()
              + " den Erfolg "
              + Achievements.BOSSFIGHT_ZOMBIE_MINI.getName()
              + " gegeben"
            );
            return true;
          }else {
            message.messageWithPrefix(player, message.red("Dieser Command exsistiert nicht!"));
            return false;
          }
        }else {
          message.messageWithPrefix(player, message.red("/achievementgive [ACHIEVEMENT]"));
          return false;
        }
      }else {
        message.messageWithPrefix(player, message.red("Du hast die Permission ")
          .append(message.aqua("soulbosssystem.achievement.give"))
          .append(message.red(" nicht um diese Aktion auszuführen"))
        );
        return false;
      }
    }else {
      logger.logError("You have to be a Player!");
      return false;
    }
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    List<String> tabs = new ArrayList<>();
    if (args.length == 2) {
      tabs.add("soulbosssystem:plugin:achievement:BOSSFIGHT");
      tabs.add("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE");
      tabs.add("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE_END");
      tabs.add("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE_MINI");
      return tabs;
    }
    return null;
  }
}
