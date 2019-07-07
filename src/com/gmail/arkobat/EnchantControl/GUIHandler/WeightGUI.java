package com.gmail.arkobat.EnchantControl.GUIHandler;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WeightGUI {
    private final EnchantControl enchantControl;
    public WeightGUI(EnchantControl enchantControl) {
        this.enchantControl = enchantControl;
    }

    public Inventory applyWeight() {
        Inventory inv = Bukkit.createInventory(null, 18, "§b§lEC §a§lWeight" + enchantControl.GUIIdentifier);
        int loc = 0;
        for (String key : enchantControl.enchantConfigSectionID) {
            if (enchantControl.VERSION >= Double.parseDouble(enchantControl.enchantConfigSection.get(key + ".ver"))) {
                ItemStack itemStack = new ItemStack(Material.valueOf(defineItem(key)));
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§6§l" + enchantControl.enchantConfigSection.get(key + ".name"));
                itemMeta.setLore(defineLore(key));
                itemStack.setItemMeta(itemMeta);
                inv.setItem(loc, itemStack);
                loc++;
                //    }
            }
        }
        for (int i = 45; i < 54; i++) {
            inv.setItem(i, enchantControl.fillerItem);
        }
        inv.setItem(49, defineBackItem());
        return inv;
    }

    private String defineItem(String id) {
        return enchantControl.enchantConfigSection.getOrDefault(id + ".item", "BOOK");
    }

    private List<String> defineLore(String id) {
        List <String> lore = new ArrayList<>();
        lore.add("§a Set the weight of the enchantment");
        lore.add("§a Weight is used when cheecking incompatible");
        lore.add("§a enchantments. The one with the lowest weight");
        lore.add("§a gets removed. If multiple enchants exit with");
        lore.add("§a same weight, it takes the first the plugin sees");
        lore.add("§c§&m--------------------------");
        lore.add("§b Left-click §ato §bincrease");
        lore.add("§b Right-click §ato §cdecrease");
        lore.add("§c§m--------------------------");
        lore.add("§a   Current weight: §b§l" + getCurrentWeight(id));
        lore.add("§c§m--------------------------");
        return null;
    }

    private String getCurrentWeight(String id) {
        return  enchantControl.enchantConfigSection.getOrDefault((id + ".weight"), "Not set");
    }

    private ItemStack defineBackItem() {
        ItemStack settingItem = new ItemStack(XMaterial.WRITABLE_BOOK.parseMaterial());
        ItemMeta settingItemMeta = settingItem.getItemMeta();
        settingItemMeta.setDisplayName("§6§lSettings");
        List<String> lore = new ArrayList<>();
        lore.add("§a Click here to go");
        lore.add("§a back to settings");
        settingItemMeta.setLore(lore);
        settingItem.setItemMeta(settingItemMeta);
        return settingItem;
    }
}
