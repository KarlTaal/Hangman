package oop;

import java.util.*;

public class Sona {
    private StringBuilder sona;
    private ArrayList<Character> tähed = new ArrayList<>(Arrays.asList('A', 'B', 'D', 'E', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'Z', 'T', 'U', 'V', 'Ä', 'Õ', 'Ö', 'Ü'));
    private int viga;

    public Sona(StringBuilder sona, int viga) {
        this.sona = sona;
        this.viga = viga;
    }

    public void setTähed(ArrayList<Character> tähed) {
        this.tähed = tähed;
    }


    public void algseadistaTähed() {
        this.tähed = new ArrayList<>(Arrays.asList('A', 'B', 'D', 'E', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'Z', 'T', 'U', 'V', 'Ä', 'Õ', 'Ö', 'Ü'));
    }

    public int getViga() {
        return viga;
    }

    public void setViga(int viga) {
        this.viga = viga;
    }

    //eemaldab etteantud tähe listist
    public void taheEemaldus(Character x) {
        this.tähed.remove(x);
    }

    public StringBuilder peidetudTahtedega() {
        StringBuilder varjatud = new StringBuilder(this.sona);
        for (int i = 1; i < varjatud.length(); i++) {
            varjatud.insert(i, " ");
            i++;
        }
        for (int i = 0; i < varjatud.length(); i++) {
            if (tähed.contains(varjatud.charAt(i))) {
                varjatud.setCharAt(i, '_');
            }
        }
        return varjatud;
    }


    public ArrayList<Character> getTähed() {
        return tähed;
    }

    //tagastab tõeväärtuse, kas on jäänud veel tähti arvata
    public Boolean allKriipuSisalduvus() {
        return peidetudTahtedega().toString().contains("_");
    }


    public StringBuilder getSona() {
        return sona;
    }

    public void setSona(StringBuilder sona) {
        this.sona = sona;
    }

    public String allesJaanudTahed() {
        String jaanud = "";
        for (Character character : this.tähed) {
            if (this.sona.indexOf(character.toString()) != -1) {
                jaanud += character;
            }
        }
        return jaanud;
    }

    public String annaSuvalinetäht() {
        int indeks = suvalineArv(0, allesJaanudTahed().length() - 1);
        return "" + allesJaanudTahed().charAt(indeks);
    }

    public Integer suvalineArv(int alumine, int ulemine) {
        return (alumine + (int) (Math.random() * ((ulemine - alumine) + 1)));
    }

    //tagastab dongeri vastavalt vigade arvule
    public String dongerRating(int viga) {
        if (viga == 1) {
            return ("༼つ ◕_◕ ༽つ  viga: 1");
        } else if (viga == 2) {
            return ("(งಠل͜ಠ)ง  viga: 2");
        } else if (viga == 3) {
            return ("໒( • ͜ʖ • )७  viga: 3");
        } else if (viga == 4) {
            return ("( * ಥ ⌂ ಥ * )  viga: 4");
        } else if (viga == 5) {
            return ("( ✖ _ ✖ ) viga: 5 (oh man sorry u dead)");
        }
        return null;
    }


}
