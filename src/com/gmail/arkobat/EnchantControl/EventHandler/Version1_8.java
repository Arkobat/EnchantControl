package com.gmail.arkobat.EnchantControl.EventHandler;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.EnchantHandler;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Version1_8 extends EventHandler implements Listener {
     EnchantControl enchantControl;
     EnchantHandler enchantHandler;
     SetupGUI setupGUI;

    public  Version1_8(EnchantControl enchantControl, EnchantHandler enchantHandler, SetupGUI setupGUI) {
        this.enchantControl = enchantControl;
        this.enchantHandler = enchantHandler;
        this.setupGUI = setupGUI;
    }

    @org.bukkit.event.EventHandler
    public void onItemPickup(org.bukkit.event.player.PlayerPickupItemEvent e) {
        Player p = e.getPlayer() instanceof Player ? e.getPlayer() : null;
        enchantHandler.checkItem(e.getItem().getItemStack(), p);
        if (p != null) {
            enchantHandler.checkItem(p.getItemInHand(), p);
        }
    }
}
