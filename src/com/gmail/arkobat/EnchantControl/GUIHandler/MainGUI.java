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
    private ItemStack settingItem = new ItemStack(Material.BOOK_AND_QUILL);

    public List<String> enchantsList = new ArrayList<>();
    private int enchantsListSize;

    public void defineEnchants() {
        enchantsList.add("Protection:PROTECTION_ENVIRONMENTAL");
        enchantsList.add("Fire_Protection:PROTECTION_FIRE");
        enchantsList.add("Feather_Falling:PROTECTION_FALL");
        enchantsList.add("Blast_Protection:PROTECTION_EXPLOSIONS");
        enchantsList.add("Projectile_Protection:PROTECTION_PROJECTILE");
        enchantsList.add("Respiration:OXYGEN");
        enchantsList.add("Auqa_Affinity:WATER_WORKER");
        enchantsList.add("Thorns:THORNS");
        enchantsList.add("Depth_Strider:DEPTH_STRIDER");
        enchantsList.add("Frost_Walker:FROST_WALKER");
        enchantsList.add("Curse_of_Binding:BINDING_CURSE");
        enchantsList.add("Sharpness:DAMAGE_ALL");
        enchantsList.add("Smite:DAMAGE_UNDEAD");
        enchantsList.add("Bane_of_Arthropods:DAMAGE_ARTHROPODS");
        enchantsList.add("Knockback:KNOCKBACK");
        enchantsList.add("Fire_Aspect:FIRE_ASPECT");
        enchantsList.add("Looting:LOOT_BONUS_MOBS");
        enchantsList.add("Sweeping_Edge:SWEEPING_EDGE");
        enchantsList.add("Efficiency:DIG_SPEED");
        enchantsList.add("Silk_Touch:SILK_TOUCH");
        enchantsList.add("Unbreaking:DURABILITY");
        enchantsList.add("Fortune:LOOT_BONUS_BLOCKS");
        enchantsList.add("Power:ARROW_DAMAGE");
        enchantsList.add("Punch:ARROW_KNOCKBACK");
        enchantsList.add("Flame:ARROW_FIRE");
        enchantsList.add("Infinity:ARROW_INFINITE");
        enchantsList.add("Luck_of_the_Sea:LUCK");
        enchantsList.add("Lure:LURE");
        enchantsList.add("Mending:MENDING");
        enchantsList.add("Curse_of_Vanishing:VANISHING_CURSE");
        enchantsListSize = enchantsList.size();
    }

    public void defineInventory() {
        for (int i = 0; i < enchantsListSize; i++) {
            String[] listName = enchantsList.get(i).split(":");
            String enchantID = listName[0].toLowerCase();
            String enchantName = listName[0].replace("_", " ");
            String bukkitName = listName[1];
            List<String> enchantLore = defineLore(enchantID, bukkitName);

            ItemStack itemStack = new ItemStack(Material.getMaterial(defineItem(enchantID)));
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§6§l" + enchantName);
            itemMeta.setLore(enchantLore);
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(i, itemStack);
        }
        for (int i = 45; i < 54; i++) {
            inventory.setItem(i, enchantControl.fillerItem);
        }
        ItemMeta settingItemMeta = settingItem.getItemMeta();
        settingItemMeta.setDisplayName("§6§lSettings");
        settingItemMeta.setLore(settingLore());
        settingItem.setItemMeta(settingItemMeta);
        inventory.setItem(49, settingItem);
    }

    private List<String> defineLore(String ID, String bukkitName) {
        List<String> lore = new ArrayList<>();
        if (!enchantControl.disabled.contains(ID)) {
            lore.add("§a§lStatus: §2§lEnabled");
            lore.add("§bLeft-click §ato §cdisable");
            enchantControl.toEnchantHandler("remove", bukkitName);
            return lore;
        } else {
            lore.add("§a§lStatus: §4§lDisabled");
            lore.add("§bLeft-click §ato §2enable");
            enchantControl.toEnchantHandler("add", bukkitName);
            return lore;
        }
    }

    private String defineItem(String ID) {
        if (enchantControl.getConfigString(ID + ".item") != null) {
            return enchantControl.getConfigString(ID + ".item");
        } else {
            return "BOOK";
        }
    }

    private List<String> settingLore() {
        List<String> lore = new ArrayList<>();
        lore.add("§a Click here to");
        lore.add("§a change settings");
        return lore;
    }
}
