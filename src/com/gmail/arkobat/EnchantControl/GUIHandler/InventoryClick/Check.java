package com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.EventHandler.EventHandler;
import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Check {
    public Check() {
    }

    EnchantControl enchantControl;
    EventHandler eventHandler;
    SetupGUI setupGUI;
    MainGUI mainGUI;

    public Check(EnchantControl enchantControl, EventHandler eventHandler, SetupGUI setupGUI, MainGUI mainGUI) {
        this.enchantControl = enchantControl;
        this.eventHandler = eventHandler;
        this.setupGUI = setupGUI;
        this.mainGUI = mainGUI;
    }
}