package me.mailo.mcbriscola.Game;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class GamePlayer {

    private final Player MCplayer;
    private boolean turn;

    public GamePlayer(Player MCplayer) {
        this.MCplayer = MCplayer;
    }

    public GamePlayer(HumanEntity MCplayer){
        this.MCplayer = (Player) MCplayer;
    }

    public Player getMCplayer() {
        return MCplayer;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
