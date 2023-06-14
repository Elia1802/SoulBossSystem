package de.elia.items.items.magicbook;

import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.TheZepserAPI;
import de.elia.api.thezepserapi.components.ComplexItem;

import net.kyori.adventure.text.Component;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

import static de.elia.items.ItemMain.MINI_MESSAGE;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @implements {@link Listener}
 * @description Create the Magic book
 */
public class Magic_Book implements Listener {

  private static ComplexItem MB;

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description The Magic book builder
   * @param plugin Requires a instance of the Plugin (is Plugin null generate exception)
   */
  public Magic_Book(Plugin plugin){
    if (MB == null) {
      List<Component> list = new ArrayList<>();
      list.add(MINI_MESSAGE.deserialize("<gray>You can used this book</gray>"));
      list.add(MINI_MESSAGE.deserialize("<gray>to cast spells.</gray>"));
      Component name = MINI_MESSAGE.deserialize("<obfuscated>#</obfuscated> <dark_purple>Magic Book</dark_purple> <obfuscated>#</obfuscated>");
      MB = TheZepserAPI.item.create(Material.BOOK, name, list)
        .setCustomModelData(1)
        .setKey(Complex.MAGIC_BOOK)
        .setAmount(1)
        .addAttribute(Attribute.GENERIC_MAX_HEALTH, 20, Operation.ADD_NUMBER, EquipmentSlot.HAND)
        .addFlag(ItemFlag.HIDE_ATTRIBUTES)
        .addFlag(ItemFlag.HIDE_ENCHANTS)
        .addEnchantment(Enchantment.ARROW_FIRE, 1)
        .save();
    }
  }


}
