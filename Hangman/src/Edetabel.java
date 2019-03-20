import java.io.*;
import java.util.*;

public class Edetabel {
    ArrayList<Mangija> tabel = new ArrayList<>();


    public void lisaMangija(Mangija profiil) {
        this.tabel.add(profiil);
    }


    public ArrayList<Mangija> getTabel() {
        return tabel;
    }


    //tagastab tõeväärtuse, kas edetabel on tühi
    public Boolean kasTühi() {
        return this.tabel.size() == 0;
    }


    //sorteerib edetabeli ära
    public void sorteeriTabel() {
        Collections.sort(this.tabel);
    }


    //loeb failist edetabeli endale sisse
    public void loeTabelFailist() throws Exception {
        File fail = new File("Edetabel.txt");
        try (Scanner f = new Scanner(fail, "UTF-8")) {
            while (f.hasNextLine()) {
                String rida = f.nextLine();
                rida = rida.strip();
                String[] tükid = rida.split(" ");
                Mangija a = new Mangija(tükid[0], Integer.parseInt(tükid[1]), Integer.parseInt(tükid[2]));
                this.tabel.add(a);
            }
        }

    }


    //kirjutab praegused kõik edetabelis olevad mängijad faili
    public void kirjutaFaili() throws Exception {
        File fail = new File("Edetabel.txt");
        try (java.io.PrintWriter f = new java.io.PrintWriter(fail, "UTF-8")) {
            for (Mangija mangija : this.tabel) {
                f.println(mangija.getNimi() + " " + mangija.getSkoor() + " " + mangija.getArvatudSonu());
            }
        }

    }
}
