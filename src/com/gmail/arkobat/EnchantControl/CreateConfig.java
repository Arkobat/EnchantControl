package com.gmail.arkobat.EnchantControl;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;

// https://hastebin.com/hozopehiqu.scala
public class CreateConfig {

    private final EnchantControl enchantControl;

    public CreateConfig(EnchantControl enchantControl) {
        this.enchantControl = enchantControl;
    }

    public void createStandard() {
        enchantControl.ifNotThenAdd("enchants.0.name", "Protection");
        enchantControl.ifNotThenAdd("enchants.0.bukkitName", "PROTECTION_ENVIRONMENTAL");
        enchantControl.ifNotThenAdd("enchants.0.ver", "1.01");

        enchantControl.ifNotThenAdd("enchants.1.name", "Fire Protection");
        enchantControl.ifNotThenAdd("enchants.1.bukkitName", "PROTECTION_FIRE");
        enchantControl.ifNotThenAdd("enchants.1.ver", "1.01");

        enchantControl.ifNotThenAdd("enchants.2.name", "Feather Falling");
        enchantControl.ifNotThenAdd("enchants.2.bukkitName", "PROTECTION_FALL");
        enchantControl.ifNotThenAdd("enchants.2.ver", "1.01");

        enchantControl.ifNotThenAdd("enchants.3.name", "Blast Protection");
        enchantControl.ifNotThenAdd("enchants.3.bukkitName", "PROTECTION_EXPLOSIONS");
        enchantControl.ifNotThenAdd("enchants.3.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.4.name", "Projectile Protection");
        enchantControl.ifNotThenAdd("enchants.4.bukkitName", "PROTECTION_PROJECTILE");
        enchantControl.ifNotThenAdd("enchants.4.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.5.name", "Respiration");
        enchantControl.ifNotThenAdd("enchants.5.bukkitName", "OXYGEN");
        enchantControl.ifNotThenAdd("enchants.5.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.6.name", "Aqua Affinity");
        enchantControl.ifNotThenAdd("enchants.6.bukkitName", "WATER_WORKER");
        enchantControl.ifNotThenAdd("enchants.6.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.7.name", "Thorns");
        enchantControl.ifNotThenAdd("enchants.7.bukkitName", "THORNS");
        enchantControl.ifNotThenAdd("enchants.7.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.8.name", "Depth Strider");
        enchantControl.ifNotThenAdd("enchants.8.bukkitName", "DEPTH_STRIDER");
        enchantControl.ifNotThenAdd("enchants.8.ver", "1.4");

        enchantControl.ifNotThenAdd("enchants.9.name", "Frost Walker");
        enchantControl.ifNotThenAdd("enchants.9.bukkitName", "FROST_WALKER");
        enchantControl.ifNotThenAdd("enchants.9.ver", "1.9");

        enchantControl.ifNotThenAdd("enchants.10.name", "Curse of Binding");
        enchantControl.ifNotThenAdd("enchants.10.bukkitName", "BINDING_CURSE");
        enchantControl.ifNotThenAdd("enchants.10.ver", "1.11");

        enchantControl.ifNotThenAdd("enchants.16.name", "Sharpness");
        enchantControl.ifNotThenAdd("enchants.16.bukkitName", "DAMAGE_ALL");
        enchantControl.ifNotThenAdd("enchants.16.ver", "1.0 ");

        enchantControl.ifNotThenAdd("enchants.17.name", "Smite");
        enchantControl.ifNotThenAdd("enchants.17.bukkitName", "DAMAGE_UNDEAD");
        enchantControl.ifNotThenAdd("enchants.17.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.18.name", "Bane of Arthropods");
        enchantControl.ifNotThenAdd("enchants.18.bukkitName", "DAMAGE_ARTHROPODS");
        enchantControl.ifNotThenAdd("enchants.18.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.19.name", "Knockback");
        enchantControl.ifNotThenAdd("enchants.19.bukkitName", "KNOCKBACK");
        enchantControl.ifNotThenAdd("enchants.19.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.20.name", "Fire Aspect");
        enchantControl.ifNotThenAdd("enchants.20.bukkitName", "FIRE_ASPECT");
        enchantControl.ifNotThenAdd("enchants.20.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.21.name", "Looting");
        enchantControl.ifNotThenAdd("enchants.21.bukkitName", "LOOT_BONUS_MOBS");
        enchantControl.ifNotThenAdd("enchants.21.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.22.name", "Sweeping Edge");
        enchantControl.ifNotThenAdd("enchants.22.bukkitName", "SWEEPING_EDGE");
        enchantControl.ifNotThenAdd("enchants.22.ver", "1.11");

        enchantControl.ifNotThenAdd("enchants.32.name", "Efficiency");
        enchantControl.ifNotThenAdd("enchants.32.bukkitName", "DIG_SPEED");
        enchantControl.ifNotThenAdd("enchants.32.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.33.name", "Silk Touch");
        enchantControl.ifNotThenAdd("enchants.33.bukkitName", "SILK_TOUCH");
        enchantControl.ifNotThenAdd("enchants.33.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.34.name", "Unbreaking");
        enchantControl.ifNotThenAdd("enchants.34.bukkitName", "DURABILITY");
        enchantControl.ifNotThenAdd("enchants.34.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.35.name", "Fortune");
        enchantControl.ifNotThenAdd("enchants.35.bukkitName", "LOOT_BONUS_BLOCKS");
        enchantControl.ifNotThenAdd("enchants.35.ver", "1.0");

        enchantControl.ifNotThenAdd("enchants.48.name", "Power");
        enchantControl.ifNotThenAdd("enchants.48.bukkitName", "ARROW_DAMAGE");
        enchantControl.ifNotThenAdd("enchants.48.ver", "1.01");

        enchantControl.ifNotThenAdd("enchants.49.name", "Punch");
        enchantControl.ifNotThenAdd("enchants.49.bukkitName", "ARROW_KNOCKBACK");
        enchantControl.ifNotThenAdd("enchants.49.ver", "1.01");

        enchantControl.ifNotThenAdd("enchants.50.name", "Flame");
        enchantControl.ifNotThenAdd("enchants.50.bukkitName", "ARROW_FIRE");
        enchantControl.ifNotThenAdd("enchants.50.ver", "1.01");

        enchantControl.ifNotThenAdd("enchants.51.name", "Infinity");
        enchantControl.ifNotThenAdd("enchants.51.bukkitName", "ARROW_INFINITE");
        enchantControl.ifNotThenAdd("enchants.51.ver", "1.01");

        enchantControl.ifNotThenAdd("enchants.61.name", "Luck of the Sea");
        enchantControl.ifNotThenAdd("enchants.61.bukkitName", "LUCK");
        enchantControl.ifNotThenAdd("enchants.61.ver", "1.7");

        enchantControl.ifNotThenAdd("enchants.62.name", "Lure");
        enchantControl.ifNotThenAdd("enchants.62.bukkitName", "LURE");
        enchantControl.ifNotThenAdd("enchants.62.ver", "1.7");

        enchantControl.ifNotThenAdd("enchants.70.name", "Mending");
        enchantControl.ifNotThenAdd("enchants.70.bukkitName", "MENDING");
        enchantControl.ifNotThenAdd("enchants.70.ver", "1.9");

        enchantControl.ifNotThenAdd("enchants.71.name", "Curse of Vanishing");
        enchantControl.ifNotThenAdd("enchants.71.bukkitName", "VANISHING_CURSE");
        enchantControl.ifNotThenAdd("enchants.71.ver", "1.11");
    }

    public void defineIlleagalEnchants() {
        for (String section : enchantControl.enchants.getKeys(false)) {
            if (enchantControl.enchants.contains(section + ".disabled")) {
                if (enchantControl.enchants.getBoolean(section + ".disabled")) {
                    enchantControl.disabledEnchants.add(Enchantment.getByName(section + ".bukkitName"));
                }
            }
            if (enchantControl.enchants.contains(section + ".maxLevel")) {
                enchantControl.maxLevel.put(Enchantment.getByName(section + ".bukkitName"), enchantControl.enchants.getInt(section + "maxLevel"));
            }
        }
    }

}
