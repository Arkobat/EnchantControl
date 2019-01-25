package com.gmail.arkobat.EnchantControl.EventHandler.InventoryClick;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ClickMendingGUI {
    Check check;

    public ClickMendingGUI(Check check) {
        this.check = check;
    }

    public void checkMending(ItemStack clicked, ClickType type) {
        if (clicked.getItemMeta().getDisplayName().equals("§6§lInfinity")) {
            onClickInfinity(clicked, type);
        }
    }

    private void onClickInfinity(ItemStack clicked, ClickType clickType) {
        ItemMeta itemMeta = clicked.getItemMeta();
        List<String> lore = itemMeta.getLore();

        if (clickType == ClickType.LEFT) {
            if (lore.contains("§a §lStatus: §2§lEnabled")) {
                lore.set(lore.size()-3, "§a §lStatus: §4§lDisabled");
                lore.set(lore.size()-2, "§b Left-click §ato §2enable");
                check.enchantControl.enchantConfigSection.remove("70.infinity");
                check.enchantControl.clearConfigPath("enchants." +  "70.infinity");
            } else {
                lore.set(lore.size()-3, "§a §lStatus: §2§lEnabled");
                lore.set(lore.size()-2, "§b Left-click §ato §cdisable");
                check.enchantControl.enchantConfigSection.put("70.infinity", String.valueOf(true));
                check.enchantControl.writeToConfig("enchants." + "70.infinity", true);
            }
            itemMeta.setLore(lore);
            clicked.setItemMeta(itemMeta);
        }
    }
}
