package de.elia.items.events.join;

import de.elia.items.ItemMain;
import de.elia.systemclasses.messages.PluginMessages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author Elia
 * @description This event give maybe the player by the join the recipe
 */
public class RecipeEvent implements Listener {

  @EventHandler
  private void onJoin(@NotNull PlayerJoinEvent event) {
    PluginMessages message = new PluginMessages();
    Player player = event.getPlayer();
    Random random = new Random();
    int chance = random.nextInt(25);
    if (chance == 1) {
      Bukkit.getWorld((String)player.getWorld().getName()).strikeLightningEffect(player.getLocation());
      message.message(player, message.white("[Unbekannte Stimme] Sieh dir das an..."));
      message.message(player, message.white("[Unbekannte Stimme] https://www.youtube.com/watch?v=Gow5H1NG0zA"));
      Bukkit.getWorld((String)player.getWorld().getName()).strikeLightningEffect(player.getLocation());
    }
    ItemMain.itemMain().itemLogger().logWarning("The chance for God is " + chance);
  }
}
