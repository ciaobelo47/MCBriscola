package me.mailo.mcbriscola.Game;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GamePlayer {

    private Player MCplayer;
    private boolean turn;
    private ArrayList<Carta> mano = new ArrayList<>(3);

    public GamePlayer(Player MCplayer) {
        this.MCplayer = MCplayer;
    }

    public GamePlayer(HumanEntity MCplayer) {
        this.MCplayer = (Player) MCplayer;
    }

    public GamePlayer() {}

    public Player getMCplayer() {
        return MCplayer;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void setMano(Mazzo mazzo) {
        for (int i = 0; i < 3; i++) {
            this.mano.add(mazzo.getCartaRnd());
        }
    }
}
