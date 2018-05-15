package com.gmail.arkobat.EnchantControl.GUIHandler;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SetupGUI {

    private final EnchantControl enchantControl;

    public SetupGUI(EnchantControl enchantControl) {
        this.enchantControl = enchantControl;
    }

    public String action;
    public String enchant;
    public String book;
    private int[] fillerPlace = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 23, 24, 25, 26};

    public Inventory inventory = Bukkit.createInventory(null, 27, "§a§lEC §b§lSettings" + "§¾§¯§¿");

    public void defineInventory() {
        defineActionItem();
        defineEnchantItem();
        defineBookItem();
        defineSaveItem();
        defineMessageItems();
        for (int location : fillerPlace) {
            inventory.setItem(location, enchantControl.fillerItem);
        }
    }

    private void defineActionItem() {
        ItemStack itemStack = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§6§lAction");
        itemMeta.setLore(defineActionLore());
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(10, itemStack);
    }

    private List<String> defineActionLore() {
        List<String> lore = new ArrayList<>();
        lore.add("§a What should happen, if a player obtains");
        lore.add("§a an item with an illegal enchantment?");
        lore.add("§b Left-click§a to change");
        lore.add("§c§m----------------------------------");
//        lore.add("§b BookAll §c- §aAll the enchantConfigSection gets removed,");
//        lore.add("§a and added to a book, given to the player");
//        lore.add("§b BookSingle §c- §aThe illegal enchantConfigSection gets removed,");
//        lore.add("§a and added to a book, given to the player");
        lore.add("§b RemoveAll §c- §aAll the enchantConfigSection gets removed.");
        lore.add("§b RemoveSingle §c- §aThe illegal enchantConfigSection gets removed.");
        lore.add(" §c§m----------------------------");
        lore.add(defineActionSetting());
        lore.add(" §c§m----------------------------");
        return lore;
    }

    private String defineActionSetting() {
        if (enchantControl.configContains("action")) {
            if (enchantControl.getConfigString("action").equals("BookSingle")) {
                action = "BookSingle";
                return "     §a§lAction: §b§lBookSingle";
            } else if (enchantControl.getConfigString("action").equals("BookAll")) {
                action = "BookAll";
                return "     §a§lAction: §b§lBookAll";
            } else if (enchantControl.getConfigString("action").equals("RemoveSingle")) {
                action = "RemoveSingle";
                return "     §a§lAction: §b§lRemoveSingle";
            } else if (enchantControl.getConfigString("action").equals("RemoveAll")) {
                action = "RemoveAll";
                return "     §a§lAction: §b§lRemoveAll";
            }
        }
        return "     §a§lAction: §b§lNot set";
    }

    private void defineEnchantItem() {
        ItemStack itemStack = new ItemStack(Material.ENCHANTMENT_TABLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§6§lEnchant");
        itemMeta.setLore(defineEnchantLore());
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(11, itemStack);
    }

    private List<String> defineEnchantLore() {
        List<String> lore = new ArrayList<>();
        lore.add("§a What should happen, if a player enchantConfigSection");
        lore.add("§a an item with an illegal enchantment?");
        lore.add("§b Left-click§a to change");
        lore.add("§c§m----------------------------------");
        lore.add("§b Remove §c- §aThe illegal enchantConfigSection gets ");
        lore.add("§a removed, and all the others are applied");
//        lore.add("§b Book §c- §aThe illegal enchantConfigSection gets removed.");
//        lore.add("§a and given to the player as a book");
        lore.add("§b Cancel §c- §aThe enchant event gets canceled, ");
        lore.add("§a and the player gets a message");
        lore.add(" §c§m----------------------------");
        lore.add(defineEnchantSetting());
        lore.add(" §c§m----------------------------");
        return lore;
    }

    private String defineEnchantSetting() {
        if (enchantControl.configContains("enchant")) {
            if (enchantControl.getConfigString("enchant").equals("Remove")) {
                enchant = "Remove";
                return "     §a§lEnchant: §b§lRemove";
            } else if (enchantControl.getConfigString("enchant").equals("Book")) {
                enchant = "Book";
                return "     §a§lEnchant: §b§lBook";
            } else if (enchantControl.getConfigString("enchant").equals("Cancel")) {
                enchant = "Cancel";
                return "     §a§lEnchant: §b§lCancel";
            }
        }
        return "     §a§lEnchant: §b§lNot set";
    }

    private void defineBookItem() {
        ItemStack itemStack = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§6§lBook");
        itemMeta.setLore(defineBookLore());
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(12, itemStack);
    }

    private List<String> defineBookLore() {
        List<String> lore = new ArrayList<>();
        lore.add("§a Should the plugin effect");
        lore.add("§a enchanted books?");
        lore.add("§b Left-click§a to change");
        lore.add("§c§m-----------------------");
        lore.add("§b Yes §c- §aEnchanted books will ");
        lore.add("§a lose illegal enchantConfigSection stored");
        lore.add("§b No §c- §aBooks aren't effected");
        lore.add("§a Make sure you don't return");
        lore.add("§a as book in other settings,");
        lore.add("§a if you choose yes");
        lore.add(" §c§m----------------------");
        lore.add(defineBookSetting());
        lore.add(" §c§m----------------------");
        return lore;
    }

    private String defineBookSetting() {
        if (enchantControl.configContains("book")) {
            if (enchantControl.getConfigBoolean("book")) {
                book = "Yes";
                enchantControl.book = true;
                return "     §a§lBook: §b§lYes";
            } else if (!enchantControl.getConfigBoolean("book")) {
                book = "No";
                enchantControl.book = false;
                return "     §a§lBook: §b§lNo";
            }
        }
        return "     §a§lBook: §b§lNot set";
    }

    public void defineMessageItems() {
        ItemStack prefix = new ItemStack(Material.PAPER);
        ItemStack enchantCancel = new ItemStack(Material.PAPER);
        ItemStack removedEnchant = new ItemStack(Material.PAPER);

        ItemMeta prefixMeta = prefix.getItemMeta();
        ItemMeta enchantCancelMeta = enchantCancel.getItemMeta();
        ItemMeta removedEnchantMeta = removedEnchant.getItemMeta();

        prefixMeta.setDisplayName("§6§lPrefix");
        enchantCancelMeta.setDisplayName("§6§lEnchant Canceled");
        removedEnchantMeta.setDisplayName("§6§lRemoved Enchant");

        prefixMeta.setLore(defineMessageLore("prefix"));
        enchantCancelMeta.setLore(defineMessageLore("canceled"));
        removedEnchantMeta.setLore(defineMessageLore("remove"));

        prefix.setItemMeta(prefixMeta);
        enchantCancel.setItemMeta(enchantCancelMeta);
        removedEnchant.setItemMeta(removedEnchantMeta);

        inventory.setItem(14, prefix);
        inventory.setItem(15, enchantCancel);
        inventory.setItem(16, removedEnchant);
    }

    private List<String> defineMessageLore(String message) {
        List<String> lore = new ArrayList<>();
        if (message.equals("prefix")) {
            lore.add(" §aThe prefix of the plugin");
            lore.add(" §bLeft-click §ato change");
            lore.add(" §c§m----------------------------");
            lore.add(" §aDefault prefix:");
            lore.add(" §3[§cEnchantControl§3]");
            lore.add(" §aYour prefix:");
            lore.add(ChatColor.translateAlternateColorCodes('&'," &f" + defineMessageSetting("prefix")));
            lore.add(" §c§m----------------------------");
        } else if (message.equals("canceled")) {
            lore.add(" §aThe message sent when the");
            lore.add(" §aplugin blocks the enchant event");
            lore.add(" §bLeft-click §ato change");
            lore.add(" §aAccepted placeholders: §b%item%");
            lore.add(" §c§m----------------------------");
            lore.add(" §aDefault message:");
            lore.add(" §3Couldn't enchant %item%. All enchantConfigSection where illegal");
            lore.add(" §aYour message:");
            lore.add(ChatColor.translateAlternateColorCodes('&'," &F" + defineMessageSetting("canceled")));
            lore.add(" §c§m----------------------------");
        } else if (message.equals("remove")) {
            lore.add(" §aThe message sent when the");
            lore.add(" §aplugin blocks the enchant event");
            lore.add(" §bLeft-click §ato change");
            lore.add(" §aAccepted placeholders: §b%item%, %enchantName%");
            lore.add(" §c§m----------------------------");
            lore.add(" §aDefault message:");
            lore.add(" §3Removed %enchantName% from %item%");
            lore.add(" §aYour message:");
            lore.add(ChatColor.translateAlternateColorCodes('&'," &f" + defineMessageSetting("remove")));
            lore.add(" §c§m----------------------------");
        }
        return lore;
    }

    private String defineMessageSetting(String message) {
        String path;
        if (message.equals("prefix")) {
            if (enchantControl.configContains("prefix")) {
                path = enchantControl.getConfigString("prefix");
                enchantControl.prefix = path;
                return path;
            } else {
                path = "§3[§cEnchantControl§3]";
                enchantControl.prefix = path;
                return path;
            }
        } else if (message.equals("canceled")) {
            if (enchantControl.configContains("enchantCancel")) {
                path = enchantControl.getConfigString("enchantCancel");
                enchantControl.enchantCancel = path;
                return path;
            } else {
                path = "§3Couldn't enchant %item%. All enchantConfigSection where illegal";
                enchantControl.enchantCancel = path;
                return path;
            }
        } else if (message.equals("remove")) {
            if (enchantControl.configContains("removedEnchant")) {
                path = enchantControl.getConfigString("removedEnchant");
                enchantControl.removedEnchant = path;
                return path;
            } else {
                path = "§3Removed %enchantName% from %item%";
                enchantControl.removedEnchant = path;
                return path;
            }
        }
        return "&4&lError: &cNot found";
    }

    private void defineSaveItem() {
        ItemStack itemStack = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§6§lSave");
        itemMeta.setLore(defineSaveLore());
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(22, itemStack);
    }

    private List<String> defineSaveLore() {
        List<String> lore = new ArrayList<>();
        lore.add("§a Click here to confirm settings");
        lore.add("§a Can be changed later if needed.");
        lore.add("§b Left-click§a to save");
        return lore;
    }
}
