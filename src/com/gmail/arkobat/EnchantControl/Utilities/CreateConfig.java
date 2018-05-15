package com.gmail.arkobat.EnchantControl.Utilities;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import org.bukkit.enchantments.Enchantment;

// https://hastebin.com/hozopehiqu.scala
public class CreateConfig {

    private final EnchantControl enchantControl;

    public CreateConfig(EnchantControl enchantControl) {
        this.enchantControl = enchantControl;
    }

    public void createStandard() {
        addToConfigSection("enchants.0.name", "Protection");
        addToConfigSection("enchants.0.bukkitName", "PROTECTION_ENVIRONMENTAL");
        addToConfigSection("enchants.0.ver", 1.01);

        addToConfigSection("enchants.1.name", "Fire Protection");
        addToConfigSection("enchants.1.bukkitName", "PROTECTION_FIRE");
        addToConfigSection("enchants.1.ver", 1.01);

        addToConfigSection("enchants.2.name", "Feather Falling");
        addToConfigSection("enchants.2.bukkitName", "PROTECTION_FALL");
        addToConfigSection("enchants.2.ver", 1.01);

        addToConfigSection("enchants.3.name", "Blast Protection");
        addToConfigSection("enchants.3.bukkitName", "PROTECTION_EXPLOSIONS");
        addToConfigSection("enchants.3.ver", 1.0);

        addToConfigSection("enchants.4.name", "Projectile Protection");
        addToConfigSection("enchants.4.bukkitName", "PROTECTION_PROJECTILE");
        addToConfigSection("enchants.4.ver", 1.0);

        addToConfigSection("enchants.5.name", "Respiration");
        addToConfigSection("enchants.5.bukkitName", "OXYGEN");
        addToConfigSection("enchants.5.ver", 1.0);

        addToConfigSection("enchants.6.name", "Aqua Affinity");
        addToConfigSection("enchants.6.bukkitName", "WATER_WORKER");
        addToConfigSection("enchants.6.ver", 1.0);

        addToConfigSection("enchants.7.name", "Thorns");
        addToConfigSection("enchants.7.bukkitName", "THORNS");
        addToConfigSection("enchants.7.ver", 1.0);

        addToConfigSection("enchants.8.name", "Depth Strider");
        addToConfigSection("enchants.8.bukkitName", "DEPTH_STRIDER");
        addToConfigSection("enchants.8.ver", 1.4);

        addToConfigSection("enchants.9.name", "Frost Walker");
        addToConfigSection("enchants.9.bukkitName", "FROST_WALKER");
        addToConfigSection("enchants.9.ver", 1.9);

        addToConfigSection("enchants.10.name", "Curse of Binding");
        addToConfigSection("enchants.10.bukkitName", "BINDING_CURSE");
        addToConfigSection("enchants.10.ver", 1.11);

        addToConfigSection("enchants.16.name", "Sharpness");
        addToConfigSection("enchants.16.bukkitName", "DAMAGE_ALL");
        addToConfigSection("enchants.16.ver", 1.0);

        addToConfigSection("enchants.17.name", "Smite");
        addToConfigSection("enchants.17.bukkitName", "DAMAGE_UNDEAD");
        addToConfigSection("enchants.17.ver", 1.0);

        addToConfigSection("enchants.18.name", "Bane of Arthropods");
        addToConfigSection("enchants.18.bukkitName", "DAMAGE_ARTHROPODS");
        addToConfigSection("enchants.18.ver", 1.0);

        addToConfigSection("enchants.19.name", "Knockback");
        addToConfigSection("enchants.19.bukkitName", "KNOCKBACK");
        addToConfigSection("enchants.19.ver", 1.0);

        addToConfigSection("enchants.20.name", "Fire Aspect");
        addToConfigSection("enchants.20.bukkitName", "FIRE_ASPECT");
        addToConfigSection("enchants.20.ver", 1.0);

        addToConfigSection("enchants.21.name", "Looting");
        addToConfigSection("enchants.21.bukkitName", "LOOT_BONUS_MOBS");
        addToConfigSection("enchants.21.ver", 1.0);

        addToConfigSection("enchants.22.name", "Sweeping Edge");
        addToConfigSection("enchants.22.bukkitName", "SWEEPING_EDGE");
        addToConfigSection("enchants.22.ver", 1.11);

        addToConfigSection("enchants.32.name", "Efficiency");
        addToConfigSection("enchants.32.bukkitName", "DIG_SPEED");
        addToConfigSection("enchants.32.ver", 1.0);

        addToConfigSection("enchants.33.name", "Silk Touch");
        addToConfigSection("enchants.33.bukkitName", "SILK_TOUCH");
        addToConfigSection("enchants.33.ver", 1.0);

        addToConfigSection("enchants.34.name", "Unbreaking");
        addToConfigSection("enchants.34.bukkitName", "DURABILITY");
        addToConfigSection("enchants.34.ver", 1.0);

        addToConfigSection("enchants.35.name", "Fortune");
        addToConfigSection("enchants.35.bukkitName", "LOOT_BONUS_BLOCKS");
        addToConfigSection("enchants.35.ver", 1.0);

        addToConfigSection("enchants.48.name", "Power");
        addToConfigSection("enchants.48.bukkitName", "ARROW_DAMAGE");
        addToConfigSection("enchants.48.ver", 1.01);

        addToConfigSection("enchants.49.name", "Punch");
        addToConfigSection("enchants.49.bukkitName", "ARROW_KNOCKBACK");
        addToConfigSection("enchants.49.ver", 1.01);

        addToConfigSection("enchants.50.name", "Flame");
        addToConfigSection("enchants.50.bukkitName", "ARROW_FIRE");
        addToConfigSection("enchants.50.ver", 1.01);

        addToConfigSection("enchants.51.name", "Infinity");
        addToConfigSection("enchants.51.bukkitName", "ARROW_INFINITE");
        addToConfigSection("enchants.51.ver", 1.01);

        addToConfigSection("enchants.61.name", "Luck of the Sea");
        addToConfigSection("enchants.61.bukkitName", "LUCK");
        addToConfigSection("enchants.61.ver", 1.7);

        addToConfigSection("enchants.62.name", "Lure");
        addToConfigSection("enchants.62.bukkitName", "LURE");
        addToConfigSection("enchants.62.ver", 1.7);

        addToConfigSection("enchants.70.name", "Mending");
        addToConfigSection("enchants.70.bukkitName", "MENDING");
        addToConfigSection("enchants.70.ver", 1.9);

        addToConfigSection("enchants.71.name", "Curse of Vanishing");
        addToConfigSection("enchants.71.bukkitName", "VANISHING_CURSE");
        addToConfigSection("enchants.71.ver", 1.11);
    }

    private void addToConfigSection(String path, String value) {
        if (!enchantControl.getConfig().contains(path)) {
            enchantControl.enchantConfigSection.set(path, value);
        }
    }

    private void addToConfigSection(String path, double value) {
        if (!enchantControl.getConfig().contains(path)) {
            enchantControl.enchantConfigSection.set(path, value);
        }
    }

/*
    public void defineIlleagalEnchants() {
        for (String section : enchantControl.enchantConfigSection.getKeys(false)) {
            if (enchantControl.enchantConfigSection.contains(section + ".disabled")) {
                if (enchantControl.enchantConfigSection.getBoolean(section + ".disabled")) {
                    enchantControl.disabledEnchants.add(Enchantment.getByName(section + ".bukkitName"));
                }
            }
            if (enchantControl.enchantConfigSection.contains(section + ".maxLevel")) {
                enchantControl.maxLevel.put(Enchantment.getByName(section + ".bukkitName"), enchantControl.enchantConfigSection.getInt(section + "maxLevel"));
            }
        }
    }
*/
}
