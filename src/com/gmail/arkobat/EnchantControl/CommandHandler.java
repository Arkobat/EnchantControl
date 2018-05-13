package com.gmail.arkobat.EnchantControl;

import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandHandler implements CommandExecutor {

    private final EnchantControl enchantControl;
    private final MainGUI mainGUI;
    private final SetupGUI setupGUI;

    public CommandHandler(EnchantControl enchantControl, MainGUI mainGUI, SetupGUI setupGUI) {
        this.enchantControl = enchantControl;
        this.mainGUI = mainGUI;
        this.setupGUI = setupGUI;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("EnchantControl")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("EnchantControl.admin")) {
                    if (!enchantControl.setup) {
                        p.openInventory(setupGUI.inventory);
                    } else {
                        p.openInventory(mainGUI.inventory);
                    }
                }
            } else {
                sender.sendMessage(enchantControl.prefix + " §3This command can only be executed ingame");
            }
        }
        return true;
    }
}