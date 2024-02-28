package me.mailo.mcbriscola.Game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class Gioco implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Mazzo mazzo = new Mazzo();
        Carta briscola = mazzo.getBriscola();
        if (event.getView().getTitle().equalsIgnoreCase("Ea Toa dea Briscola")){
            event.getInventory().setItem(18,briscola);
        }

    }

}
