package de.elia.api;

import de.elia.PluginInstances;
import de.elia.systemclasses.messages.prefix.PluginPrefix;
import de.elia.systemclasses.logging.PluginLogger;
import de.elia.api.components.ComplexItem;
import de.elia.api.components.ComplexItemKeyConstructer;
import de.elia.api.components.DamageBlocked;
import de.elia.api.components.FlightBlocked;
import de.elia.api.components.SpawnCircle;
import de.elia.api.datatypes.Region;
import de.elia.api.enums.RegionType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public interface TheZepserAPI {

  Component Prefix= new PluginPrefix().prefix();
  PluginLogger logger = PluginInstances.THE_ZEPSER_API_LOGGER;
  static void registerAPIEvents() {
    Bukkit.getPluginManager().registerEvents(new FlightBlocked(), TheZepserAPIMain.thZepserAPIMain().main());
    Bukkit.getPluginManager().registerEvents(new DamageBlocked(), TheZepserAPIMain.thZepserAPIMain().main());
  }
  static void indicator(int number) {
    Bukkit.getServer().getLogger().info("Indicator: "+number);
  }

  class region {

    public static Collection<LivingEntity> contains(Region region) {
      return region.getCenter().getNearbyLivingEntities(region.getRadius());
    }
    public static boolean containsObject(LivingEntity entity, Region region) {
      if (region == null) {
        return false;
      }
      else if (entity.getLocation().distance(region.getCenter()) < region.getRadius()) {
        return true;
      }
      else {
        return false;
      }
    }
    public static void delete(Region region) {
      region.clear();
    }
    public static void spawnCircle(Region region, Particle particle) {
      new SpawnCircle(region, particle);
    }
    public static Region create(Location center, double radius, RegionType type, Player owner, boolean pvp) {
      return new Region(center, radius, type, owner, pvp);
    }
  }
  class utilities {
    public static String[] convertString(List<String> list) {
      return list.toArray(new String[0]);
    }
    public static CommandSender createCommandSender() {
      return new CommandSender() {
        @Override
        public void sendMessage(@NotNull String message) {

        }

        @Override
        public void sendMessage(@NotNull String... messages) {

        }

        @Override
        public void sendMessage(@Nullable UUID sender, @NotNull String message) {

        }

        @Override
        public void sendMessage(@Nullable UUID sender, @NotNull String... messages) {

        }

        @Override
        public @NotNull Server getServer() {
          return Bukkit.getServer();
        }

        @Override
        public @NotNull String getName() {
          return "TheZepserAPI";
        }

        @Override
        public @NotNull Spigot spigot() {
          return null;
        }

        @Override
        public @NotNull Component name() {
          return null;
        }

        @Override
        public boolean isPermissionSet(@NotNull String name) {
          return false;
        }

        @Override
        public boolean isPermissionSet(@NotNull Permission perm) {
          return false;
        }

        @Override
        public boolean hasPermission(@NotNull String name) {
          return false;
        }

        @Override
        public boolean hasPermission(@NotNull Permission perm) {
          return false;
        }

        @Override
        public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
          return null;
        }

        @Override
        public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin) {
          return null;
        }

        @Override
        public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks) {
          return null;
        }

        @Override
        public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
          return null;
        }

        @Override
        public void removeAttachment(@NotNull PermissionAttachment attachment) {

        }

        @Override
        public void recalculatePermissions() {

        }

        @Override
        public @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions() {
          return null;
        }

        @Override
        public boolean isOp() {
          return false;
        }

        @Override
        public void setOp(boolean value) {

        }
      };
    }
    public static void setFlightBlocked(Player player, boolean blocked) {
      FlightBlocked.set(player, blocked);
    }
    public static boolean getFlightBlocked(Player player) {
      return FlightBlocked.get(player);
    }
    public static void setDamageBlocked(Player player, boolean blocked) {
      new DamageBlocked().set(player, blocked);

    }
    public static boolean getDamageBlocked(Player player) {
      return new DamageBlocked().get(player);
    }

  }
  class item {
    public static String createKey(Complex item) {
      return new ComplexItemKeyConstructer().createKey(item);
    }

    public static boolean hasKey(ItemStack itemStack, String key) {
      if (itemStack == null)return false;
      return new ComplexItemKeyConstructer().hasKey(itemStack, key);
    }
    public static String getKey(ItemStack itemStack) {
      return new ComplexItemKeyConstructer().getKey(itemStack);
    }
    public static ComplexItem create(Material material, Component displayName, List<Component> lores) {
      return new ComplexItem(material, displayName, lores);
    }
  }
}














