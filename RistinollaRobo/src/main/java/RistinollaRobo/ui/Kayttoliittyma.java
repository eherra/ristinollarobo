
package RistinollaRobo.ui;

import RistinollaRobo.algo.Minimax;
import RistinollaRobo.lauta.Pelisysteemi;
import RistinollaRobo.lauta.Tarkastaja;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * RistinollaRobon käyttöliittymä JavaFX:llä.
 * Laudan koko vaihdettu aluksi 3x3 ja voittorivi 3 merkin pituinen. Sovellan lautaa ja minimax-algoritmia että 10x10 laudan peluu on mahdollista.
 * Tällä hetkellä isommalla laudalla algoritmilla kestää liian kauan seuraavan optimaalisimman siirron löytöön.
 * Tekoäly voittaa kaikki pelit.
 */

public class Kayttoliittyma extends Application {    
    private Boolean pieniL, keskiL, isoL;
    
    @Override
    public void start(Stage ikkuna) throws Exception {
        Button pieniLauta = new Button("3x3");
        Button keskiLauta = new Button("10x10");
        Button isoLauta = new Button("15x15");
        Button kaynnistaPeli = new Button("Käynnistä peli!");
        
        pieniLauta.setFont(Font.font("Monospaced", 20));
        keskiLauta.setFont(Font.font("Monospaced", 20));
        isoLauta.setFont(Font.font("Monospaced", 20));
        kaynnistaPeli.setFont(Font.font("Monospaced", 20));
        pieniLauta.setStyle("-fx-background-color: #B4CDCD");
        keskiLauta.setStyle("-fx-background-color: #B4CDCD");
        isoLauta.setStyle("-fx-background-color: #B4CDCD");
        kaynnistaPeli.setStyle("-fx-background-color: #B4CDCD");

        Label ylateksti = new Label("RistinollaRobo");
        ylateksti.setFont(Font.font("Monospaced", 40));
        
        HBox kokoNapit = new HBox();
        kokoNapit.setSpacing(20);
        kokoNapit.setAlignment(Pos.CENTER);
        kokoNapit.getChildren().addAll(pieniLauta, keskiLauta, isoLauta);
        
        VBox keskiAsetus = new VBox();
        keskiAsetus.setSpacing(30);
        keskiAsetus.getChildren().addAll(ylateksti, kokoNapit, kaynnistaPeli);
        keskiAsetus.setAlignment(Pos.CENTER);
        
        BorderPane kaynnistysNaytto = new BorderPane();
        kaynnistysNaytto.setPrefSize(500, 400);
        kaynnistysNaytto.setCenter(keskiAsetus);
        
        pieniLauta.setOnMouseClicked(event -> {
            pieniL = true;
            keskiL = false;
            isoL = false;
            pieniLauta.setStyle("-fx-background-color: #5F9F9F");
            keskiLauta.setStyle("-fx-background-color: #B4CDCD");
            isoLauta.setStyle("-fx-background-color: #B4CDCD");
        });
        
        keskiLauta.setOnMouseClicked(event -> {
            pieniL = false;
            keskiL = true;
            isoL = false;
            pieniLauta.setStyle("-fx-background-color: #B4CDCD");
            keskiLauta.setStyle("-fx-background-color: #5F9F9F");
            isoLauta.setStyle("-fx-background-color: #B4CDCD");
        });
        
        isoLauta.setOnMouseClicked(event -> {
            pieniL = false;
            keskiL = false;
            isoL = true;
            pieniLauta.setStyle("-fx-background-color: #B4CDCD");
            keskiLauta.setStyle("-fx-background-color: #B4CDCD");
            isoLauta.setStyle("-fx-background-color: #5F9F9F");
        });
        
        kaynnistaPeli.setOnMouseClicked(event -> {
            ikkuna.setScene(getPeliScene());
        });
                
        ikkuna.setScene(new Scene(kaynnistysNaytto));
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
                    Integer viimesinX = GridPane.getRowIndex(nappula);
                    Integer viimesinY = GridPane.getColumnIndex(nappula);
                    systeemi.setArvoTaulukkoon(viimesinX, viimesinY, systeemi.getVuoroArvona());
                    
                    if (tark.laskePistearvo(viimesinX, viimesinY) == 100) { // tarkistetaan voittiko ihmisen siirto
                        label.setText("Loppu! Ihminen voittaa!"); 
                        return;
                    }
                    systeemi.vuoroEteenpäin();
                    
                    if (!systeemi.vuorojaJaljella()) { // jos vika ruutu pelattiin
                        int pisteet = tark.laskePistearvo(viimesinX, viimesinY);
                        if (pisteet == -100) {
                            label.setText("Loppu! Tekoäly voittaa!");
                            return;
                        } else if (pisteet == 100) {
                            label.setText("Loppu! Ihminen voittaa!");
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
                    
                    if (tark.laskePistearvo(liike[0], liike[1]) == -100) {
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
    
    public Scene getPeliScene() {
        Pelisysteemi systeemi = new Pelisysteemi(getLaudanKoko()); 
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
        Scene pal = new Scene(asettelu);
        
        return pal;
    }
    
    public int getLaudanKoko() {
        int koko = 0;
        if (keskiL) koko = 10;
        else if (isoL) koko = 15;
        else koko = 3;
        return koko;
    }
}
