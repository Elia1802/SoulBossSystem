package de.elia.soulboss.fight;

import de.elia.soulboss.entity.mobs.boss.mob.Boss;
import de.elia.soulboss.functions.register.Register;
import de.elia.soulboss.messages.messages.CustomMessages;
import net.minecraft.world.entity.Entity;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BossFight {

  private final Location location;
  private final Player player;
  private final String key;
  private Boss boss;
  private final BossFightManager bossFightManager = new BossFightManager();
  private final Register.Configuration configuration = new Register.Configuration();

  public BossFight(Player player, Location location, String key){
    this.player = player;
    this.location = location;
    this.key = key;
    startFight();
  }

  public BossFight(Player player, Location location){
    this(player, location, "680035753");
  }

  public void despawnBoss(Entity.RemovalReason reason){
    this.boss.remove(reason);
  }

  public void startFight(){
    CustomMessages message = new CustomMessages();
    boss = new Boss(location);
    if (player == null)return;
    message.messageWithPrefix(player, message.green("Der Boss Kampf wird in kürze beginnen"));
    bossFightManager.setPlayerStart(player, true);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is methode stopped the Bossfight
   */
  public void stopFight(boolean removeFromList){
    boss.remove(Entity.RemovalReason.KILLED);
    if (removeFromList) {
      bossFightManager.removePlayerStart(player);
    }
    CustomMessages message = new CustomMessages();
    message.messageWithPrefix(player, message.gold("Der BossFight wurde beendet!"));
  }

  @NotNull
  public String key(){
    return key;
  }

  @NotNull
  public Boss boss() {
    return boss;
  }

  @NotNull
  public BossFightManager bossFightManager() {
    return bossFightManager;
  }

  @NotNull
  public Register.Configuration configuration() {
    return configuration;
  }

  @NotNull
  public Player player() {
    return player;
  }

  @NotNull
  public Location location() {
    return location;
  }
}
