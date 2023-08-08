package de.elia.achivements.achievement.storage;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description This enum saves all achievements of this Plugin.
 */
public enum Achievements {

  BOSSFIGHT("Bossfight", "904980", "Schlage das erste mal ein Boss!", 20),
  BOSSFIGHT_ZOMBIE("Erstes Zombie?", "443548", "Das erste Zombie als Boss!", 15),
  BOSSFIGHT_ZOMBIE_END("Zombieboss Hunter", "265969", "Sei der, der den Zombieboss getötet hat!", 50),
  BOSSFIGHT_ZOMBIE_MINI("Vorsicht! Unterstützung!", "820652", "Töte die Unterstützung vom Boss!", 20);

  private final String name;
  private final String dataID;
  private final String target;
  private final int xp;

  /**
   * @description Create a new Achievement
   * @param name Requires a name for the achievement.
   * @param dataID Requires a data id for the achievement.
   * @param target Requires a target for the achievement.
   * @param xp Requires a value of xp for the achievement.
   */
  Achievements(String name, String dataID, String target, int xp) {
    this.name = name;
    this.dataID = dataID;
    this.target = target;
    this.xp = xp;
  }

  /**
   * @description Gets the name of a achievement.
   * @return Return the name of the achievement.
   */
  @NotNull
  public String getName() {
    return this.name;
  }

  /**
   * @description Gets the data id of a achievement.
   * @return Return the data id of the achievement.
   */
  @NotNull
  public String dataID() {
    return this.dataID;
  }

  /**
   * @description Gets the target of a achievement.
   * @return Return the target of the achievement.
   */
  @NotNull
  public String target() {
    return this.target;
  }

  /**
   * @description Gets the xp of a achievement.
   * @return Return the xp of the achievement.
   */
  public int xp() {
    return this.xp;
  }
}
