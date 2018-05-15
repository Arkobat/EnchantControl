package com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.EventHandler.EventHandler;
import com.gmail.arkobat.EnchantControl.GUIHandler.EnchantSettingsGUIs;
import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;

public class Check {
    public Check() {
    }

    EnchantControl enchantControl;
    EventHandler eventHandler;
    SetupGUI setupGUI;
    MainGUI mainGUI;
    GetEnchant getEnchant;
    EnchantSettingsGUIs enchantSettingsGUIs;

    public Check(EnchantControl enchantControl, EventHandler eventHandler, SetupGUI setupGUI, MainGUI mainGUI, GetEnchant getEnchant, EnchantSettingsGUIs enchantSettingsGUIs ) {
        this.enchantControl = enchantControl;
        this.eventHandler = eventHandler;
        this.setupGUI = setupGUI;
        this.mainGUI = mainGUI;
        this.getEnchant = getEnchant;
        this.enchantSettingsGUIs = enchantSettingsGUIs;
    }
}