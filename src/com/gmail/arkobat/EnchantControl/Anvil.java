package com.gmail.arkobat.EnchantControl;

import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

import java.util.HashMap;
import java.util.Map;

public class Anvil {
    private final EnchantControl enchantControl;
    private final GetEnchant getEnchant;
    private final EnchantHandler enchantHandler;

    public Anvil(EnchantControl enchantControl, GetEnchant getEnchant, EnchantHandler enchantHandler) {
        this.enchantControl = enchantControl;
        this.getEnchant = getEnchant;
        this.enchantHandler = enchantHandler;
    }

    public ItemStack getResultItem(ItemStack item1, ItemStack item2, ItemStack result) {
        if (!EnchantControl.setup || !EnchantControl.UNSAFE_ENCHANTS) {
            return result;
        }
        if (item1 == null) {
            return null;
        }
        if (item1.hasItemMeta()) {
            ItemMeta im = item1.getItemMeta();
            if (im.hasDisplayName()) {
                item1.getItemMeta().setDisplayName(im.getDisplayName());
            }
            if (im.hasLore()) {
                item1.getItemMeta().setLore(im.getLore());
            }
        }

        result = addEnchants(result, getResultEnchants(item1, item2));
        enchantHandler.checkItem(result, null);


        Repairable repairable = (Repairable) result.getItemMeta();
        if (repairable == null) {
            return result;
        }
        int repairPrice = repairable.getRepairCost();
        if (repairPrice > 15) {
            repairable.setRepairCost(15);
        }
        result.setItemMeta((ItemMeta) repairable);

        return result;
    }

    private Map<Enchantment, Integer> getResultEnchants(ItemStack item1, ItemStack item2) {
        Map<Enchantment, Integer> enchants = new HashMap<>();

        Map<Enchantment, Integer> item1Enchants;
        if (item1.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item1.getItemMeta();
            item1Enchants = meta.getStoredEnchants();
        } else {
            item1Enchants = item1.getEnchantments();
        }

        Map<Enchantment, Integer> item2Enchants = new HashMap<>();
        if (item2 != null) {
            if (item2.getType() == Material.ENCHANTED_BOOK) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item2.getItemMeta();
                item2Enchants = meta.getStoredEnchants();
            } else {
                item2Enchants = item2.getEnchantments();
            }
        }

        for (Enchantment enchant : item1Enchants.keySet()) {
            int value = item1Enchants.get(enchant);
            if (item2Enchants.keySet().contains(enchant)) {
                if (item2Enchants.get(enchant) == value) {
                    value++;
                } else if (item1Enchants.get(enchant) < item2Enchants.get(enchant)) {
                    value = item2Enchants.get(enchant);
                }
            }
            if (value > getMaxValue(enchant)) {
                value = getMaxValue(enchant);
            }
            enchants.put(enchant, value);
        }
        if (item2 == null) {
            return enchants;
        }
        for (Enchantment enchant : item2Enchants.keySet()) {
            if (canHaveEnchantment(item1.getType(), enchant)) {
                if (!enchants.containsKey(enchant)) {
                    enchants.put(enchant, item2Enchants.get(enchant));
                }
            }
        }
        return enchants;
    }

    private boolean canHaveEnchantment(Material type, Enchantment enchant) {
        if (type == Material.ENCHANTED_BOOK) {
            return true;
        }
        try {
            ItemStack is = new ItemStack(type);
            is.addEnchantment(enchant, 1);
            return is.getEnchantments().containsKey(enchant);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private int getMaxValue(Enchantment enchant) {
        if (!Boolean.valueOf(enchantControl.getConfigString("enchants." + getEnchant.getIDSting(enchant) + ".custom"))) {
            return 999;
        }
        String maxlvl = (enchantControl.getConfigString("enchants." + getEnchant.getIDSting(enchant) + ".maxLevel"));
        if (maxlvl == null) {
            return 999;
        }
        return Integer.parseInt(maxlvl);
    }

    private ItemStack addEnchants(ItemStack item, Map<Enchantment, Integer> enchants) {
        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            for (Enchantment enchant : enchants.keySet()) {
                meta.addStoredEnchant(enchant, enchants.get(enchant), true);
                item.setItemMeta(meta);
            }
            return item;
        }
        for (Enchantment enchant : enchants.keySet()) {
            item.addUnsafeEnchantment(enchant, enchants.get(enchant));
        }
        return item;
    }

}
