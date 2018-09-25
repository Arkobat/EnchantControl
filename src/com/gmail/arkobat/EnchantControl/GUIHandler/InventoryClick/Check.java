package com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.EventHandler.EventHandler;
import com.gmail.arkobat.EnchantControl.GUIHandler.EnchantSettingsGUIs;
import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import com.gmail.arkobat.EnchantControl.Utilities.SendPlayerMsg;

public class Check {
    public Check() {
    }

    EnchantControl enchantControl;
    EventHandler eventHandler;
    SetupGUI setupGUI;
    MainGUI mainGUI;
    GetEnchant getEnchant;
    EnchantSettingsGUIs enchantSettingsGUIs;
    SendPlayerMsg sendPlayerMsg;

    public Check(EnchantControl enchantControl, EventHandler eventHandler, SetupGUI setupGUI, MainGUI mainGUI, GetEnchant getEnchant, EnchantSettingsGUIs enchantSettingsGUIs, SendPlayerMsg sendPlayerMsg) {
        this.enchantControl = enchantControl;
        this.eventHandler = eventHandler;
        this.setupGUI = setupGUI;
        this.mainGUI = mainGUI;
        this.getEnchant = getEnchant;
        this.enchantSettingsGUIs = enchantSettingsGUIs;
        this.sendPlayerMsg = sendPlayerMsg;
    }
}