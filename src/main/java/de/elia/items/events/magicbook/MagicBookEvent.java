package de.elia.items.events.magicbook;

import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.TheZepserAPI;
import de.elia.api.thezepserapi.datatypes.ItemRegion;
import de.elia.api.thezepserapi.spells.Spells;

import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Elia, Zopnote
 * @description This event give the magic book functions.
 * @implements {@link Listener}
 */
public class MagicBookEvent implements Listener {

  private final Plugin plugin;
  private static final Collection<ItemRegion> REGIONS = new ArrayList<>();
  private int count;
  public final Map<Player, Integer> cooldowns = new HashMap<>();

  public MagicBookEvent(Plugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  private void onRightClick(@NotNull PlayerInteractEvent event) {
    PluginMessages message = new PluginMessages();
    if (event.getAction().isRightClick() && TheZepserAPI.Item.hasKey(event.getItem(), TheZepserAPI.Item.createKey(Complex.MAGIC_BOOK), this.plugin)) {
      if (!this.cooldowns.containsKey(event.getPlayer())) {
        this.startCooldown(event.getPlayer(), 20*30);
        Spells.FIRE_ATTACK(event.getPlayer(), true, this.plugin);
      }else {
        event.setCancelled(true);
        message.message(event.getPlayer(), message.gray("Das Item lädt grade nach"));
      }
    } else if (event.getAction().isLeftClick() && TheZepserAPI.Item.hasKey(event.getItem(), TheZepserAPI.Item.createKey(Complex.MAGIC_BOOK), this.plugin)) {
      if (!this.cooldowns.containsKey(event.getPlayer())) {
        this.startCooldown(event.getPlayer(), 20*20);
        Spells.GRAVITATION_ATTACK(event.getPlayer(), true, this.plugin);
      }else {
        event.setCancelled(true);
        message.message(event.getPlayer(), message.gray("Das Item lädt grade nach"));
      }
    }
  }

  private void startCooldown(@NotNull Player player, int time){
    this.cooldowns.put(player, time);
    new BukkitRunnable() {
      @Override
      public void run() {
        if (MagicBookEvent.this.cooldowns.containsKey(player)) {
          int remainingTicks = MagicBookEvent.this.cooldowns.get(player);
          if (remainingTicks > 0) {
            MagicBookEvent.this.cooldowns.put(player, remainingTicks-1);
          }else {
            MagicBookEvent.this.cooldowns.remove(player);
          }
        }
      }
    }.runTaskTimer(this.plugin, 0, 1);
  }

}
