package de.elia.party;

import de.elia.PluginMain;
import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Party {

  private final PluginMessages message = new PluginMessages();

  private final Player owner;
  private final String name;
  private final Set<Player> members;

  public Party(Player owner, String name){
    this.owner = owner;
    this.name = name;
    this.members = new HashSet<>();
    this.owner.getPersistentDataContainer().set(new NamespacedKey(PluginMain.main(), name + "-owner"), PersistentDataType.BYTE, (byte) 1);
    this.members.add(owner);
    this.message.message(owner, message.gray("Die Party ").append(message.aqua(this.name)).append(message.aqua(" wurde erfolgreich erstellt!")));
    this.message.message(owner, message.gray("Du kannst mit dem Befehl").append(message.aqua(" /party add ")).append(message.aqua(this.name)).append(message.aqua(" [PLAYER] ")).append(message.gray("ein neuen Spieler hinzufügen!")));
    this.message.message(owner, message.gray("Und mit dem Befehl").append(message.aqua(" /party remove ")).append(message.aqua(this.name)).append(message.aqua(" [PLAYER] ")).append(message.gray("kannst du ein Spieler aus der Party löschen!")));
  }

  public void addPlayer(Player newMember){
    this.members.add(newMember);
  }

  public void removePlayer(Player target) {
    members.remove(target);
  }

  @NotNull
  public Set<Player> members(){
    return this.members;
  }

  @NotNull
  public String name(){
    return this.name;
  }

  @NotNull
  public Player owner(){
    return this.owner;
  }
}
