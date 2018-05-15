package com.gmail.arkobat.EnchantControl;

import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;

import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import com.gmail.arkobat.EnchantControl.Utilities.SendPlayerMsg;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
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
/*
    public void addEnchant(String enchant) {
        Enchantment enchantment = getBukkitEnchant(enchant);
        if (!enchantControl.disabledEnchants.contains(enchantment)) {
            enchantControl.disabledEnchants.add(enchantment);
        }
    }

    public void removeEnchant(String enchant) {
        Enchantment enchantment = getBukkitEnchant(enchant);
        if (enchantControl.disabledEnchants.contains(enchantment)) {
            enchantControl.disabledEnchants.remove(enchantment);
        }
    }
*/

    public void checkItem(ItemStack itemStack, Player p) {
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            if (itemStack.getType() == Material.ENCHANTED_BOOK) {
                //checkBook(itemStack, p);
                return;
            }
            if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasEnchants()) {
                checkDisabled(itemStack, p);
                checkMaxLvl(itemStack, p);
            }
        }
    }

    private void checkDisabled(ItemStack itemStack, Player p) {
        List<Enchantment> toRemove = new ArrayList<>();
        for (Enchantment enchantment : itemStack.getEnchantments().keySet()) {
            if (enchantControl.enchantConfigSection.contains(getEnchant.getIDStingF(enchantment.getName() + ".disabled")) && enchantControl.enchantConfigSection.getBoolean(getEnchant.getIDStingF(enchantment.getName() + ".disabled"))) {
                if (setupGUI.action.equals("RemoveAll")) {
                    toRemove.addAll(itemStack.getEnchantments().keySet());
                    break;
                } else {
                    toRemove.add(enchantment);
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
        }
    }

    private void checkMaxLvl(ItemStack itemStack, Player p) {
    }
/*
    private void checkBook(ItemStack book, Player p) {
        if (enchantControl.book) {
            if (book.hasItemMeta()) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
                List<Enchantment> toRemove = new ArrayList<>();
                for (Enchantment enchantment : meta.getStoredEnchants().keySet()) {
                    if (enchantControl.disabledEnchants.contains(enchantment)) {
                        toRemove.add(enchantment);
                    }
                }
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
            }
        }
    }
*/
}

