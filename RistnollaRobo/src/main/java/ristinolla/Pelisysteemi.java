package ristinolla;


import java.util.Arrays;
import ristinolla.Tarkastaja;
/**
 * Pelisysteemi-luokka joka tällä hetkellä hieman vaiheessa.
 * Luokasta poistettu Javan valmiit metodit ja tietorakenteet. 
 * Muokkaan testit sopiviksi ensi viikon aikana.
 */

public class Pelisysteemi {
    private int vuoro, vuorotAlgoon;
    private int pelaaja, AI;
    private int[][] taulukko;
    private int[] liike;
    private Tarkastaja tark;
    private Minimax minimax;
    
    public Pelisysteemi() {
        this(3);
    }
    
    public Pelisysteemi(int koko) { // jos valitaan isompi alusta
        this.taulukko = new int[koko][koko];  
        
        liike = new int[2];
        vuoro = 0;
        vuorotAlgoon = 0;
        pelaaja = 1;
        AI = 10;
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
        vuorotAlgoon++;
    }
    
    public void setArvoTaulukkoon(int x, int y, int arvo) {
        taulukko[x][y] = arvo;
    }
    
    public int getArvoTaulukosta(int x, int y) {
        return taulukko[x][y];
    }
    
    public int getTaulukonPituus() {
        return taulukko.length;
    }
    
    public int[] getParasLiike() {
        int parasArvo = Integer.MAX_VALUE; 
        liike = new int[2];

        for (int i = 0; i < taulukko.length; i++) { 
            for (int j = 0; j < taulukko.length; j++) { 
                if (taulukko[i][j] == 0) { 
                    taulukko[i][j] = AI; 
                    vuorotAlgoon++;
                    int liikkeenArvo = minimax.suoritaMinimax(taulukko, 0, true); 
                    taulukko[i][j] = 0; 
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
    
    public int getVuorotAlgoon() {
        return vuorotAlgoon;
    }
    
    public void vuorotAlgoonMiinus() {
        vuorotAlgoon--;
    }
    
    public void vuorotAlgoonPlus() {
        vuorotAlgoon++;
    }
            
    
    public boolean vuorojaJaljellaAlgoon() {
        return vuorotAlgoon == taulukko.length * taulukko.length; // onko tyhjiä paikkoja jäljellä
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

    
