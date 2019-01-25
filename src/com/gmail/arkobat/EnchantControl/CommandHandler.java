package com.gmail.arkobat.EnchantControl;

import com.gmail.arkobat.EnchantControl.GUIHandler.MainGUI;
import com.gmail.arkobat.EnchantControl.GUIHandler.SetupGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


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
                    if (args.length >= 1 && withArgs(args, p)) {
                        return true;
                    }
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

    private boolean withArgs (String[] args, Player p) {
        if (args[0].equalsIgnoreCase("debug")) {
            p.sendMessage("Server version " + enchantControl.version);
            return true;
        }
        if (args[0].equalsIgnoreCase("writeFullConfig")) {
            return writeFullConfig();
        } else if (args[0].equalsIgnoreCase("exclude")) {
            return exclude(p);
        }
        return false;
    }

    private boolean writeFullConfig() {
        for (String key : enchantControl.enchantConfigSection.keySet()) {
            Bukkit.getServer().getConsoleSender().sendMessage(key + ": " + enchantControl.enchantConfigSection.get(key));
        }
        return true;
    }

    private boolean exclude(Player p) {
        ItemStack itemStack;
        if (enchantControl.version == 1.08) {
            itemStack = p.getItemInHand();
        } else {
            itemStack = p.getInventory().getItemInMainHand();
        }
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            int amount = itemStack.getAmount();
            itemStack.setAmount(1);
            String str =itemStack.toString();
            itemStack.setAmount(amount);
            if (!enchantControl.excluded.contains(str)) {
                enchantControl.excluded.add(str);
                enchantControl.writeToConfig("excluded", enchantControl.excluded);
                p.sendMessage(enchantControl.prefix + " §aItem added to excluded list");
            } else {
                p.sendMessage(enchantControl.prefix + " §cItem already excluded");
            }
        }
        return true;
    }
}