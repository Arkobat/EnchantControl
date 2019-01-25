package com.gmail.arkobat.EnchantControl;
//https://pastebin.com/TKS7GqT0

import com.gmail.arkobat.EnchantControl.EventHandler.RegisterEvents;
import com.gmail.arkobat.EnchantControl.GUIHandler.EnchantSettings.MendingGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.EnchantSettings.SharedGUI;
import com.gmail.arkobat.EnchantControl.EventHandler.InventoryClick.*;
import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import com.gmail.arkobat.EnchantControl.Utilities.CreateConfig;
import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import com.gmail.arkobat.EnchantControl.Utilities.SendPlayerMsg;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnchantControl extends JavaPlugin{

    public String GUIIdentifier = "§¾§¯§¿"; // Unique characters to identify an inventory from this plugin
    public Boolean setup; // If the first time setup is done
    public ItemStack fillerItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7); // Filler item in inventories
    public String prefix; // Plugin prefix
    public String enchantCancel; // Message sent to players when enchanting is canceled
    public String removedEnchant; // Message sent to players when enchant is removed
    public List<String> msgAdd = new ArrayList<>(); // UUID's of players to change messages, with message they want to edit
    public Boolean book; // Determine if enchanted books should be affected
    public Boolean unsafeEnchants;
    public double version; // The server version

    public List<String> enchantConfigSectionID = new ArrayList<>(); // List over all enchant ID's - The same as Bukkit enchant ID, but my own method.
    public HashMap<String, String> enchantConfigSection = new HashMap<>(); // A map of all settings.
    public List<String> excluded = new ArrayList<>(); // Items excludes from the plugin. Name, lore etc need to be the same

    private MainGUI mainGUI = new MainGUI(this);
    private SetupGUI setupGUI = new SetupGUI(this);
    private GetEnchant getEnchant = new GetEnchant(this, mainGUI);
    private MendingGUI mendingGUI = new MendingGUI(this);
    private SharedGUI sharedGUI = new SharedGUI(this, getEnchant, mendingGUI);
    private SendPlayerMsg sendPlayerMsg = new SendPlayerMsg(this, getEnchant);
    private EnchantHandler enchantHandler = new EnchantHandler(this, mainGUI, setupGUI, sendPlayerMsg, getEnchant);
    private MessageChanger messageChanger = new MessageChanger(this, setupGUI, sendPlayerMsg);
    private RegisterEvents evt = new RegisterEvents(this, enchantHandler, setupGUI, messageChanger, sendPlayerMsg, getEnchant);
    private Check check = new Check(this,evt, setupGUI, mainGUI, getEnchant, sharedGUI, sendPlayerMsg);
    private ClickMainGUI clickMainGUI = new ClickMainGUI(check);
    private ClickSetupGUI clickSetupGUI = new ClickSetupGUI(check);
    private ClickMendingGUI clickMendingGUI = new ClickMendingGUI(check);
    private ClickEnchantGUIs clickEnchantGUIs = new ClickEnchantGUIs(check, clickMendingGUI);
    private GUISelector guiSelector = new GUISelector(this, clickSetupGUI, clickMainGUI, clickEnchantGUIs);
    private CreateConfig createConfig = new CreateConfig(this);


    @Override
    public void onEnable() {
        getVersion();
        evt.reqisterEvents(version);
        getCommand("EnchantControl").setExecutor(new CommandHandler(this, mainGUI, setupGUI));
        Bukkit.getPluginManager().registerEvents(new RegisterEvents(this, enchantHandler, setupGUI, messageChanger, sendPlayerMsg, getEnchant), this);

        saveDefaultConfig();
        loadDefaultConfig();
        Bukkit.getServer().getLogger().info("EnchantControl Enabled");
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (p.getOpenInventory().getTopInventory().getTitle().contains("§¾§¯§¿")) {
                p.closeInventory();
            }
        }

        //Bukkit.getServer().getOnlinePlayers().stream().filter(player -> player.getOpenInventory().getTopInventory().getTitle().contains("§¾§¯§¿")).forEach(Player::closeInventory);

        Bukkit.getServer().getLogger().info("EnchantControl Disabled");
    }

    private void loadDefaultConfig(){
        createConfig.configToSection(getConfig().getConfigurationSection("enchants"));
        createConfig.createStandard();
        excluded = getConfig().getStringList("excluded");
        prefix = getConfig().getString("prefix");
        ItemMeta itemMeta = fillerItem.getItemMeta();
        itemMeta.setDisplayName("§r");
        fillerItem.setItemMeta(itemMeta);
        setup = getConfig().getBoolean("setup");
        mainGUI.defineInventory();
        setupGUI.defineInventory();
    }

    public String getConfigString(String string) {
        return getConfig().getString(string);
    }

    public Boolean getConfigBoolean(String string) {
        return getConfig().getBoolean(string);
    }

    public boolean configContains(String string) {
        return getConfig().contains(string);
    }

    public void writeToConfig(String path, String value) {
        getConfig().set(path,value);
        saveConfig();
    }

    public void writeToConfig(String path, List<String> value) {
        getConfig().set(path,value);
        saveConfig();
    }

    public void writeToConfig(String path, boolean value) {
        getConfig().set(path,value);
        saveConfig();
    }

    public void writeToConfig(String path, int value) {
        getConfig().set(path,value);
        saveConfig();
    }

    public void clearConfigPath(String path) {
        getConfig().set(path,null);
        saveConfig();
    }

    public void onClick(Inventory inventory, ItemStack clicked, ClickType type, Player player, int slot) {
        guiSelector.onClick(inventory, clicked, type, player, slot);
    }


    public void setMessage(String type, String message) {
        message = message.replaceAll("&", "§");
        switch (type) {
            case "prefix":
                prefix = message;
                break;
            case "enchantCancel":
                enchantCancel = message;
                break;
            case "removedEnchant":
                removedEnchant = message;
                break;
        }
    }


    private void getVersion() {
        String ver = Bukkit.getVersion();
        if (ver.contains("1.7")) {
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c§m----------------------------");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c          WARNING");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c       This plugin is not  ");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c        supported by 1.7   ");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c           servers!!!      ");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c          WARNING");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c§m----------------------------");
            version = 1.07;
        } else if (ver.contains("1.8")) {
            version = 1.08;
        } else if (ver.contains("1.9")) {
            version = 1.09;
        } else if (ver.contains("1.10")) {
            version = 1.10;
        } else if (ver.contains("1.11")) {
            version = 1.11;
        } else if (ver.contains("1.12")) {
            version = 1.12;
        } else if (ver.contains("1.13")) {
            version = 1.13;
        } else {
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c§m---------------------------------------------");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c                     WARNING                   ");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c            §3[§cEnchantControl§3]             ");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c          Could not determine version.         ");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c  Assuming you are running higher than §b1.13  ");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c                     WARNING                   ");
            Bukkit.getConsoleSender().sendMessage("§3[§cEC§3] §c§m---------------------------------------------");
            version = 1.13;
        }
    }

}
