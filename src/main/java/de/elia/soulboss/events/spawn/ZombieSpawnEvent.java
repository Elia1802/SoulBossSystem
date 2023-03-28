package de.elia.soulboss.events.spawn;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.achievement.process.BossFightAchievements;
import de.elia.soulboss.achievement.storage.BossFightAchievementStorage;
import de.elia.CustomMessages;
import de.elia.soulboss.spawn.SpawnEgg;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link Listener}
 * @description This is the Listener for the SpawnEgg of the Boss
 */
public class ZombieSpawnEvent implements Listener {
  private final CustomMessages message = new CustomMessages();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Event for the Attack Fire.
   */
  @EventHandler
  public void onSpawnEgg(@NotNull PlayerInteractEvent event){
    Player player = event.getPlayer();
    MiniMessage miniMessage = SoulBoss.soulBoss().miniMessage();
    SpawnEgg spawnEgg = new SpawnEgg(SoulBoss.main());
    if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
      if (player.getInventory().getItemInMainHand().getItemMeta() == null)return;
      if (player.getInventory().getItemInMainHand().getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
        player.getInventory().getItemInMainHand().removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        new BossFightAchievements(SoulBoss.main()).giveAchievement(player, BossFightAchievementStorage.BOSSFIGHT_ZOMBIE);
        //TODO: COMMAND FÜR DIESE MESSAGE (/soul_boss_end)
        Component component = miniMessage.deserialize("<click:run_command:/soul_boss_end><green>Drücke hier</green></click> <gold>um den Bossfight vor dem Tod des Bosses zu beenden!</gold>");
        message.messageWithPrefix(player, component);
        //new ArenaSender().sendArena(player);
      }
    }
  }
}
