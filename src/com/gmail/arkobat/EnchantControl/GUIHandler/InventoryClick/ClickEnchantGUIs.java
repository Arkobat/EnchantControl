package com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ClickEnchantGUIs {
    Check check;

    public ClickEnchantGUIs(Check check) {
        this.check = check;
    }

    public void onClickGUIs(ItemStack clicked, int slot, ClickType type, Player player) {
        if (clicked.hasItemMeta()) {
            if (clicked.getItemMeta().hasDisplayName()) {
                if (clicked.getItemMeta().getDisplayName().equals("§c§lBack")) {
                    player.openInventory(check.mainGUI.inventory);
                } else if (clicked.getItemMeta().getDisplayName().equals("§6§lMax level")) {
                    onClickMaxLevel(clicked, slot, type, player);
                }
            }
        }
    }


    private void onClickMaxLevel(ItemStack clicked, int slot, ClickType clickType, Player player) {
        ItemMeta itemMeta = clicked.getItemMeta();
        List<String> lore = itemMeta.getLore();
        String id = getId(player);
        int maxLevel;
        if (lore.contains("§a Current max level: §b§lNot set")) {
            maxLevel = -1;
        } else {
            maxLevel = Integer.parseInt(lore.get(lore.size() - 2).replace("§a Current max level: §b§l", ""));
        }
        if (clickType  == ClickType.LEFT) {
            if (maxLevel < 999) {
                maxLevel++;
            }
        } else if (clickType == ClickType.RIGHT) {
            if (maxLevel > 1) {
                maxLevel--;
            }
        } else if (clickType == ClickType.SHIFT_LEFT) {
            maxLevel = 1;
        } else if (clickType == ClickType.SHIFT_RIGHT) {
            maxLevel = -1;
            lore.set(lore.size() - 2, "§a Current max level: §b§lNot set");
            check.enchantControl.enchantConfigSection.remove(id + ".maxLevel");
            check.enchantControl.clearConfigPath("enchants." + id + ".maxLevel");
        }
        if (maxLevel != -1) {
            lore.set(lore.size() - 2, "§a Current max level: §b§l" + maxLevel);
            check.enchantControl.enchantConfigSection.put(id + ".maxLevel", String.valueOf(maxLevel));
            check.enchantControl.writeToConfig("enchants." + id + ".maxLevel", maxLevel);
        }

        itemMeta.setLore(lore);
        clicked.setItemMeta(itemMeta);
       // check.mainGUI.inventory.setItem(slot, clicked);
    }

    private String getId(Player p) {
        String[]idSplit = p.getOpenInventory().getTitle().split("§¾§¯§¿§_");
        int id = Integer.parseInt(idSplit[1].replaceAll("§", ""));
        return String.valueOf(id);
    }
}


