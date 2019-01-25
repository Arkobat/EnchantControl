package com.gmail.arkobat.EnchantControl.GUIHandler.EnchantSettings;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MendingGUI {
    EnchantControl enchantControl;

    public MendingGUI(EnchantControl enchantControl ) {
        this.enchantControl = enchantControl;
    }

    public Inventory applyMendingSettings(Inventory inv) {
        if (enchantControl.version >= 1.11) {
            inv.setItem(1, defineInfinityCapability());
        }
        return inv;
    }


    private ItemStack defineInfinityCapability() {
        ItemStack itemStack = new ItemStack(Material.ARROW);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§6§lInfinity");

        List<String> lore = new ArrayList<>();
        lore.add("§a Define if you want to allow Mending ");
        lore.add("§a and Infinity on the same Bow. This");
        lore.add("§a feature was removed in 1.11");
        lore.add("§c§m-----------------------");
        lore.add("§a §lStatus: " + getInfinityState(1));
        lore.add("§b Left-click §ato " + getInfinityState(2));
        lore.add("§c§m-----------------------");

        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private String getInfinityState(int number) {
        return enchantControl.getConfigBoolean("enchants.70.infinity") ? number == 1 ? "§2§lEnabled" : "§cdisable" : number == 1 ? "§4§lDisabled" : "§2enable";
    }
}
