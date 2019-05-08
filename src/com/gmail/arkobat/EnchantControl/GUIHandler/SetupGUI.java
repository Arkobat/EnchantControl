package com.gmail.arkobat.EnchantControl.GUIHandler;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.EventHandler.RegisterEvents;
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
    private RegisterEvents registerEvents;

    public SetupGUI(EnchantControl enchantControl) {
        this.enchantControl = enchantControl;
    }

    public void setRegisterEvents(RegisterEvents registerEvents) {
        this.registerEvents = registerEvents;
    }

    private int[] fillerPlace = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 22, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};

    public Inventory inventory = Bukkit.createInventory(null, 36, "§a§lEC §b§lSettings" + "§¾§¯§¿");

    public void defineInventory() {
        for (int location : fillerPlace) {
            inventory.setItem(location, enchantControl.fillerItem);
        }
        defineActionItem();
        defineEnchantItem();
        defineBookItem();
        defineUnsafeEnchantItem();
        defineSaveItem();
        defineMessageItems();

        definePickupItemEvent();
        defineInteractEvent();
        defineItemHeldEvent();
        if (enchantControl.version >= 1.09) {
            defineItemSwapEvent();
        }
        defineClickItemEvent();
        defineEnchantEvent();
        defineAnvilEvent();
    }

    private void definePickupItemEvent() {
        ItemStack itemStack = new ItemStack(Material.REDSTONE_COMPARATOR);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§6§lPickup Event");

        List<String> lore = new ArrayList<>();
        lore.add("§a This event triggers when a player");
        lore.add("§a pickup items from the ground.");
        lore.add("§a Should this check be enabled?");
        lore.add("§b Left-click§a to change");
        lore.add("§c§m----------------------------------");
        lore.add("§b Enabled §c- §aThe check is active");
        lore.add("§b Disabled §c- §aThe plugin does nothing");
        lore.add(" §c§m----------------------------");
        if (enchantControl.configContains("Events.ItemSwap") && !enchantControl.getConfigBoolean("Events.Pickup")) {
            lore.add("     §a§lPickup Event: §b§lDisabled");
            RegisterEvents.pickupItemEvent = false;
        } else {
            lore.add("     §a§lPickup Event: §b§lEnabled");
            RegisterEvents.pickupItemEvent = true;
        }
        lore.add(" §c§m----------------------------");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(19, itemStack);
    }

    private void defineInteractEvent() {
        ItemStack itemStack = new ItemStack(Material.REDSTONE_COMPARATOR);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§6§lInteract Event");

        List<String> lore = new ArrayList<>();
        lore.add("§a This event triggers when a player");
        lore.add("§a interacts with a block. (Clicks a block).");
        lore.add("§a Should this check be enabled?");
        lore.add("§b Left-click§a to change");
        lore.add("§c§m----------------------------------");
        lore.add("§b Enabled §c- §aThe check is active");
        lore.add("§b Disabled §c- §aThe plugin does nothing");
        lore.add(" §c§m----------------------------");
        if (enchantControl.configContains("Events.ItemSwap") && !enchantControl.getConfigBoolean("Events.Interact")) {
            lore.add("     §a§lInteract Event: §b§lDisabled");
            RegisterEvents.interactEvent = false;
        } else {
            lore.add("     §a§lInteract Event: §b§lEnabled");
            RegisterEvents.interactEvent = true;
        }
        lore.add(" §c§m----------------------------");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(20, itemStack);
    }

    private void defineItemHeldEvent() {
        ItemStack itemStack = new ItemStack(Material.REDSTONE_COMPARATOR);
        ItemMeta itemMeta = itemStack.getItemMeta();
       itemMeta.setDisplayName("§6§lItem Held Event");

        List<String> lore = new ArrayList<>();
        lore.add("§a This event triggers when a player");
        lore.add("§a equip an item in the main hand.");
        lore.add("§a Should this check be enabled?");
        lore.add("§b Left-click§a to change");
        lore.add("§c§m----------------------------------");
        lore.add("§b Enabled §c- §aThe check is active");
        lore.add("§b Disabled §c- §aThe plugin does nothing");
        lore.add(" §c§m----------------------------");
        if (enchantControl.configContains("Events.ItemSwap") && !enchantControl.getConfigBoolean("Events.Held")) {
            lore.add("     §a§lHeld Event: §b§lDisabled");
            RegisterEvents.itemHeldEvent = false;
        } else {
            lore.add("     §a§lHeld Event: §b§lEnabled");
            RegisterEvents.itemHeldEvent = true;
        }
        lore.add(" §c§m----------------------------");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(21, itemStack);
    }

    private void defineItemSwapEvent() {
        ItemStack itemStack = new ItemStack(Material.REDSTONE_COMPARATOR);
        ItemMeta itemMeta = itemStack.getItemMeta();
       itemMeta.setDisplayName("§6§lSwap Event");

        List<String> lore = new ArrayList<>();
        lore.add("§a This event triggers when a player");
        lore.add("§a swap an item to the offhand.");
        lore.add("§a Should this check be enabled?");
        lore.add("§b Left-click§a to change");
        lore.add("§c§m----------------------------------");
        lore.add("§b Enabled §c- §aThe check is active");
        lore.add("§b Disabled §c- §aThe plugin does nothing");
        lore.add(" §c§m----------------------------");
        if (enchantControl.configContains("Events.ItemSwap") && !enchantControl.getConfigBoolean("Events.ItemSwap")) {
            lore.add("     §a§lSwap Event: §b§lDisabled");
            RegisterEvents.itemSwapEvent = false;
        } else {
            lore.add("     §a§lSwap Event: §b§lEnabled");
            RegisterEvents.itemSwapEvent = true;
        }
        lore.add(" §c§m----------------------------");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(22, itemStack);
    }

    private void defineClickItemEvent() {
        ItemStack itemStack = new ItemStack(Material.REDSTONE_COMPARATOR);
        ItemMeta itemMeta = itemStack.getItemMeta();
       itemMeta.setDisplayName("§6§lInventory Click Event");

        List<String> lore = new ArrayList<>();
        lore.add("§a This event triggers when a player");
        lore.add("§a clicks an item in an inventory.");
        lore.add("§a Should this check be enabled?");
        lore.add("§b Left-click§a to change");
        lore.add("§c§m----------------------------------");
        lore.add("§b Enabled §c- §aThe check is active");
        lore.add("§b Disabled §c- §aThe plugin does nothing");
        lore.add(" §c§m----------------------------");
        if (enchantControl.configContains("Events.ItemSwap") && !enchantControl.getConfigBoolean("Events.InventoryClick")) {
            lore.add("     §a§lInventory Click Event: §b§lDisabled");
            RegisterEvents.clickItemEvent = false;
        } else {
            lore.add("     §a§lInventory Click Event: §b§lEnabled");
            RegisterEvents.clickItemEvent = true;
        }
        lore.add(" §c§m----------------------------");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(23, itemStack);
    }

    private void defineEnchantEvent() {
        ItemStack itemStack = new ItemStack(Material.REDSTONE_COMPARATOR);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§6§lEnchant Event");

        List<String> lore = new ArrayList<>();
        lore.add("§a This event triggers when a player");
        lore.add("§a enchants an item.");
        lore.add("§a Should this check be enabled?");
        lore.add("§b Left-click§a to change");
        lore.add("§c§m----------------------------------");
        lore.add("§b Enabled §c- §aThe check is active");
        lore.add("§b Disabled §c- §aThe plugin does nothing");
        lore.add(" §c§m----------------------------");
        if (enchantControl.configContains("Events.ItemSwap") && !enchantControl.getConfigBoolean("Events.Enchant")) {
            lore.add("     §a§lEnchant Event: §b§lDisabled");
            RegisterEvents.enchantEvent = false;
        } else {
            lore.add("     §a§lEnchant Event: §b§lEnabled");
            RegisterEvents.enchantEvent = true;
        }
        lore.add(" §c§m----------------------------");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(24, itemStack);
    }

    private void defineAnvilEvent() {
        ItemStack itemStack = new ItemStack(Material.REDSTONE_COMPARATOR);
        ItemMeta itemMeta = itemStack.getItemMeta();
       itemMeta.setDisplayName("§6§lAnvil Event");

        List<String> lore = new ArrayList<>();
        lore.add("§a This event triggers when a player");
        lore.add("§a uses an anvil.");
        lore.add("§a Should this check be enabled?");
        lore.add("§b Left-click§a to change");
        lore.add("§c§m----------------------------------");
        lore.add("§b Enabled §c- §aThe check is active");
        lore.add("§b Disabled §c- §aThe plugin does nothing");
        lore.add(" §c§m----------------------------");
        if (enchantControl.configContains("Events.ItemSwap") && !enchantControl.getConfigBoolean("Events.Anvil")) {
            lore.add("     §a§lAnvil Event: §b§lDisabled");
            RegisterEvents.anvilEvent = false;
        } else {
            lore.add("     §a§lAnvil Event: §b§lEnabled");
            RegisterEvents.anvilEvent = true;
        }
        lore.add(" §c§m----------------------------");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(25, itemStack);
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
        lore.add("§b RemoveAll §c- §aAll the enchants gets removed.");
        lore.add("§b RemoveSingle §c- §aThe illegal enchants gets removed.");
        lore.add(" §c§m----------------------------");
        lore.add(defineActionSetting());
        lore.add(" §c§m----------------------------");
        return lore;
    }

    private String defineActionSetting() {
        if (enchantControl.configContains("action")) {
            if (enchantControl.getConfigString("action").equals("BookSingle")) {
                EnchantControl.ACTION = "BookSingle";
                return "     §a§lAction: §b§lBookSingle";
            } else if (enchantControl.getConfigString("action").equals("BookAll")) {
                EnchantControl.ACTION = "BookAll";
                return "     §a§lAction: §b§lBookAll";
            } else if (enchantControl.getConfigString("action").equals("RemoveSingle")) {
                EnchantControl.ACTION = "RemoveSingle";
                return "     §a§lAction: §b§lRemoveSingle";
            } else if (enchantControl.getConfigString("action").equals("RemoveAll")) {
                EnchantControl.ACTION = "RemoveAll";
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
        lore.add("§a What should happen, if a player enchants");
        lore.add("§a an item with an illegal enchantment?");
        lore.add("§b Left-click§a to change");
        lore.add("§c§m----------------------------------");
        lore.add("§b Remove §c- §aThe illegal enchants gets ");
        lore.add("§a removed, and all the others are applied");
//        lore.add("§b Book §c- §aThe illegal enchants gets removed.");
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
                EnchantControl.ENCHANT = "Remove";
                return "     §a§lEnchant: §b§lRemove";
            } else if (enchantControl.getConfigString("enchant").equals("Book")) {
                EnchantControl.ENCHANT = "Book";
                return "     §a§lEnchant: §b§lBook";
            } else if (enchantControl.getConfigString("enchant").equals("Cancel")) {
                EnchantControl.ENCHANT = "Cancel";
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
        lore.add("§a lose illegal enchants stored");
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
                EnchantControl.BOOK = "Yes";
                EnchantControl.AFFECT_BOOKS = true;
                return "     §a§lBook: §b§lYes";
            } else if (!enchantControl.getConfigBoolean("book")) {
                EnchantControl.BOOK = "No";
                EnchantControl.AFFECT_BOOKS = false;
                return "     §a§lBook: §b§lNo";
            }
        } else {
            EnchantControl.AFFECT_BOOKS = false;
        }
        return "     §a§lBook: §b§lNot set";
    }




    private void defineUnsafeEnchantItem() {
        ItemStack itemStack = new ItemStack(Material.ANVIL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§6§lUnsafe Enchants");
        itemMeta.setLore(defineUnsafeEnchantLore());
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(13, itemStack);
    }

    private List<String> defineUnsafeEnchantLore() {
        List<String> lore = new ArrayList<>();
        lore.add("§a Should a player be allowed to create");
        lore.add("§a illegal enchants with an anvil?.");
        lore.add("§a This means they can keep combining");
        lore.add("§a items to get a higher level, ignoring");
        lore.add("§a the vanilla max enchant level.");
        lore.add("§b Left-click§a to change");
        lore.add("§c§m----------------------------------");
        lore.add("§b True §c- §aUnsafe enchants are added");
        lore.add("§b False §c- §aVanilla enchant rules are used");
        lore.add(" §c§m----------------------------");
        lore.add(defineUnsafeEnchantSetting());
        lore.add(" §c§m----------------------------");
        return lore;
    }

    private String defineUnsafeEnchantSetting() {
        if (enchantControl.configContains("unsafeEnchant")) {
            if (enchantControl.getConfigBoolean("unsafeEnchant")) {
                EnchantControl.UNSAFE_ENCHANTS = true;
                return "     §a§lUnsafe: §b§lTrue";
            }
        }
        EnchantControl.UNSAFE_ENCHANTS = false;
        return "     §a§lUnsafe: §b§lFalse";

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
            lore.add(" §3Couldn't enchant %item%. Some enchants where illegal");
            lore.add(" §aYour message:");
            lore.add(ChatColor.translateAlternateColorCodes('&'," &F" + defineMessageSetting("canceled")));
            lore.add(" §c§m----------------------------");
        } else if (message.equals("remove")) {
            lore.add(" §aThe message sent when the plugin");
            lore.add(" §aremoves an enchantment from an item");
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
                path = "§3Couldn't enchant %item%. Some enchants where illegal";
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
        inventory.setItem(31, itemStack);
    }

    private List<String> defineSaveLore() {
        List<String> lore = new ArrayList<>();
        lore.add("§a Click here to confirm settings");
        lore.add("§a Can be changed later if needed.");
        lore.add("§b Left-click§a to save");
        return lore;
    }
}