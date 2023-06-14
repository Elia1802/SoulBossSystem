package de.elia.achivements.achievement.process;

import de.elia.achivements.AchievementMain;
import de.elia.achivements.achievement.storage.Achievements;
import de.elia.systemclasses.messages.PluginMessages;
import de.elia.soulbosssystem.configuation.SoulBossSystemConfigurationLoader;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description This is the class to set or remove the {@link Achievements}s
 */
public class AchievementClass {

  private final Plugin plugin;
  private final SoulBossSystemConfigurationLoader soulBossSystemConfigurationLoader = new SoulBossSystemConfigurationLoader();
  private final PluginMessages messageBuilder = new PluginMessages();

  public AchievementClass(Plugin plugin) {
    this.plugin = plugin;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Asks if the player the has a specific achievement
   * @param player Requires a Player
   * @param achievement Requires a Achievement
   * @return true if player has not the achievement
   */
  public boolean hasAchievement(@NotNull Player player, @NotNull Achievements achievement) {
    return this.soulBossSystemConfigurationLoader.achievementStorage().get(player.getUniqueId() + ".Achievements." + achievement.dataID()) != null;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Give the Player the achievement
   * @param player Requires a {@link Player}
   * @param achievement Requires a {@link Achievements}
   */
  public void giveAchievement(@NotNull Player player, @NotNull Achievements achievement) {
    if (!this.hasAchievement(player, achievement)) {
      this.soulBossSystemConfigurationLoader.achievementStorage().set(player.getUniqueId() + ".Achievements." + achievement.dataID(), true);
      messageBuilder.broadcastWithPrefix(
        messageBuilder.aqua(player.getName())
          .append(messageBuilder.gray(" hat den BossFight Erfolg ")
          .append(messageBuilder.aqua(achievement.getName())
          .append(messageBuilder.gray(" erreicht"))))
      );
      messageBuilder.message(player, messageBuilder.gray(achievement.target()));
      player.giveExp(achievement.xp());
      player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
    }else {
      AchievementMain.achievementMain().achievementPluginLogger().logWarning("Der Spieler " + player.getName() + "hat das Achievement " + achievement.getName() + " bereits!");
    }
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Remove the Player the achievement
   * @param player Requires a Player
   * @param achievement Requires a Achievement
   */
  public void removeAchievement(@NotNull Player player, @NotNull Achievements achievement) {
    if (this.hasAchievement(player, achievement)) {
      this.soulBossSystemConfigurationLoader.achievementStorage().set(player.getUniqueId() + ".Achievements." + achievement.dataID(), null);
      messageBuilder.message(player,
        messageBuilder.gray("Du hast dem Spieler den BossFight Erfolg ")
          .append(messageBuilder.aqua(achievement.getName() + " (" + achievement.dataID() + ") ")
          .append(messageBuilder.gray("abgenommen!"))));
    } else {
      messageBuilder.message(player, messageBuilder.red("Dieser Spieler hat diesen BossFight Erfolg nicht!"));
    }
  }

}
