
package ristinolla;

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
    private String voittoriviX, voittoriviO, tarkistettavaRivi;
    
    public Tarkastaja(Pelisysteemi systeemi) {
        ristiFlag = false;
        nollaFlag = false;
        sys = systeemi;
        taulukonPituus = sys.getTaulukonPituus();
    }
    
    public int laskePistearvo() {     
        if (tarkastaVaaka() || tarkastaPysty()) {
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
            if (tarkastaDiagonalIsompiLauta()) {
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
   
    public boolean tarkastaVaaka() {
        for (int x = 0; x < taulukonPituus; x++) {
            tarkistettavaRivi = "";
            for (int y = 0; y < taulukonPituus; y++) {
                tarkistettavaRivi = tarkistettavaRivi + sys.getArvoTaulukosta(x, y);
            }
            if (sisaltaakoVoittoRivin(tarkistettavaRivi)) return true;
        }
       return false;
    }
    
    public boolean tarkastaPysty() {
        for (int y = 0; y < taulukonPituus; y++) {
            tarkistettavaRivi = "";
            for (int x = 0; x < taulukonPituus; x++) { 
                tarkistettavaRivi = tarkistettavaRivi + sys.getArvoTaulukosta(x, y);
            }
            if (sisaltaakoVoittoRivin(tarkistettavaRivi)) return true;
        }
        return false;
    }    
    
    public boolean tarkastaDiagonalPieniTaulukko() {
        if (sys.getArvoTaulukosta(0, 0).equals(sys.getArvoTaulukosta(1, 1)) && sys.getArvoTaulukosta(1, 1).equals(sys.getArvoTaulukosta(2, 2))) { 
            if (sys.getArvoTaulukosta(0, 0).equals("X")) {
                ristiFlag = true;
                return true;
            } 
            if (sys.getArvoTaulukosta(0, 0).equals("O")){
                nollaFlag = true;
                return true;
            } 
        } 
        
        if (sys.getArvoTaulukosta(0, 2).equals(sys.getArvoTaulukosta(1, 1)) && sys.getArvoTaulukosta(1, 1).equals(sys.getArvoTaulukosta(2, 0))) {
            if (sys.getArvoTaulukosta(0, 2).equals("X")) {
                ristiFlag = true;
                return true;
            } 
            if (sys.getArvoTaulukosta(0, 2).equals("O")){
                nollaFlag = true;
                return true;
            }        
        }
        return false;
    }    
    public boolean tarkastaDiagonalIsompiLauta() {     
        return (vasenYlhaaltaDiagnolOikeaYlaosa() || vasenYlhaaltaDiagonalAlasOikeaAlaosa() || oikeaYlhaaltaDiagnolVasenAlasYlaosa() || oikeaYlhaaltaDiagonalVasenAlasAlaosa());
        // jaettu neljään eri metodiin. Alla oleva kuva kertoo metodien testialueet
        //1111111111
        // 111111111
        //  11111111
        //   1111111
        //    111111
        //     11111
        //      1111
        //       111
        //        11
        //         1
        
        //
        //2
        //22
        //222
        //2222
        //22222
        //222222
        //2222222
        //22222222
        //222222222
        
        //         3
        //        33
        //       333
        //      3333
        //     33333
        //    333333
        //   3333333
        //  33333333
        // 333333333
        //3333333333  
        
        //444444444
        //44444444
        //4444444
        //444444
        //44444
        //4444
        //444
        //44
        //4
        //
    }
    
    public boolean vasenYlhaaltaDiagnolOikeaYlaosa() {
        int asti = taulukonPituus;
        int yy = 0; 
      
        while (asti != 4) { // kulmaa ei tarkasteta, viiden suora ei mahdu muodostumaan
            int y = yy;
            tarkistettavaRivi = "";
            for (int x = 0; x < asti; x++) {
                tarkistettavaRivi = tarkistettavaRivi + sys.getArvoTaulukosta(x, y);
                y++;
            }
            if (sisaltaakoVoittoRivin(tarkistettavaRivi)) return true;
            asti--;
            yy++;
        }
        
        return false;
    }
    
    public boolean vasenYlhaaltaDiagonalAlasOikeaAlaosa() {
        int asti = taulukonPituus;
        int xx = 0;
        
        while (asti != 4) {
            int x = xx;
            tarkistettavaRivi = "";
            for (int y = 0; y < asti; y++) {
                tarkistettavaRivi = tarkistettavaRivi + sys.getArvoTaulukosta(x, y);
                x++;
            }
            
            if (sisaltaakoVoittoRivin(tarkistettavaRivi)) return true;
            asti--;
            xx++;
        }   
        
        return false;
    }
    
    public boolean oikeaYlhaaltaDiagnolVasenAlasYlaosa() {
        int asti = taulukonPituus;
        int yy = taulukonPituus - 1; 
        
        while (asti != 4) { 
            int y = yy;
            tarkistettavaRivi = "";
            for (int x = 0; x < asti; x++) {
                tarkistettavaRivi = tarkistettavaRivi + sys.getArvoTaulukosta(x, y);
                y--;
            }
            if (sisaltaakoVoittoRivin(tarkistettavaRivi)) return true;
            asti--;
            yy--;
        }  
        return false;
    }
    
    public boolean oikeaYlhaaltaDiagonalVasenAlasAlaosa() {
        int asti = 0;
        int xx = 1;
        
        while (asti != 5) {
            int x = xx;
            tarkistettavaRivi = "";
            for (int y = 9; y > asti; y--) { 
                tarkistettavaRivi = tarkistettavaRivi + sys.getArvoTaulukosta(x, y);
                x++;
            }
            if (sisaltaakoVoittoRivin(tarkistettavaRivi)) return true;
            asti++;
            xx++;
        } 
        return false;
    }
    
    public boolean sisaltaakoVoittoRivin(String rivi) {
        char merkki = rivi.charAt(0);
        int maara = 1; // perakkaisten merkkien maara
        for (int i = 1; i < rivi.length(); i++) {
            if (rivi.charAt(i) == '-' && merkki == '-') continue;  
            
            if (merkki != rivi.charAt(i)) {
                if (maara == 5) break; // löydettiin 5 suora samaa merkkiä
                merkki = rivi.charAt(i);
                maara = 1;
            } else {
                maara++;
            }
        }
        
        if (sys.getTaulukonPituus() == 3) {
            if (maara == 3) {
                if (merkki == 'X') ristiFlag = true;
                if (merkki == 'O') nollaFlag = true;
                return true;
            }
        } else { // isompi taulu
            if (maara == 5) {
                if (merkki == 'X') ristiFlag = true;
                if (merkki == 'O') nollaFlag = true;
                return true;
            }
        }
        return false;
    }
}

    
