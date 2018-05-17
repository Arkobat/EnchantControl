package com.gmail.arkobat.EnchantControl;

import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;

import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import com.gmail.arkobat.EnchantControl.Utilities.SendPlayerMsg;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnchantHandler {

    private final EnchantControl enchantControl;
    private final MainGUI mainGUI;
    private final SetupGUI setupGUI;
    private final SendPlayerMsg sendPlayerMsg;
    private final GetEnchant getEnchant;

    public EnchantHandler(EnchantControl enchantControl, MainGUI mainGUI, SetupGUI setupGUI, SendPlayerMsg sendPlayerMsg, GetEnchant getEnchant) {
        this.enchantControl = enchantControl;
        this.mainGUI = mainGUI;
        this.setupGUI = setupGUI;
        this.sendPlayerMsg = sendPlayerMsg;
        this.getEnchant = getEnchant;

    }

    public boolean checkItem(ItemStack itemStack, Player p) {
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            if (itemStack.getType() == Material.ENCHANTED_BOOK) {
                return checkBook(itemStack, p) || checkBookMaxLvl(itemStack, p);
            }
            if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasEnchants()) {
                return checkDisabled(itemStack, p) || checkMaxLvl(itemStack, p);
            }
        }
        return false;
    }

    private boolean checkDisabled(ItemStack itemStack, Player p) {
        List<Enchantment> toRemove = new ArrayList<>();
        for (Enchantment enchantment : itemStack.getEnchantments().keySet()) {
            if (enchantControl.enchantConfigSection.containsKey(getEnchant.getIDSting(enchantment) + ".disabled")) {
                if (Boolean.valueOf(enchantControl.enchantConfigSection.get(getEnchant.getIDSting(enchantment) + ".disabled"))) {
                    if (setupGUI.action.equals("RemoveAll")) {
                        toRemove.addAll(itemStack.getEnchantments().keySet());
                        break;
                    } else {
                        toRemove.add(enchantment);
                    }
                }
            }
        }
        if (!toRemove.isEmpty()) {
            for (Enchantment enchantment : toRemove) {
                itemStack.removeEnchantment(enchantment);
                if (p != null) {
                    sendPlayerMsg.sendPlayerMsg(p, "removedEnchant", enchantment, itemStack);
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkMaxLvl(ItemStack itemStack, Player p) {
        HashMap<Enchantment, Integer> toSet = new HashMap<>();
        for (Enchantment enchantment : itemStack.getEnchantments().keySet()) {
            if (enchantControl.enchantConfigSection.containsKey(getEnchant.getIDSting(enchantment) + ".custom")) {
                if (Boolean.valueOf(enchantControl.enchantConfigSection.get(getEnchant.getIDSting(enchantment) + ".custom"))) {
                    if (enchantControl.enchantConfigSection.containsKey(getEnchant.getIDSting(enchantment) + ".maxLevel")) {
                        int maxLevel = Integer.parseInt(enchantControl.enchantConfigSection.get(getEnchant.getIDSting(enchantment) + ".maxLevel"));
                        if (itemStack.getEnchantmentLevel(enchantment) > maxLevel) {
                            toSet.put(enchantment, maxLevel);
                        }
                    }
                }
            }
        }
        if (!toSet.isEmpty()) {
            for (Enchantment enchantment : toSet.keySet()) {
                itemStack.removeEnchantment(enchantment);
                itemStack.addEnchantment(enchantment, toSet.get(enchantment));
                if (p != null) {
                    sendPlayerMsg.sendPlayerMsg(p, "removedEnchant", enchantment, itemStack);
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkBookMaxLvl(ItemStack book, Player p) {
        if (enchantControl.book) {
            if (book.hasItemMeta()) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
                HashMap<Enchantment, Integer> toSet = new HashMap<>();

                for (Enchantment enchantment : meta.getStoredEnchants().keySet()) {
                    if (enchantControl.enchantConfigSection.containsKey(getEnchant.getIDSting(enchantment) + ".custom")) {
                        if (Boolean.valueOf(enchantControl.enchantConfigSection.get(getEnchant.getIDSting(enchantment) + ".custom"))) {
                            if (enchantControl.enchantConfigSection.containsKey(getEnchant.getIDSting(enchantment) + ".maxLevel")) {
                                int maxLevel = Integer.parseInt(enchantControl.enchantConfigSection.get(getEnchant.getIDSting(enchantment) + ".maxLevel"));
                                if (meta.getStoredEnchantLevel(enchantment) > maxLevel) {
                                    toSet.put(enchantment, maxLevel);
                                }
                            }
                        }
                    }
                }

                if (!toSet.isEmpty()) {
                    for (Enchantment enchantment : toSet.keySet()) {
                        meta.removeStoredEnchant(enchantment);
                        meta.addStoredEnchant(enchantment, toSet.get(enchantment), true);
                        book.setItemMeta(meta);
                        if (p != null) {
                            sendPlayerMsg.sendPlayerMsg(p, "removedEnchant", enchantment, book);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkBook(ItemStack book, Player p) {
        if (enchantControl.book) {
            if (book.hasItemMeta()) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
                List<Enchantment> toRemove = new ArrayList<>();
                for (Enchantment enchantment : meta.getStoredEnchants().keySet()) {
                    if (enchantControl.enchantConfigSection.containsKey(getEnchant.getIDSting(enchantment) + ".disabled")) {
                        if (Boolean.valueOf(enchantControl.enchantConfigSection.get(getEnchant.getIDSting(enchantment) + ".disabled"))) {
                            toRemove.add(enchantment);
                        }
                    }
                }
                if (!toRemove.isEmpty()) {
                    for (Enchantment enchantment : toRemove) {
                        meta.removeStoredEnchant(enchantment);
                        book.setItemMeta(meta);
                        if (p != null) {
                            sendPlayerMsg.sendPlayerMsg(p, "removedEnchant", enchantment, book);
                        }
                    }
                    if (meta.getStoredEnchants().isEmpty()) {
                        book.setType(Material.BOOK);
                    }
                    return true;
                }
            }
        }
        return false;
    }


}

