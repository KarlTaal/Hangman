package oop;

import java.io.*;
import java.util.*;

public class koikSonad {
    private List<String> sonad;

    public koikSonad() throws Exception{
        this.sonad = failistJarjend();
    }

    public String annaSõna(){
        int arv = suvalineArv(1, this.sonad.size()-1);
        return this.sonad.get(arv);
    }

    public ArrayList<String> failistJarjend() throws Exception {
        ArrayList<String> jarjend = new ArrayList<>();
        try (Scanner fail = new Scanner(new File("hangman_sonad.txt"), "UTF-8")) {
            while (fail.hasNextLine()) {
                String rida = fail.nextLine();
                rida = rida.toUpperCase();
                jarjend.add(rida);
            }
        }
        return jarjend;
    }


    //Juhuarv, ülemine&alumine on kaasaarvatud\\
    public Integer suvalineArv(int alumine, int ulemine){
        return (alumine + (int)(Math.random() * ((ulemine - alumine) + 1)));
    }

}
