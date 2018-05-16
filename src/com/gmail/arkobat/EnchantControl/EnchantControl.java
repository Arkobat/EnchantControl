package com.gmail.arkobat.EnchantControl;
//https://pastebin.com/TKS7GqT0

import com.gmail.arkobat.EnchantControl.EventHandler.EventHandler;
import com.gmail.arkobat.EnchantControl.GUIHandler.EnchantSettingsGUIs;
import com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick.Check;
import com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick.ClickMainGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick.ClickSetupGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick.GUISelector;
import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import com.gmail.arkobat.EnchantControl.Utilities.CreateConfig;
import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import com.gmail.arkobat.EnchantControl.Utilities.SendPlayerMsg;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class EnchantControl extends JavaPlugin{

    public String GUIIdentifier = "§¾§¯§¿"; // Unique characters to identify an inventory from this plugin
    public Boolean setup; // If the first time setup is done
    public ItemStack fillerItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7); // Filler item in inventories
    public String prefix; // Plugin prefix
    public String enchantCancel; // Message sent to players when enchanting is canceled
    public String removedEnchant; // Message sent to players when enchant is removed
    public List<String> msgAdd = new ArrayList<>(); // UUID's of players to change messages, with message they want to edit
    public Boolean book; // Not yet in use, but implemented in some parts of the code
    public double version; // The server version

    public ConfigurationSection enchantConfigSection;

    private MainGUI mainGUI = new MainGUI(this);
    private SetupGUI setupGUI = new SetupGUI(this);
    private GetEnchant getEnchant = new GetEnchant(this, mainGUI);
    private EnchantSettingsGUIs enchantSettingsGUIs = new EnchantSettingsGUIs(this, getEnchant);
    private SendPlayerMsg sendPlayerMsg = new SendPlayerMsg(this, getEnchant);
    private EnchantHandler enchantHandler = new EnchantHandler(this, mainGUI, setupGUI, sendPlayerMsg, getEnchant);
    private MessageChanger messageChanger = new MessageChanger(this, setupGUI );
    private EventHandler evt = new EventHandler(this, enchantHandler, setupGUI, messageChanger, sendPlayerMsg, getEnchant);
    private Check check = new Check(this,evt, setupGUI, mainGUI, getEnchant, enchantSettingsGUIs);
    private ClickMainGUI clickMainGUI = new ClickMainGUI(check);
    private ClickSetupGUI clickSetupGUI = new ClickSetupGUI(check);
    private GUISelector guiSelector = new GUISelector(this, clickSetupGUI, clickMainGUI);
    private CreateConfig createConfig = new CreateConfig(this);


    @Override
    public void onEnable() {
        getVersion();
        evt.reqisterEvents(version);
        getCommand("EnchantControl").setExecutor(new CommandHandler(this, mainGUI, setupGUI));
        Bukkit.getPluginManager().registerEvents(new EventHandler(this, enchantHandler, setupGUI, messageChanger, sendPlayerMsg, getEnchant), this);

        saveDefaultConfig();
        loadDefaultConfig();
        Bukkit.getServer().getLogger().info("EnchantControl Enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().info("EnchantControl Disabled");
    }

    private void loadDefaultConfig(){
        enchantConfigSection = getConfig().getConfigurationSection("enchants");
        createConfig.createStandard();
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
        if (ver.contains("1.8")) {
            version = 1.8;
        } else if (ver.contains("1.9")) {
            version = 1.9;
        } else if (ver.contains("1.10")) {
            version = 1.10;
        } else if (ver.contains("1.11")) {
            version = 1.11;
        } else if (ver.contains("1.12")) {
            version = 1.12;
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage("§3[§cEnchantControl§3] §cCould not determine version. Assuming you are running higher than §b1.12");
            version = 10.0;
        }
    }

}
