
package ristinolla;

import java.util.Arrays;
import javafx.scene.control.Button;

/**
 * Pelisysteemi-luokka pitää kirjaa vuoroista ja pelilaudasta. 
 */
public class Pelisysteemi {
    private int vuoro, taulukonPituus;
    private String[][] taulukko;
    
    public Pelisysteemi() {
        vuoro = 0;
        this.taulukko = new String[10][10];  
        taulukonPituus = taulukko.length;
    }

    public String getVuoro() {
        return vuoro % 2 == 0 ? "X" : "O";
    }
    
    public void vuoroEteenpäin() {
        vuoro++;
    }
    
    public void setArvoTaulukkoon(int x, int y, String arvo) {
        taulukko[x][y] = arvo;
    }
    
    public String getArvoTaulukosta(int x, int y) {
        return taulukko[x][y];
    }
    
    public int getTaulukonPituus() {
        return taulukko.length;
    }
}
