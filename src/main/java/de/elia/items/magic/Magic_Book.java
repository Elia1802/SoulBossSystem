package de.elia.items.magic;

import de.elia.api.Complex;
import de.elia.api.TheZepserAPI;
import de.elia.api.components.ComplexItem;
import de.elia.api.datatypes.Region;
import de.elia.api.enums.RegionType;
import de.elia.api.spells.Spells;
import de.elia.items.ItemMain;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException.CheckVariable;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static de.elia.items.ItemMain.MINI_MESSAGE;

public class Magic_Book implements Listener {

  private static ComplexItem MB;
  private static final Collection<Region> REGIONS = new ArrayList<>();
  private int count;

  public Magic_Book(Plugin plugin) throws SoulBossSystemNullException {
    if (MB == null) {
      if (!new CheckVariable().check(plugin, "Magic_Book(Plugin)"))return;
      Bukkit.getPluginManager().registerEvents(this, plugin);
      List<Component> list = new ArrayList<>();
      list.add(MINI_MESSAGE.deserialize("<gray>You can used this book</gray>"));
      list.add(MINI_MESSAGE.deserialize("<gray>to cast spells.</gray>"));
      Component name = MINI_MESSAGE.deserialize("<obfuscated>#</obfuscated> <dark_purple>Magic Book<dark_purple> <obfuscated>#</obfuscated>");
      MB = TheZepserAPI.item.create(Material.BOOK, name, list)
        .setCustomModelData(1)
        .setKey(Complex.MAGIC_BOOK)
        .setAmount(1)
        .addAttribute(Attribute.GENERIC_MAX_HEALTH, 20, Operation.ADD_NUMBER, EquipmentSlot.HAND)
        .addFlag(ItemFlag.HIDE_ATTRIBUTES)
        .addFlag(ItemFlag.HIDE_ENCHANTS)
        .addEnchantment(Enchantment.ARROW_FIRE, 1)
        .save();
    }
  }

  @EventHandler
  public void onRightClick(@NotNull PlayerInteractEvent event){
    if (event.getAction().isRightClick()) {
      if (TheZepserAPI.item.hasKey(event.getItem(), TheZepserAPI.item.createKey(Complex.MAGIC_BOOK))) {
        Collection<LivingEntity> livingEntities = event.getPlayer().getLocation().getNearbyLivingEntities(20);
        for (LivingEntity entity : livingEntities) {
          if (entity instanceof Player player) {
            player.spawnParticle(Particle.ELECTRIC_SPARK, event.getPlayer().getLocation(), 15);
            player.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 0.7f, 0.8f);
          }
        }
        Region region = TheZepserAPI.region.create(
          new Location(event.getPlayer().getWorld(), event.getPlayer().getLocation().x(), event.getPlayer().getLocation().y()+5, event.getPlayer().getLocation().z()),
          6,
          RegionType.NORMAL,
          event.getPlayer(),
          false);
        REGIONS.add(region);
        count = 0;
        new BukkitRunnable() {
          @Override
          public void run() {
            if (count < 40) {
              count++;
              TheZepserAPI.region.spawnCircle(region, Particle.DRIPPING_OBSIDIAN_TEAR);
              for (LivingEntity entity : livingEntities) {
                if (entity instanceof Player player) {
                  double currentHealth = player.getHealth();
                  double newHealth = currentHealth + 0.5;
                  player.setHealth(Math.min(newHealth, player.getHealthScale()));
                  player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.7f, 0.2f);
                }
              }
            }else {
              REGIONS.remove(region);
              cancel();
            }
          }
        }.runTaskTimer(ItemMain.itemMain().main(), 0, 5);
      }
    }else if (event.getAction().isLeftClick()) {
      if (TheZepserAPI.item.hasKey(event.getItem(), TheZepserAPI.item.createKey(Complex.MAGIC_BOOK))){
        Spells.GRAVITATION_ATTACK(event.getPlayer(), false);
      }
    }
  }


}
