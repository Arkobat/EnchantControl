package com.gmail.arkobat.EnchantControl.GUIHandler;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MainGUI {

    private final EnchantControl enchantControl;

    public MainGUI(EnchantControl enchantControl) {
        this.enchantControl = enchantControl;
    }

    public Inventory inventory = Bukkit.createInventory(null, 54, "§a§lEnchantControl" + "§¾§¯§¿");

    public void defineInventory() {
        int loc = 0;
        for (String key : enchantControl.enchantConfigSection.getKeys(false)) {
            ItemStack itemStack = new ItemStack(Material.valueOf(defineItem(key)));
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§6§l" + enchantControl.enchantConfigSection.get(key + ".name"));
            itemMeta.setLore(defineLore(key));
            inventory.setItem(loc, itemStack);
            loc++;
        }

        for (int i = 45; i < 54; i++) {
            inventory.setItem(i, enchantControl.fillerItem);
        }
        inventory.setItem(49, defineSettingItem());
    }

    private List<String> defineLore(String id) {
        List<String> lore = new ArrayList<>();
        if (!enchantControl.enchantConfigSection.contains(id + ".custom")) {
            if (enchantControl.enchantConfigSection.contains(id + ".disabled") && enchantControl.enchantConfigSection.getBoolean(id + ".disabled")) {
                lore.add("§a§lStatus: §4§lDisabled");
                lore.add("§bLeft-click §ato §2enable");
                lore.add("§bRight-click §afor more settings");

            } else {
                lore.add("§a§lStatus: §2§lEnabled");
                lore.add("§bLeft-click §ato §cdisable");
                lore.add("§bRight-click §afor more settings");
            }
        } else if (enchantControl.enchantConfigSection.getBoolean(id + ".custom")){
            lore.add("§2§lStatus: §c§lCustom");
            lore.add("§bLeft-click §ato §2disable");
            lore.add("§bRight-click §afor more settings");
            lore.add("§aThis will disable your custom settings");
            lore.add("§asettings but not delete them!");
        }
        return lore;
    }

    private String defineItem(String id) {
        if (enchantControl.enchantConfigSection.contains(id + ".item")) {
            return enchantControl.enchantConfigSection.getString(id + ".item");
        } else {
            return "BOOK";
        }
    }

    private ItemStack defineSettingItem() {
        ItemStack settingItem = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta settingItemMeta = settingItem.getItemMeta();
        settingItemMeta.setDisplayName("§6§lSettings");
        List<String> lore = new ArrayList<>();
        lore.add("§a Click here to");
        lore.add("§a change settings");
        settingItemMeta.setLore(lore);
        settingItem.setItemMeta(settingItemMeta);
        return settingItem;
    }
}
