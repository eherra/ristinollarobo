
package RistinollaRobo.utils;

import RistinollaRobo.lauta.Pelisysteemi;

/**
 *
 * @author balooza
 */
public class Minimax {
    private Tarkastaja tark;
    private Pelisysteemi sys;
    private int[] liike;
    private int ruutujaPelattuMaara;
    
    /**
     *
     * @param tark - pelin tarkastaukseen oleva luokka
     * @param sys - pelinkulkuun oleva luokka
     */
    public Minimax(Tarkastaja tark, Pelisysteemi sys) {
        this.tark = tark;
        this.sys = sys;
        ruutujaPelattuMaara = 0;
    }
    
    public int suoritaMinimax(int[][] taulukko, int syvyys, int alpha, int beta, Boolean onkoMaxVuorossa, int viimesinX, int viimesinY) {
        int pisteet = tark.laskePistearvo(viimesinX, viimesinY); 
        if (pisteet == 100) return pisteet - syvyys;
        if (pisteet == -100) return pisteet + syvyys;
        if (!onkoRuutujaJaljella()) return 0;
//         if (syvyys == 3) return 0; // jos haluaa pelata isommilla laudoilla ilman isoa viivettä

        if (onkoMaxVuorossa) { 
            int parasPiste = Integer.MIN_VALUE; 

            for (int i = 0; i < taulukko.length; i++) { 
                for (int j = 0; j < taulukko.length; j++) { 
                    if (taulukko[i][j] == 0) { 
                        taulukko[i][j] = 1; 
                        ruutujaPelattuMaara++; // pidetään kirjaa että montako ruutua pelattu
                        int lasku = suoritaMinimax(taulukko, syvyys + 1, alpha, beta, !onkoMaxVuorossa, i, j);
                        taulukko[i][j] = 0; // backtracking, palautetetaan ruutu tyhjaksi
                        ruutujaPelattuMaara--; // backtracking, palautetaan ruutujen käyttöastetta                        
                        parasPiste = parasPiste > lasku ? parasPiste : lasku; 
                        alpha = alpha > parasPiste ? alpha : parasPiste;
                        if (beta <= alpha) break;
                    } 
                } 
            } 
            return parasPiste; 
        } 
        else { 
            int parasPiste = Integer.MAX_VALUE; 

            for (int i = 0; i < taulukko.length; i++) { 
                for (int j = 0; j < taulukko.length; j++) { 
                    if (taulukko[i][j] == 0) { 
                        taulukko[i][j] = 10; 
                        ruutujaPelattuMaara++;
                        int lasku = suoritaMinimax(taulukko, syvyys + 1, alpha, beta, !onkoMaxVuorossa, i, j);
                        ruutujaPelattuMaara--;
                        taulukko[i][j] = 0;                         
                        parasPiste = parasPiste < lasku ? parasPiste : lasku;
                        beta = beta < lasku ? beta : lasku;
                        if (beta <= alpha) break;                        
                    } 
                } 
            } 
        return parasPiste; 
        } 
    }
    
    public int[] getParasLiike() {
        int parasArvo = Integer.MAX_VALUE; 
        liike = new int[2];

        for (int i = 0; i < sys.getTaulukonPituus(); i++) { 
            for (int j = 0; j < sys.getTaulukonPituus(); j++) { 
                if (sys.getArvoTaulukosta(i, j) == 0) { 
                    sys.setArvoTaulukkoon(i, j, 10);
                    ruutujaPelattuMaara++;
                    int liikkeenArvo = suoritaMinimax(sys.getTaulukko(), 0, Integer.MIN_VALUE, Integer.MAX_VALUE, true, i, j); 
                    sys.setArvoTaulukkoon(i, j, 0);
                    ruutujaPelattuMaara--;

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
    
    public void pelattujaRuutujaPlus() {
        ruutujaPelattuMaara++;
    }
    
    public boolean onkoRuutujaJaljella() {
        return ruutujaPelattuMaara != sys.getTaulukonPituus() * sys.getTaulukonPituus(); // onko tyhjiä paikkoja jäljellä
    }
}
