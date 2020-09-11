
package ristinolla;

/**
 * Tarkastaja-luokalla on metodit pelin päättymisen todentamiseen. 
 * 
 * @param Pelisysteemi-luokka, josta saamme pelilaudan tiedot haettua tarkastusta varten
 */

public class Tarkastaja {
    private StringBuilder build;
    private Pelisysteemi sys;
    private int taulukonPituus;
    
    public Tarkastaja(Pelisysteemi systeemi) {
        build = new StringBuilder();
        sys = systeemi;
        taulukonPituus = sys.getTaulukonPituus();
    }
    
    public boolean tarkastaPeli() {     
        return (tarkastaVaaka() || tarkastaPysty() || tarkastaDiagonal());
    }
    
    public boolean tarkastaVaaka() {
        for (int x = 0; x < taulukonPituus; x++) {
            build = new StringBuilder();
            for (int y = 0; y < taulukonPituus; y++) {
                build.append(sys.getArvoTaulukosta(x, y));
            }
            if (sisaltaakoVoittoRivin(build.toString())) return true;
        }
       return false;
    }
    
    public boolean tarkastaPysty() {
        for (int y = 0; y < taulukonPituus; y++) {
            build = new StringBuilder();
            for (int x = 0; x < taulukonPituus; x++) { 
                build.append(sys.getArvoTaulukosta(x, y));
            }
            
            if (sisaltaakoVoittoRivin(build.toString())) return true;
        }
        return false;
    }    
    
    public boolean tarkastaDiagonal() {     
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
            build = new StringBuilder();
            for (int x = 0; x < asti; x++) {
                build.append(sys.getArvoTaulukosta(x, y));
                y++;
            }
            if (sisaltaakoVoittoRivin(build.toString())) return true;
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
             build = new StringBuilder();
            for (int y = 0; y < asti; y++) {
                build.append(sys.getArvoTaulukosta(x, y));
                x++;
            }
            
            if (sisaltaakoVoittoRivin(build.toString())) return true;
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
             build = new StringBuilder();
            for (int x = 0; x < asti; x++) {
                build.append(sys.getArvoTaulukosta(x, y));
                y--;
            }
            if (sisaltaakoVoittoRivin(build.toString())) return true;
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
             build = new StringBuilder();
            for (int y = 9; y > asti; y--) { 
                build.append(sys.getArvoTaulukosta(x, y));
                x++;
            }
            if (sisaltaakoVoittoRivin(build.toString())) return true;
            asti++;
            xx++;
        } 
        return false;
    }
    
    public boolean sisaltaakoVoittoRivin(String rivi) { 
        return (rivi.contains("XXXXX") || rivi.contains("OOOOO")); 
    }
}
