package de.elia.achivements.achievement.storage;

import org.jetbrains.annotations.NotNull;

import java.lang.String;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description This is the storage for all boss achievements.
 */
public enum Achievements {

  BOSSFIGHT("Bossfight", "904980", "Schlage das erste mal ein Boss!", 20),
  BOSSFIGHT_ZOMBIE("Erstes Zombie?", "443548", "Das erste Zombie als Boss!", 15),
  BOSSFIGHT_ZOMBIE_END("Zombieboss Hunter", "265969", "Sei der, der den Zombieboss getötet hat!", 50),
  BOSSFIGHT_ZOMBIE_MINI("Vorsicht! Unterstützung!", "820652", "Töte die Unterstützung vom Boss!", 20);//Coming Soon

  private final String name; //Is the name of the Achievement
  private final String dataID; //Is the dataID of the Achievement
  private final String target; //Is the target of the Achievement
  private final int xp; //Is the amount of xp of the Achievement

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Set the new Achievement
   * @param name Requires a name
   * @param dataID Requires a dataID
   * @param target Requires a target
   * @param xp Requires a amount of xp points
   */
  Achievements(String name, String dataID, String target, int xp){
    this.name = name;
    this.dataID = dataID;
    this.target = target;
    this.xp = xp;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the name of the Achievement
   * @return {@link #name}
   */
  @NotNull
  public String getName() {
    return name;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the dataID of the Achievement
   * @return {@link #dataID}
   */
  @NotNull
  public String dataID() {
    return dataID;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the target of the Achievement
   * @return {@link #target}
   */
  @NotNull
  public String target() {
    return target;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the amount of xp of the Achievement
   * @return {@link #xp}
   */
  public int xp() {
    return xp;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the name of the Achievement of this Class
   * @param achievement Requires a Achievement
   * @return {@link #name} + {@link #dataID} + {@link #target} + {@link #xp}
   */
  @NotNull
  public String achievement(Achievements achievement) {
    return this.name + this.dataID + this.target + this.xp;
  }
}
