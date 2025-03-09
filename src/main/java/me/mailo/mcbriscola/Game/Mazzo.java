package me.mailo.mcbriscola.Game;

import java.util.ArrayList;

public class Mazzo {

    private final ArrayList<Carta> mazzo = new ArrayList<>();

    public Mazzo() {
        String[] semi = {"Spade", "Denari", "Coppe", "Bastoni"};
        for (String seme : semi) {
            for (int i = 1; i <= 10; i++) {
                mazzo.add(new Carta(i, seme));
            }
        }
    }

    public ArrayList<Carta> getMazzo() {
        return mazzo;
    }

    public Carta get(int i) {
        return this.mazzo.get(i);
    }

    public Carta getCartaRnd() {
        Carta carta = this.get((int) (Math.random() * this.getMazzo().size()));
        this.getMazzo().remove(carta);
        return carta;
    }
}
