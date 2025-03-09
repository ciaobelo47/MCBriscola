package me.mailo.mcbriscola.Game;

import me.mailo.mcbriscola.MCBriscola;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gioco implements Listener {

    public static boolean singlePlayer = false;
    private final ItemStack BLANK = new ItemStack(Material.PAPER);
    private final ItemMeta BLANKMETA = BLANK.getItemMeta();
    private final Logger logger = MCBriscola.getInstance().getLogger();
    private final ArrayList<GamePlayer> players = new ArrayList<>(2);
    private int playerTurn;

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Mazzo mazzoToa = new Mazzo();

        if (event.getView().getTitle().contains("Ea Toa dea Briscola")) {
            //Creating players
            if (singlePlayer) {
                players.add(new GamePlayer(event.getViewers().get(0)));
                players.add(new Bot());
            } else {
                if (event.getView().getTitle().equals("Ea Toa dea Briscola - Player 1")) {
                    players.add(new GamePlayer(event.getViewers().get(0)));
                }

                if (event.getView().getTitle().equals("Ea Toa dea Briscola - Player 2")) {
                    players.add(new GamePlayer(event.getViewers().get(0)));
                }
            }

            players.get(0).setMano(mazzoToa);
            players.get(1).setMano(mazzoToa);

            // Creating ToaPl1
            BLANKMETA.setDisplayName("???");
            BLANK.setItemMeta(BLANKMETA);
            Carta briscola = mazzoToa.getCartaRnd();
            if (event.getView().getTitle().equalsIgnoreCase("Ea Toa dea Briscola - Player 1")) {
                event.getInventory().setItem(18, briscola);
                event.getInventory().setItem(3, BLANK);
                event.getInventory().setItem(4, BLANK);
                event.getInventory().setItem(5, BLANK);
                event.getInventory().setItem(41, players.get(0).getMano().get(0));
                event.getInventory().setItem(40, players.get(0).getMano().get(1));
                event.getInventory().setItem(39, players.get(0).getMano().get(2));
            }

            if (event.getView().getTitle().equalsIgnoreCase("Ea Toa dea Briscola - Player 2")) {
                event.getInventory().setItem(18, briscola);
                event.getInventory().setItem(3, BLANK);
                event.getInventory().setItem(4, BLANK);
                event.getInventory().setItem(5, BLANK);
                event.getInventory().setItem(41, players.get(1).getMano().get(0));
                event.getInventory().setItem(40, players.get(1).getMano().get(1));
                event.getInventory().setItem(39, players.get(1).getMano().get(2));
            }

            playerTurn = 0;
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase("Ea Toa dea Briscola - Player 1") || event.getView().getTitle().equals("Ea Toa dea Briscola - Player 2")) {
//            if (singlePlayer) {
//
//            }
            multiplayerTurno(event);
        }
    }

    private void multiplayerTurno(InventoryClickEvent event) {
        players.get(playerTurn).setTurn(true);

        if (event.getCurrentItem() == null || event.getSlot() == 18 || event.getCurrentItem().equals(BLANK)) {
            event.setCancelled(true);
            return;
        }

        if (!event.getWhoClicked().equals(players.get(playerTurn).getMCplayer())) {
            event.setCancelled(true);
            return;
        }

        //player turn choice
        if (event.getInventory().getItem(22) == null) {
            players.get(0).getMCplayer().getOpenInventory().setItem(22, event.getCurrentItem());
            players.get(1).getMCplayer().getOpenInventory().setItem(22, event.getCurrentItem());
//            event.getInventory().setItem(22, event.getCurrentItem());

        } else if (event.getInventory().getItem(23) == null) {
            players.get(0).getMCplayer().getOpenInventory().setItem(23, event.getCurrentItem());
            players.get(1).getMCplayer().getOpenInventory().setItem(23, event.getCurrentItem());
//            event.getInventory().setItem(23, event.getCurrentItem());

        } else {
            event.setCancelled(true);
            return;
        }

        players.get(playerTurn).getMano().remove(event.getCurrentItem());
        logger.log(Level.SEVERE, players.get(playerTurn).getMano().toString());

        event.getInventory().clear(event.getSlot());
        players.get(playerTurn).getMCplayer().setItemOnCursor(null);
        players.get(playerTurn).setTurn(false);

        checkWin(event.getInventory().getItem(22), event.getInventory().getItem(23));
        event.setCancelled(true);
    }

    private void checkWin(ItemStack c1, ItemStack c2) {
        new Carta(c1);
        new Carta(c2);
    }

}
