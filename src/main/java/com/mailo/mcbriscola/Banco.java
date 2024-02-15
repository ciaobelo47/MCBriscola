package com.mailo.mcbriscola;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class Banco implements CommandExecutor {
    public static Inventory Toa = Bukkit.createInventory((InventoryHolder) Bukkit.getServer(), InventoryType.ANVIL);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("briscola")){
            if (args[0].equalsIgnoreCase("start")){
                ToaCreate();
                p.openInventory(Toa);
            }
        }
        return false;
    }

    private void ToaCreate() {
    }
}
