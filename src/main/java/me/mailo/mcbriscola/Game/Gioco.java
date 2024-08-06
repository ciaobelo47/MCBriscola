package me.mailo.mcbriscola.Game;

import me.mailo.mcbriscola.MCBriscola;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Gioco implements Listener {

    private final ItemStack BLANK = new ItemStack(Material.PAPER);
    private final ItemMeta BLANKMETA = BLANK.getItemMeta();
    private final Logger logger = MCBriscola.getInstance().getLogger();

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        // Creating Toa
        Mazzo MazzoToa = new Mazzo();
        BLANKMETA.setDisplayName("???");
        BLANK.setItemMeta(BLANKMETA);
        Carta briscola = MazzoToa.getCartaRnd(MazzoToa);
        if (event.getView().getTitle().equalsIgnoreCase("Ea Toa dea Briscola")) {
            event.getInventory().setItem(18, briscola);
            event.getInventory().setItem(3, BLANK);
            event.getInventory().setItem(4, BLANK);
            event.getInventory().setItem(5, BLANK);
            event.getInventory().setItem(41, MazzoToa.getCartaRnd(MazzoToa));
            event.getInventory().setItem(40, MazzoToa.getCartaRnd(MazzoToa));
            event.getInventory().setItem(39, MazzoToa.getCartaRnd(MazzoToa));
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        logger.log(Level.SEVERE, event.getInventory().getViewers().toString());
//        GamePlayer player1 = new GamePlayer(event.getInventory().getViewers().get(0));
//        GamePlayer player2 = new GamePlayer(event.getInventory().getViewers().get(1));
        if (event.getView().getTitle().equalsIgnoreCase("Ea Toa dea Briscola")) {
            if (event.getCurrentItem().equals(event.getInventory().getItem(18)) || event.getCurrentItem().equals(BLANK)) {
                event.setCancelled(true);
                return;
            }

            event.getInventory().setItem(22,event.getCurrentItem());
            event.getInventory().remove(event.getWhoClicked().getItemOnCursor());
            event.setCancelled(true);

        }
    }

}
