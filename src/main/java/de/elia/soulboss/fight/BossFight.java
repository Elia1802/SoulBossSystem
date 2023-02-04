package de.elia.soulboss.fight;

import de.elia.soulboss.entity.mobs.boss.mob.ZombieBoss;
import de.elia.soulboss.functions.register.Register;
import de.elia.soulboss.messages.message.CustomMessages;
import net.minecraft.world.entity.Entity;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author D1p4k, Elia
 * @version 1.0
 * @since 1.0
 * @description This is BossFight class.
 */
public class BossFight {

  private final Location location;
  private final Player player;
  private final String key;
  private ZombieBoss boss;
  private final BossFightManager bossFightManager = new BossFightManager();
  private final Register.Configuration configuration = new Register.Configuration();
  private final CustomMessages message = new CustomMessages();
  public static BossFight bossFight;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is constructor create a new Bossfight and start him.
   * @param player Requrieres a {@link Player}.
   * @param location Requieres a {@link Location}.
   * @param key Requieres a key as {@link String}.
   */
  public BossFight(Player player, Location location, String key){
    this.player = player;
    this.location = location;
    this.key = key;
    startFight();
    bossFight = this;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is constructor create a new Bossfight and start him.
   * @param player Requrieres a {@link Player}.
   * @param location Requieres a {@link Location}.
   */
  public BossFight(Player player, Location location){
    this(player, location, "680035753");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is methode removed the boss with a specify reaseon.
   */
  public void despawnBoss(Entity.RemovalReason reason){
    this.boss.remove(reason);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is methode start the Bossfight
   */
  public void startFight(){
    if (player == null)return;
    boss = new ZombieBoss(location);
    bossFightManager.setPlayerStart(player, true);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is methode stopped the Bossfight
   */
  public void stopFight(boolean removeFromList){
    this.despawnBoss(Entity.RemovalReason.KILLED);
    if (removeFromList) {
      bossFightManager.removePlayerStart(player);
    }
    message.messageWithPrefix(player, message.gold("Der BossFight wurde beendet!"));
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is methode load the BossFight.
   */
  public static void loadBossFight(int ticks, Player player){
    CustomMessages message = new CustomMessages();
    message.messageWithPrefix(player, message.green("Der Boss Kampf wird in kürze beginnen"));
    new Utils().start(ticks, player);
  }
}
