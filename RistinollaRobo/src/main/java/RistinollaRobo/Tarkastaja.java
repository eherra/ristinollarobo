
package RistinollaRobo;

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
            tarkistettavaRivi = 0;
            for (int y = 0; y < taulukonPituus; y++) {
                tarkistettavaRivi += sys.getArvoTaulukosta(x, y);
            }
            if (sisaltaakoVoittoRivin(tarkistettavaRivi)) return true;
        }
       return false;
    }
    
    public boolean tarkastaPysty() {
        for (int y = 0; y < taulukonPituus; y++) {
            tarkistettavaRivi = 0;
            for (int x = 0; x < taulukonPituus; x++) { 
                tarkistettavaRivi += sys.getArvoTaulukosta(x, y);
            }
            if (sisaltaakoVoittoRivin(tarkistettavaRivi)) return true;
        }
        return false;
    }    
    
    public boolean tarkastaDiagonalPieniTaulukko() {
        if (sys.getArvoTaulukosta(0, 0) == sys.getArvoTaulukosta(1, 1) && sys.getArvoTaulukosta(1, 1) == sys.getArvoTaulukosta(2, 2)) { 
            if (sys.getArvoTaulukosta(0, 0) == 1) {
                ristiFlag = true;
                return true;
            } 
            if (sys.getArvoTaulukosta(0, 0) == 10){
                nollaFlag = true;
                return true;
            } 
        } 
        
        if (sys.getArvoTaulukosta(0, 2) == sys.getArvoTaulukosta(1, 1) && sys.getArvoTaulukosta(1, 1) == sys.getArvoTaulukosta(2, 0)) {
            if (sys.getArvoTaulukosta(0, 2) == 1) {
                ristiFlag = true;
                return true;
            } 
            if (sys.getArvoTaulukosta(0, 2) == 10){
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
            tarkistettavaRivi = 0;
            for (int x = 0; x < asti; x++) {
                tarkistettavaRivi += sys.getArvoTaulukosta(x, y);
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
            tarkistettavaRivi = 0;
            for (int y = 0; y < asti; y++) {
                tarkistettavaRivi += sys.getArvoTaulukosta(x, y);
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
            tarkistettavaRivi = 0;
            for (int x = 0; x < asti; x++) {
                tarkistettavaRivi += sys.getArvoTaulukosta(x, y);
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
            tarkistettavaRivi = 0;
            for (int y = 9; y > asti; y--) { 
                tarkistettavaRivi += sys.getArvoTaulukosta(x, y);
                x++;
            }
            if (sisaltaakoVoittoRivin(tarkistettavaRivi)) return true;
            asti++;
            xx++;
        } 
        return false;
    }
    
    public boolean sisaltaakoVoittoRivin(int rivi) {
        if (sys.getTaulukonPituus() == 3) {
            if (rivi == 3 || rivi == 30) {
                if (rivi == 3) ristiFlag = true;
                if (rivi == 30) nollaFlag = true;
                return true;
            }
        } else { // isompi taulu
            if (rivi == 5 && rivi == 50) {
                if (rivi == 5) ristiFlag = true;
                if (rivi == 50) nollaFlag = true;
                return true;
            }
        }
        return false;
    }

}

    
