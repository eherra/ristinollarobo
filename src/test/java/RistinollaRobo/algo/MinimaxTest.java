/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RistinollaRobo.algo;

import RistinollaRobo.algo.Minimax;
import RistinollaRobo.lauta.Pelisysteemi;
import RistinollaRobo.lauta.Tarkastaja;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author balooza
 */
public class MinimaxTest {

    
    @Test
    public void testGetParasLiike() {
        Pelisysteemi s = new Pelisysteemi(3);
        Tarkastaja t = new Tarkastaja(s);
        Minimax m = new Minimax(t, s);
        s.setMinimax(m);
        s.setTarkastaja(t);
        
        s.setArvoTaulukkoon(0, 0, 1);
        s.vuoroEteenpäin();
        s.setArvoTaulukkoon(0, 1, 1);
        s.vuoroEteenpäin();
        s.setArvoTaulukkoon(1, 1, 10);        
        s.vuoroEteenpäin();
        
        int[] parasLiike = m.getParasLiike();
        assertEquals(parasLiike[0], 0);
        assertEquals(parasLiike[1], 2);
        
        s.setArvoTaulukkoon(0, 2, 10);
        s.vuoroEteenpäin();
        s.setArvoTaulukkoon(2, 0, 1);
        s.vuoroEteenpäin();
        
        int[] paras2Liike = m.getParasLiike();
        assertEquals(paras2Liike[0], 1);
        assertEquals(paras2Liike[1], 0);
    }

    @Test
    public void testGetTyhjienMaara() {
        Pelisysteemi s = new Pelisysteemi(10);
        Tarkastaja t = new Tarkastaja(s);
        Minimax m = new Minimax(t, s);
        s.setMinimax(m);
        s.setTarkastaja(t);
        assertEquals(m.getTyhjienMaara(), 100);
        s.vuoroEteenpäin();
        assertEquals(m.getTyhjienMaara(), 99);
    }

    
    @Test
    public void testAsetaMaxSyvyys() {
        Pelisysteemi s = new Pelisysteemi(3);
        Tarkastaja t = new Tarkastaja(s);
        Minimax m = new Minimax(t, s);        
        s.setMinimax(m);
        s.setTarkastaja(t);
        assertEquals(9, m.getMaxSyvyys());
        
        Pelisysteemi s1 = new Pelisysteemi(10);
        Tarkastaja t1 = new Tarkastaja(s);
        Minimax m1 = new Minimax(t1, s1);        
        s1.setMinimax(m1);
        s1.setTarkastaja(t1);
        assertEquals(3, m1.getMaxSyvyys());
    }
}
