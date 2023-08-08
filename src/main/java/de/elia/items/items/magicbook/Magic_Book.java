/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  de.elia.api.thezepserapi.Complex
 *  de.elia.api.thezepserapi.TheZepserAPI$item
 *  de.elia.api.thezepserapi.components.ComplexItem
 *  net.kyori.adventure.text.Component
 *  org.bukkit.Material
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeModifier$Operation
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.inventory.EquipmentSlot
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.plugin.Plugin
 */
package de.elia.items.items.magicbook;

import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.TheZepserAPI;
import de.elia.api.thezepserapi.components.ComplexItem;

import de.elia.items.ItemMain;

import net.kyori.adventure.text.Component;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

/**
 * @author Elia, Zopnote
 * @description This class create the item magic book
 */
public class Magic_Book {

    private static ComplexItem MB;

    public Magic_Book(Plugin plugin) {
        if (MB == null) {
            ArrayList<Component> list = new ArrayList<>();
            list.add(ItemMain.MINI_MESSAGE.deserialize("<gray>You can used this book</gray>"));
            list.add(ItemMain.MINI_MESSAGE.deserialize("<gray>to cast spells.</gray>"));
            Component name = ItemMain.MINI_MESSAGE.deserialize("<obfuscated>#</obfuscated> <dark_purple>Magic Book</dark_purple> <obfuscated>#</obfuscated>");
            MB = TheZepserAPI.item.create(Material.BOOK, name, list).setCustomModelData(1).setKey(Complex.MAGIC_BOOK, plugin).setAmount(1).addAttribute(Attribute.GENERIC_MAX_HEALTH, 20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND).addFlag(ItemFlag.HIDE_ATTRIBUTES).addFlag(ItemFlag.HIDE_ENCHANTS).addEnchantment(Enchantment.ARROW_FIRE, 1).save();
        }
    }
}

