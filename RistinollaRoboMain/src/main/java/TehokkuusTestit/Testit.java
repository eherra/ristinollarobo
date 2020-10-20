
package TehokkuusTestit;

import RistinollaRobo.algo.Minimax;
import RistinollaRobo.lauta.Pelisysteemi;
import RistinollaRobo.lauta.Tarkastaja;


public class Testit {
    public void suoritaTestit(Minimax mini) {
        long alku = System.nanoTime();
        mini.getParasLiike();
        long end = System.nanoTime();
        System.out.println("\t" + ((end - alku) / 1e9));
    }
    
    public void suoritaTehokkuusTestit() {
        System.out.println("\tTehokkuus Testit");
        System.out.println("Tekoälyn ensimmäinen siirto 3x3");
        testaaPieniLautaAloitusSiirto();
        System.out.println("Tekoälyn ensimmäinen siirto 10x10");
        testaaKeskiLautaAloitusSiirto();
        System.out.println("Tekoälyn ensimmäinen siirto 15x15");
        testaaIsoLautaAloitusSiirto();
        System.out.println("");
        System.out.println("Tekoälyn voitonblokkaus 3x3");
        testaaPieniLautaVoitonBlokkaus();
        System.out.println("Tekoälyn voitonblokkaus 10x10");
        testaaKeskiLautaVoitonBlokkaus();
        System.out.println("Tekoälyn voitonblokkaus 15x15");
        testaaIsoLautaVoitonBlokkaus();
    }
    
    public void testaaPieniLautaAloitusSiirto() {
        Pelisysteemi systeemi = new Pelisysteemi(3);
        Tarkastaja tark = new Tarkastaja(systeemi);
        Minimax mini = new Minimax(tark, systeemi);
        systeemi.setMinimax(mini);
        systeemi.setTarkastaja(tark);  
        
        systeemi.setArvoTaulukkoon(1, 1, 1);
        systeemi.vuoroEteenpäin();
        suoritaTestit(mini);
    }
    
    public void testaaKeskiLautaAloitusSiirto() {
        Pelisysteemi systeemi = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(systeemi);
        Minimax mini = new Minimax(tark, systeemi);
        systeemi.setMinimax(mini);
        systeemi.setTarkastaja(tark);  
        
        systeemi.setArvoTaulukkoon(1, 1, 1);
        systeemi.vuoroEteenpäin();
        suoritaTestit(mini);
    }    
    
    public void testaaIsoLautaAloitusSiirto() {
        Pelisysteemi systeemi = new Pelisysteemi(15);
        Tarkastaja tark = new Tarkastaja(systeemi);
        Minimax mini = new Minimax(tark, systeemi);
        systeemi.setMinimax(mini);
        systeemi.setTarkastaja(tark); 
        
        systeemi.setArvoTaulukkoon(1, 1, 1);
        systeemi.vuoroEteenpäin();
        suoritaTestit(mini);
    }
    
    public void testaaPieniLautaVoitonBlokkaus() {
        Pelisysteemi systeemi = new Pelisysteemi(3);
        Tarkastaja tark = new Tarkastaja(systeemi);
        Minimax mini = new Minimax(tark, systeemi);
        systeemi.setMinimax(mini);
        systeemi.setTarkastaja(tark);     
        
        systeemi.setArvoTaulukkoon(1, 1, 1);
        systeemi.setArvoTaulukkoon(2, 0, 1);
        systeemi.setArvoTaulukkoon(0, 0, 10);
        systeemi.vuoroEteenpäin();
        systeemi.vuoroEteenpäin();
        systeemi.vuoroEteenpäin();
        
        suoritaTestit(mini);
    }
    
    public void testaaKeskiLautaVoitonBlokkaus() {        
        Pelisysteemi systeemi = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(systeemi);
        Minimax mini = new Minimax(tark, systeemi);
        systeemi.setMinimax(mini);
        systeemi.setTarkastaja(tark); 
        
        
        for (int i = 2; i < 6; i++) {
            systeemi.setArvoTaulukkoon(2, i, 1);
            systeemi.vuoroEteenpäin();
        }
        
        systeemi.setArvoTaulukkoon(0, 0, 10);
        systeemi.setArvoTaulukkoon(2, 1, 10);
        systeemi.setArvoTaulukkoon(0, 4, 10);
        systeemi.vuoroEteenpäin();
        systeemi.vuoroEteenpäin();
        systeemi.vuoroEteenpäin();
        systeemi.vuoroEteenpäin();

        suoritaTestit(mini);
    }    
    
    public void testaaIsoLautaVoitonBlokkaus() {
        Pelisysteemi systeemi = new Pelisysteemi(15);
        Tarkastaja tark = new Tarkastaja(systeemi);
        Minimax mini = new Minimax(tark, systeemi);
        systeemi.setMinimax(mini);
        systeemi.setTarkastaja(tark); 
                
        for (int i = 2; i < 6; i++) {
            systeemi.setArvoTaulukkoon(2, i, 1);
            systeemi.vuoroEteenpäin();
        }
        
        systeemi.setArvoTaulukkoon(0, 0, 10);
        systeemi.setArvoTaulukkoon(2, 1, 10);
        systeemi.setArvoTaulukkoon(0, 4, 10);
        systeemi.vuoroEteenpäin();
        systeemi.vuoroEteenpäin();
        systeemi.vuoroEteenpäin();
        systeemi.vuoroEteenpäin();
  
        suoritaTestit(mini);
    }
}