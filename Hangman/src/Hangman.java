import java.util.*;
import java.io.File;


public class Hangman {
    public static void main(String[] args) throws Exception {
        Edetabel tabel = new Edetabel();
        tabel.loeTabelFailist();
        tabel.sorteeriTabel();
        System.out.println("Tere tulemas poomismängu. Alustades mängu on Teil aega 4 minutit arvata ära nii palju sõnu kui võimalik.");
        System.out.println("Iga ära arvatud tähe eest saate Te 10 punkti, äraarvatud sõna eest aga lausa 25 punkti!");
        System.out.println("Iga sõna korral on Teil 5 elu ja iga viga annab -5 punkti. Elude otsa saamise korral antakse Teile ette uus sõna.");
        System.out.println("NB! Kui tahate, et Teie tulemus edetabelisse salvestatakse, siis sulgege mäng vastava toimingu numbriga! Lisaks ei tohi sisestatud nimi sisaldada tühikuid!");
        System.out.println();
        System.out.println("Mängu alustamiseks sisesta 1.");
        System.out.println("Edetabeli nägemiseks sisesta 2.");
        System.out.println("Lõpetamiseks sisesta 3.");
        System.out.println();

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Sisesta toimingu number: ");
            int i = sc.nextInt();
            System.out.println();

            if (i == 1) {
                ArrayList<String> koikSonad = failistJarjend();
                mang(koikSonad, tabel);

            }

            if (i == 2) {
                if (tabel.kasTühi()) {
                    System.out.println("Edetabelis pole veel kahjuks mitte kedagi :(");
                    System.out.println();
                } else {
                    tabel.sorteeriTabel();
                    System.out.println("EDETABEL:");
                    int b = 0;
                    for (Mangija mangija : tabel.getTabel()) {
                        if (b == 10) {                            //väljastab ainult esimesed 10
                            break;
                        }
                        System.out.println(mangija);
                        b++;
                    }
                    System.out.println();
                }
            }

            if (i == 3) {
                tabel.sorteeriTabel();
                tabel.kirjutaFaili();
                System.out.println("Järgmise korrani!");
                break;
            }
        }
    }


    //kahekordne while loop, esimeses käib kogu mäng ja teine loop on iga sõna jaoks, kus hakkab tähti küsima kasutajalt
    public static void mang(ArrayList<String> sonad, Edetabel t) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta mängija nimi: ");
        String N = scan.next();
        Mangija profiil = new Mangija(N, 0, 0);

        long algusaeg = System.nanoTime();
        while (true) {
            if (kulunudAeg(algusaeg) > 240) {
                t.lisaMangija(profiil);
                break;
            }
            String a = sonad.get(suvalineArv(0, sonad.size()));
            Sona sõna = new Sona(new StringBuilder(a), 0);
            sonaArvamine(algusaeg, profiil, sõna, t);

        }

        System.out.println("AEG SAI OTSA!");
        System.out.println();
    }


    //sõna arvamise loop
    public static void sonaArvamine(double algusaeg, Mangija profiil, Sona sõna, Edetabel t) {
        while (true) {
            if (kulunudAeg(algusaeg) > 240) {
                break;
            }
            System.out.println("Vastuse võimalused on: " + sõna.getTähed());
            //System.out.println(sõna.getSona());    //näitab sõna sulle ette praegu, mis on arvamisel
            System.out.println("Praegune sõna, mis on arvamisel: " + "\n" + sõna.peidetudTahtedega());
            Scanner scan = new Scanner(System.in);
            System.out.println("Sisesta täht: ");
            char ch = Character.toUpperCase(scan.next().charAt(0));
            System.out.println();
            String lol = "" + ch;
            if (sõna.getTähed().contains(ch)) {//kui pakutud täht on tähtede listis olemas, siis eemaldab selle sealt
                if (sõna.getSona().indexOf(lol) >= 0) {
                    profiil.setSkoor(profiil.getSkoor() + 10);
                }
                if (sõna.getSona().indexOf(lol) == -1) {
                    sõna.setViga(sõna.getViga() + 1);
                    System.out.println(sõna.dongerRating(sõna.getViga()));
                    profiil.setSkoor(profiil.getSkoor() - 5);
                }
                sõna.taheEemaldus(ch);           // ja selle tulemusel muutub ka kohe peidetudTahtedega meetodi tagastus
            }
            if (sõna.allKriipuSisalduvus()) {//kui ühtegi allkriipsu pole enam, siis see tähendab et sõna on arvatud ja võetakse ette uus sõna|
                profiil.setSkoor(profiil.getSkoor() + (25));
                profiil.setArvatudSonu(profiil.getArvatudSonu() + 1);
                System.out.println("Tubli, arvasid sõna ära: " + sõna.getSona() + "\n");
                System.out.println("----------------------------------------------------------------------------------------------------");
                break;
            }
            if (sõna.getViga() == 5) {
                System.out.println("Õige vastus oli " + sõna.getSona() + ".");
                System.out.println("----------------------------------------------------------------------------------------------------");
                System.out.println("Proovime uue sõnaga!");
                break;
            }
        }
    }


    //tagastab arvutab kulunud aja
    public static Double kulunudAeg(double algusaeg) {
        long loppaeg = System.nanoTime();
        return (loppaeg - algusaeg) / 1.e9;
    }


    //tagastab suvalise arvu, millega valitakse sõna arvamisele
    public static Integer suvalineArv(int alumine, int ulemine) {
        return (alumine + (int) (Math.random() * ((ulemine - alumine) + 1)));
    }


    //tagastab järjendi, kus on sees kõik sõnad
    public static ArrayList<String> failistJarjend() throws Exception {
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
}