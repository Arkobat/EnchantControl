package com.gmail.arkobat.EnchantControl.EventHandler;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.EnchantHandler;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class Version1_10 extends RegisterEvents implements Listener {
    EnchantControl enchantControl;
    EnchantHandler enchantHandler;
    SetupGUI setupGUI;

    public Version1_10(EnchantControl enchantControl, EnchantHandler enchantHandler, SetupGUI setupGUI) {
        this.enchantControl = enchantControl;
        this.enchantHandler = enchantHandler;
        this.setupGUI = setupGUI;
    }

    @EventHandler
    public void onItemPickup(org.bukkit.event.player.PlayerPickupItemEvent e) {
        if (!pickupItemEvent) {
            return;
        }
        Player p = e.getPlayer();
        enchantHandler.checkItem(e.getItem().getItemStack(), p);
        if (p != null) {
            enchantHandler.checkItem(p.getItemInHand(), p);
        }
    }

    @EventHandler
    public void onItemSwap(PlayerSwapHandItemsEvent e) {
        if (!itemSwapEvent) {
            return;
        }
        enchantHandler.checkItem(e.getMainHandItem(), e.getPlayer());
        enchantHandler.checkItem(e.getOffHandItem(), e.getPlayer());
    }

    @EventHandler
    public void onAnvilUse(PrepareAnvilEvent e) {
        if (!anvilEvent) {
            return;
        }
        enchantHandler.checkItem(e.getResult(), null);
        enchantHandler.checkItem(e.getInventory().getItem(0), null);
        enchantHandler.checkItem(e.getInventory().getItem(1), null);
    }

}
