import java.util.*;

public class Sona {
    private StringBuilder sona;
    private ArrayList<Character> tähed = new ArrayList<>(Arrays.asList('A', 'B', 'D', 'E', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'U', 'V', 'Ä', 'Õ', 'Ö', 'Ü'));

    public Sona(StringBuilder sona) {
        this.sona = sona;
    }


    //eemaldab etteantud tähe listist
    public void taheEemaldus(Character x){
        this.tähed.remove(x);
    }


    //Tagastab sõna varjatud kohtadega, kui tähte on tähed listis olemas, siis kuvab seda kohta kui _ .
    public StringBuilder peidetudTahtedega(){
        StringBuilder varjatud = new StringBuilder(this.sona);
        for (int i = 0; i < this.sona.length(); i++) {
            if (tähed.contains(varjatud.charAt(i))){
                varjatud.setCharAt(i, '_');
            }
        }
        return varjatud;
    }

    public ArrayList<Character> getTähed() {
        return tähed;
    }


    //tagastab tõeväärtuse, kas on jäänud veel tähti arvata
    public Boolean allKriipuSisalduvus(){
        return !peidetudTahtedega().toString().contains("_");
    }


    public StringBuilder getSona() {
        return sona;
    }
}
