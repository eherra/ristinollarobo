
package RistinollaRobo.lauta;

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
    private boolean ristiFlag, nollaFlag; // tiedetään kumman voittoon tilanne päätynyt
    private int tarkistettavaRivi;
    
    public Tarkastaja(Pelisysteemi systeemi) {
        ristiFlag = false;
        nollaFlag = false;
        sys = systeemi;
        taulukonPituus = sys.getTaulukonPituus();
    }
    
    public int laskePistearvo(int x, int y) {     
        if (tarkastaVaaka(x, y) || tarkastaPysty(x, y) || tarkastaDiagonal(x, y)) return kummanVoittoon();           
        return 0;
    }
    
    public int kummanVoittoon() {
        if (ristiFlag) { // johtaa 'X' pelaajan voittoon
            ristiFlag = false;
            return 100;
        } 
        
        if (nollaFlag) { // johtaa 'O' pelaajan voittoon
            nollaFlag = false;
            return -100;
        } 
        return -1; // riviä ei suoriteta
    }
    
    // 3x3 pienen taulun tarkastukset
    public boolean tarkastaPieniTauluVaaka(int x) {
        return sisaltaakoVoittoArvon(sys.getArvoTaulukosta(x, 0) + sys.getArvoTaulukosta(x, 1) + sys.getArvoTaulukosta(x, 2));
    }

    public boolean tarkastaPieniTauluPysty(int y) {
        return sisaltaakoVoittoArvon(sys.getArvoTaulukosta(0, y) + sys.getArvoTaulukosta(1, y) + sys.getArvoTaulukosta(2, y));
    }  

    public boolean tarkastaDiagonalPieniTaulukko() {
        if (sisaltaakoVoittoArvon(sys.getArvoTaulukosta(0, 0) + sys.getArvoTaulukosta(1, 1) + sys.getArvoTaulukosta(2, 2))) return true;
        return sisaltaakoVoittoArvon(sys.getArvoTaulukosta(0, 2) + sys.getArvoTaulukosta(1, 1) + sys.getArvoTaulukosta(2, 0));
    } 
    
    /**
     * Voittorivin tarkastus isompiin tauluihin. Tarkastaa vain kohdata mihin voi syntyä 5 suora. 
     * Tarkastus tehokas, käytetään ikkuna liuku tekniikkaa (window sliding technique) rivin lukujen läpikäyntiin
     * mutta koodin luettavuus kärsi hieman, kun turhia kohtia ei tarkasteta riviltä
     * eli paikat johon pelatusta kohdasta ei voi syntyä 5 suoraa.
     * @param viimesinX 
     * @param viimesinY viimeisimmän paikan koordinaatit mihin ihminen tai tekoäly on pelannut.
     */
    public boolean tarkastaVaaka(int viimesinX, int viimesinY) { 
        if (taulukonPituus == 3) return (tarkastaPieniTauluVaaka(viimesinX));
        
        if (viimesinY < 5 && taulukonPituus > 9) { // tämä nopeuttamaan tarkastusta isoimmille laudoille eli koko 10x10 ja ylöspäin. 
            int[] luvut = new int[taulukonPituus - (5 - viimesinY)];  //jos ollaan vasemmassa laidassa kohdassa jossa ei voi 5 suoraa muodostua vasemmalle puolelle
            for (int i = 0; i < luvut.length; i++) {
                luvut[i] = sys.getArvoTaulukosta(viimesinX, i);
            }
            if (ikkunaLiuku(luvut)) return true;
        } else if (taulukonPituus - viimesinY < 5 && taulukonPituus > 9) { // jos ollaan oikeassa laidassa
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
        if (taulukonPituus == 3) return tarkastaPieniTauluPysty(viimesinY);  
        
        if (viimesinX < 5 && taulukonPituus > 9) {
            int[] luvut = new int[taulukonPituus - (5 - viimesinX)];
            for (int i = 0; i < luvut.length; i++) {
                luvut[i] = sys.getArvoTaulukosta(i, viimesinY);
            }
            if (ikkunaLiuku(luvut)) return true;
        } else if (viimesinX > 5 && taulukonPituus > 9) {
            int[] luvut = new int[taulukonPituus - (viimesinX - 4)];
            int alku = viimesinX - 4;

            for (int i = 0; i < luvut.length; i++) {
                luvut[i] = sys.getArvoTaulukosta(alku, viimesinY);
                alku++;
            }
            if (ikkunaLiuku(luvut)) return true;
        } else {
            int[] luvut = new int[taulukonPituus];
            for (int i = 0; i < luvut.length; i++) {
                luvut[i] = sys.getArvoTaulukosta(i, viimesinY);
            }
            if (ikkunaLiuku(luvut)) return true;
        }
        return false;
    }  
    
    public boolean tarkastaDiagonal(int x, int y) {
        if (taulukonPituus == 3) return tarkastaDiagonalPieniTaulukko();
        return (tarkastaVasenDiagonal(x, y) || tarkastaOikeaDiagonal(x, y));
    }
    
    // tarkastetaan tällä hetkellä koko diagonal rivi kohdasta viimesinX, viimesinY
    public boolean tarkastaVasenDiagonal(int viimesinX, int viimesinY) { 
        int[] luvut = new int[taulukonPituus];
        
        
        while (true) { // mennään diagonal rivin alkuun
            if (viimesinX == 0 || viimesinY == taulukonPituus - 1) break;
            viimesinX--;
            viimesinY++;
        }
        int ind = 0;
        while (true) { // muodostetaan taulukko johon diagonal kohdan arvot
            if (viimesinX == taulukonPituus || viimesinY == -1) break; // jos taulukon reunassa
            luvut[ind] = sys.getArvoTaulukosta(viimesinX, viimesinY);
            viimesinX++;
            viimesinY--;
            ind++;
        }
        return ikkunaLiuku(luvut);
    }
    
    public boolean tarkastaOikeaDiagonal(int viimesinX, int viimesinY) {
        int[] luvut = new int[taulukonPituus];
  
        while (true) { // pumpataan rivin alkuun
            if (viimesinX == 0 || viimesinY == 0) break;
            viimesinX--;
            viimesinY--;
        }
        int ind = 0;
        while (true) {
            if (viimesinX == taulukonPituus || viimesinY == taulukonPituus) break;
            luvut[ind] = sys.getArvoTaulukosta(viimesinX, viimesinY);
            viimesinX++;
            viimesinY++;
            ind++;
        }
        return ikkunaLiuku(luvut);
    }
    
    public boolean sisaltaakoVoittoArvon(int rivi) {
        if (sys.getTaulukonPituus() == 3) { // pieni taulu
            if (rivi == 3 || rivi == 30) {
                if (rivi == 3) ristiFlag = true; // tiedetään kumman voittoon päätynyt 
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
    // window sliding tekniikka
    public boolean ikkunaLiuku(int[] luvut) {
        int ikkunanSumma = 0;
        for (int i = 0; i < 5; i++) { // lasketaan eka ikkuna
            ikkunanSumma += luvut[i];
        }
        if (sisaltaakoVoittoArvon(ikkunanSumma)) return true;
        
        for (int i = 0; i < luvut.length - 5; i++) { // liikutetaan ikkunaa yhdellä eteenpäin
            ikkunanSumma -= luvut[i];
            ikkunanSumma += luvut[i+5];
            if (sisaltaakoVoittoArvon(ikkunanSumma)) return true;
        }
        return false;
    }
}