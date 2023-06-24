package de.elia.soulboss.entity.mobs.boss.mob;

import de.elia.soulboss.entity.equipment.armors.boots.Boots;
import de.elia.soulboss.entity.equipment.armors.chestplate.Chestplate;
import de.elia.soulboss.entity.equipment.armors.helmet.Helmet;
import de.elia.soulboss.entity.equipment.armors.leggins.Leggins;
import de.elia.soulboss.entity.equipment.tools.axe.Axe;
import de.elia.soulboss.entity.equipment.tools.sword.Sword;
import de.elia.systemclasses.keys.PluginKeys;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.UUID;

/**
 * @author Elia, D1p4k, Zopnote
 * @description This is the boss.
 * @extends {@link Zombie}
 */
public class ZombieBoss extends Zombie {

  public ZombieBoss(@NotNull Location location, String name) {
    super(EntityType.ZOMBIE, ((CraftWorld)location.getWorld()).getHandle());
    this.getBukkitEntity().getPersistentDataContainer().set(PluginKeys.ZOMBIE_KEY.key(), PersistentDataType.BYTE, (byte) 1);
    this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(500);
    this.setHealth(500);
    this.setBaby(false);
    this.setPos(location.getX(), location.getY(), location.getZ());
    this.setCustomName(Component.literal(name));
    this.setCanBreakDoors(true);
    this.setAggressive(true);
    this.randomTool(this);
    new Helmet().helmet(this);
    new Chestplate().chestplate(this);
    new Leggins().leggins(this);
    new Boots().boots(this);
    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(this);
  }

  @Override
  public void registerGoals(){
    super.registerGoals();
    this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.5D, false));//Speed of 1.0D to 1.5D
  }

  @NotNull
  public ZombieBoss boss() {
    return this;
  }

  private void randomTool(Zombie zombie) {
    Random random = new Random();
    float x = random.nextFloat();
    if ((double)x < 0.5) {
      new Sword().sword(zombie);
    } else {
      new Axe().axe(zombie);
    }
  }

  @NotNull
  public UUID uuid() {
    return this.uuid;
  }
}

