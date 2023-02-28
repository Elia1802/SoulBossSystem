package de.elia.soulboss.plugin.load.start.register.world;

import de.elia.soulboss.SoulBoss;

import java.lang.String;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Load a new world with the custom generator of this plugin.
 */
public class WorldLoader {

  public WorldLoader(){
    //...
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load a new world with the custom generator of this plugin.
   * @param name Requrires a name for the world
   * @param id Requires a id of the world
   */
  public void loadWorld(String name, String id){
    SoulBoss.soulBoss().utils().getDefaultWorldGenerator(name, id);
  }

}
