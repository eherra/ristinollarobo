
package RistinollaRobo.utils;

import RistinollaRobo.lauta.Pelisysteemi;
import java.util.Arrays;

/**
 * Tarkastaja-luokalla on metodit pelin päättymisen todentamiseen. 
 * Luokasta poistettu Javan valmiit tietorakenteet ja metodit
 * 
 * @param Pelisysteemi-luokka, josta saamme pelilaudan tiedot haettua tarkastusta varten
 */

public class Tarkastaja {
    private Pelisysteemi sys;
    private int taulukonPituus;
    private boolean ristiFlag, nollaFlag;
    private int tarkistettavaRivi;
    
    public Tarkastaja(Pelisysteemi systeemi) {
        ristiFlag = false;
        nollaFlag = false;
        sys = systeemi;
        taulukonPituus = sys.getTaulukonPituus();
    }
    
    public int laskePistearvo(int x, int y) {     
        if (tarkastaVaaka(x, y) || tarkastaPysty(x, y)) {
            if (ristiFlag) {
                ristiFlag = false;
                return 10; // johtaa 'X' pelaajan voittoon
            } else if (nollaFlag) {
                nollaFlag = false;
                return -10; // johtaa 'O' pelaajan voittoon
            }
        }
        
        if (taulukonPituus == 3) {
            if (tarkastaDiagonalPieniTaulukko()) {
                if (ristiFlag) {
                     ristiFlag = false;
                     return 10;
                 } else if (nollaFlag) {
                     nollaFlag = false;
                     return -10;
                 }            
            }
        } else {
            if (tarkastaDiagonal(x, y)) {
                if (ristiFlag) {
                     ristiFlag = false;
                     return 10;
                 } else if (nollaFlag) {
                     nollaFlag = false;
                     return -10;
                 } 
            }
        }
        return 0;
    }
   
    public boolean tarkastaVaaka(int viimesinX, int viimesinY) {
        if (viimesinY < 5) {
            if (taulukonPituus == 3) {
                if (tarkastaPieniTauluVaaka(viimesinX)) return true;
            } else {
                int[] luvut = new int[taulukonPituus - (5 - viimesinY)];
                for (int i = 0; i < luvut.length; i++) {
                    luvut[i] = sys.getArvoTaulukosta(viimesinX, i);
                }
                if (ikkunaLiuku(luvut)) return true;
            }
        } else if (viimesinY > 5) {
            int[] luvut = new int[taulukonPituus - (viimesinY - 4)];
            int alku = viimesinY - 4;
            
            for (int i = 0; i < luvut.length; i++) {
                luvut[i] = sys.getArvoTaulukosta(viimesinX, alku);
                alku++;
            }
            if (ikkunaLiuku(luvut)) return true;
        } else {
            int[] luvut = new int[taulukonPituus];
            for (int i = 0; i < luvut.length; i++) {
                luvut[i] = sys.getArvoTaulukosta(viimesinX, i);
            }
            if (ikkunaLiuku(luvut)) return true;
        }
       return false;
    }
    
    public boolean tarkastaPysty(int viimesinX, int viimesinY) {
        if (viimesinX < 5) {
            if (taulukonPituus == 3) {
                if (tarkastaPieniTauluPysty(viimesinY)) return true;
            } else {
                int[] luvut = new int[taulukonPituus - (5 - viimesinX)];
                for (int i = 0; i < luvut.length; i++) {
                    luvut[i] = sys.getArvoTaulukosta(i, viimesinY);
                }
                if (ikkunaLiuku(luvut)) return true;
            }
        } else if (viimesinX > 5) {
            int[] luvut = new int[taulukonPituus - (viimesinX - 4)];
            int alku = viimesinX - 4;

            for (int i = 0; i < luvut.length; i++) {
                luvut[i] = sys.getArvoTaulukosta(alku, viimesinY);
                alku++;
            }
            if (ikkunaLiuku(luvut)) return true;
        } else {
            int[] luvut = new int[10];
            for (int i = 0; i < luvut.length; i++) {
                luvut[i] = sys.getArvoTaulukosta(i, viimesinY);
            }
            if (ikkunaLiuku(luvut)) return true;
        }
        return false;
    }  
    
    public boolean tarkastaPieniTauluVaaka(int x) {
        return sisaltaakoVoittoRivin(sys.getArvoTaulukosta(x, 0) + sys.getArvoTaulukosta(x, 1) + sys.getArvoTaulukosta(x, 2));
    }
    
    public boolean tarkastaPieniTauluPysty(int y) {
        return sisaltaakoVoittoRivin(sys.getArvoTaulukosta(0, y) + sys.getArvoTaulukosta(1, y) + sys.getArvoTaulukosta(2, y));
    }  
    
    public boolean tarkastaDiagonalPieniTaulukko() {
        if (sisaltaakoVoittoRivin(sys.getArvoTaulukosta(0, 0) + sys.getArvoTaulukosta(1, 1) + sys.getArvoTaulukosta(2, 2))) return true;
        return sisaltaakoVoittoRivin(sys.getArvoTaulukosta(0, 2) + sys.getArvoTaulukosta(1, 1) + sys.getArvoTaulukosta(2, 0));
    }    
    
    public boolean tarkastaDiagonal(int x, int y) {
       return (tarkastaVasenDiagonal(x, y) || tarkastaOikeaDiagonal(x, y));
    }
    
    public boolean tarkastaVasenDiagonal(int viimesinX, int viimesinY) {
        if (viimesinX > 5 || viimesinY < 4) return false;
        int[] luvut = new int[10];
        int xx = viimesinX;
        int yy = viimesinY;
        
        while (true) { // pumpataan rivin alkuun
            if (xx == 0 || yy == 9) break;
            xx--;
            yy++;
        }
        int ind = 0;
        while (true) { // muodostetaan taulukko johon diagonal kohdan arvot
            if (xx == 10 || yy == -1) break; // jos taulukon reunassa
            luvut[ind] = sys.getArvoTaulukosta(xx, yy);
            xx++;
            yy--;
            ind++;
        }
        return (ikkunaLiuku(luvut));
    }
    
    public boolean tarkastaOikeaDiagonal(int viimesinX, int viimesinY) {
        if (viimesinX > 5 || viimesinY > 5) return false;
        int[] luvut = new int[10];
        int xx = viimesinX;
        int yy = viimesinY;
        
        while (true) { // pumpataan alkuun
            if (xx == 0 || yy == 0) break;
            xx--;
            yy--;
        }
        int ind = 0;
        while (true) {
            if (xx == 10 || yy == 10) break;
            luvut[ind] = sys.getArvoTaulukosta(xx, yy);
            xx++;
            yy++;
            ind++;
        }
        return (ikkunaLiuku(luvut));
    }
    
    public boolean sisaltaakoVoittoRivin(int rivi) {
        if (sys.getTaulukonPituus() == 3) {
            if (rivi == 3 || rivi == 30) {
                if (rivi == 3) ristiFlag = true;
                if (rivi == 30) nollaFlag = true;
                return true;
            }
        } else { // isompi taulu
            if (rivi == 5 || rivi == 50) {
                if (rivi == 5) ristiFlag = true;
                if (rivi == 50) nollaFlag = true;
                return true;
            }
        }
        return false;
    }
    
    public boolean ikkunaLiuku(int[] luvut) {
        int rivinPituus = 5; 
        
        for (int i = 0; i < luvut.length - rivinPituus + 1; i++) {
            int summa = 0; 
            for (int j = 0; j < rivinPituus; j++) {
                summa += luvut[i + j];
            }
            if (sisaltaakoVoittoRivin(summa)) return true;
        }
        
        return false;
    }
}

    
