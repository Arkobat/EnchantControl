package com.gmail.arkobat.EnchantControl.EventHandler.InventoryClick;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.EventHandler.RegisterEvents;
import com.gmail.arkobat.EnchantControl.GUIHandler.EnchantSettings.MendingGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.EnchantSettings.SharedGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import com.gmail.arkobat.EnchantControl.Utilities.SendPlayerMsg;

public class Check {
    public Check() {
    }

    EnchantControl enchantControl;
    RegisterEvents registerEvents;
    SetupGUI setupGUI;
    MainGUI mainGUI;
    GetEnchant getEnchant;
    SharedGUI sharedGUI;
    SendPlayerMsg sendPlayerMsg;

    public Check(EnchantControl enchantControl, RegisterEvents registerEvents, SetupGUI setupGUI, MainGUI mainGUI, GetEnchant getEnchant, SharedGUI sharedGUI, SendPlayerMsg sendPlayerMsg) {
        this.enchantControl = enchantControl;
        this.registerEvents = registerEvents;
        this.setupGUI = setupGUI;
        this.mainGUI = mainGUI;
        this.getEnchant = getEnchant;
        this.sharedGUI = sharedGUI;
        this.sendPlayerMsg = sendPlayerMsg;
    }
}