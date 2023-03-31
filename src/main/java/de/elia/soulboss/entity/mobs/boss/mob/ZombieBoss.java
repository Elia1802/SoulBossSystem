package de.elia.soulboss.entity.mobs.boss.mob;

import de.elia.Keys;
import de.elia.soulboss.entity.equipment.armors.boots.Boots;
import de.elia.soulboss.entity.equipment.armors.chestplate.Chestplate;
import de.elia.soulboss.entity.equipment.armors.helmet.Helmet;
import de.elia.soulboss.entity.equipment.armors.leggins.Leggins;
import de.elia.soulboss.entity.equipment.tools.axe.Axe;
import de.elia.soulboss.entity.equipment.tools.sword.Sword;
import de.elia.CustomMessages;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @extends {@link Zombie}
 * @description This class create the Boss.
 */
public class ZombieBoss extends Zombie {

  private ZombieBoss boss;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This Methode create and spawn the Boss on a specify location.
   * @param location Requires the Location
   */
  public ZombieBoss(@NotNull Location location) {
    super(EntityType.ZOMBIE, ((CraftWorld)location.getWorld()).getHandle());
    CustomMessages message = new CustomMessages();
    boss = this;
    this.getBukkitEntity().getPersistentDataContainer().set(Keys.ZOMBIE_KEY.key(), PersistentDataType.BYTE, (byte) 1);
    this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(500);
    this.setHealth(500);
    this.setBaby(false);
    this.setPos(location.getX(), location.getY(), location.getZ());
    this.setCustomName(Component.literal("ZombieBoss"));
    this.setCanBreakDoors(true);
    this.setAggressive(true);
    this.randomTool(this);
    new Helmet().helmet(this);
    new Chestplate().chestplate(this);
    new Leggins().leggins(this);
    new Boots().boots(this);
    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(this);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Register the Goals of the Zombie
   */
  @Override
  public void registerGoals(){
    super.registerGoals();
    this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.5D, false));//Speed of 1.0D to 1.5D
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets all Methodes of this and the Zombie class.
   */
  @NotNull
  public ZombieBoss boss() {
    return boss;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Give the Zombie an axe or a sword.
   */
  private void randomTool(Zombie zombie){
    Random random = new Random();
    float x = random.nextFloat();
    if (x<0.5){
      new Sword().sword(zombie);
    }else {
      new Axe().axe(zombie);
    }
  }

}
