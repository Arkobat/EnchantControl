package com.gmail.arkobat.EnchantControl.EventHandler.InventoryClick;

import org.bukkit.ChatColor;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ClickWeightGUI {
    Check check;

    public ClickWeightGUI(Check check) {
        this.check = check;
    }

    public void onClickGUIs(ItemStack clicked, ClickType type) {
        if (clicked.hasItemMeta()) {
            if (clicked.getItemMeta().hasDisplayName()) {
                onClickItem(clicked, type);
            }
        }
    }

    private void onClickItem(ItemStack clicked, ClickType clickType) {
        ItemMeta itemMeta = clicked.getItemMeta();
        List<String> lore = itemMeta.getLore();
        String id = getID(clicked.getItemMeta().getDisplayName());
        int weight;
        if (lore.contains("§a Current max level: §b§lNot set")) {
            weight = -1;
        } else {
            weight = Integer.parseInt(lore.get(lore.size() - 2).replace("§a   Current weight: §b§l", ""));
        }
        if (clickType  == ClickType.LEFT) {
            if (weight < 999) {
                weight = (weight < 1) ? 1 : weight + 1;
            }
        } else if (clickType == ClickType.RIGHT) {
            if (weight > 1) {
                weight--;
            }
        }
        if (weight != -1) {
            lore.set(lore.size() - 2, "§a   Current weight: §b§l" + weight);
            check.enchantControl.enchantConfigSection.put(id + ".weight", String.valueOf(weight));
            check.enchantControl.writeToConfig("enchants." + id + ".weight", weight);
        }
        itemMeta.setLore(lore);
        clicked.setItemMeta(itemMeta);
    }

    private String getID(String itemName) {
        return check.getEnchant.getIDSting(ChatColor.stripColor(itemName));
    }
}
