package oop;

import javafx.application.Application;
import javafx.beans.binding.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.function.*;

public class Main extends Application {

    @Override
    public void start(Stage peaLava) throws Exception {
        BorderPane juur = new BorderPane();

        Scene stseen = new Scene(juur, 650, 800);
        Nupud(juur);

        peaLava.setTitle("Poomismäng");
        peaLava.setScene(stseen);
        peaLava.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static void Nupud(BorderPane juur) {
        String[] nupud = {"A", "B", "D", "E", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "R", "S", "Z", "T", "U", "V", "Ä", "Õ", "Ö", "Ü"};
        HBox h1 = new HBox();
        h1.setSpacing(5);
        h1.setMinSize(400, 50);
        HBox h2 = new HBox();
        h2.setSpacing(5);
        h2.setMinSize(400, 50);

        for (int i = 0; i < 24; i++) {
            Button button = new Button();
            button.setText(nupud[i]);
            button.prefWidthProperty().bind(Bindings.divide(h1.widthProperty(), 12));
            button.prefHeightProperty().bind(h1.heightProperty());
            if (i < 12) {
                h1.getChildren().add(button);
            } else {
                h2.getChildren().add(button);
            }
        }
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(h1, h2);
        juur.setBottom(vbox);
    }
}
