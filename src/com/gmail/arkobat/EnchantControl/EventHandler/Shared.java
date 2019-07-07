package com.gmail.arkobat.EnchantControl.EventHandler;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.EnchantHandler;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import com.gmail.arkobat.EnchantControl.MessageChanger;
import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import com.gmail.arkobat.EnchantControl.Utilities.SendPlayerMsg;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Shared extends RegisterEvents implements Listener{

    private EnchantControl enchantControl;
    private EnchantHandler enchantHandler;
    private SetupGUI setupGUI;
    private MessageChanger messageChanger;
    private SendPlayerMsg sendPlayerMsg;
    private GetEnchant getEnchant;

    public Shared(EnchantControl enchantControl, EnchantHandler enchantHandler, SetupGUI setupGUI, MessageChanger messageChanger, SendPlayerMsg sendPlayerMsg, GetEnchant getEnchant) {
        this.enchantControl = enchantControl;
        this.enchantHandler = enchantHandler;
        this.setupGUI = setupGUI;
        this.messageChanger = messageChanger;
        this.sendPlayerMsg = sendPlayerMsg;
        this.getEnchant = getEnchant;
    }

    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent e) {
        if (!itemHeldEvent) {
            return;
        }
        Player p = e.getPlayer() instanceof Player ? e.getPlayer() : null;
        if (enchantControl.VERSION == 1.08) {
            enchantHandler.checkItem(e.getPlayer().getItemInHand(), p);
        } else {
            enchantHandler.checkItem(e.getPlayer().getInventory().getItemInMainHand(), p);
            enchantHandler.checkItem(e.getPlayer().getInventory().getItemInOffHand(), p);
        }
    }

    @org.bukkit.event.EventHandler
    public void interactEvent(PlayerInteractEvent e) {
        if (!interactEvent) {
            return;
        }
        Player p = e.getPlayer() instanceof Player ? e.getPlayer() : null;
        if (enchantControl.VERSION == 1.08) {
            enchantHandler.checkItem(e.getPlayer().getItemInHand(), p);
        } else {
            enchantHandler.checkItem(e.getPlayer().getInventory().getItemInMainHand(), p);
            enchantHandler.checkItem(e.getPlayer().getInventory().getItemInOffHand(), p);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = e.getWhoClicked() instanceof Player ? (Player) e.getWhoClicked() : null;
        Inventory inventory = e.getInventory(); // The inventory that was clicked in
        ItemStack clicked = e.getCurrentItem(); // The item that was clicked
        ClickType type = e.getClick(); // The button that was clicked
        String inventoryName;
        if (EnchantControl.VERSION >= 1.14) {
            inventoryName = e.getView().getTitle();
        } else {
            inventoryName = inventory.getName();
        }
        if (inventoryName.contains(enchantControl.GUIIdentifier)) {
            e.setCancelled(true);
            int slot = e.getSlot();
            if (clicked != null && clicked.getType() != Material.AIR) {
                enchantControl.onClick(inventory, clicked, type, p, slot);
                if (p != null) {p.updateInventory();}
            }
            return;
        }
        if (!clickItemEvent) {
            return;
        }
        if (!e.isCancelled()) {
            if (type != ClickType.DROP) {
                e.setCancelled(enchantHandler.checkItem(clicked, p));
            } else {
                enchantHandler.checkItem(clicked, p);
            }
        } else {
            enchantHandler.checkItem(clicked, p);
        }
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent e) {
        if (!enchantEvent) {
            return;
        }
        Player p = e.getEnchanter() instanceof Player ? e.getEnchanter() : null;
        List<Enchantment> enchantList = new ArrayList<>();
        for (Enchantment enchantment : e.getEnchantsToAdd().keySet()) {
            if (enchantControl.enchantConfigSection.containsKey(getEnchant.getIDSting(enchantment) + ".disabled") && Boolean.valueOf(enchantControl.enchantConfigSection.get(getEnchant.getIDSting(enchantment) + ".disabled"))) {
                if (EnchantControl.ENCHANT.equals("Cancel")) {
                    sendPlayerMsg.sendPlayerMsg(p, "enchantCancel", e.getItem());
                    e.setCancelled(true);
                    return;
                }
                enchantList.add(enchantment);
            }
        }
        for (Enchantment enchantment : enchantList) {
            e.getEnchantsToAdd().remove(enchantment);
        }
        if (e.getEnchantsToAdd().isEmpty()) {
            if (EnchantControl.ENCHANT.equals("RemoveReturn")) {
                sendPlayerMsg.sendPlayerMsg(p, "enchantCancel", e.getItem());
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (messageChanger.checkMessage(e.getMessage(), e.getPlayer())) {
            e.setCancelled(true);
        }
    }

}
