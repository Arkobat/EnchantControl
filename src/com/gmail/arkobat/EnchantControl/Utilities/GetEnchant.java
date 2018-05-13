package com.gmail.arkobat.EnchantControl.Utilities;

import com.gmail.arkobat.EnchantControl.EnchantControl;
import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;
import org.bukkit.enchantments.Enchantment;

public class GetEnchant {

    private EnchantControl enchantControl;
    private MainGUI mainGUI;
    public GetEnchant(EnchantControl enchantControl, MainGUI mainGUI) {
        this.enchantControl = enchantControl;
        this.mainGUI = mainGUI;
    }

    public String getFriendlyEnchantName(Enchantment enchantment) {
        String name = enchantment.getName();
        for (String enchant : mainGUI.enchantsList) {
            if (enchant.contains(enchantment.getName())) {
                String[] names = enchant.split(":");
                name = names[0].replace("_", " ");
                return name;
            }
        }
        return name;
    }

}
