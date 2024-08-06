package me.mailo.mcbriscola.Game;

import java.util.ArrayList;
import java.util.List;

public class Mazzo {

    private final List<Carta> Mazzo = new ArrayList<>();

    public Mazzo() {
        String[] semi = {"Spade", "Denari", "Coppe", "Bastoni"};
        for (String seme : semi) {
            for (int i = 1; i <= 10; i++) {
                Mazzo.add(new Carta(i, seme));
            }
        }
    }

    public List<Carta> getMazzo() {
        return Mazzo;
    }

    public Carta get(int i) {
        return this.Mazzo.get(i);
    }

    public Carta getCartaRnd(Mazzo mazzo) {
        Carta carta = mazzo.get((int) (Math.random() * 40));
        mazzo.getMazzo().remove(carta);
        return carta;
    }
}
