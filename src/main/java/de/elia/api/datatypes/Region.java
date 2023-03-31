package de.elia.api.datatypes;

import de.elia.api.enums.RegionType;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Region {
  private Location center;
  private double radius;
  private World world;
  private Player owner;
  private RegionType type;
  private boolean pvp;

  public Region(Location center, double radius, RegionType type, Player owner, boolean pvp) {
    this.pvp = pvp;
    this.world = center.getWorld();
    this.center = center;
    this.radius = radius;
    this.owner = owner;
    this.type = type;
  }
  public World getWorld(){
    return world;
  }
  public Location getCenter() {
    return center;
  }
  public double getRadius() {
    return radius;
  }
  public Player getOwner() {
    return owner;
  }
  public RegionType getType() {
    return type;
  }
  public boolean getPvP() {
    return pvp;
  }
  public void clear() {
    this.world=null;
    this.center=null;
    this.radius=0;
    this.owner=null;
    this.type=RegionType.NORMAL;
    this.pvp=false;
  }
}
