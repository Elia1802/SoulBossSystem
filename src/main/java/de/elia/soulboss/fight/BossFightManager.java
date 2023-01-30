package de.elia.soulboss.fight;

import de.elia.soulboss.functions.register.Register;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Manager of the BossFights
 */
public class BossFightManager {
  private final Register.Configuration configuration = new Register.Configuration();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the methode to set if the player has a bossfight start or not.
   */
  public void setPlayerStart(@NotNull Player player, boolean value){
    configuration.playerRegisterConfiguration().set("BossFightCreator" + ".Player." + player.getName() + " " + player.getUniqueId(), value);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is remove the player of the bossfight
   */
  public void removePlayerStart(@NotNull Player player){
    configuration.playerRegisterConfiguration().clearPath("BossFightCreator" + ".Player." + player.getName() + " " + player.getUniqueId());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Asks if player have start or not
   */
  public boolean playerHasStart(@NotNull Player player){
    return configuration.playerRegisterConfiguration().get("BossFightCreator" + ".Player." + player.getName() + " " + player.getUniqueId()) != null ? true : false;
  }

}
