package de.elia.soulboss.achievement.process;

import de.elia.soulboss.achievement.storage.BossFightAchievementStorage;
import de.elia.systemclasses.messages.PluginMessages;
import de.elia.soulboss.plugin.load.start.register.configuation.ConfigurationLoader;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the class to set or remove the achievements
 */
public class BossFightAchievements {

  private final Plugin plugin;
  private final ConfigurationLoader configurationLoader = new ConfigurationLoader();
  private final PluginMessages messageBuilder = new PluginMessages();

  public BossFightAchievements(Plugin plugin) {
    this.plugin = plugin;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Asks if the player the has a specific achievement
   * @param player Requires a Player
   * @param achievementStorage Requires a Achievement
   * @return new Register.Configuration.getConfiguration().get(player.getUniqueId() + ".Achievements." + achievementStorage.dataID()) != null;
   */
  public boolean hasAchievement(@NotNull Player player, @NotNull BossFightAchievementStorage achievementStorage) {
    return this.configurationLoader.achievementStorage().get(player.getUniqueId() + ".Achievements." + achievementStorage.dataID()) != null;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Give the Player the achievement
   * @param player Requires a Player
   * @param achievementStorage Requires a Achievement
   */
  public void giveAchievement(@NotNull Player player, @NotNull BossFightAchievementStorage achievementStorage) {
    if (!this.hasAchievement(player, achievementStorage)) {
      this.configurationLoader.achievementStorage().set(player.getUniqueId() + ".Achievements." + achievementStorage.dataID(), true);
      messageBuilder.message(player,
        messageBuilder.aqua(player.getName())
          .append(messageBuilder.gray(" hat den BossFight Erfolg ")
          .append(messageBuilder.aqua(achievementStorage.getName())
          .append(messageBuilder.gray(" erreicht")))));
      player.giveExp(achievementStorage.xp());
      player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
    }

  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Remove the Player the achievement
   * @param player Requires a Player
   * @param achievementStorage Requires a Achievement
   */
  public void removeAchievement(@NotNull Player player, @NotNull BossFightAchievementStorage achievementStorage) {
    if (this.hasAchievement(player, achievementStorage)) {
      this.configurationLoader.achievementStorage().set(player.getUniqueId() + ".Achievements." + achievementStorage.dataID(), null);
      messageBuilder.message(player,
        messageBuilder.gray("Du hast dem Spieler den BossFight Erfolg ")
          .append(messageBuilder.aqua(achievementStorage.getName() + " (" + achievementStorage.dataID() + ") ")
          .append(messageBuilder.gray("abgenommen!"))));
    } else {
      messageBuilder.message(player, messageBuilder.red("Dieser Spieler hat diesen BossFight Erfolg nicht!"));
    }

  }

}
