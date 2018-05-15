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
                    } else if (clickType == ClickType.RIGHT) {

                        ItemMeta itemMeta = clicked.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore.add("§2§lStatus: §c§lCustom");
                        lore.add("§bLeft-click §ato §2disable");
                        lore.add("§bRight-click §afor more settings");
                        lore.add("§aThis will disable your custom settings");
                        lore.add("§asettings but not delete them!");
                        itemMeta.setLore(lore);
                        clicked.setItemMeta(itemMeta);
                        check.mainGUI.inventory.setItem(slot, clicked);
                        check.enchantControl.enchantConfigSection.set(check.getEnchant.getIDStingF(itemMeta.getDisplayName()) + ".custom", true);

                        p.openInventory(check.enchantSettingsGUIs.setupInv(check.getEnchant.getIDStingF(check.getEnchant.removeColorCode(clicked.getItemMeta().getDisplayName()))));
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
            lore.add("§bRight-click §afor more settings");
            check.enchantControl.enchantConfigSection.set(check.getEnchant.getIDStingF(itemMeta.getDisplayName()) + ".disabled", true);
            check.enchantControl.writeToConfig("enchant." + check.getEnchant.getIDStingF(itemMeta.getDisplayName()) + ".disabled", true);
        } else {
            lore.add("§a§lStatus: §2§lEnabled");
            lore.add("§bLeft-click §ato §cdisable");
            lore.add("§bRight-click §afor more settings");
            check.enchantControl.enchantConfigSection.set(check.getEnchant.getIDStingF(itemMeta.getDisplayName()) + ".disabled", false);
            check.enchantControl.writeToConfig("enchant." + check.getEnchant.getIDStingF(itemMeta.getDisplayName()) + ".disabled", false);
        }
        itemMeta.setLore(lore);
        clicked.setItemMeta(itemMeta);
        check.mainGUI.inventory.setItem(slot, clicked);
    }
}
