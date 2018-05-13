package com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ClickMainGUI{
    Check check;
    public ClickMainGUI(Check check) {
        this.check = check;
    }

    public void onClickMain(ItemStack clicked, Player p, int slot, ClickType clickType) {
        if (clicked.hasItemMeta()) {
            if (clicked.getItemMeta().hasDisplayName()) {
                if (clicked.getItemMeta().getDisplayName().equals("§6§lSettings")) {
                    if (clickType == ClickType.LEFT) {
                        p.openInventory(check.setupGUI.inventory);
                    }
                } else if (clicked.getItemMeta().hasLore()) {
                    if (clickType == ClickType.LEFT) {
                        if (clicked.getItemMeta().getLore().get(0).contains("§a§lStatus: ")) {
                            onClickMainEnchant(clicked, slot);
                        }
                    }
                }
            }
        }
    }

    private void onClickMainEnchant(ItemStack clicked, int slot) {
        ItemMeta itemMeta = clicked.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (itemMeta.getLore().contains("§a§lStatus: §2§lEnabled")) {
            lore.add("§a§lStatus: §4§lDisabled");
            lore.add("§bLeft-click §ato §2enable");
            check.enchantControl.disabled.add(getID(itemMeta.getDisplayName()));
            check.enchantControl.toEnchantHandler("add", getID(itemMeta.getDisplayName()));
            check.enchantControl.writeToConfig("disabled", check.enchantControl.disabled);
        } else if (itemMeta.getLore().contains("§a§lStatus: §4§lDisabled")) {
            lore.add("§a§lStatus: §2§lEnabled");
            lore.add("§bLeft-click §ato §cdisable");
            check.enchantControl.disabled.remove(getID(itemMeta.getDisplayName()));
            check.enchantControl.toEnchantHandler("remove", getID(itemMeta.getDisplayName()));
            check.enchantControl.writeToConfig("disabled", check.enchantControl.disabled);
        }
        itemMeta.setLore(lore);
        clicked.setItemMeta(itemMeta);
        check.mainGUI.inventory.setItem(slot, clicked);
    }

    private String getID(String name) {
        name = name.replace("§6§l", "");
        name = name.replace(" ", "_");
        name = name.toLowerCase();
        return name;
    }

}
