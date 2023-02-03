package de.elia.soulboss.events.spawn;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.fight.BossFight;
import de.elia.soulboss.messages.message.CustomMessages;
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
    SpawnEgg spawnEgg = new SpawnEgg(SoulBoss.soulBoss());
    CustomMessages message = new CustomMessages();
    if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
      if (player.getInventory().getItemInMainHand().getItemMeta() == null)return;
      if (player.getInventory().getItemInMainHand().getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
        player.getInventory().getItemInMainHand().removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        Component component = miniMessage.deserialize("<click:run_command:/bossfight stop><green>Drücke hier</green></click> <gold>um den Bossfight vor dem Tod des Bosses zu beenden!</gold>");
        message.messageWithPrefix(player, component);
        BossFight.loadBossFight(11*20, player);
      }
    }
  }
}
