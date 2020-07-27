package com.gmail.arkobat.EnchantControl;

import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
        result = checkMendingInfinity(item1, item2, result);
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
        if (item1.getType() == XMaterial.ENCHANTED_BOOK.parseMaterial()) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item1.getItemMeta();
            item1Enchants = meta.getStoredEnchants();
        } else {
            item1Enchants = item1.getEnchantments();
        }

        Map<Enchantment, Integer> item2Enchants = new HashMap<>();
        if (item2 != null) {
            if (item2.getType() == XMaterial.ENCHANTED_BOOK.parseMaterial()) {
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
            } else {
                if (enchants.containsKey(enchant)) {
                    enchants.remove(enchant);
                }
            }
        }
        return enchants;
    }

    private boolean canHaveEnchantment(Material type, Enchantment enchant) {
        if (type == XMaterial.ENCHANTED_BOOK.parseMaterial()) {
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
        if (item == null || item.getType() == Material.AIR) {
            return item;
        }
        if (item.getType() == XMaterial.ENCHANTED_BOOK.parseMaterial()) {
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

    public ItemStack checkMendingInfinity(ItemStack item1, ItemStack item2, ItemStack result) {
        if (EnchantControl.VERSION < 1.11) {
            return result;
        }
        if (item1 == null || item2 == null) {
            return result;
        }
        if (item1.getType() != Material.BOW && (item2.getType() != Material.BOW || item2.getType() != Material.ENCHANTED_BOOK)) {
            return result;
        }
        if (!enchantControl.enchantConfigSection.containsKey("70.infinity")) {
            return result;
        }
        if (!Boolean.parseBoolean(enchantControl.enchantConfigSection.get("70.infinity"))) {
            return result;
        }
        Map<Enchantment, Integer> enchant1 = item1.getEnchantments();
        Map<Enchantment, Integer> enchant2;
        if (item2.getType() == XMaterial.ENCHANTED_BOOK.parseMaterial()) {
            enchant2 = item2.getItemMeta().getEnchants();
        } else {
            enchant2 = item2.getEnchantments();
        }
        if (result.getType() == Material.AIR) {
            result = new ItemStack(item1.getType());
            result.setAmount(item1.getAmount());
            result.setData(item1.getData());
            if (item1.hasItemMeta()) {
                result.setItemMeta(item1.getItemMeta());
            }
        }
        if (enchant1.containsKey(Enchantment.ARROW_INFINITE) && enchant2.containsKey(Enchantment.MENDING)) {
            result.addUnsafeEnchantment(Enchantment.MENDING, 1);
        } else if (enchant1.containsKey(Enchantment.MENDING) && enchant2.containsKey(Enchantment.ARROW_INFINITE)) {
            result.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        }
        return result;
    }

}
