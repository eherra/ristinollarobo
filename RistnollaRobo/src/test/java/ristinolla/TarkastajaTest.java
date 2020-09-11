
package ristinolla;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TarkastajaTest {
    
    @Test
    public void testTarkastaVaakaKeskeltä() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);        
        
        for (int i = 4; i < 9; i++) {
            sys.setArvoTaulukkoon(0, i, "X");
        }
        assertEquals(true, tark.tarkastaVaaka());
    }
    
    @Test
    public void testTarkastaVaakaTokanrivinLoppu() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);        
        
        for (int i = 5; i < 10; i++) {
            sys.setArvoTaulukkoon(1, i, "X");
        }
        assertEquals(true, tark.tarkastaVaaka());
    }
    
    @Test
    public void testTarkastaVaakaAlimmanrivinKeski() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);       
        
        for (int i = 2; i < 7; i++) {
            sys.setArvoTaulukkoon(9, i, "O");
        }
        assertEquals(true, tark.tarkastaVaaka());
    }    
    
    @Test
    public void testTarkastaVaakaKeskirivinAlku() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);        
        
        for (int i = 0; i < 5; i++) {
            sys.setArvoTaulukkoon(0, i, "O");
        }
        assertEquals(true, tark.tarkastaVaaka());
    }    
    
    @Test
    public void testTarkastaVaakaKeskeltäKolmeMerkkia() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        
        for (int i = 5; i < 9; i++) {
            sys.setArvoTaulukkoon(1, i, "X");
        }
        assertNotEquals(true, tark.tarkastaVaaka());
    }
    
    @Test
    public void testTarkastaVaakaKeskeltäKaksiMerkkia() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        for (int i = 2; i < 5; i++) {
            sys.setArvoTaulukkoon(1, i, "X");
        }
        assertNotEquals(true, tark.tarkastaVaaka());
    }
    
    
    
    @Test
    public void testTarkastaPystyVasenReunaKeski() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        
        for (int i = 4; i < 9; i++) {
            sys.setArvoTaulukkoon(i, 0, "X");
        }
        assertEquals(true, tark.tarkastaPysty());
    }
    @Test
    public void testTarkastaPystyKeskeltä() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        
        for (int i = 4; i < 9; i++) {
            sys.setArvoTaulukkoon(i, 5, "O");
        }
        sys.setArvoTaulukkoon(0, 5, "O");
        sys.setArvoTaulukkoon(1, 5, "O");
        assertEquals(true, tark.tarkastaPysty());
    }
    
    @Test
    public void testTarkastaPystyKeskeltäMerkitErikseen() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        for (int i = 4; i < 6; i++) {
            sys.setArvoTaulukkoon(i, 5, "O");
        }
        sys.setArvoTaulukkoon(0, 5, "O");
        sys.setArvoTaulukkoon(1, 5, "O");
        assertNotEquals(true, tark.tarkastaPysty());
    }
    
    @Test
    public void testTarkastaPystyOikeaReuna() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        for (int i = 0; i < 5; i++) {
            sys.setArvoTaulukkoon(i, 9, "O");
        }
        assertEquals(true, tark.tarkastaPysty());
    }
    
    @Test
    public void testTarkastaPystyKeskeltäKolmeMerkkia() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        for (int i = 0; i < 3; i++) {
            sys.setArvoTaulukkoon(i, 5, "O");
        }
        assertNotEquals(true, tark.tarkastaPysty());
    }
    
    @Test
    public void testTarkastaPystyKeskeltäNeljaMerkkia() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        for (int i = 0; i < 4; i++) {
            sys.setArvoTaulukkoon(i, 2, "X");
        }
        assertNotEquals(true, tark.tarkastaPysty());
    }    
    
    @Test
    public void testTarkastaDiagonal() {
    }

    @Test
    public void testVasenYlhaaltaDiagnolOikeaYlaosa() { // vasemmasta yläkulmasta puoleen väliin. Oikealle kääntyvät suorat
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 0;                                         
        for (int x = 0; x < 5; x++) {                    
            sys.setArvoTaulukkoon(x, y, "O");
            y++;
        }
        
        assertEquals(true, tark.vasenYlhaaltaDiagnolOikeaYlaosa());
    }
    
    @Test
    public void testVasenYlhaaltaDiagnolOikeaYlaosaKeskeltä() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 4; 
        for (int x = 0; x < 5; x++) {
            sys.setArvoTaulukkoon(x, y, "O");
            y++;
        }
        
        assertEquals(true, tark.vasenYlhaaltaDiagnolOikeaYlaosa());
    }
    
    @Test
    public void testVasenYlhaaltaDiagnolOikeaYlaosaOikeaReuna() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 5; 
        for (int x = 0; x < 5; x++) {
            sys.setArvoTaulukkoon(x, y, "X");
            y++;
        }
        
        assertEquals(true, tark.vasenYlhaaltaDiagnolOikeaYlaosa());
    }
    
    @Test
    public void testVasenYlhaaltaDiagnolOikeaYlaosaKolmeMerkkia() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 0; 
        for (int x = 0; x < 3; x++) {
            sys.setArvoTaulukkoon(x, y, "O");
            y++;
        }

        assertNotEquals(true, tark.vasenYlhaaltaDiagnolOikeaYlaosa());
    }
    
    @Test
    public void testVasenYlhaaltaDiagnolOikeaYlaosaMerkitErikseen() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        sys.setArvoTaulukkoon(0, 1, "O");
        sys.setArvoTaulukkoon(1, 2, "O");
        sys.setArvoTaulukkoon(2, 3, "O");
        // väli
        sys.setArvoTaulukkoon(5, 6, "O");
        sys.setArvoTaulukkoon(7, 8, "O");

        assertNotEquals(true, tark.vasenYlhaaltaDiagnolOikeaYlaosa());
    }
    
    @Test
    public void testVasenYlhaaltaDiagonalAlasOikeaAlaosa() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 0; 
        for (int x = 1; x < 6; x++) {
            sys.setArvoTaulukkoon(x, y, "O");
            y++;
        }
        
        assertEquals(true, tark.vasenYlhaaltaDiagonalAlasOikeaAlaosa());    
    }
    
    @Test
    public void testVasenYlhaaltaDiagonalAlasOikeaAlaosaKeskeltä() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 3; 
        for (int x = 5; x < 10; x++) {
            sys.setArvoTaulukkoon(x, y, "X");
            y++;
        }
        
        assertEquals(true, tark.vasenYlhaaltaDiagonalAlasOikeaAlaosa());    
    }
    
    @Test
    public void testVasenYlhaaltaDiagonalAlasOikeaAlaosaAlaReuna() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 0; 
        for (int x = 5; x < 10; x++) {
            sys.setArvoTaulukkoon(x, y, "O");
            y++;
        }
        
        assertEquals(true, tark.vasenYlhaaltaDiagonalAlasOikeaAlaosa());    
    }
    
    @Test
    public void testVasenYlhaaltaDiagonalAlasOikeaAlaosaKolmeMerkkia() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 4; 
        for (int x = 3; x < 7; x++) {
            sys.setArvoTaulukkoon(x, y, "O");
            y++;
        }
        
        assertNotEquals(true, tark.vasenYlhaaltaDiagonalAlasOikeaAlaosa());    
    }
    
    @Test
    public void testVasenYlhaaltaDiagonalAlasOikeaAlaosaMerkitErikseen() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        sys.setArvoTaulukkoon(3, 1, "O");
        sys.setArvoTaulukkoon(4, 2, "O");
        sys.setArvoTaulukkoon(5, 3, "O");
        // väli
        sys.setArvoTaulukkoon(7, 5, "O");
        sys.setArvoTaulukkoon(8, 6, "O");
        
        assertNotEquals(true, tark.vasenYlhaaltaDiagonalAlasOikeaAlaosa());    
    }
    @Test
    public void testOikeaYlhaaltaDiagnolVasenAlasYlaosaOikeaReuna() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 9;
        for (int x = 0; x < 5; x++) {
            sys.setArvoTaulukkoon(x, y, "X");
            y--;
        }
        
        assertEquals(true, tark.oikeaYlhaaltaDiagnolVasenAlasYlaosa());
    }
    
    @Test
    public void testOikeaYlhaaltaDiagnolVasenAlasYlaosaKeskeltä() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 6;
        for (int x = 0; x < 5; x++) {
            sys.setArvoTaulukkoon(x, y, "O");
            y--;
        }
        
        assertEquals(true, tark.oikeaYlhaaltaDiagnolVasenAlasYlaosa());
    }
    
    @Test
    public void testOikeaYlhaaltaDiagnolVasenAlasYlaosaVasenReuna() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 4;
        for (int x = 0; x < 5; x++) {
            sys.setArvoTaulukkoon(x, y, "X");
            y--;
        }

        assertEquals(true, tark.oikeaYlhaaltaDiagnolVasenAlasYlaosa());
    }

@Test
    public void testOikeaYlhaaltaDiagnolVasenAlasYlaosaKolmeMerkkia() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 9;
        for (int x = 0; x < 3; x++) {
            sys.setArvoTaulukkoon(x, y, "X");
            y--;
        }
        
        assertNotEquals(true, tark.oikeaYlhaaltaDiagnolVasenAlasYlaosa());
    }
    
    @Test
    public void testOikeaYlhaaltaDiagnolVasenAlasYlaosaMerkitErikseen() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        sys.setArvoTaulukkoon(0, 7, "O");
        sys.setArvoTaulukkoon(1, 6, "O");
        sys.setArvoTaulukkoon(2, 5, "O");
        // väli
        sys.setArvoTaulukkoon(4, 3, "O");
        sys.setArvoTaulukkoon(5, 2, "O");
        
        assertNotEquals(true, tark.oikeaYlhaaltaDiagnolVasenAlasYlaosa());    
    }
    
    @Test
    public void testOikeaYlhaaltaDiagonalVasenAlasAlaosa() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 9;
        
        for (int x = 1; x < 6; x++) {
            sys.setArvoTaulukkoon(x, y, "X");
            y--;
        }
        
        assertEquals(true, tark.oikeaYlhaaltaDiagonalVasenAlasAlaosa());
    }
    
    @Test
    public void testOikeaYlhaaltaDiagonalVasenAlasAlaosaKeskelta() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 7;
        
        for (int x = 4; x < 9; x++) {
            sys.setArvoTaulukkoon(x, y, "X");
            y--;
        }
        
        assertEquals(true, tark.oikeaYlhaaltaDiagonalVasenAlasAlaosa());
    }    
    
    @Test
    public void testOikeaYlhaaltaDiagonalVasenAlasAlaosaOikeaReuna() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 9;
        
        for (int x = 5; x < 10; x++) {
            sys.setArvoTaulukkoon(x, y, "X");
            y--;
        }
        
        assertEquals(true, tark.oikeaYlhaaltaDiagonalVasenAlasAlaosa());
    }    
    
    @Test
    public void testOikeaYlhaaltaDiagonalVasenAlasAlaosaKolmeMerkkia() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 7;
        
        for (int x = 6; x < 9; x++) {
            sys.setArvoTaulukkoon(x, y, "O");
            y--;
        }
        
        assertNotEquals(true, tark.oikeaYlhaaltaDiagonalVasenAlasAlaosa());
    }    
    
    @Test
    public void testOikeaYlhaaltaDiagonalVasenAlasAlaosaMerkitErikseen() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);         
        sys.setArvoTaulukkoon(4, 8, "O");
        sys.setArvoTaulukkoon(5, 7, "O");
        sys.setArvoTaulukkoon(6, 6, "O");
        // väli
        sys.setArvoTaulukkoon(8, 4, "O");
        sys.setArvoTaulukkoon(9, 3, "O");
        
        assertNotEquals(true, tark.oikeaYlhaaltaDiagonalVasenAlasAlaosa()); 
    }
    
}
