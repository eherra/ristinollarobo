package ristinolla;


import java.util.Arrays;
import ristinolla.Tarkastaja;
/**
 * Pelisysteemi-luokka joka tällä hetkellä hieman vaiheessa. Minimax-metodi erotellaan myöhemmin omaan luokkaansa.
 * Luokasta poistettu Javan valmiit metodit ja tietorakenteet. JUnit testit eivät toimi tässä luokassa, kun tein paljon muutoksia luokkaan.
 * Muokkaan testit sopiviksi ensi viikon aikana.
 */

public class Pelisysteemi {
    private int vuoro, vuorotAlgoon;
    private String pelaaja, AI;
    private String[][] taulukko;
    private int[] liike;
    private Tarkastaja tark;
    
    public Pelisysteemi() {
        this.taulukko = new String[3][3];  
        alustaTaulu();
        
        liike = new int[2];
        vuoro = 0;
        vuorotAlgoon = 0;
        pelaaja = "X";
        AI = "O";
    }
    
    public void alustaTaulu() {
        for (int i = 0; i < taulukko.length; i++) {
            for (int j = 0; j < taulukko.length; j++) {
                taulukko[i][j] = "-";
            }
        }
    }
    
    public void setTarkastaja(Tarkastaja tark) {
        this.tark = tark;
    }
    
    public String getVuoro() {
        return vuoro % 2 == 0 ? pelaaja : AI;
    }
    
    public void vuoroEteenpäin() {
        vuoro++;
        vuorotAlgoon++;
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
    
    public int minimax(int syvyys, Boolean onkoMaxVuorossa) {
        int pisteet = tark.laskePistearvo(); 
        if (pisteet == 10 || pisteet == -10) return pisteet;
        if (vuorojaJaljellaAlgoon()) return 0;

        if (onkoMaxVuorossa) { 
            int parasPiste = Integer.MIN_VALUE; 

            for (int i = 0; i < taulukko.length; i++) { 
                for (int j = 0; j < taulukko.length; j++) { 
                    if (taulukko[i][j].equals("-")) { 
                        taulukko[i][j] = pelaaja; 
                        vuorotAlgoon++; // tällä saadaan laskettua tyhjien paikkojen määrä pelitaulussa
                        int lasku = minimax(syvyys + 1, !onkoMaxVuorossa);
                        parasPiste = parasPiste > lasku ? parasPiste : lasku; 
                        taulukko[i][j] = "-"; // backtracking, palautetetaan ruutu tyhjaksi
                        vuorotAlgoon--; // backtracking, vähennetään ruutujen käyttöastetta
                    } 
                } 
            } 
            return parasPiste; 
        } 
        else { 
            int parasPiste = Integer.MAX_VALUE; 

            for (int i = 0; i < taulukko.length; i++) { 
                for (int j = 0; j < taulukko.length; j++) { 
                    if (taulukko[i][j].equals("-")) { 
                        taulukko[i][j] = AI; 
                        vuorotAlgoon++;
                        int lasku = minimax(syvyys + 1, !onkoMaxVuorossa);
                        parasPiste = parasPiste < lasku ? parasPiste : lasku;
                        taulukko[i][j] = "-"; 
                        vuorotAlgoon--;
                    } 
                } 
            } 
        return parasPiste; 
        } 
    }
      
    public int[] getParasLiike() {
        int parasArvo = Integer.MAX_VALUE; 
        liike = new int[2];

        for (int i = 0; i < taulukko.length; i++) { 
            for (int j = 0; j < taulukko.length; j++) { 
                if (taulukko[i][j].equals("-")) { 
                    taulukko[i][j] = AI; 
                    vuorotAlgoon++;
                    int liikkeenArvo = minimax(0, true); 
                    taulukko[i][j] = "-"; 
                    vuorotAlgoon--;

                    if (liikkeenArvo < parasArvo) { 
                        liike[0] = i;
                        liike[1] = j;
                        parasArvo = liikkeenArvo; 
                    } 
                } 
            } 
        } 
        return liike; 
    } 
    
    public boolean vuorojaJaljellaAlgoon() {
        return vuorotAlgoon == taulukko.length * taulukko.length; // onko tyhjiä paikkoja jäljellä
   }
    
    public boolean vuorojaJaljellaKayttoliittymaan() {
        return vuoro == 8;
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

    
