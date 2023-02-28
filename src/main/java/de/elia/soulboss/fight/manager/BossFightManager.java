package de.elia.soulboss.fight.manager;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.plugin.load.start.register.configuation.ConfigurationLoader;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @deprecated use {@link SoulBoss#playerStatusMap()}
 * @description This is the Manager of the BossFights
 */
@Deprecated
public class BossFightManager extends ConfigurationLoader {

  /**
   *  @author Elia
   * @version 1.0
   * @since 1.0
   * @deprecated use {@link SoulBoss#playerStatusMap()}
   * @description This is the methode to set if the player has a bossfight start or not.
   * @param player Requires a {@link Player}
   * @param mobUUID Requires the {@link UUID} of a boss
   */
  @Deprecated
  public void setPlayerStart(@NotNull Player player, @NotNull UUID mobUUID){
    this.playerRegisterStorage().set("Bosses" + ".Zombie." + mobUUID, player.getUniqueId().toString());
    this.playerRegisterStorage().set("Player" + ".Zombie." + player.getUniqueId(), mobUUID.toString());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @deprecated use {@link SoulBoss#playerStatusMap()}
   * @description This is remove the player of the bossfight
   * @param player Requires a {@link Player}
   * @param uuid Requires the {@link UUID} of a boss
   */
  @Deprecated
  public void removePlayerStart(@NotNull UUID uuid, @NotNull Player player){
    this.playerRegisterStorage().clearPath("Bosses" + ".Zombie." + uuid);
    this.playerRegisterStorage().clearPath("Player" + ".Zombie." + player.getUniqueId());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @deprecated use {@link SoulBoss#playerStatusMap()}
   * @description Asks if player have start or not
   * @param player Requires a {@link Player}
   */
  @Deprecated
  public boolean playerHasStart(@NotNull Player player){
    return this.playerRegisterStorage().get("Player" + ".Zombie." + player.getUniqueId()) != null ? true : false;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @deprecated use {@link SoulBoss#playerStatusMap()}
   * @description gets of the {@link Player} the uuid
   * @param player Requires a {@link Player}
   * @return {@link UUID#fromString(String)}
   */
  @NotNull
  @Deprecated
  public UUID getUUIDofMonsterbyPlayer(@NotNull Player player) {
    return UUID.fromString(this.playerRegisterStorage().getString("Player" + ".Zombie." + player.getUniqueId()));
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @deprecated use {@link SoulBoss#playerStatusMap()}
   * @description gets of the uuid the {@link Player}
   * @param uuid Requires the {@link UUID} of a boss
   * @return {@link UUID#fromString(String)}
   */
  @NotNull
  @Deprecated
  public UUID getUUIDofPlayerbyMonster(UUID uuid) {
    return UUID.fromString(this.playerRegisterStorage().getString("Bosses" + ".Zombie." + uuid));
  }
}
