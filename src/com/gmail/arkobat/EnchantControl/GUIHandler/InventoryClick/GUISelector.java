package com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUISelector {
    private ClickMainGUI clickMainGUI;
    private ClickSetupGUI clickSetupGUI;
    private EnchantControl enchantControl;
    public GUISelector(EnchantControl enchantControl, ClickSetupGUI clickSetupGUI, ClickMainGUI clickMainGUI) {
        this.clickMainGUI = clickMainGUI;
        this.clickSetupGUI = clickSetupGUI;
        this.enchantControl = enchantControl;
    }

    public void onClick(Inventory inventory, ItemStack clicked, ClickType type, Player player, int slot) {
        if (inventory.getName().equals("§a§lEC §b§lSettings" + enchantControl.GUIIdentifier)) {
            clickSetupGUI.onClickSettings(clicked, type, player);
        } else if (inventory.getName().equals("§a§lEnchantControl" + enchantControl.GUIIdentifier)) {
            clickMainGUI.onClickMain(clicked, player, slot, type);
        }
    }
}
