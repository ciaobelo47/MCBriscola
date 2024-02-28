package me.mailo.mcbriscola.Game;

import java.util.ArrayList;
import java.util.List;

public class Mazzo {

    private List<Carta> mazzo = new ArrayList<>();
    private Carta briscola;
    public Mazzo(){
        String[] semi = {"Spade","Denari","Coppe","Bastoni"};
        for (String seme : semi){
            for (int i = 1; i <= 10; i++) {
                mazzo.add(new Carta(i,seme));
            }
        }

        this.briscola = mazzo.get((int) (Math.random()*40));
    }

    public Carta getBriscola() {
        return briscola;
    }

    public void setBriscola(Carta briscola) {
        this.briscola = briscola;
    }

    public List<Carta> getMazzo() {
        return mazzo;
    }

    public void setMazzo(List<Carta> mazzo) {
        this.mazzo = mazzo;
    }

    public Carta get(int i) {
        Carta carta = this.mazzo.get(i);
        return carta;
    }
}
