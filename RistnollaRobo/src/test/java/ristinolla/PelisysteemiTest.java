
package ristinolla;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PelisysteemiTest {

    @Test
    public void testGetVuoro() {
        Pelisysteemi sys = new Pelisysteemi();
        assertEquals("X", sys.getVuoro()); // alkutilanteessa X vuorossa
        
        sys.vuoroEteenpäin();
        assertEquals("O", sys.getVuoro()); // yksi vuoro eteenpäin, onko O vuorossa?
        
        sys.vuoroEteenpäin(); // kaksi vuoroa eteenpäin, onko X vuorossa?
        assertEquals("X", sys.getVuoro());
    }

    @Test
    public void testVuoroEteenpäin() {
        Pelisysteemi sys = new Pelisysteemi();
        String ekaVuorossa = sys.getVuoro();
        
        sys.vuoroEteenpäin();
        
        assertNotEquals(ekaVuorossa, sys.getVuoro()); // kun vuoro vaihtuu, vuorossa eri
    }


    @Test
    public void testSetArvoTaulukkoon() {
        Pelisysteemi sys = new Pelisysteemi();
        
        sys.setArvoTaulukkoon(0, 0, "X");
        sys.setArvoTaulukkoon(9, 9, "X");
        sys.setArvoTaulukkoon(0, 9, "O");
        
        assertEquals("X", sys.getArvoTaulukosta(0, 0));
        assertEquals("X", sys.getArvoTaulukosta(9, 9));
        assertEquals("O", sys.getArvoTaulukosta(0, 9));
        assertNotEquals("O", sys.getArvoTaulukosta(0, 0));
        assertNotEquals("X", sys.getArvoTaulukosta(5, 5));
    }
}


