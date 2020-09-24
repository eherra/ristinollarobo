/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RistinollaRobo;

public class Minimax {
    private Tarkastaja tark;
    private Pelisysteemi sys;
    
    public Minimax(Tarkastaja tark, Pelisysteemi sys) {
        this.tark = tark;
        this.sys = sys;
    }
    
    public int suoritaMinimax(int[][] taulukko, int syvyys, Boolean onkoMaxVuorossa) {
        int pisteet = tark.laskePistearvo(); 
        if (pisteet == 10 || pisteet == -10) return pisteet;
        if (sys.vuorojaJaljellaAlgoon()) return 0;

        if (onkoMaxVuorossa) { 
            int parasPiste = Integer.MIN_VALUE; 

            for (int i = 0; i < taulukko.length; i++) { 
                for (int j = 0; j < taulukko.length; j++) { 
                    if (taulukko[i][j] == 0) { 
                        taulukko[i][j] = 1; 
                        sys.vuorotAlgoonPlus(); // tällä saadaan laskettua tyhjien paikkojen määrä pelitaulussa
                        int lasku = suoritaMinimax(taulukko, syvyys + 1, !onkoMaxVuorossa);
                        parasPiste = parasPiste > lasku ? parasPiste : lasku; 
                        taulukko[i][j] = 0; // backtracking, palautetetaan ruutu tyhjaksi
                        sys.vuorotAlgoonMiinus(); // backtracking, vähennetään ruutujen käyttöastetta
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
                        sys.vuorotAlgoonPlus();
                        int lasku = suoritaMinimax(taulukko, syvyys + 1, !onkoMaxVuorossa);
                        parasPiste = parasPiste < lasku ? parasPiste : lasku;
                        taulukko[i][j] = 0; 
                        sys.vuorotAlgoonMiinus();
                    } 
                } 
            } 
        return parasPiste; 
        } 
    }
}
