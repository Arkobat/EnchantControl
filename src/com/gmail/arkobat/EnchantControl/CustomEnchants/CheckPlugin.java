package com.gmail.arkobat.EnchantControl.CustomEnchants;

import org.bukkit.Bukkit;

public class CheckPlugin {

    public Boolean isInstalled() {
        return (Bukkit.getServer().getPluginManager().getPlugin("CustomEnchants") != null);
    }

    public Boolean isEnabled() {
        return  (Bukkit.getServer().getPluginManager().getPlugin("CustomEnchants").isEnabled());
    }
}
