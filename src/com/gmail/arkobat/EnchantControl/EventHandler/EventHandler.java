package com.gmail.arkobat.EnchantControl.EventHandler;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.EnchantHandler;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;

import com.gmail.arkobat.EnchantControl.MessageChanger;
import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import com.gmail.arkobat.EnchantControl.Utilities.SendPlayerMsg;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;


public class EventHandler implements Listener {
    public EventHandler(){}
    private EnchantControl enchantControl;
    private EnchantHandler enchantHandler;
    private SetupGUI setupGUI;
    private MessageChanger messageChanger;
    private SendPlayerMsg sendPlayerMsg;
    private GetEnchant getEnchant;

    public EventHandler(EnchantControl enchantControl, EnchantHandler enchantHandler, SetupGUI setupGUI, MessageChanger messageChanger, SendPlayerMsg sendPlayerMsg, GetEnchant getEnchant) {
        this.enchantControl = enchantControl;
        this.enchantHandler = enchantHandler;
        this.setupGUI = setupGUI;
        this.messageChanger = messageChanger;
        this.sendPlayerMsg = sendPlayerMsg;
        this.getEnchant = getEnchant;
    }

    public void reqisterEvents(double version) {
        Bukkit.getPluginManager().registerEvents(new Shared(enchantControl, enchantHandler, setupGUI, messageChanger, sendPlayerMsg, getEnchant), enchantControl);
        if (version == 1.8) {
            Bukkit.getPluginManager().registerEvents(new Version1_8(enchantControl, enchantHandler, setupGUI), enchantControl);
        } else if (version == 1.9) {
            Bukkit.getPluginManager().registerEvents(new Version1_9(enchantControl, enchantHandler, setupGUI), enchantControl);
        } else if (version == 1.10) {
            Bukkit.getPluginManager().registerEvents(new Version1_10(enchantControl, enchantHandler, setupGUI), enchantControl);
        } else if (version == 1.11) {
            Bukkit.getPluginManager().registerEvents(new Version1_11(enchantControl, enchantHandler, setupGUI), enchantControl);
        } else if (version == 1.2) {
            Bukkit.getPluginManager().registerEvents(new Version1_12(enchantControl, enchantHandler, setupGUI), enchantControl);
        }
    }

}