package de.elia.soulboss.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is Information Class of this Plugin.
 */
public class ThisPlugin {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is gets the Authors.
   * @return "Elia, D1p4k and Zopnote."
   */
  @NotNull
  public String author(){
    return "Elia, D1p4k and Zopnote.";
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is gets the version.
   * @return "1.0"
   */
  @NotNull
  public String version(){
    return "1.0";
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is gets the contact.
   * @return "～Elia～#0001"
   */
  @NotNull
  public String contact(){
    return "～Elia～#0001";
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is gets the description.
   * @return string
   */
  @NotNull
  public String description(){
    String string = "Dieses Plugin wurde für SoulSMP geschrieben. Ich hab mir richtig viel Mühe gegeben und hoffe das" +
      "es euch wirklich sehr gefällt. Ich weiß, dieses Plugin verändert den kompletten Server, aber manchmal" +
      "ist ne Veränderung doch schon wichtig. Dieses Plugin sorgt dafür, das ihr ein komplett ein eigenen" +
      "Bossfight euch erspielen könnt. Da ich aber noch ne Menge Ideen brauche schreibt gerne eine Idee mit" +
      "dem Command /mobidea eine Idee.";
    return string;
  }

}
