
package RistinollaRobo;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;


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
}


