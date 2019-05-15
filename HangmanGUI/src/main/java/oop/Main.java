package oop;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.*;

import java.util.*;

import static javafx.application.Platform.exit;


public class Main extends Application {

    @Override
    public void start(Stage peaLava) {
        peaLava.setTitle("Poomismäng");
        peaLava.setWidth(1000);
        peaLava.setHeight(700);
        peaLava.setScene(mainMenu(peaLava));
        //peaLava.setScene(nimeKüsimine(peaLava));
        peaLava.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Scene mainMenu(Stage peaLava) { //esmane menüü mis avab kui programmi valid ja siis saad vastavad valikud teha.
        BorderPane juur = new BorderPane();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(4+1);
        grid.setVgap(5);

        Blend blendEffect = new Blend(BlendMode.DIFFERENCE);
        ColorInput input = new ColorInput();
        blendEffect.setTopInput(input);

        Button mang = new Button("Alusta mängu");
        Button scoreb = new Button("Edetabel");
        Button exit = new Button("Exit");


        juur.widthProperty().addListener(me -> {
            mang.prefWidthProperty().bind(Bindings.divide(juur.widthProperty(), 3));
            exit.prefWidthProperty().bind(Bindings.divide(juur.widthProperty(), 2 + 1));
            scoreb.prefWidthProperty().bind(Bindings.divide(juur.widthProperty(), 3));
            mang.setFont(new Font(juur.getWidth() / 30));
            scoreb.setFont(new Font(juur.getWidth() / (25 + 5)));
            exit.setFont(new Font(juur.getWidth() / 30));

        });
        mang.setOnMouseClicked(me -> {
            peaLava.setScene(nimeKüsimine(peaLava));
        });
        exit.setOnMouseClicked(me -> {
            exit();

        });

        grid.add(mang, 0, 0);
        grid.add(scoreb, 0, 2 - 1);
        grid.add(exit, 0, 1 + 1);
        juur.setCenter(grid);
        Scene stseen = new Scene(juur, peaLava.getWidth(), peaLava.getHeight());
        return stseen;
    }


    public static Scene nimeKüsimine(Stage peaLava) {
        BorderPane juur = new BorderPane();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        Label info = new Label("Sisesta siia oma mängija nimi");
        Button alustaMängu = new Button("Alusta mängu");
        TextField tf = new TextField();
        Label info2 = new Label("Nimi ei tohi sisaldada tühikuid!");
        Label info3 = new Label("Pead nime valima alustamiseks!");
        Label info4 = new Label("Vali lühem nimi palun!");

        info.setAlignment(Pos.CENTER);
        info2.setAlignment(Pos.CENTER);
        info3.setAlignment(Pos.CENTER);
        info4.setAlignment(Pos.CENTER);
        tf.setAlignment(Pos.CENTER);

        juur.widthProperty().addListener(me -> {
            alustaMängu.prefWidthProperty().bind(Bindings.divide(juur.widthProperty(), 3));
            tf.prefWidthProperty().bind(Bindings.divide(juur.widthProperty(), 3));
            info.prefWidthProperty().bind(Bindings.divide(juur.widthProperty(), 3));
            info2.prefWidthProperty().bind(Bindings.divide(juur.widthProperty(), 3));
            info3.prefWidthProperty().bind(Bindings.divide(juur.widthProperty(), 3));
            info4.prefWidthProperty().bind(Bindings.divide(juur.widthProperty(), 2 + 1));
            alustaMängu.setFont(new Font(juur.getWidth() / 30));
            tf.setFont(new Font(juur.getWidth() / 30));
            info.setFont(new Font(juur.getWidth() / 40));
            info2.setFont(new Font(juur.getWidth() / 45));
            info3.setFont(new Font(juur.getWidth() / 45));
            info4.setFont(new Font(juur.getWidth() / 45));

        });

        alustaMängu.setOnMouseClicked(me -> {
            if (!tf.getText().equals("") && !tf.getText().contains(" ") && tf.getText().length() <= 15) {
                try {
                    peaLava.setScene(mänguStseen(peaLava, tf.getText()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (tf.getText().equals("")) {
                if (grid.getChildren().size() == 4) {
                    grid.getChildren().remove(3);
                }
                grid.add(info3, 0, 3);
            } else if (tf.getText().contains(" ")) {
                if (grid.getChildren().size() == 4) {
                    grid.getChildren().remove(3);
                }
                grid.add(info2, 0, 3);
            } else if (tf.getText().length() > 15) {
                if (grid.getChildren().size() == 4) {
                    grid.getChildren().remove(3);
                }
                grid.add(info4, 0, 3);
            }
        });

        grid.add(info, 0, 0);
        grid.add(tf, 0, 1);
        grid.add(alustaMängu, 0, 2);
        juur.setCenter(grid);
        Scene stseen = new Scene(juur, peaLava.getWidth(), peaLava.getHeight());
        return stseen;
    }

    public static Scene mänguLõpp(Mangija profiil, Stage peaLava) throws Exception {
        //Loob edetabeli, andmed failist sisse lugedes, lisatakse juurde uus tulemus ja kirjutatakse uuesti faili.
        Edetabel edetabel = new Edetabel();
        edetabel.lisaMangija(profiil);
        edetabel.sorteeriTabel();
        edetabel.kirjutaFaili();

        BorderPane juur = new BorderPane();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        Label pealkiri = new Label("TULEMUS");
        Button PlayAgain = new Button("Play again");
        Button Menu = new Button("Menu");
        Button exit = new Button("Exit");
        Label nimi = new Label("Nimi: " + profiil.getNimi());
        Label skoor = new Label("Skoor: " + profiil.getSkoor());
        Label arvatudSõnu = new Label("Arvatud sõnu: " + profiil.getArvatudSonu());

        PlayAgain.prefWidthProperty().bind(Bindings.divide(juur.widthProperty(), 5));
        PlayAgain.prefHeightProperty().bind(Bindings.divide(juur.heightProperty(), 10));
        Menu.prefWidthProperty().bind(Bindings.divide(juur.widthProperty(), 5));
        Menu.prefHeightProperty().bind(Bindings.divide(juur.heightProperty(), 10));
        exit.prefWidthProperty().bind(Bindings.divide(juur.widthProperty(), 5));
        exit.prefHeightProperty().bind(Bindings.divide(juur.heightProperty(), 10));

        juur.widthProperty().addListener(me -> {
            pealkiri.setFont(new Font(juur.getWidth() / 15));
            nimi.setFont(new Font(juur.getWidth() / 25));
            skoor.setFont(new Font(juur.getWidth() / 25));
            arvatudSõnu.setFont(new Font(juur.getWidth() / 25));
            Menu.setFont(new Font(juur.getWidth() / 30));
            PlayAgain.setFont(new Font(juur.getWidth() / 30));
            exit.setFont(new Font(juur.getWidth() / 30));

        });


        PlayAgain.setOnMouseClicked(me -> {
            try {
                peaLava.setScene(nimeKüsimine(peaLava));
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
        });

        exit.setOnMouseClicked(me -> System.exit(0));

        Menu.setOnMouseClicked(me -> {
            peaLava.setScene(mainMenu(peaLava));
        });

        grid.add(nimi, 0, 0);
        grid.add(skoor, 0, 1);
        grid.add(arvatudSõnu, 0, 2);
        grid.add(PlayAgain, 1, 0);
        grid.add(Menu, 1, 1);
        grid.add(exit, 1, 2);
        StackPane st = new StackPane();
        st.getChildren().add(pealkiri);
        juur.setTop(st);
        juur.setCenter(grid);
        Scene stseen = new Scene(juur, peaLava.getWidth(), peaLava.getHeight());
        return stseen;
    }

    //Stseen mänguajaks, kui käib sõnade arvamine.
    public static Scene mänguStseen(Stage peaLava, String mängijanimi) throws Exception {

        BorderPane juur = new BorderPane();

        //Kui on algmenüü stseen ja nime sisestamine stseen ka tehtud, siis tuleks parameetrina anda kaasa see nimi ja siin kasutada.
        Mangija mangija = new Mangija(mängijanimi, 0, 0);

        koikSonad koikSonad = new koikSonad();
        Sona sona = new Sona(new StringBuilder(koikSonad.annaSõna()), 0);

        Text arvatav = new Text(sona.peidetudTahtedega().toString());
        //System.out.println(sona.getSona());

        StackPane kesk = new StackPane();

        arvatav.scaleXProperty().bind(Bindings.divide(juur.widthProperty(), 300));
        arvatav.scaleYProperty().bind(Bindings.divide(arvatav.scaleXProperty(), 1));

        kesk.getChildren().add(arvatav);
        juur.setCenter(arvatav);

        Scene stseen = new Scene(juur, peaLava.getWidth(), peaLava.getHeight());

        NupudjaInfo(sona, arvatav, juur, stseen, koikSonad, mangija);
        Timer(juur, peaLava, mangija);

        return stseen;

    }

    public static void edutabel(Scene scene) {

    }

    //Loob, kuvab ja haldab tegevusi, mis on seatud mängus olevate nuppude ja infoga.
    public static void NupudjaInfo(Sona sona, Text arvatav, BorderPane juur, Scene stseen, koikSonad koiksonad, Mangija mangija) {
        ////////////////////////////////////////////// paremal oleva info kood
        GridPane info = new GridPane();

        info.setAlignment(Pos.CENTER);
        info.setPadding(new Insets(0, 35, 0, 0));
        Label skoor = new Label("Skoor: 0");
        Label arvatud = new Label("Arvatud: 0");
        Label vigu = new Label("Vead: 0");
        info.add(skoor, 0, 0);
        info.add(arvatud, 0, 1);
        info.add(vigu, 0, 2);
        Label[] infotekstid = {skoor, arvatud, vigu};
        for (Label label : infotekstid) {
            //label.setStyle("-fx-border-color: black;");
            label.setFont(new Font(juur.getWidth() / 25));
            juur.widthProperty().addListener(me -> {
                label.setFont(new Font(juur.getWidth() / 25));
            });
        }

        juur.setRight(info);

        ////////////////////////////////////////////alumiste nuppude kood
        String[] nupud = {"A", "B", "D", "E", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "R", "S", "Z", "T", "U", "V", "Ä", "Õ", "Ö", "Ü"};
        HBox h1 = new HBox();
        h1.setSpacing(5);
        h1.setMinSize(400, 50);
        HBox h2 = new HBox();
        h2.setSpacing(5);
        h2.setMinSize(400, 50);
        List<Button> koikTäheNupud = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            Button button = new Button();
            button.setText(nupud[i]);
            koikTäheNupud.add(button);
            button.setMinSize(50, 50);
            button.prefWidthProperty().bind(Bindings.divide(h1.widthProperty(), 12));
            button.prefHeightProperty().bind(button.prefWidthProperty());

            button.widthProperty().addListener(me -> {
                int uusFont = (int) button.getWidth();
                button.setFont(new Font(uusFont / 5));
            });

            button.setOnMouseClicked(me -> {
                String nupuTäht = button.getText();
                if (sona.getTähed().contains(nupuTäht.charAt(0)) && sona.getSona().toString().contains(nupuTäht)) {
                    mangija.setSkoor(mangija.getSkoor() + 10);
                    skoor.setText("Skoor: " + mangija.getSkoor());
                    button.setVisible(false);
                    sona.taheEemaldus(button.getText().charAt(button.getText().length() - 1));
                    arvatav.setText(sona.peidetudTahtedega().toString());
                }
                if (sona.getTähed().contains(nupuTäht.charAt(0)) && !sona.getSona().toString().contains(nupuTäht)) {
                    sona.setViga(sona.getViga() + 1);
                    vigu.setText("Vead: " + sona.getViga());
                    mangija.setSkoor(mangija.getSkoor() - 5);
                    skoor.setText("Skoor: " + mangija.getSkoor());
                    button.setVisible(false);
                    sona.taheEemaldus(button.getText().charAt(0));
                    arvatav.setText(sona.peidetudTahtedega().toString());
                    if (sona.getViga() == 7) {
                        uusSõna(sona, koiksonad, arvatav, h1, h2);
                        vigu.setText("Vead: " + sona.getViga());
                        arvatav.setText(sona.peidetudTahtedega().toString());
                    }
                }
            });

            if (i < 12) {
                h1.getChildren().add(button);
            } else {
                h2.getChildren().add(button);
            }
        }
        VBox vboxalumine = new VBox();
        vboxalumine.setSpacing(5);
        vboxalumine.getChildren().addAll(h1, h2);
        juur.setBottom(vboxalumine);

        ////////////////////////////////////////////////////// vasakul olevate nuppude kood
        VBox nupudvasakul = new VBox();

        nupudvasakul.setPadding(new Insets(60, 0, 5, 5));
        nupudvasakul.setSpacing(5);
        Button vasta = new Button("Vasta");
        Button jätaVahele = new Button("Jäta vahele");
        Button vihje = new Button("Vihje");
        Button exit = new Button("Lõpeta mäng");
        Button[] n = {vasta, jätaVahele, vihje, exit};
        for (Button button : n) {
            button.prefWidthProperty().bind(Bindings.divide(stseen.widthProperty(), 5));
            button.prefHeightProperty().bind(Bindings.divide(stseen.heightProperty(), 10));
            button.widthProperty().addListener(me -> {
                int uusFont = (int) button.getWidth();
                button.setFont(new Font(uusFont / 15));
            });
        }

        //Praegu paneb programmi kinni lihtsalt, hiljem peaks algmenüü juurde tagasi viskama sind.
        exit.setOnMouseClicked(me -> System.exit(0));

        jätaVahele.setOnMouseClicked(me -> {
            if (sona.allKriipuSisalduvus()) {
                uusSõna(sona, koiksonad, arvatav, h1, h2);
                mangija.setSkoor(mangija.getSkoor() - 20);
                skoor.setText("Skoor: " + mangija.getSkoor());
                vigu.setText("Vead: " + sona.getViga());
            }
        });

        vasta.setOnMouseClicked(me -> {
            if (!sona.allKriipuSisalduvus()) {
                uusSõna(sona, koiksonad, arvatav, h1, h2);
                mangija.setSkoor(mangija.getSkoor() + 30);
                skoor.setText("Skoor: " + mangija.getSkoor());
                vigu.setText("Vead: " + sona.getViga());
                mangija.setArvatudSonu(mangija.getArvatudSonu() + 1);
                arvatud.setText("Arvatud: " + mangija.getArvatudSonu());
            }
        });

        vihje.setOnMouseClicked(me -> {
            if (sona.allKriipuSisalduvus()) {
                String täht = sona.annaSuvalinetäht();
                for (Button button : koikTäheNupud) {
                    if (button.getText().equals(täht)) {
                        button.setVisible(false);
                        sona.taheEemaldus(button.getText().charAt(0));
                        arvatav.setText(sona.peidetudTahtedega().toString());
                        mangija.setSkoor(mangija.getSkoor() - 5);
                        skoor.setText("Skoor: " + mangija.getSkoor());
                    }
                }
            }
        });
        nupudvasakul.getChildren().addAll(vasta, jätaVahele, vihje, exit);
        juur.setLeft(nupudvasakul);
    }


    //Abimeetod olukorra jaoks, kus tuleb uus sõna arvamisele. Seadistab vajalikud väljad ümber.
    public static void uusSõna(Sona sona, koikSonad koiksonad, Text arvatav, HBox h1, HBox h2) {
        sona.setSona(new StringBuilder(koiksonad.annaSõna()));
        sona.setViga(0);
        //System.out.println(sona.getSona());
        sona.algseadistaTähed();
        arvatav.setText(sona.peidetudTahtedega().toString());
        for (Node child : h1.getChildren()) {
            child.setVisible(true);
        }
        for (Node child : h2.getChildren()) {
            child.setVisible(true);
        }

    }


    public static void Timer(BorderPane juur, Stage peaLava, Mangija mangija) {
        Integer STARTTIME = 5;  //Aeg sekundites.
        Label timerLabel = new Label();
        IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");

        Timeline timeline = new Timeline();
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(STARTTIME + 1), new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
        timeline.setOnFinished(me -> {
            try {
                peaLava.setScene(mänguLõpp(mangija, peaLava));
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(timerLabel);
        juur.setTop(stackPane);
    }
}
