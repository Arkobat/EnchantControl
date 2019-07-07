package com.gmail.arkobat.EnchantControl.EventHandler;

import com.gmail.arkobat.EnchantControl.Anvil;
import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.EnchantHandler;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;

import com.gmail.arkobat.EnchantControl.MessageChanger;
import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import com.gmail.arkobat.EnchantControl.Utilities.SendPlayerMsg;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;


public class RegisterEvents implements Listener {
    public static boolean pickupItemEvent = true;
    public static boolean interactEvent = true;
    public static boolean itemHeldEvent = true;
    public static boolean itemSwapEvent = true;
    public static boolean clickItemEvent = true;
    public static boolean enchantEvent = true;
    public static boolean anvilEvent = true;

    public RegisterEvents(){}
    private EnchantControl enchantControl;
    private EnchantHandler enchantHandler;
    private SetupGUI setupGUI;
    private MessageChanger messageChanger;
    private SendPlayerMsg sendPlayerMsg;
    private GetEnchant getEnchant;
    private Anvil anvil;

    public RegisterEvents(EnchantControl enchantControl, EnchantHandler enchantHandler, SetupGUI setupGUI, MessageChanger messageChanger, SendPlayerMsg sendPlayerMsg, GetEnchant getEnchant, Anvil anvil) {
        this.enchantControl = enchantControl;
        this.enchantHandler = enchantHandler;
        this.setupGUI = setupGUI;
        this.messageChanger = messageChanger;
        this.sendPlayerMsg = sendPlayerMsg;
        this.getEnchant = getEnchant;
        this.anvil = anvil;
    }

    public void reqisterEvents(double version) {
        Bukkit.getPluginManager().registerEvents(new Shared(enchantControl, enchantHandler, setupGUI, messageChanger, sendPlayerMsg, getEnchant), enchantControl);
        if (version == 1.08) {
            Bukkit.getPluginManager().registerEvents(new Version1_8(enchantControl, enchantHandler, setupGUI), enchantControl);
        } else if (version == 1.09) {
            Bukkit.getPluginManager().registerEvents(new Version1_9(enchantControl, enchantHandler, setupGUI, anvil), enchantControl);
        } else if (version == 1.10) {
            Bukkit.getPluginManager().registerEvents(new Version1_10(enchantControl, enchantHandler, setupGUI, anvil), enchantControl);
        } else if (version == 1.11) {
            Bukkit.getPluginManager().registerEvents(new Version1_11(enchantControl, enchantHandler, setupGUI, anvil), enchantControl);
        } else if (version == 1.12) {
            Bukkit.getPluginManager().registerEvents(new Version1_12(enchantControl, enchantHandler, setupGUI, anvil), enchantControl);
        } else if (version == 1.13) {
            Bukkit.getPluginManager().registerEvents(new Version1_13(enchantControl, enchantHandler, setupGUI, anvil), enchantControl);
        } else if (version == 1.14) {
            Bukkit.getPluginManager().registerEvents(new Version1_14(enchantControl, enchantHandler, setupGUI, anvil), enchantControl);
        }
    }

}