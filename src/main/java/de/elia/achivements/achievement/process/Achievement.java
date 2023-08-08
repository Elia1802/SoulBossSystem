package de.elia.achivements.achievement.process;

import de.elia.achivements.AchievementMain;
import de.elia.achivements.achievement.storage.Achievements;
import de.elia.soulbosssystem.configuation.SoulBossSystemConfigurationLoader;
import de.elia.systemclasses.messages.PluginMessages;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description This class give a {@link Player} a achievement
 */
public class Achievement {

  private final PluginMessages messageBuilder = new PluginMessages();

  /**
   * @description Checks if the {@link Player} has a achievement.
   * @param player Requires the {@link Player}.
   * @param achievement Requires the achievement.
   * @return Return true if the player has the achievement, false if not.
   */
  public boolean hasAchievement(@NotNull Player player, @NotNull Achievements achievement) {
    return SoulBossSystemConfigurationLoader.achievementStorage().get(player.getUniqueId() + ".Achievements." + achievement.dataID()) != null;
  }

  /**
   * @description This methode give the {@link Player} the achievement
   * @param player Requires the {@link Player} to give him the achievement.
   * @param achievement Requires the achievement for the {@link Player}.
   */
  public void giveAchievement(@NotNull Player player, @NotNull Achievements achievement) {
    if (!this.hasAchievement(player, achievement)) {
      SoulBossSystemConfigurationLoader.achievementStorage().set(player.getUniqueId() + ".Achievements." + achievement.dataID(), (Object)true);
      this.messageBuilder.broadcastWithPrefix(this.messageBuilder.aqua(player.getName()).append(this.messageBuilder.gray(" hat den BossFight Erfolg ").append(this.messageBuilder.aqua(achievement.getName()).append(this.messageBuilder.gray(" erreicht")))));
      this.messageBuilder.message(player, this.messageBuilder.gray(achievement.target()));
      player.giveExp(achievement.xp());
      player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    } else {
      AchievementMain.achievementMain().achievementPluginLogger().logWarning("Der Spieler " + player.getName() + "hat das Achievement " + achievement.getName() + " bereits!");
    }
  }

  /**
   * @description This methode remove the achievement of the {@link Player}.
   * @param player Requires the {@link Player} to remove his achievement.
   * @param achievement Requires the achievement of the {@link Player}.
   */
  public void removeAchievement(@NotNull Player player, @NotNull Achievements achievement) {
    if (this.hasAchievement(player, achievement)) {
      SoulBossSystemConfigurationLoader.achievementStorage().set(player.getUniqueId() + ".Achievements." + achievement.dataID(), null);
      this.messageBuilder.message(player, this.messageBuilder.gray("Du hast dem Spieler den BossFight Erfolg ").append(this.messageBuilder.aqua(achievement.getName() + " (" + achievement.dataID() + ") ").append(this.messageBuilder.gray("abgenommen!"))));
    } else {
      this.messageBuilder.message(player, this.messageBuilder.red("Dieser Spieler hat diesen BossFight Erfolg nicht!"));
    }
  }
}

