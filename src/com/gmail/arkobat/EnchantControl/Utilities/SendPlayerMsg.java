package com.gmail.arkobat.EnchantControl.Utilities;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SendPlayerMsg  {


    private EnchantControl enchantControl;
    private GetEnchant getEnchant;
    public SendPlayerMsg(EnchantControl enchantControl, GetEnchant getEnchant) {
        this.enchantControl = enchantControl;
        this.getEnchant = getEnchant;
    }

    public void sendPlayerMsg(Player p, String path) {
        String msg = "disabled";
        switch (path) {
            case "removedEnchant":
                msg = enchantControl.removedEnchant;
                break;
            case "enchantCancel":
                msg = enchantControl.enchantCancel;
                break;
        }
        if (!enchantControl.prefix.equalsIgnoreCase("disabled")) {
            if (!msg.equalsIgnoreCase("disabled")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', enchantControl.prefix + " " + msg));
            }
        }
    }

    public void sendPlayerMsg(Player p, String path, ItemStack itemStack) {
        String msg = "disabled";
        switch (path) {
            case "removedEnchant":
                msg = enchantControl.removedEnchant;
                break;
            case "enchantCancel":
                msg = enchantControl.enchantCancel;
                break;
        }
        if (!enchantControl.prefix.equalsIgnoreCase("disabled")) {
            if (!msg.equalsIgnoreCase("disabled")) {
                msg = msg.replaceAll("%item%", itemStack.getType().name());
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', enchantControl.prefix + " " + msg));
            }
        }
    }

    public void sendPlayerMsg(Player p, String path, Enchantment enchantment, ItemStack itemStack) {
        String msg = "disabled";
        switch (path) {
            case "removedEnchant":
                msg = enchantControl.removedEnchant;
                break;
            case "enchantCancel":
                msg = enchantControl.enchantCancel;
                break;
        }
        if (!enchantControl.prefix.equalsIgnoreCase("disabled")) {
            if (!msg.equalsIgnoreCase("disabled")) {
                msg = msg.replaceAll("%enchantName%", getEnchant.getFriendlyEnchantName(enchantment));
                msg = msg.replaceAll("%item%", itemStack.getType().name());
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', enchantControl.prefix + " " + msg));
            }
        }
    }
}
