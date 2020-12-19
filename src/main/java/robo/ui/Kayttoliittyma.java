
package robo.ui;

import robo.algo.Minimax;
import robo.lauta.Pelisysteemi;
import robo.lauta.Tarkastaja;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * RistinollaRobon käyttöliittymä JavaFX:llä.
 * Laudan koot 3x3, 10x10 ja 15x15 - valittavissa kaynnistysruudusta. 
 */

public class Kayttoliittyma extends Application {    
    private Boolean pelataankoKeskiLauta, pelataankoIsoLauta;
    private Stage ikkuna;
    private Button uusiPeli;
    
    @Override
    public void start(Stage ikkuna) throws Exception {
        this.ikkuna = ikkuna;
        this.ikkuna.setScene(getKaynnistysNaytto());
        this.ikkuna.show();
    }
    
    public Scene getKaynnistysNaytto() {
        pelataankoKeskiLauta = false;
        pelataankoIsoLauta = false;
        Button pieniLauta = new Button("3x3");
        Button keskiLauta = new Button("10x10");
        Button isoLauta = new Button("15x15");
        Button kaynnistaPeli = new Button("Käynnistä peli!");
        Button suljeNappi = new Button("X");
        
        pieniLauta.setFont(Font.font("Monospaced", 20));
        keskiLauta.setFont(Font.font("Monospaced", 20));
        isoLauta.setFont(Font.font("Monospaced", 20));
        kaynnistaPeli.setFont(Font.font("Monospaced", 20));
        suljeNappi.setFont(Font.font("Monospaced", 15));

        pieniLauta.setStyle("-fx-background-color: #5F9F9F");
        keskiLauta.setStyle("-fx-background-color: #B4CDCD");
        isoLauta.setStyle("-fx-background-color: #B4CDCD");
        kaynnistaPeli.setStyle("-fx-background-color: #B4CDCD");
        suljeNappi.setStyle("-fx-background-color: #CD5C5C");
        kaynnistaPeli.setOnMouseEntered(e -> kaynnistaPeli.setStyle("-fx-background-color: #20B2AA"));
        kaynnistaPeli.setOnMouseExited(e -> kaynnistaPeli.setStyle("-fx-background-color: #B4CDCD"));
        
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
        kaynnistysNaytto.setRight(suljeNappi);
        
        pieniLauta.setOnMouseClicked(event -> {
            pelataankoKeskiLauta = false;
            pelataankoIsoLauta = false;
            pieniLauta.setStyle("-fx-background-color: #5F9F9F");
            keskiLauta.setStyle("-fx-background-color: #B4CDCD");
            isoLauta.setStyle("-fx-background-color: #B4CDCD");
        });
        
        keskiLauta.setOnMouseClicked(event -> {
            pelataankoKeskiLauta = true;
            pelataankoIsoLauta = false;
            pieniLauta.setStyle("-fx-background-color: #B4CDCD");
            keskiLauta.setStyle("-fx-background-color: #5F9F9F");
            isoLauta.setStyle("-fx-background-color: #B4CDCD");
        });
        
        isoLauta.setOnMouseClicked(event -> {
            pelataankoKeskiLauta = false;
            pelataankoIsoLauta = true;
            pieniLauta.setStyle("-fx-background-color: #B4CDCD");
            keskiLauta.setStyle("-fx-background-color: #B4CDCD");
            isoLauta.setStyle("-fx-background-color: #5F9F9F");
        });
        
        suljeNappi.setOnMouseClicked(event -> {
            ikkuna.close();
        });
        
        kaynnistaPeli.setOnMouseClicked(event -> {
            this.ikkuna.setScene(getPeliScene());
        });
        
        return new Scene(kaynnistysNaytto);
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
        
        Button takaisin = new Button("Takaisin");
        uusiPeli = new Button("Uusi peli?");
        takaisin.setStyle("-fx-background-color: #CD5C5C");
        uusiPeli.setStyle("-fx-background-color: #5F9F9F");
        uusiPeli.setVisible(false);
        
        BorderPane ylaosa = new BorderPane();
        VBox oikeaYlakulma = new VBox();
        oikeaYlakulma.getChildren().addAll(takaisin, uusiPeli);
        oikeaYlakulma.setSpacing(5);
        ylaosa.setRight(oikeaYlakulma);
        ylaosa.setLeft(labeli);

        GridPane pane = lisaaNapit(systeemi, labeli, tark, minMax);
        asettelu.setTop(ylaosa);        
        asettelu.setPrefSize(300, 180);    
        asettelu.setPadding(new Insets(10, 10, 10, 10));
        asettelu.setCenter(pane);
        
        takaisin.setOnMouseClicked(event -> {
            this.ikkuna.setScene(getKaynnistysNaytto());
        });       
        
        uusiPeli.setOnMouseClicked(event -> {
            this.ikkuna.setScene(getPeliScene());
            uusiPeli.setVisible(false);
        });   
        
        return new Scene(asettelu);
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
                    systeemi.vuoroEteenpäin();

                    if (tark.laskePistearvo(viimesinX, viimesinY) == 100) { // tarkistetaan voittiko ihmisen siirto
                        uusiPeli.setVisible(true);
                        label.setText("Loppu. Ihminen voittaa!"); 
                        return;
                    } else if (!systeemi.vuorojaJaljella()) {
                        label.setText("Loppu. Tasapeli!"); 
                        uusiPeli.setVisible(true);
                        return;
                    }
                    
                    //tekoälyn liike
                    int[] liike = minimax.getParasLiike(); 
                    systeemi.setArvoTaulukkoon(liike[0], liike[1], 10);
                    Button AI = new Button("O");
                    AI.setFont(Font.font("Monospaced", 20));
                    palautus.add(AI, liike[1], liike[0]); // laitetaan tekoälyn pelaama siirto käyttöliittymän pelialustalle
                    systeemi.vuoroEteenpäin();
                    
                    if (tark.laskePistearvo(liike[0], liike[1]) == -100) {
                        label.setText("Loppu. Tekoäly \nvoittaa!");
                        uusiPeli.setVisible(true);
                        return;
                    } else if (!systeemi.vuorojaJaljella()) {
                        label.setText("Loppu. Tasapeli!"); 
                        uusiPeli.setVisible(true);
                        return;
                    }
                    
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
    
    public int getLaudanKoko() {
        if (pelataankoKeskiLauta) return 10;
        if (pelataankoIsoLauta) return 15;
        return 3;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
