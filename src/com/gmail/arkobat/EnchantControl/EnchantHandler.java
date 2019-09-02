package com.gmail.arkobat.EnchantControl;

import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import com.gmail.arkobat.EnchantControl.Utilities.SendPlayerMsg;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnchantHandler {

    private final EnchantControl enchantControl;
    private final SendPlayerMsg sendPlayerMsg;
    private final GetEnchant getEnchant;

    public EnchantHandler(EnchantControl enchantControl, SendPlayerMsg sendPlayerMsg, GetEnchant getEnchant) {
        this.enchantControl = enchantControl;
        this.sendPlayerMsg = sendPlayerMsg;
        this.getEnchant = getEnchant;

    }

    public boolean checkItem(ItemStack itemStack, Player p) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return false;
        }
        if (check64(itemStack)) {
            return false;
        }
        if (itemStack.getType() == XMaterial.ENCHANTED_BOOK.parseMaterial()) {
            return checkBook(itemStack, p) || checkBookMaxLvl(itemStack, p);
        }
        if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasEnchants()) {
            return checkDisabled(itemStack, p) || checkMaxLvl(itemStack, p);
        }
        return false;
    }

    private boolean check64(ItemStack itemStack) {
        /*
        Itemstack skull = new ItemStack(Material.SKULL_ITEM, getAmount(), (short) SkullType.PLAYER.ordinal());
         */
        ItemStack newItemStack = new ItemStack(itemStack);
        newItemStack.setAmount(1);
        newItemStack.setDurability((short) 0);


        if (newItemStack.getType() == XMaterial.PLAYER_HEAD.parseMaterial()) {
            SkullMeta skullMeta = (SkullMeta) newItemStack.getItemMeta();
            if (!skullMeta.hasOwner() || (skullMeta.hasOwner() && skullMeta.getOwner() == null)) {
                ItemStack skull = new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial(), 1);
                ItemMeta itemMeta = skull.getItemMeta();

                if (newItemStack.hasItemMeta()) {
                    if (newItemStack.getItemMeta().hasDisplayName()) {
                        itemMeta.setDisplayName(newItemStack.getItemMeta().getDisplayName());
                    }
                    if (newItemStack.getItemMeta().hasLore()) {
                        itemMeta.setLore(newItemStack.getItemMeta().getLore());
                    }
                    if (newItemStack.getItemMeta().hasEnchants()) {
                        for (Enchantment enchantment : newItemStack.getItemMeta().getEnchants().keySet()) {
                            itemMeta.addEnchant(enchantment, newItemStack.getEnchantmentLevel(enchantment), false);
                        }
                    }
                    skull.setItemMeta(itemMeta);
                    newItemStack = skull;
                }
            }
        }
        return enchantControl.excluded.contains(newItemStack.toString());
        //  return true;
    }

    private boolean checkDisabled(ItemStack itemStack, Player p) {
        if (EnchantControl.ACTION == null) {
            return false;
        }
        if (EnchantControl.BOOK.equalsIgnoreCase("Only")) {
            return false;
        }
        List<Enchantment> toRemove = new ArrayList<>();
        for (Enchantment enchantment : itemStack.getEnchantments().keySet()) {
            if (enchantControl.enchantConfigSection.containsKey(getEnchant.getIDSting(enchantment) + ".disabled")) {
                if (Boolean.valueOf(enchantControl.enchantConfigSection.get(getEnchant.getIDSting(enchantment) + ".disabled"))) {
                    if (EnchantControl.ACTION.equals("RemoveAll")) {
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
                if (toSet.get(enchantment) > getEnchant.getMaxEnchantLevel(enchantment)) {
                    itemStack.addUnsafeEnchantment(enchantment, toSet.get(enchantment));
                } else {
                    itemStack.addEnchantment(enchantment, toSet.get(enchantment));
                }
                if (p != null) {
                    sendPlayerMsg.sendPlayerMsg(p, "removedEnchant", enchantment, itemStack);
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkBookMaxLvl(ItemStack book, Player p) {
        if (EnchantControl.BOOK.equalsIgnoreCase("Yes") || EnchantControl.BOOK.equalsIgnoreCase("Only")) {
            if (book.hasItemMeta()) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
                HashMap<Enchantment, Integer> toSet = new HashMap<>();
                for (Enchantment enchantment : meta.getStoredEnchants().keySet()) {
                    if (Boolean.valueOf(enchantControl.enchantConfigSection.get(getEnchant.getIDSting(enchantment) + ".custom"))) {
                        if (enchantControl.enchantConfigSection.containsKey(getEnchant.getIDSting(enchantment) + ".maxLevel")) {
                            int maxLevel = Integer.parseInt(enchantControl.enchantConfigSection.get(getEnchant.getIDSting(enchantment) + ".maxLevel"));
                            if (meta.getStoredEnchantLevel(enchantment) > maxLevel) {
                                toSet.put(enchantment, maxLevel);
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
        if (EnchantControl.BOOK.equalsIgnoreCase("Yes") || EnchantControl.BOOK.equalsIgnoreCase("Only")) {
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
                        book.setType(XMaterial.BOOK.parseMaterial());
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkIllegalMatch(ItemStack itemStack, Player p) {
        Enchantment lowsetWeightEnchant;
        int lowestWeight;
        for (Enchantment enchantment : itemStack.getEnchantments().keySet()) {
            for (Enchantment enchantment2 : itemStack.getEnchantments().keySet()) {

            }
        }
        return false;
    }
}