package com.gmail.arkobat.EnchantControl.EventHandler;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.EnchantHandler;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class Version1_13 extends EventHandler implements Listener {
    EnchantControl enchantControl;
    EnchantHandler enchantHandler;
    SetupGUI setupGUI;

    public Version1_13(EnchantControl enchantControl, EnchantHandler enchantHandler, SetupGUI setupGUI) {
        this.enchantControl = enchantControl;
        this.enchantHandler = enchantHandler;
        this.setupGUI = setupGUI;
    }

    @org.bukkit.event.EventHandler
    public void onItemPickup(org.bukkit.event.entity.EntityPickupItemEvent e) {
        Player p = e.getEntity() instanceof Player ? (Player) e.getEntity() : null;
        enchantHandler.checkItem(e.getItem().getItemStack(), p);
        if (p != null) {
            enchantHandler.checkItem(p.getInventory().getItemInMainHand(), p);
            enchantHandler.checkItem(p.getInventory().getItemInOffHand(), p);
        }
    }

    @org.bukkit.event.EventHandler
    public void onItemSwap(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer() instanceof Player ? e.getPlayer() : null;
        enchantHandler.checkItem(e.getMainHandItem(), e.getPlayer());
        enchantHandler.checkItem(e.getOffHandItem(), e.getPlayer());
    }

    @org.bukkit.event.EventHandler
    public void onAnvilUse(PrepareAnvilEvent e) {
        enchantHandler.checkItem(e.getResult(), null);
        enchantHandler.checkItem(e.getInventory().getItem(0), null);
        enchantHandler.checkItem(e.getInventory().getItem(1), null);
    }

}
