package com.gmail.arkobat.EnchantControl;

import com.gmail.arkobat.EnchantControl.EventHandler.EventHandler;
import com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick.Check;
import com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick.ClickMainGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick.ClickSetupGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.InventoryClick.GUISelector;
import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import com.gmail.arkobat.EnchantControl.Utilities.GetEnchant;
import com.gmail.arkobat.EnchantControl.Utilities.SendPlayerMsg;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnchantControl extends JavaPlugin{

    public String GUIIdentifier = "§¾§¯§¿";
    public Boolean setup;
    public List<String> disabled = new ArrayList<>();
    public ItemStack fillerItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
    public String prefix;
    public String enchantCancel;
    public String removedEnchant;
    public List<String> msgAdd = new ArrayList<>();
    public Boolean book;
    public double version;

    public ConfigurationSection enchants;
    public List<Enchantment> disabledEnchants = new ArrayList<>();
    public Map<Enchantment, Integer> maxLevel;

//    private CreateConfig createConfig = new CreateConfig(this);
    private MainGUI mainGUI = new MainGUI(this);
    private SetupGUI setupGUI = new SetupGUI(this);
    private GetEnchant getEnchant = new GetEnchant(this, mainGUI);
    private SendPlayerMsg sendPlayerMsg = new SendPlayerMsg(this, getEnchant);
    private EnchantHandler enchantHandler = new EnchantHandler(this, mainGUI, setupGUI, sendPlayerMsg);
    private MessageChanger messageChanger = new MessageChanger(this, setupGUI );
    private EventHandler evt = new EventHandler(this, enchantHandler, setupGUI, messageChanger, sendPlayerMsg);
    private Check check = new Check(this,evt, setupGUI, mainGUI);
    private ClickMainGUI clickMainGUI = new ClickMainGUI(check);
    private ClickSetupGUI clickSetupGUI = new ClickSetupGUI(check);
    private GUISelector guiSelector = new GUISelector(this, clickSetupGUI, clickMainGUI);


    @Override
    public void onEnable() {
        getVersion();
        evt.reqisterEvents(version);
        getCommand("EnchantControl").setExecutor(new CommandHandler(this, mainGUI, setupGUI));
        Bukkit.getPluginManager().registerEvents(new EventHandler(this, enchantHandler, setupGUI, messageChanger, sendPlayerMsg), this);

        saveDefaultConfig();
        loadDefaultConfig();
        Bukkit.getServer().getLogger().info("EnchantControl Enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().info("EnchantControl Disabled");
    }

    private void loadDefaultConfig(){
        enchants = getConfig().getConfigurationSection("enchants");
        prefix = getConfig().getString("prefix");
        ItemMeta itemMeta = fillerItem.getItemMeta();
        itemMeta.setDisplayName("§r");
        fillerItem.setItemMeta(itemMeta);
        setup = getConfig().getBoolean("setup");
        disabled = getConfig().getStringList("disabled");
        mainGUI.defineEnchants();
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

    public void ifNotThenAdd(String path, String value) {
        if (!getConfig().contains(path)) {
            enchants.set(path, value);
        } else {
            enchants.set(path, getConfig().get(value));
        }
    }

    public void onClick(Inventory inventory, ItemStack clicked, ClickType type, Player player, int slot) {
        guiSelector.onClick(inventory, clicked, type, player, slot);
    }

    public void toEnchantHandler(String method, String enchant) {
        if (method.equals("add")) {
            enchantHandler.addEnchant(enchant);
        } else if (method.equals("remove")) {
            enchantHandler.removeEnchant(enchant);
        }

    }

    public void setMessage(String type, String message) {
        message = message.replaceAll("&", "§");
        if (type.equals("prefix")) {
            prefix = message;
            writeToConfig("prefix", message);
        } else if (type.equals("enchantCancel")) {
            enchantCancel = message;
            writeToConfig("enchantCancel", message);
        } else if (type.equals("removedEnchant")) {
            removedEnchant = message;
            writeToConfig("removedEnchant", message);
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
            Bukkit.getServer().getConsoleSender().sendMessage("§3[§cEnchantControl§3] §cCould not determine version. Assuming you are running higher than 1.12");
            version = 10.0;
        }
    }

}
