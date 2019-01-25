package com.gmail.arkobat.EnchantControl;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.Map;

public class Anvil {

    public Anvil () {

    }

    private void loopEnchants(ItemStack item1, ItemStack item2) {
        if (item1 == null && item2 == null) {
            return;
        }
        if (item1.getType() == Material.ENCHANTED_BOOK && item2.getType() != Material.ENCHANTED_BOOK) {
            return;
        }
    }

    public int getLevelToAdd(ItemStack item1, ItemStack item2, Enchantment enchantment) {
        Map<Enchantment, Integer> enchants1;
        Map<Enchantment, Integer> enchants2;
        if (item1.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta bookEnchants = (EnchantmentStorageMeta) item1.getItemMeta();
            enchants1 = bookEnchants.getStoredEnchants();
        } else {
            enchants1 = item1.getEnchantments();
        }

        if (item2.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta bookEnchants = (EnchantmentStorageMeta) item1.getItemMeta();
            enchants2 = bookEnchants.getStoredEnchants();
        } else {
            enchants2 = item2.getEnchantments();
        }
        Map<Enchantment, Integer> newEnchants;

        return -1;
    }


}
