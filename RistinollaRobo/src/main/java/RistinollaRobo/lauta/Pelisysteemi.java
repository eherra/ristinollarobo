package RistinollaRobo.lauta;


import RistinollaRobo.algo.Minimax;
import java.util.Arrays;
import RistinollaRobo.lauta.Tarkastaja;
/**
 * Pelisysteemi-luokka.
 * Luokka pitää kirjaa pelitilanteista ja luokasta saadaan tiedot vuorosta sekä pelattujen vuorojen määrästä
 * Luokasta poistettu Javan valmiit metodit ja tietorakenteet. 
 */

public class Pelisysteemi {
    private int vuoro, ruutujaPelattuMaara;
    private int[][] taulukko;
    private Tarkastaja tark;
    private Minimax minimax;

    /**
     * @param koko laudan koko riippuen pelaajan valinnasta
     */
    public Pelisysteemi(int koko) { // jos valitaan isompi alusta
        this.taulukko = new int[koko][koko];  
        vuoro = 0;
        ruutujaPelattuMaara = 0;
    }
   
    public void setTarkastaja(Tarkastaja tark) {
        this.tark = tark;
    }
    
    public void setMinimax(Minimax minmax) {
        this.minimax = minmax;
    }
           
    public String getVuoro() {
        return vuoro % 2 == 0 ? "X" : "O";
    }
    
    public int getVuoroArvona() {
        return vuoro % 2 == 0 ? 1 : 10;
    }
    
    public void vuoroEteenpäin() {
        vuoro++;
        minimax.pelattujaRuutujaPlus(); // pidetään ylhäällä pelattujen ruutujen määrää minimax-algoritmin avuksi
    }
    
    public void setArvoTaulukkoon(int x, int y, int arvo) {
        taulukko[x][y] = arvo;
    }
    
    public int getArvoTaulukosta(int x, int y) {
        return taulukko[x][y];
    }
    
    public int[][] getTaulukko() {
        return taulukko;
    }
    
    public int getTaulukonPituus() {
        return taulukko.length;
    }
   
    public boolean vuorojaJaljella() {
        return vuoro != taulukko.length * taulukko.length;
    }
    
    public void tulostaTaulu() {
        for (int i = 0; i < taulukko.length; i++) {
            for (int j = 0; j < taulukko.length; j++) {
                System.out.print(taulukko[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}

    
