package com.gmail.arkobat.EnchantControl.Utilities;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

public class GetEnchant {

    private EnchantControl enchantControl;
    private MainGUI mainGUI;
    public GetEnchant(EnchantControl enchantControl, MainGUI mainGUI) {
        this.enchantControl = enchantControl;
        this.mainGUI = mainGUI;
    }

    public String removeColorCode(String string) {
        /*
        String[] colors = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};
        for (String s : colors) {
            string = string.replaceAll("§" + s,"");
            string = string.replaceAll("§" + s.toUpperCase(), "");
        }
*/
        return ChatColor.stripColor(string);
    }

    public String getFriendlyName(Enchantment enchantment) {
        for (String key : enchantControl.enchantConfigSection.getKeys(false)) {
            if (enchantControl.enchantConfigSection.getString(key + ".bukkitName").equalsIgnoreCase(enchantment.getName())) {
                return enchantControl.enchantConfigSection.getString(key + ".name");
            }
        }
        return null;
    }

    public String getFriendlyName(String id) {
        for (String key : enchantControl.enchantConfigSection.getKeys(false)) {
            if (key.equalsIgnoreCase(id)) {
                return enchantControl.enchantConfigSection.getString(key + ".name");
            }
        }
        return null;
    }

    public int getIDInt(String friendlyName) {
        friendlyName = removeColorCode(friendlyName);
        for (String key : enchantControl.enchantConfigSection.getKeys(false)) {
            if (enchantControl.enchantConfigSection.contains(key + "." + friendlyName)) {
                return Integer.parseInt(key);
            }
        }
        return -1;
    }

    public String getIDSting(String friendlyName) {
        friendlyName = removeColorCode(friendlyName);
        for (String key : enchantControl.enchantConfigSection.getKeys(false)) {
            if (enchantControl.enchantConfigSection.get(key + ".name").toString().equalsIgnoreCase(friendlyName)) {
                return key;
            }
        }
        return null;
    }

    public String getIDSting(Enchantment enchantment) {
        for (String key : enchantControl.enchantConfigSection.getKeys(false)) {
            if (enchantControl.enchantConfigSection.get(key + ".bukkitName").toString().equalsIgnoreCase(enchantment.getName())) {
                return key;
            }
        }
        return null;
    }

}
