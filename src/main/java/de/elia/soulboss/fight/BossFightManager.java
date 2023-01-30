package de.elia.soulboss.fight;

import de.elia.soulboss.functions.register.Register;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BossFightManager {
  private final Register.Configuration configuration = new Register.Configuration();


  public void setPlayerStart(@NotNull Player player, boolean value){
    configuration.playerRegisterConfiguration().set("BossFightCreator" + ".Player." + player.getName() + " " + player.getUniqueId(), value);
  }

  public void removePlayerStart(@NotNull Player player){
    configuration.playerRegisterConfiguration().clearPath("BossFightCreator" + ".Player." + player.getName() + " " + player.getUniqueId());
  }

  public boolean playerHasStart(@NotNull Player player){
    return configuration.playerRegisterConfiguration().get("BossFightCreator" + ".Player." + player.getName() + " " + player.getUniqueId()) != null ? true : false;
  }

}
