package com.gmail.arkobat.EnchantControl.EventHandler.InventoryClick;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUISelector {
    private ClickMainGUI clickMainGUI;
    private ClickSetupGUI clickSetupGUI;
    private EnchantControl enchantControl;
    private ClickEnchantGUIs clickEnchantGUIs;
    public GUISelector(EnchantControl enchantControl, ClickSetupGUI clickSetupGUI, ClickMainGUI clickMainGUI, ClickEnchantGUIs clickEnchantGUIs) {
        this.clickMainGUI = clickMainGUI;
        this.clickSetupGUI = clickSetupGUI;
        this.enchantControl = enchantControl;
        this.clickEnchantGUIs = clickEnchantGUIs;
    }

    public void onClick(Inventory inventory, ItemStack clicked, ClickType type, Player player, int slot) {
        if (inventory.getSize() >= slot && inventory.getItem(slot) != null && inventory.getItem(slot).equals(clicked)) {
            if (inventory.getName().equals("§a§lEC §b§lSettings" + enchantControl.GUIIdentifier)) {
                clickSetupGUI.onClickSettings(clicked, type, player);
            } else if (inventory.getName().equals("§a§lEnchantControl" + enchantControl.GUIIdentifier)) {
                clickMainGUI.onClickMain(clicked, player, slot, type);
            } else if (inventory.getName().contains(enchantControl.GUIIdentifier + "§_")) {
                clickEnchantGUIs.onClickGUIs(clicked, slot, type, player);
            }
        }
    }
}
