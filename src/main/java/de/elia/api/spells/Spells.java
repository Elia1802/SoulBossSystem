package de.elia.api.spells;

import de.elia.api.spells.spells.fire.FIRE_ATTACK;
import de.elia.api.spells.spells.fire.FIRE_DEFENSE;
import de.elia.api.spells.spells.fire.FIRE_SUPER;
import de.elia.api.spells.spells.gravitation.GRAVITATION_ATTACK;
import de.elia.api.spells.spells.gravitation.GRAVITATION_DEFENSE;
import de.elia.api.spells.spells.gravitation.GRAVITATION_SUPER;
import de.elia.api.spells.spells.weather.WEATHER_ATTACK;
import de.elia.api.spells.spells.weather.WEATHER_DEFENSE;
import de.elia.api.spells.spells.weather.WEATHER_SUPER;
import org.bukkit.entity.Player;

public interface Spells {
  static void FIRE_ATTACK(Player player, boolean pvp) {
    new FIRE_ATTACK().spawn(player, pvp);
  }
  static void FIRE_DEFENSE(Player player, boolean pvp) {
    new FIRE_DEFENSE().spawn(player, pvp);
  }
  static void FIRE_SUPER(Player player, boolean pvp) {
    new FIRE_SUPER().spawn(player, pvp);
  }
  static void WEATHER_ATTACK(Player player, boolean pvp) {
    new WEATHER_ATTACK().spawn(player, pvp);
  }
  static void WEATHER_DEFENSE(Player player, boolean pvp) {
    new WEATHER_DEFENSE().spawn(player, pvp);
  }
  static void WEATHER_SUPER(Player player) {
    new WEATHER_SUPER().spawn(player);
  }
  static void GRAVITATION_ATTACK(Player player, boolean pvp) {
    new GRAVITATION_ATTACK().spawn(player, pvp);
  }
  static void GRAVITATION_DEFENSE(Player player, boolean pvp) {
    new GRAVITATION_DEFENSE(player, pvp);
  }
  static void GRAVITATION_SUPER(Player player, boolean pvp) {
    new GRAVITATION_SUPER().spawn(player, pvp);
  }
}
