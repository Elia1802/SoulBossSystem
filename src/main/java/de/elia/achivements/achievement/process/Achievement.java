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
 * @description This Class give and remove a Achievement of a {@link Player}.
 */
public class Achievement {

  private final SoulBossSystemConfigurationLoader soulBossSystemConfigurationLoader = new SoulBossSystemConfigurationLoader();
  private final PluginMessages messageBuilder = new PluginMessages();

  /**
   * @description Checks if the {@link Player} has the Achievement.
   * @param player Requires a Player.
   * @param achievement Requires a Achievement.
   * @return If the {@link Player} has this Achievement true, or if not false.
   */
  public boolean hasAchievement(@NotNull Player player, @NotNull Achievements achievement) {
    return this.soulBossSystemConfigurationLoader.achievementStorage().get(player.getUniqueId() + ".Achievements." + achievement.dataID()) != null;
  }

  /**
   * @description Give the {@link Player} the specify Achievement.
   * @param player Requires a Player.
   * @param achievement Requires a Achievement.
   */
  public void giveAchievement(@NotNull Player player, @NotNull Achievements achievement) {
    if (!this.hasAchievement(player, achievement)) {
      this.soulBossSystemConfigurationLoader.achievementStorage().set(player.getUniqueId() + ".Achievements." + achievement.dataID(), true);
      this.messageBuilder.broadcastWithPrefix(this.messageBuilder.aqua(player.getName()).append(this.messageBuilder.gray(" hat den BossFight Erfolg ").append(this.messageBuilder.aqua(achievement.getName()).append(this.messageBuilder.gray(" erreicht")))));
      this.messageBuilder.message(player, this.messageBuilder.gray(achievement.target()));
      player.giveExp(achievement.xp());
      player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    } else {
      AchievementMain.achievementMain().achievementPluginLogger().logWarning("Der Spieler " + player.getName() + "hat das Achievement " + achievement.getName() + " bereits!");
    }
  }

  /**
   * @description Remove the specify Achievement of the {@link Player}.
   * @param player Requires the Player.
   * @param achievement Requires the Achievement.
   */
  public void removeAchievement(@NotNull Player player, @NotNull Achievements achievement) {
    if (this.hasAchievement(player, achievement)) {
      this.soulBossSystemConfigurationLoader.achievementStorage().set(player.getUniqueId() + ".Achievements." + achievement.dataID(), null);
      this.messageBuilder.message(player, this.messageBuilder.gray("Du hast dem Spieler den BossFight Erfolg ").append(this.messageBuilder.aqua(achievement.getName() + " (" + achievement.dataID() + ") ").append(this.messageBuilder.gray("abgenommen!"))));
    } else {
      this.messageBuilder.message(player, this.messageBuilder.red("Dieser Spieler hat diesen BossFight Erfolg nicht!"));
    }
  }
}

