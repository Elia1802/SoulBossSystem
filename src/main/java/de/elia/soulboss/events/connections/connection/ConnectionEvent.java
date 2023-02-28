package de.elia.soulboss.events.connections.connection;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.messages.message.CustomMessages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class ConnectionEvent implements Listener {

  @EventHandler
  public void onConnection(@NotNull PlayerJoinEvent event){
    CustomMessages message = new CustomMessages();
    Player player = event.getPlayer();
    var status = SoulBoss.playerStatusMap();
    if (status.get(player) == null) {
      status.put(player, 0);
    }else {
      message.errorLog("This player (" + player + ") has a exist Status!");
      message.warningLog("Status of the Player set to 0!");
      status.replace(player, 0);
    }
  }

}
