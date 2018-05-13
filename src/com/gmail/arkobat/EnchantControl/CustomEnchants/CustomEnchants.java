package com.gmail.arkobat.EnchantControl.CustomEnchants;

public class CustomEnchants {

    private CheckPlugin checkPlugin = new CheckPlugin();

    private Boolean isInstalled() {
        if (checkPlugin.isInstalled()) {
            return (checkPlugin.isEnabled());
        }
        return false;
    }
}
