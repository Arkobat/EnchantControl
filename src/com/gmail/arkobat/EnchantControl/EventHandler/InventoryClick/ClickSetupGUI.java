package com.gmail.arkobat.EnchantControl.EventHandler.InventoryClick;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.EventHandler.RegisterEvents;
import com.gmail.arkobat.EnchantControl.XMaterial;
import org.bukkit.Material;
import org.bukkit.craftbukkit.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ClickSetupGUI {
    private final Check check;

    public ClickSetupGUI(Check check) {
        this.check = check;
    }

    public void onClickSettings(ItemStack clicked, ClickType type, Player player) {
        if (clicked.hasItemMeta()) {
            if (clicked.getItemMeta().hasDisplayName()) {
                if (clicked.getItemMeta().getDisplayName().equals("§6§lAction")) {
                    if (type == ClickType.LEFT) {
                        onClickSettingsAction(clicked);
                    }
                } else if (clicked.getItemMeta().getDisplayName().equals("§6§lEnchant")) {
                    if (type == ClickType.LEFT) {
                        onClickSettingsEnchant(clicked);
                    }
                } else if (clicked.getItemMeta().getDisplayName().equals("§6§lBook")) {
                    if (type == ClickType.LEFT) {
                        onClickSettingsBook(clicked);
                    }
                } else if (clicked.getItemMeta().getDisplayName().equals("§6§lUnsafe Enchants")) {
                    if (type == ClickType.LEFT) {
                        onClickSettingsUnsafeEnchants(clicked);
                    }
                } else if (clicked.getItemMeta().getDisplayName().equals("§6§lSave")) {
                    if (type == ClickType.LEFT) {
                        onClickSettingsSave(player);
                    }
                } else if (clicked.getType() == XMaterial.PAPER.parseMaterial()) {
                    if (type == ClickType.LEFT) {
                        onClickSettingsMessage(clicked, player);
                    }
                } else if (clicked.getType() == XMaterial.COMPARATOR.parseMaterial()) {
                    if (type == ClickType.LEFT) {
                        onClickSettingdEvents(clicked);
                    }
                }
            }
        }
    }

    private void onClickSettingsAction(ItemStack itemStack) {
        List<String> lore = itemStack.getItemMeta().getLore();
        String actionLine = lore.get(lore.size() - 2);
//        if (actionLine.contains("BookAll")) {
//            lore.set(lore.size() - 2, "     §a§lAction: §b§lBookSingle");
//            setupGUI.action = "BookSingle";
//        } else if (actionLine.contains("BookSingle")) {
//            lore.set(lore.size() - 2, "     §a§lAction: §b§lRemoveAll");
//            setupGUI.action = "RemoveAll";
//        } else
        if (actionLine.contains("RemoveAll")) {
            lore.set(lore.size() - 2, "     §a§lAction: §b§lRemoveSingle");
            EnchantControl.ACTION = "RemoveSingle";
//        } else if (actionLine.contains("RemoveSingle")) {
//            lore.set(lore.size() - 2, "     §a§lAction: §b§lBookAll");
//            setupGUI.action = "BookAll";
        } else {
            lore.set(lore.size() - 2, "     §a§lAction: §b§lRemoveAll");
            EnchantControl.ACTION = "RemoveAll";
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        check.setupGUI.inventory.setItem(10, itemStack);
    }

    private void onClickSettingsEnchant(ItemStack itemStack) {
        List<String> lore = itemStack.getItemMeta().getLore();
        String actionLine = lore.get(lore.size() - 2);
        if (actionLine.contains("Cancel")) {
            lore.set(lore.size() - 2, "     §a§lAction: §b§lRemove");
            EnchantControl.ENCHANT = "Remove";
            //       } else if (actionLine.contains("Remove")) {
            //           lore.set(lore.size() - 2, "     §a§lAction: §b§lBook");
            //          check.setupGUI.enchant = "Book";
        } else {
            lore.set(lore.size() - 2, "     §a§lAction: §b§lCancel");
            EnchantControl.ENCHANT = "Cancel";
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        check.setupGUI.inventory.setItem(11, itemStack);
    }

    private void onClickSettingsBook(ItemStack itemStack) {
        List<String> lore = itemStack.getItemMeta().getLore();
        String actionLine = lore.get(lore.size() - 2);
        if (actionLine.contains("Yes")) {
            lore.set(lore.size() - 2, "     §a§lBook: §b§lNo");
            EnchantControl.BOOK = "No";
        } else if (actionLine.contains("No")) {
            lore.set(lore.size() - 2, "     §a§lBook: §b§lOnly");
            EnchantControl.BOOK = "Only";
        } else {
            lore.set(lore.size() - 2, "     §a§lBook: §b§lYes");
            EnchantControl.BOOK = "Yes";
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        check.setupGUI.inventory.setItem(12, itemStack);
    }

    private void onClickSettingsUnsafeEnchants(ItemStack itemStack) {
        List<String> lore = itemStack.getItemMeta().getLore();
        String actionLine = lore.get(lore.size() - 2);
        if (actionLine.contains("False")) {
            lore.set(lore.size() - 2, "     §a§lUnsafe: §b§lTrue");
            EnchantControl.UNSAFE_ENCHANTS = true;
        } else {
            lore.set(lore.size() - 2, "     §a§lUnsafe: §b§lFalse");
            EnchantControl.UNSAFE_ENCHANTS = false;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        check.setupGUI.inventory.setItem(13, itemStack);
    }

    private void onClickSettingsMessage(ItemStack itemStack, Player p) {
        for (String string : check.enchantControl.msgAdd) {
            if (string.contains(p.getUniqueId().toString())) {
                check.sendPlayerMsg.sendPlayerMsg(p, "§cError: §3Already written up to change a message. You can only change one at a time");
                p.closeInventory();
                return;
            }
        }
        String item = itemStack.getItemMeta().getDisplayName();
        if (item.contains("Prefix")) {
            check.enchantControl.msgAdd.add(p.getUniqueId().toString() + ":prefix");
            p.closeInventory();
            check.sendPlayerMsg.sendPlayerMsg(p, "§3Please write in the chat what you would like the prefix to be");
            check.sendPlayerMsg.sendPlayerMsg(p, "§3To cancel, write §bcancel");
            check.sendPlayerMsg.sendPlayerMsg(p, "§3To disable every messages sent to users write §bdisabled");
            check.sendPlayerMsg.sendPlayerMsg(p, "§3To disable prefix, simple use a colorcode (like &§34)");
        } else if (item.contains("Canceled")) {
            check.enchantControl.msgAdd.add(p.getUniqueId().toString() + ":enchantCancel");
            p.closeInventory();
            check.sendPlayerMsg.sendPlayerMsg(p, "§3Please write in the chat what you would like the new message to be");
            check.sendPlayerMsg.sendPlayerMsg(p, "§3Accepted placeholders are: §b%item%");
            check.sendPlayerMsg.sendPlayerMsg(p, "§3To cancel, write §bcancel §3 - To disable write §bdisabled");
        } else if (item.contains("Removed")) {
            check.enchantControl.msgAdd.add(p.getUniqueId().toString() + ":removedEnchant");
            p.closeInventory();
            check.sendPlayerMsg.sendPlayerMsg(p, "§3Please write in the chat what you would like the new message to be");
            check.sendPlayerMsg.sendPlayerMsg(p, "§3Accepted placeholders are: §b%item%, %enchantName%");
            check.sendPlayerMsg.sendPlayerMsg(p, "§3To cancel, write §bcancel §3 - To disable write §bdisabled");
        }
    }

    private void onClickSettingdEvents(ItemStack itemStack) {
        if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lPickup Event")) {
            List<String> lore = itemStack.getItemMeta().getLore();
            String actionLine = lore.get(lore.size() - 2);
            if (actionLine.contains("Disabled")) {
                lore.set(lore.size() - 2, "     §a§lPickup Event: §b§lEnabled");
                RegisterEvents.pickupItemEvent = true;
                check.enchantControl.writeToConfig("Events.Pickup", true);
            } else {
                lore.set(lore.size() - 2, "     §a§lPickup Event: §b§lDisabled");
                RegisterEvents.pickupItemEvent = false;
                check.enchantControl.writeToConfig("Events.Pickup", false);
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            check.setupGUI.inventory.setItem(19, itemStack);
        } else if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lInteract Event")) {
            List<String> lore = itemStack.getItemMeta().getLore();
            String actionLine = lore.get(lore.size() - 2);
            if (actionLine.contains("Disabled")) {
                lore.set(lore.size() - 2, "     §a§lInteract Event: §b§lEnabled");
                RegisterEvents.interactEvent = true;
                check.enchantControl.writeToConfig("Events.Interact", true);
            } else {
                lore.set(lore.size() - 2, "     §a§lInteract Event: §b§lDisabled");
                RegisterEvents.interactEvent = false;
                check.enchantControl.writeToConfig("Events.Interact", false);
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            check.setupGUI.inventory.setItem(20, itemStack);
        } else if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lItem Held Event")) {
            List<String> lore = itemStack.getItemMeta().getLore();
            String actionLine = lore.get(lore.size() - 2);
            if (actionLine.contains("Disabled")) {
                lore.set(lore.size() - 2, "     §a§lHeld Event: §b§lEnabled");
                RegisterEvents.itemHeldEvent = true;
                check.enchantControl.writeToConfig("Events.Held", true);
            } else {
                lore.set(lore.size() - 2, "     §a§lHeld Event: §b§lDisabled");
                RegisterEvents.itemHeldEvent = false;
                check.enchantControl.writeToConfig("Events.Held", false);
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            check.setupGUI.inventory.setItem(21, itemStack);
        } else if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSwap Event")) {
            List<String> lore = itemStack.getItemMeta().getLore();
            String actionLine = lore.get(lore.size() - 2);
            if (actionLine.contains("Disabled")) {
                lore.set(lore.size() - 2, "     §a§lSwap Event: §b§lEnabled");
                RegisterEvents.itemSwapEvent = true;
                check.enchantControl.writeToConfig("Events.ItemSwap", true);
            } else {
                lore.set(lore.size() - 2, "     §a§lSwap Event: §b§lDisabled");
                RegisterEvents.itemSwapEvent = false;
                check.enchantControl.writeToConfig("Events.ItemSwap", false);
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            check.setupGUI.inventory.setItem(22, itemStack);
        } else if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lInventory Click Event")) {
            List<String> lore = itemStack.getItemMeta().getLore();
            String actionLine = lore.get(lore.size() - 2);
            if (actionLine.contains("Disabled")) {
                lore.set(lore.size() - 2, "     §a§lInventory Click Event: §b§lEnabled");
                RegisterEvents.clickItemEvent = true;
                check.enchantControl.writeToConfig("Events.InventoryClick", true);
            } else {
                lore.set(lore.size() - 2, "     §a§lInventory Click Event: §b§lDisabled");
                RegisterEvents.clickItemEvent = false;
                check.enchantControl.writeToConfig("Events.InventoryClick", false);
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            check.setupGUI.inventory.setItem(23, itemStack);
        } else if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lEnchant Event")) {
            List<String> lore = itemStack.getItemMeta().getLore();
            String actionLine = lore.get(lore.size() - 2);
            if (actionLine.contains("Disabled")) {
                lore.set(lore.size() - 2, "     §a§lEnchant Event:: §b§lEnabled");
                RegisterEvents.enchantEvent = true;
                check.enchantControl.writeToConfig("Events.Enchant", true);
            } else {
                lore.set(lore.size() - 2, "     §a§lEnchant Event: §b§lDisabled");
                RegisterEvents.enchantEvent = false;
                check.enchantControl.writeToConfig("Events.Enchant", false);
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            check.setupGUI.inventory.setItem(24, itemStack);
        } else if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAnvil Event")) {
            List<String> lore = itemStack.getItemMeta().getLore();
            String actionLine = lore.get(lore.size() - 2);
            if (actionLine.contains("Disabled")) {
                lore.set(lore.size() - 2, "     §a§lAnvil Event: §b§lEnabled");
                RegisterEvents.anvilEvent = true;
                check.enchantControl.writeToConfig("Events.Anvil", true);
            } else {
                lore.set(lore.size() - 2, "     §a§lAnvil Event: §b§lDisabled");
                RegisterEvents.anvilEvent = false;
                check.enchantControl.writeToConfig("Events.Anvil", false);
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            check.setupGUI.inventory.setItem(25, itemStack);
        }
    }

    private void onClickSettingsSave(Player p) {
        if (EnchantControl.ACTION != null && EnchantControl.ENCHANT != null && EnchantControl.BOOK != null && EnchantControl.UNSAFE_ENCHANTS != null) {
            if (EnchantControl.BOOK.equals("Yes") && (EnchantControl.ACTION.equals("BookSingle") || EnchantControl.ACTION.equals("BookAll") || EnchantControl.ENCHANT.equals("Book"))) {
                check.sendPlayerMsg.sendPlayerMsg(p, "§3Invalid settings. Please check your settings");
                return;
            }
            EnchantControl.setup = true;
            check.enchantControl.writeToConfig("action", EnchantControl.ACTION);
            check.enchantControl.writeToConfig("enchant", EnchantControl.ENCHANT);
            check.enchantControl.writeToConfig("book", EnchantControl.BOOK);
            check.enchantControl.writeToConfig("unsafeEnchant", EnchantControl.UNSAFE_ENCHANTS);
            check.enchantControl.writeToConfig("setup", true);
            p.openInventory(check.mainGUI.inventory);
        } else {
            check.sendPlayerMsg.sendPlayerMsg(p, "§3You need define your settings first");
        }
    }

}
