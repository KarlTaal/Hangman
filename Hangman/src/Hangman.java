import java.util.*;
import java.io.File;


public class Hangman {
    public static void main(String[] args) throws Exception {
        ArrayList<String> a = failistJärjend();

        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta mängija nimi: ");
        String nimi = scan.next();

        mang(a);
    }





//peab tegema vist kahe kordse while loopi, esimeses käib kogu mäng ja teine loop on iga sõna jaoks, kus hakkab tähti küsima kasutajalt

    public static void mang(ArrayList<String> sõnad) {
        long algusaeg = System.nanoTime();
        while (true) {
            //Kui aeg on otsas siis breakib mängu loopi, aeg läheb käima peale nime sisestamist
            long lõppaeg = System.nanoTime();
            double aegSekundites = (lõppaeg - algusaeg) / 1.e9;
            if (aegSekundites > 60) {
                break;
            }
        }
    }


    public static Integer suvalineArv(int alumine, int ulemine) {
        return (alumine + (int) (Math.random() * ((ulemine - alumine) + 1)));
    }


    public static ArrayList<String> failistJärjend() throws Exception {
        ArrayList<String> järjend = new ArrayList<>();
        try (Scanner fail = new Scanner(new File("sonad_vähemalt_6_tähte.txt"), "UTF-8")) {
            while (fail.hasNextLine()) {
                String rida = fail.nextLine();
                rida = rida.replaceAll("[^\\p{Graph}\n\r\t ]", "");
                rida = rida.toUpperCase();
                järjend.add(rida);
            }
        }
        return järjend;
    }
}