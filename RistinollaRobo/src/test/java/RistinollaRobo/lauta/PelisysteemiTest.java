/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RistinollaRobo.lauta;

import RistinollaRobo.algo.Minimax;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author balooza
 */
public class PelisysteemiTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetVuoro() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja t = new Tarkastaja(sys);
        Minimax m = new Minimax(t, sys);
        sys.setMinimax(m);
       
        assertEquals("X", sys.getVuoro()); // alkutilanteessa X vuorossa
        
        sys.vuoroEteenpäin();
        assertEquals("O", sys.getVuoro()); // yksi vuoro eteenpäin, onko O vuorossa?
        
        sys.vuoroEteenpäin(); // kaksi vuoroa eteenpäin, onko X vuorossa?
        assertEquals("X", sys.getVuoro());
    }

    /**
     * Test of getVuoroArvona method, of class Pelisysteemi.
     */
    @Test
    public void testGetVuoroArvona() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja t = new Tarkastaja(sys);
        Minimax m = new Minimax(t, sys);        
        sys.setMinimax(m);
        assertEquals(1, sys.getVuoroArvona()); // alkutilanteessa X vuorossa
        
        sys.vuoroEteenpäin();
        assertEquals(10, sys.getVuoroArvona()); // yksi vuoro eteenpäin, onko O vuorossa?
        
        sys.vuoroEteenpäin(); // kaksi vuoroa eteenpäin, onko X vuorossa?
        assertEquals(1, sys.getVuoroArvona());

    }

    /**
     * Test of vuoroEteenpäin method, of class Pelisysteemi.
     */
    @Test
    public void testVuoroEteenpäin() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja t = new Tarkastaja(sys);
        Minimax m = new Minimax(t, sys);    
        sys.setMinimax(m);
        String ekaVuorossa = sys.getVuoro();

        sys.vuoroEteenpäin();

        assertNotEquals(ekaVuorossa, sys.getVuoro()); // kun vuoro vaihtuu, vuorossa eri
    }

    /**
     * Test of setArvoTaulukkoon method, of class Pelisysteemi.
     */
    @Test
    public void testSetArvoTaulukkoon() {
        Pelisysteemi sys = new Pelisysteemi(10);
     
        sys.setArvoTaulukkoon(0, 0, 1);
        sys.setArvoTaulukkoon(9, 9, 1);
        sys.setArvoTaulukkoon(0, 9, 10);
        
        assertEquals(1, sys.getArvoTaulukosta(0, 0));
        assertEquals(1, sys.getArvoTaulukosta(9, 9));
        assertEquals(10, sys.getArvoTaulukosta(0, 9));
        assertNotEquals(10, sys.getArvoTaulukosta(0, 0));
        assertNotEquals(1, sys.getArvoTaulukosta(5, 5));
    }

    /**
     * Test of getArvoTaulukosta method, of class Pelisysteemi.
     */
    @Test
    public void testGetArvoTaulukosta() {
        Pelisysteemi sys = new Pelisysteemi();
        sys.setArvoTaulukkoon(0, 0, 10);
        assertEquals(10, sys.getArvoTaulukosta(0, 0));
    }

    /**
     * Test of getTaulukko method, of class Pelisysteemi.
     */
    @Test
    public void testGetTaulukonPituus() {
        Pelisysteemi sys = new Pelisysteemi();
        assertEquals(3, sys.getTaulukonPituus());
        
        Pelisysteemi sys2 = new Pelisysteemi(10);
        assertEquals(10, sys2.getTaulukonPituus());
    }

    @Test
    public void testVuorojaJaljella() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja t = new Tarkastaja(sys);
        Minimax m = new Minimax(t, sys);
        sys.setTarkastaja(t);
        sys.setMinimax(m);
        
        assertEquals(true, sys.vuorojaJaljella());
        for (int i = 0; i < sys.getTaulukonPituus() * sys.getTaulukonPituus(); i++) {
            sys.vuoroEteenpäin();
        }
        assertEquals(false, sys.vuorojaJaljella());
    }
}
