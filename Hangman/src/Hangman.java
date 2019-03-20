import java.util.*;
import java.io.File;


public class Hangman {
    public static void main(String[] args) throws Exception {
        ArrayList<String> koikSonad = failistJarjend();

        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta mängija nimi: ");
        String N = scan.next();

        Mangija player = new Mangija(N, 0,0, 0);

        mang(koikSonad, player);

        System.out.println("Aeg sai otsa!");
        System.out.println(player);
    }


//kahekordne while loop, esimeses käib kogu mäng ja teine loop on iga sõna jaoks, kus hakkab tähti küsima kasutajalt
    public static void mang(ArrayList<String> sonad, Mangija profiil) {
        long algusaeg = System.nanoTime();
        while (true) {
            if (kulunudAeg(algusaeg) > 30) {
                break;
            }
            String a = sonad.get(suvalineArv(0, sonad.size()));
            Sona sõna = new Sona(new StringBuilder(a));

            sonaArvamine(algusaeg, profiil, sõna);
        }
    }


    //sõna arvamise loop
    public static void sonaArvamine(double algusaeg, Mangija profiil, Sona sõna){
        while (true){
            if (kulunudAeg(algusaeg) > 30) {
                break;
            }
            System.out.println("Vastuse võimaluse on: " + sõna.getTähed());
            System.out.println(sõna.getSona());    //näitab sõna sulle ette praegu, mis on arvamisel
            System.out.println("Praegune sõna, mis on arvamisel: " + "\n" + sõna.peidetudTahtedega());
            Scanner scan = new Scanner(System.in);
            System.out.println("Sisesta täht: ");
            char ch = Character.toUpperCase(scan.next().charAt(0));
            String lol = "" + ch;
            if(sõna.getTähed().contains(ch)){//kui pakutud täht on tähtede listis olemas, siis eemaldab selle sealt
                if (sõna.getSona().indexOf(lol) >= 0){
                    profiil.setSkoor(profiil.getSkoor()+10);
                }
                if (sõna.getSona().indexOf(lol) == -1){
                    profiil.setViga(profiil.getViga()+1);
                    System.out.println(profiil.getViga());
                    System.out.println(profiil.dongerRating(profiil.getViga()));
                    profiil.setSkoor(profiil.getSkoor()-5);
                }
                sõna.taheEemaldus(ch);           // ja selle tulemusel muutub ka kohe peidetudTahtedega meetodi tagastus
            }
            if (sõna.allKriipuSisalduvus()){//kui ühtegi allkriipsu pole enam, siis see tähendab et sõna on arvatud ja võetakse ette uus sõna|
                profiil.setViga(0);
                profiil.setSkoor(profiil.getSkoor()+(25));
                profiil.setArvatudSonu(profiil.getArvatudSonu()+1);
                System.out.println("Tubli, arvasid sõna ära: " + sõna.getSona() + "\n");
                break;
            }
        }
    }


    public static Double kulunudAeg(double algusaeg){
        long loppaeg = System.nanoTime();
        return (loppaeg - algusaeg) / 1.e9;
    }


    public static Integer suvalineArv(int alumine, int ulemine) {
        return (alumine + (int) (Math.random() * ((ulemine - alumine) + 1)));
    }


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