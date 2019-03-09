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

        System.out.println(player);
    }


//kahekordne while loop, esimeses käib kogu mäng ja teine loop on iga sõna jaoks, kus hakkab tähti küsima kasutajalt
    public static void mang(ArrayList<String> sonad, Mangija profiil) {
        long algusaeg = System.nanoTime();
        while (true) {
            if (kulunudAeg(algusaeg) > 30) {
                break;
            }
            String sona = sonad.get(suvalineArv(0, sonad.size()));

            sonaArvamine(algusaeg, profiil, sona);
            System.out.println("uus");
        }
    }


    //sõna arvamise loop
    public static void sonaArvamine(double algusaeg, Mangija profiil, String sona){
        while (true){
            if (kulunudAeg(algusaeg) > 30) {
                break;
            }

            //ns testing
            System.out.println("Praegune sõna, mis on arvamisel: " + sona);
            Scanner scan = new Scanner(System.in);
            System.out.println("Sisesta saadud punkte: ");
            int k = Integer.valueOf(scan.next());
            profiil.setSkoor(k);    //liidab skoorile juurde
            if (k == 10) {break;}   // praegu kui 10 sisestad siis arvab nagu sõna oleks ära arvatud ja läheb sellest loopist välja korraks
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