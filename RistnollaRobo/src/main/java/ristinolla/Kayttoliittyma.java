
package ristinolla;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * RistinollaRobon käyttöliittymä JavaFX:llä.
 * Laudan koko vaihdettu aluksi 3x3 ja voittorivi 3 merkin pituinen. Sovellan lautaa ja minimax algoritmia että 10x10 laudan peluu on mahdollista.
 * Minimax-algoritmi implentoitu, AlphaBeta-karsintaa ei vielä lisätty. Tekoäly voittaa kaikki pelit.
 */

public class Kayttoliittyma extends Application {
    @Override
    public void start(Stage ikkuna) throws Exception {
        Pelisysteemi systeemi = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(systeemi);
        systeemi.setTarkastaja(tark);
        BorderPane asettelu = new BorderPane();
      
        Label labeli = new Label("Vuoro: " + systeemi.getVuoro());
        labeli.setFont(Font.font("Monospaced", 20));
        
        GridPane pane = lisaaNapit(systeemi, labeli, tark);
        asettelu.setTop(labeli);        
        asettelu.setPrefSize(300, 180);    
        asettelu.setPadding(new Insets(10, 10, 10, 10));
        asettelu.setCenter(pane);
        
        Scene skene = new Scene(asettelu);
        
        ikkuna.setScene(skene);
        ikkuna.show();
    }
    
    public GridPane lisaaNapit(Pelisysteemi systeemi, Label label, Tarkastaja tark) {        
        GridPane palautus = new GridPane();
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button nappula = new Button(" ");
                nappula.setFont(Font.font("Monospaced", 20));
                
                //nappulan painallukset
                nappula.setOnAction((event) -> {
                    if (!nappula.getText().equals(" ") || label.getText().startsWith("L")) return;
                    
                    nappula.setText(systeemi.getVuoro());
                    systeemi.setArvoTaulukkoon(GridPane.getRowIndex(nappula), GridPane.getColumnIndex(nappula), systeemi.getVuoro());

                    systeemi.vuoroEteenpäin();
                    
                    int[] liike = systeemi.getParasLiike(); // tekoälyn liike
                    systeemi.setArvoTaulukkoon(liike[0], liike[1], "O");
                    Button AI = new Button("O");
                    AI.setFont(Font.font("Monospaced", 20));
                    palautus.add(AI, liike[1], liike[0]); // laitetaan tekoälyn pelaama siirto käyttöliittymän pelialustalle
                    
                    if (tark.laskePistearvo() == -10) {
                        label.setText("Loppu! Tekoäly voittaa!");
                        return;
                    } else if (tark.laskePistearvo() == 10) {
                        label.setText("Loppu! Ihminen voittaa!"); // tämä ei tapahdu koskaan, sillä tekoäly voittamaton
                        return;
                    } else if (!systeemi.vuorojaJaljella()) {
                        label.setText("Loppu! Tasapeli!"); 
                        return;
                    }
                    systeemi.vuoroEteenpäin();
                    label.setText("Vuoro: " + systeemi.getVuoro());
                });
                // nappula GridPaneen
                palautus.add(nappula, j, i);
            }
        }

        return modifoiGridi(palautus);
    }
    
    public GridPane modifoiGridi(GridPane palautus) {
        palautus.setPrefSize(300, 180);
        palautus.setAlignment(Pos.CENTER);
        palautus.setVgap(5);
        palautus.setHgap(5);
        palautus.setPadding(new Insets(10, 10, 10, 10));
        return palautus;
    }
}
