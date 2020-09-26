
package RistinollaRobo;

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
 * Laudan koko vaihdettu aluksi 3x3 ja voittorivi 3 merkin pituinen. Sovellan lautaa ja minimax-algoritmia että 10x10 laudan peluu on mahdollista.
 * Tällä hetkellä isommalla laudalla algoritmilla kestää liian kauan seuraavan optimaalisimman siirron löytöön.
 * Tekoäly voittaa kaikki pelit.
 */

public class Kayttoliittyma extends Application {
    @Override
    public void start(Stage ikkuna) throws Exception {
        Pelisysteemi systeemi = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(systeemi);
        systeemi.setTarkastaja(tark);
        Minimax minMax = new Minimax(tark, systeemi);
        systeemi.setMinimax(minMax);
        
        BorderPane asettelu = new BorderPane();
      
        Label labeli = new Label("Vuoro: " + systeemi.getVuoro());
        labeli.setFont(Font.font("Monospaced", 20));
        
        GridPane pane = lisaaNapit(systeemi, labeli, tark, minMax);
        asettelu.setTop(labeli);        
        asettelu.setPrefSize(300, 180);    
        asettelu.setPadding(new Insets(10, 10, 10, 10));
        asettelu.setCenter(pane);
        
        Scene skene = new Scene(asettelu);
        
        ikkuna.setScene(skene);
        ikkuna.show();
    }
    
    public GridPane lisaaNapit(Pelisysteemi systeemi, Label label, Tarkastaja tark, Minimax minimax) {        
        GridPane palautus = new GridPane();
        
        for (int i = 0; i < systeemi.getTaulukonPituus(); i++) {
            for (int j = 0; j < systeemi.getTaulukonPituus(); j++) {
                Button nappula = new Button(" ");
                nappula.setFont(Font.font("Monospaced", 20));
                
                //nappulan painallukset
                nappula.setOnAction((event) -> {
                    if (!nappula.getText().equals(" ") || label.getText().startsWith("L")) return;
                    
                    nappula.setText(systeemi.getVuoro());
                    systeemi.setArvoTaulukkoon(GridPane.getRowIndex(nappula), GridPane.getColumnIndex(nappula), systeemi.getVuoroArvona());
                    if (tark.laskePistearvo() == 10) { // tarkistetaan voittiko ihmisen siirto
                        label.setText("Loppu! Ihminen voittaa!"); 
                        return;
                    }
                    systeemi.vuoroEteenpäin();
                    
                    if (!systeemi.vuorojaJaljella()) { // jos vika ruutu pelattiin
                        int pisteet = tark.laskePistearvo();
                        if (pisteet == -10) {
                            label.setText("Loppu! Tekoäly voittaa!");
                            return;
                        } else if (pisteet == 10) {
                            label.setText("Loppu! Ihminen voittaa!"); // jos laudan koko 3x3, tämä ei tapahdu koskaan sillä tekoäly voittamaton
                            return;
                        } else {
                            label.setText("Loppu! Tasapeli!"); 
                            return;
                        }           
                    }
                    
                    int[] liike = minimax.getParasLiike(); // tekoälyn liike
                    systeemi.setArvoTaulukkoon(liike[0], liike[1], 10);
                    Button AI = new Button("O");
                    AI.setFont(Font.font("Monospaced", 20));
                    palautus.add(AI, liike[1], liike[0]); // laitetaan tekoälyn pelaama siirto käyttöliittymän pelialustalle
                    
                    if (tark.laskePistearvo() == -10) {
                        label.setText("Loppu! Tekoäly voittaa!");
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
