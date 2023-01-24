package de.elia.soulboss.inventory;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.messages.messages.CustomMessages;
import de.elia.soulmain.allplugins.builders.item.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomInventories {

  public CustomInventories(){

  }

  public void inventory(@NotNull Player player){
    MiniMessage miniMessage = SoulBoss.soulBoss().miniMessage();
    CustomMessages customMessages = new CustomMessages();
    Inventory inventory;
    if (player.hasPermission("soulboss.admin")) {
      String string = "<red>Admin Panel</red>";
      Component inventoryName = miniMessage.deserialize(string);
      inventory = Bukkit.createInventory(null, 17, inventoryName);
      Component itemName1 = miniMessage.deserialize("<green>Start</green>");
      Component lore1 = miniMessage.deserialize("<dark_purple>Click to start the Bossfight!</dark_purple>");
      inventory.setItem(3, new ItemBuilder(Material.GREEN_WOOL).displayName(itemName1).lore(List.of(lore1)).build());
      Component itemName2 = miniMessage.deserialize("<red>Stop</red>");
      Component lore2 = miniMessage.deserialize("<dark_purple>Click to stop the Bossfight!</dark_purple>");
      inventory.setItem(5, new ItemBuilder(Material.RED_WOOL).displayName(itemName2).lore(List.of(lore2)).build());
      Component itemName3 = miniMessage.deserialize("<gold>Help</gold>");
      Component lore3 = miniMessage.deserialize("<dark_purple>Click to see a Help for all functions!</dark_purple>");
      inventory.setItem(11, new ItemBuilder(Material.ORANGE_WOOL).displayName(itemName3).lore(List.of(lore3)).build());
      Component itemName4 = miniMessage.deserialize("<dark_aqua>Information</dark_aqua>");
      Component lore4 = miniMessage.deserialize("<dark_purple>Click to see all information!</dark_purple>");
      inventory.setItem(13, new ItemBuilder(Material.GRAY_WOOL).displayName(itemName4).lore(List.of(lore4)).build());
      Component itemName5 = miniMessage.deserialize("<blue>Permissions</blue>");
      Component lore5 = miniMessage.deserialize("<dark_purple>Click to see all Permission!</dark_purple>");
      inventory.setItem(15, new ItemBuilder(Material.RED_WOOL).displayName(itemName5).lore(List.of(lore5)).build());
      player.closeInventory();
      player.openInventory(inventory);
      return;
    }else customMessages.messageWithPrefix(player, "<red>You have don't the permission for this action!</red>");
    if (player.hasPermission("soulboss.dev")) {
      String string = "<red>Admin Panel</red>";
      Component inventoryName = miniMessage.deserialize(string);
      inventory = Bukkit.createInventory(null, 17, inventoryName);
      Component itemName1 = miniMessage.deserialize("<gold>Help</gold>");
      Component lore1 = miniMessage.deserialize("<dark_purple>Click to see a Help for all functions!</dark_purple>");
      inventory.setItem(11, new ItemBuilder(Material.ORANGE_WOOL).displayName(itemName1).lore(List.of(lore1)).build());
      Component itemName2 = miniMessage.deserialize("<dark_aqua>Information</dark_aqua>");
      Component lore2 = miniMessage.deserialize("<dark_purple>Click to see all information!</dark_purple>");
      inventory.setItem(13, new ItemBuilder(Material.GRAY_WOOL).displayName(itemName2).lore(List.of(lore2)).build());
      Component itemName3 = miniMessage.deserialize("<blue>Permissions</blue>");
      Component lore3 = miniMessage.deserialize("<dark_purple>Click to see all Permission!</dark_purple>");
      inventory.setItem(15, new ItemBuilder(Material.RED_WOOL).displayName(itemName3).lore(List.of(lore3)).build());
      player.closeInventory();
      player.openInventory(inventory);
      return;
    }else customMessages.messageWithPrefix(player, "<red>You have don't the permission for this action!</red>");
    if (player.hasPermission("soulboss.spawn")) {
      String string = "<red>Admin Panel</red>";
      Component inventoryName = miniMessage.deserialize(string);
      inventory = Bukkit.createInventory(null, 17, inventoryName);
      Component itemName1 = miniMessage.deserialize("<green>Start</green>");
      Component lore1 = miniMessage.deserialize("<dark_purple>Click to start the Bossfight!</dark_purple>");
      inventory.setItem(3, new ItemBuilder(Material.GREEN_WOOL).displayName(itemName1).lore(List.of(lore1)).build());
      Component itemName2 = miniMessage.deserialize("<red>Stop</red>");
      Component lore2 = miniMessage.deserialize("<dark_purple>Click to stop the Bossfight!</dark_purple>");
      inventory.setItem(5, new ItemBuilder(Material.RED_WOOL).displayName(itemName2).lore(List.of(lore2)).build());
      player.closeInventory();
      player.openInventory(inventory);
    }else customMessages.messageWithPrefix(player, "<red>You have don't the permission for this action!</red>");
  }

}
