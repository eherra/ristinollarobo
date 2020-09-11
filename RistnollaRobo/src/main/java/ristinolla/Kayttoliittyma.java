
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
 * Laudan koko aluksi 10x10 ja voittorivi 5 merkin pituinen. Mahdollisesti teen myöhemmin mahdollisuuden laudan koon valintaan.
 * Minimax-algoritmia ei vielä impletoitu. 
 */

public class Kayttoliittyma extends Application {
    @Override
    public void start(Stage ikkuna) throws Exception {
        Pelisysteemi systeemi = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(systeemi);
        BorderPane asettelu = new BorderPane();
      
        Label labeli = new Label("Vuoro: " + systeemi.getVuoro());
        labeli.setFont(Font.font("Monospaced", 40));
        
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
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button nappula = new Button(" ");
                nappula.setFont(Font.font("Monospaced", 20));
                
                //nappulan painallukset
                nappula.setOnAction((event) -> {
                    if (!nappula.getText().equals(" ") || label.getText().startsWith("Loppu!")) return;
                    
                    nappula.setText(systeemi.getVuoro());
                    
                    systeemi.setArvoTaulukkoon(GridPane.getRowIndex(nappula), GridPane.getColumnIndex(nappula), systeemi.getVuoro());
                    
                    if (tark.tarkastaPeli()) {
                        label.setText("Loppu! " + systeemi.getVuoro() + " voitti!");
                        return;
                    } 
                    
                    systeemi.vuoroEteenpäin();
                    label.setText("Vuoro: " + systeemi.getVuoro()); 
                });
                // nappula GridPaneen
                palautus.add(nappula, i, j);
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
