package me.mailo.mcbriscola.Game;

import me.mailo.mcbriscola.MCBriscola;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gioco implements Listener {

    public static boolean singlePlayer = false;
    public boolean gameFinished = true;
    private final ItemStack BLANK = new ItemStack(Material.PAPER);
    private final ItemMeta BLANKMETA = BLANK.getItemMeta();
    private final Logger logger = MCBriscola.getInstance().getLogger();
    private final ArrayList<GamePlayer> players = new ArrayList<>();
    private Mazzo mazzoToa;
    private int playerTurn;
    private int lastPlayerWon = 0;
    private Carta briscola;
    private ItemStack pointCounterPL1 = new ItemStack(Material.PLAYER_HEAD);
    private ItemStack pointCounterPL2 = new ItemStack(Material.PLAYER_HEAD);

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getView().getTitle().contains("Ea Toa dea Briscola")) {
            mazzoToa = new Mazzo();

            //Creating players
            if (singlePlayer) {
                players.add(new GamePlayer(event.getViewers().get(0)));
                players.add(new Bot(null));
            } else {
                if (event.getView().getTitle().equals("Ea Toa dea Briscola - Player 1")) {
                    players.add(new GamePlayer(event.getViewers().get(0)));
                    logger.log(Level.SEVERE, "Added Player1");
                }

                if (event.getView().getTitle().equals("Ea Toa dea Briscola - Player 2")) {
                    players.add(new GamePlayer(event.getViewers().get(0)));
                    logger.log(Level.SEVERE, "AddedPlayer 2");
                }
            }

            logger.log(Level.SEVERE, players.toString());

            if (players.size() == 2) {
                SkullMeta counterPl1Meta = (SkullMeta) pointCounterPL1.getItemMeta();
                counterPl1Meta.setOwner(players.get(0).getMCplayer().getDisplayName());
                counterPl1Meta.setDisplayName(players.get(0).getMCplayer().getDisplayName());
                List<String> lore = new ArrayList<>();
                lore.add("Current Points: 0");
                counterPl1Meta.setLore(lore);
                pointCounterPL1.setItemMeta(counterPl1Meta);

                SkullMeta counterPl2Meta = (SkullMeta) pointCounterPL2.getItemMeta();
                counterPl2Meta.setOwner(players.get(1).getMCplayer().getDisplayName());
                counterPl2Meta.setDisplayName(players.get(1).getMCplayer().getDisplayName());
                List<String> lore2 = new ArrayList<>();
                lore2.add("Current Points: 0");
                counterPl2Meta.setLore(lore2);
                pointCounterPL2.setItemMeta(counterPl2Meta);

                players.get(0).setMano(mazzoToa);
                players.get(1).setMano(mazzoToa);

                // Creating ToaPl1
                BLANKMETA.setDisplayName("???");
                BLANK.setItemMeta(BLANKMETA);
                briscola = mazzoToa.getCartaRnd();

                Toa.ToaPl1.setItem(18, briscola);
                Toa.ToaPl1.setItem(3, BLANK);
                Toa.ToaPl1.setItem(4, BLANK);
                Toa.ToaPl1.setItem(5, BLANK);
                Toa.ToaPl1.setItem(41, players.get(0).getMano().get(0));
                Toa.ToaPl1.setItem(40, players.get(0).getMano().get(1));
                Toa.ToaPl1.setItem(39, players.get(0).getMano().get(2));
                Toa.ToaPl1.setItem(44, pointCounterPL1);

                // creatin toaPl2
                if (event.getView().getTitle().equalsIgnoreCase("Ea Toa dea Briscola - Player 2")) {
                    event.getInventory().setItem(18, briscola);
                    event.getInventory().setItem(3, BLANK);
                    event.getInventory().setItem(4, BLANK);
                    event.getInventory().setItem(5, BLANK);
                    event.getInventory().setItem(41, players.get(1).getMano().get(0));
                    event.getInventory().setItem(40, players.get(1).getMano().get(1));
                    event.getInventory().setItem(39, players.get(1).getMano().get(2));
                    event.getInventory().setItem(44, pointCounterPL2);
                }

                playerTurn = 0;
                gameFinished = false;
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!gameFinished) {
            if (event.getView().getTitle().contains("Ea Toa dea Briscola")) {
                for (GamePlayer p : players) {
                    p.getMCplayer().sendMessage(ChatColor.RED + MCBriscola.langLoader.get("closed_game"));
                }

                players.clear();
            }
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
        logger.log(Level.SEVERE, "Player turn: " + playerTurn);

        if (gameFinished) {
            for (GamePlayer p : players) {
                p.getMCplayer().closeInventory();
            }

            annouceVictory();
            event.setCancelled(true);
            return;
        }

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
            repaintInventory(event);
            if (lastPlayerWon == 1) {
                playerTurn = 0;
            } else if (lastPlayerWon == 0) {
                playerTurn = 1;
            }
            return;

        } else if (event.getInventory().getItem(23) == null) {
            players.get(0).getMCplayer().getOpenInventory().setItem(23, event.getCurrentItem());
            players.get(1).getMCplayer().getOpenInventory().setItem(23, event.getCurrentItem());
            repaintInventory(event);

        } else {
            event.setCancelled(true);
            return;
        }

        checkWin(new Carta(event.getInventory().getItem(22)), new Carta(event.getInventory().getItem(23)));
        event.setCancelled(true);
    }

    private void annouceVictory() {
        int playerVictory;

        if (players.get(0).getSommaPunti() > players.get(1).getSommaPunti()) {
            playerVictory = 0;
        } else {
            playerVictory = 1;
        }

        for (GamePlayer p : players) {
            p.getMCplayer().sendMessage(ChatColor.GREEN + "------------------ MCBriscola ------------------");
            p.getMCplayer().sendMessage(ChatColor.GREEN + "The player " + players.get(playerVictory).getMCplayer().getDisplayName() + " has won this briscola game!");
            p.getMCplayer().sendMessage(ChatColor.GREEN + players.get(0).getMCplayer().getDisplayName() + " somma punti: " + players.get(0).getSommaPunti());
            p.getMCplayer().sendMessage(ChatColor.GREEN + players.get(1).getMCplayer().getDisplayName() + " somma punti: " + players.get(1).getSommaPunti());
            p.getMCplayer().sendMessage(ChatColor.GREEN + "------------------------------------------------");
        }
    }

    private void repaintInventory(InventoryClickEvent event) {
        switch (event.getSlot()) {
            case 39: players.get(playerTurn).getMano().set(2, null); break;
            case 40: players.get(playerTurn).getMano().set(1, null); break;
            case 41: players.get(playerTurn).getMano().set(0, null); break;
        }

        event.getInventory().clear(event.getSlot());
        players.get(playerTurn).getMCplayer().setItemOnCursor(null);
    }

    private void checkWin(Carta c1, Carta c2) {
        logger.log(Level.INFO, "Checking Win...");

        if (isBriscola(c1)) {
            playerTurn = lastPlayerWon;
            players.get(lastPlayerWon).addPunti(c1.getValue());
        } else if (isBriscola(c2)) {
            assignWin();
            players.get(lastPlayerWon).addPunti(c2.getValue());
        } else {
            if (c1.getSeme() != c2.getSeme()) {
                playerTurn = lastPlayerWon;
                players.get(lastPlayerWon).addPunti(c1.getValue());
            } else if (c1.getSeme() == c2.getSeme()) {
                if (c1.getValue() > c2.getValue()) {
                    playerTurn = lastPlayerWon;
                    players.get(lastPlayerWon).addPunti(c1.getValue());
                } else {
                    assignWin();
                    players.get(lastPlayerWon).addPunti(c2.getValue());
                }
            } else {
                throw new RuntimeException("BO!");
            }
        }

        clearBanco();

        fullManos();

        updateGUIPointsAndTurn();
    }

    private void updateGUIPointsAndTurn() {
        Toa.ToaPl1.getItem(44).getItemMeta().getLore().set(0, "Current Points: " + players.get(0).getSommaPunti());
        Toa.ToaPl2.getItem(44).getItemMeta().getLore().set(0, "Current Points: " + players.get(1).getSommaPunti());
    }

    private void assignWin() {
        if (lastPlayerWon == 1) {
            playerTurn = 0;
        } else if (lastPlayerWon == 0) {
            playerTurn = 1;
        }

        lastPlayerWon = playerTurn;
    }

    private boolean isBriscola(Carta c) {
        return c.getSeme() == briscola.getSeme();
    }

    private void clearBanco() {
        Toa.ToaPl1.clear(22);
        Toa.ToaPl1.clear(23);
        Toa.ToaPl2.clear(22);
        Toa.ToaPl2.clear(23);
    }

    /**
     *
     * @return true if mazzo has carte, false if mazzo is empty
     */
    private void fullManos() {
        for (GamePlayer p : players) {
            for (int i = 0; i < p.getMano().size(); i++) {
                if (p.getMano().get(i) == null) {
                    try {
                        p.getMano().set(i, mazzoToa.getCartaRnd());
                    } catch (Exception ignored) {}
                }
            }
        }

        if (players.get(0).getMano().stream().allMatch(Objects::isNull) && !(players.get(1).getMano().stream().allMatch(Objects::isNull))) {
            // briscola to player 0
            players.get(0).getMano().set(0, briscola);
            Toa.ToaPl1.clear(18);
            Toa.ToaPl2.clear(18);
        } else if (players.get(1).getMano().stream().allMatch(Objects::isNull) && !(players.get(0).getMano().stream().allMatch(Objects::isNull))) {
            // briscola to player 1
            players.get(1).getMano().set(0, briscola);
            Toa.ToaPl1.clear(18);
            Toa.ToaPl2.clear(18);
        } else if (players.get(0).getMano().stream().allMatch(Objects::isNull) && players.get(1).getMano().stream().allMatch(Objects::isNull)) {
            gameFinished = true;
        }

        Toa.ToaPl1.setItem(41, players.get(0).getMano().get(0));
        Toa.ToaPl1.setItem(40, players.get(0).getMano().get(1));
        Toa.ToaPl1.setItem(39, players.get(0).getMano().get(2));
        Toa.ToaPl2.setItem(41, players.get(1).getMano().get(0));
        Toa.ToaPl2.setItem(40, players.get(1).getMano().get(1));
        Toa.ToaPl2.setItem(39, players.get(1).getMano().get(2));
    }
}
