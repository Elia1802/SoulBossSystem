package de.elia;

import de.elia.api.TheZepserAPIMain;
import de.elia.bossfightcreator.BossFightCreator;
import de.elia.items.ItemMain;
import de.elia.soulboss.SoulBoss;
import de.elia.systemclasses.logging.PluginLogger;

public class PluginInstances {

  public static final TheZepserAPIMain THE_ZEPSER_API_MAIN = new TheZepserAPIMain();//Gets the Main class of the TheZepserAPI
  public static final ItemMain ITEM_MAIN = new ItemMain();//Gets the the Main class of Item
  public static final BossFightCreator BOSS_FIGHT_CREATOR = new BossFightCreator();//Gets the Main class of the bossfightcreator Plugin
  public static final SoulBoss SOUL_BOSS = new SoulBoss();//Gets the Main class of the SoulBoss Plugin
  public static final PluginLogger MAIN_LOGGER = new PluginLogger("SoulBossSystem -> SoulBossSystem");//A logger for the  SoulBossPlugin
  public static final PluginLogger BOSS_FIGHT_CREATOR_LOGGER = new PluginLogger("SoulBossSystem -> BossFightCreator");//A logger for the  BossFightCreator
  public static final PluginLogger SOUL_BOSS_LOGGER = new PluginLogger("SoulBossSystem -> SoulBoss");//A logger for the Plugin
  public static final PluginLogger THE_ZEPSER_API_LOGGER = new PluginLogger("SoulBossSystem -> TheZepserAPI");//A logger for the TheZepserAPI
  public static final PluginLogger ITEM_LOGGER = new PluginLogger("SoulBossSystem -> Item");

}
