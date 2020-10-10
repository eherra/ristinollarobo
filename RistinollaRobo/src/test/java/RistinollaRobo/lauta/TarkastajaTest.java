
package RistinollaRobo.lauta;

import RistinollaRobo.*;
import RistinollaRobo.lauta.Pelisysteemi;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class TarkastajaTest {
    
    @Test
    public void testTarkastaVaaka() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);        
        
        for (int i = 4; i < 9; i++) {
            sys.setArvoTaulukkoon(0, i, 1);
        }
        assertEquals(true, tark.tarkastaVaaka(0, 8));
    }
    
    @Test
    public void testTarkastaVaakaTokanrivinLoppu() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);        
        
        for (int i = 5; i < 10; i++) {
            sys.setArvoTaulukkoon(1, i, 1);
        }
        assertEquals(true, tark.tarkastaVaaka(1, 9));
    }
    
    @Test
    public void testTarkastaVaakaAlimmanrivinKeski() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);       
        
        for (int i = 2; i < 7; i++) {
            sys.setArvoTaulukkoon(9, i, 10);
        }
        assertEquals(true, tark.tarkastaVaaka(9, 5));
    }    
    
    @Test
    public void testTarkastaVaakaKeskirivinAlku() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);        
        
        for (int i = 0; i < 5; i++) {
            sys.setArvoTaulukkoon(0, i, 10);
        }
        assertEquals(true, tark.tarkastaVaaka(0, 3));
    }    
    
    @Test
    public void testTarkastaVaakaKeskeltäKolmeMerkkia() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        
        for (int i = 5; i < 9; i++) {
            sys.setArvoTaulukkoon(1, i, 1);
        }
        assertNotEquals(true, tark.tarkastaVaaka(1,8));
    }
    
    @Test
    public void testTarkastaVaakaKeskeltäKaksiMerkkia() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        for (int i = 2; i < 5; i++) {
            sys.setArvoTaulukkoon(1, i, 1);
        }
        assertNotEquals(true, tark.tarkastaVaaka(1,4));
    }
    
    
    
    @Test
    public void testTarkastaPystyVasenReunaKeski() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        
        for (int i = 4; i < 9; i++) {
            sys.setArvoTaulukkoon(i, 0, 1);
        }

    }
    
    @Test
    public void testTarkastaPystyVasenReunaKeskiK() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        
        for (int i = 4; i < 9; i++) {
            sys.setArvoTaulukkoon(i, 0, 1);
        }
        assertEquals(true, tark.tarkastaPysty(5,0));
    }
    @Test
    public void testTarkastaPysty() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        
        for (int i = 4; i < 9; i++) {
            sys.setArvoTaulukkoon(i, 5, 10);
        }
        
        assertEquals(true, tark.tarkastaPysty(8,5));
    }
    
    @Test
    public void testTarkastaPystyKeskeltäMerkitErikseen() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        for (int i = 4; i < 6; i++) {
            sys.setArvoTaulukkoon(i, 5, 10);
        }
        sys.setArvoTaulukkoon(0, 5, 10);
        sys.setArvoTaulukkoon(1, 5, 10);
        assertNotEquals(true, tark.tarkastaPysty(4,5));
    }
    
    @Test
    public void testTarkastaPystyOikeaReuna() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        for (int i = 0; i < 5; i++) {
            sys.setArvoTaulukkoon(i, 9, 10);
        }
        assertEquals(true, tark.tarkastaPysty(0,9));
    }
    
    @Test
    public void testTarkastaPystyKeskeltäKolmeMerkkia() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        for (int i = 0; i < 3; i++) {
            sys.setArvoTaulukkoon(i, 5, 10);
        }
        assertNotEquals(true, tark.tarkastaPysty(0,5));
    }
    
    @Test
    public void testTarkastaPystyKeskeltäNeljaMerkkia() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        for (int i = 0; i < 4; i++) {
            sys.setArvoTaulukkoon(i, 2, 1);
        }
        assertNotEquals(true, tark.tarkastaPysty(0,2));
    }    

    @Test
    public void testVasenYlhaaltaDiagnolOikeaYlaosa() { 
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 0;                                         
        for (int x = 0; x < 5; x++) {                    
            sys.setArvoTaulukkoon(x, y, 10);
            y++;
        }
        
        assertEquals(true, tark.tarkastaDiagonal(0, 0));
    }
    
    @Test
    public void testVasenYlhaaltaDiagnolOikeaYlaosaKeskeltä() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 4; 
        for (int x = 0; x < 5; x++) {
            sys.setArvoTaulukkoon(x, y, 10);
            y++;
        }
        
        assertEquals(true, tark.tarkastaDiagonal(0, 4));
    }
    
    @Test
    public void testVasenYlhaaltaDiagnolOikeaYlaosaOikeaReuna() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 5; 
        for (int x = 0; x < 5; x++) {
            sys.setArvoTaulukkoon(x, y, 1);
            y++;
        }
        
        assertEquals(true, tark.tarkastaDiagonal(0, 5));
    }
    
    @Test
    public void testVasenYlhaaltaDiagnolOikeaYlaosaKolmeMerkkia() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 0; 
        for (int x = 0; x < 3; x++) {
            sys.setArvoTaulukkoon(x, y, 10);
            y++;
        }

        assertNotEquals(true, tark.tarkastaDiagonal(0, 0));
    }
    
    @Test
    public void testVasenYlhaaltaDiagnolOikeaYlaosaMerkitErikseen() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        sys.setArvoTaulukkoon(0, 1, 10);
        sys.setArvoTaulukkoon(1, 2, 10);
        sys.setArvoTaulukkoon(2, 3, 10);
        // väli
        sys.setArvoTaulukkoon(5, 6, 10);
        sys.setArvoTaulukkoon(7, 8, 10);

        assertNotEquals(true, tark.tarkastaDiagonal(0, 1));
    }
    
    @Test
    public void testVasenYlhaaltaDiagonalAlasOikeaAlaosa() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 0; 
        for (int x = 1; x < 6; x++) {
            sys.setArvoTaulukkoon(x, y, 10);
            y++;
        }
        
        assertEquals(true, tark.tarkastaDiagonal(2, 1));    
    }
    
    @Test
    public void testVasenYlhaaltaDiagonalAlasOikeaAlaosaKeskeltä() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 3; 
        for (int x = 5; x < 10; x++) {
            sys.setArvoTaulukkoon(x, y, 1);
            y++;
        }
        
        assertEquals(true, tark.tarkastaDiagonal(5, 3));    
    }
    
    @Test
    public void testVasenYlhaaltaDiagonalAlasOikeaAlaosaAlaReuna() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 0; 
        for (int x = 5; x < 10; x++) {
            sys.setArvoTaulukkoon(x, y, 10);
            y++;
        }
        assertEquals(true, tark.tarkastaDiagonal(5, 0));    
    }
    
    @Test
    public void testVasenYlhaaltaDiagonalAlasOikeaAlaosaKolmeMerkkia() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 4; 
        for (int x = 3; x < 7; x++) {
            sys.setArvoTaulukkoon(x, y, 1);
            y++;
        }
        
        assertNotEquals(true, tark.tarkastaDiagonal(3, 4));    
    }
    
    @Test
    public void testVasenYlhaaltaDiagonalAlasOikeaAlaosaMerkitErikseen() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        sys.setArvoTaulukkoon(3, 1, 10);
        sys.setArvoTaulukkoon(4, 2, 10);
        sys.setArvoTaulukkoon(5, 3, 10);
        // väli
        sys.setArvoTaulukkoon(7, 5, 10);
        sys.setArvoTaulukkoon(8, 6, 10);
        
        assertNotEquals(true, tark.tarkastaDiagonal(3, 1));    
    }
    @Test
    public void testOikeaYlhaaltaDiagnolVasenAlasYlaosa() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 9;
        for (int x = 0; x < 5; x++) {
            sys.setArvoTaulukkoon(x, y, 1);
            y--;
        }
        
        assertEquals(true, tark.tarkastaDiagonal(0, 9));
    }
    
    @Test
    public void testOikeaYlhaaltaDiagnolVasenAlasYlaosaKeskeltä() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 6;
        for (int x = 0; x < 5; x++) {
            sys.setArvoTaulukkoon(x, y, 1);
            y--;
        }
        
        assertEquals(true, tark.tarkastaDiagonal(0, 6));
    }
    
    @Test
    public void testOikeaYlhaaltaDiagnolVasenAlasYlaosaVasenReuna() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 4;
        for (int x = 0; x < 5; x++) {
            sys.setArvoTaulukkoon(x, y, 1);
            y--;
        }

        assertEquals(true, tark.tarkastaDiagonal(0, 4));
    }

@Test
    public void testOikeaYlhaaltaDiagnolVasenAlasYlaosaKolmeMerkkia() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 9;
        for (int x = 0; x < 3; x++) {
            sys.setArvoTaulukkoon(x, y, 1);
            y--;
        }
        
        assertNotEquals(true, tark.tarkastaDiagonal(0, 9));
    }
    
    @Test
    public void testOikeaYlhaaltaDiagnolVasenAlasYlaosaMerkitErikseen() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        sys.setArvoTaulukkoon(0, 7, 10);
        sys.setArvoTaulukkoon(1, 6, 10);
        sys.setArvoTaulukkoon(2, 5, 10);
        // väli
        sys.setArvoTaulukkoon(4, 3, 10);
        sys.setArvoTaulukkoon(5, 2, 10);
        
        assertNotEquals(true, tark.tarkastaDiagonal(4, 3));
    }
    
    @Test
    public void testOikeaYlhaaltaDiagonalVasenAlasAlaosa() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 9;
        
        for (int x = 1; x < 6; x++) {
            sys.setArvoTaulukkoon(x, y, 1);
            y--;
        }
        
        assertEquals(true, tark.tarkastaDiagonal(1, 9));
    }
    
    @Test
    public void testOikeaYlhaaltaDiagonalVasenAlasAlaosaKeskelta() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 7;
        
        for (int x = 4; x < 9; x++) {
            sys.setArvoTaulukkoon(x, y, 1);
            y--;
        }
        
        assertEquals(true, tark.tarkastaDiagonal(4, 7));
    }    
    
    @Test
    public void testOikeaYlhaaltaDiagonalVasenAlasAlaosaOikeaReuna() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 9;
        
        for (int x = 5; x < 10; x++) {
            sys.setArvoTaulukkoon(x, y, 1);
            y--;
        }
        
        assertEquals(true, tark.tarkastaDiagonal(5, 9));
    }    
    
    @Test
    public void testOikeaYlhaaltaDiagonalVasenAlasAlaosaKolmeMerkkia() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 7;
        
        for (int x = 6; x < 9; x++) {
            sys.setArvoTaulukkoon(x, y, 10);
            y--;
        }
        
        assertNotEquals(true, tark.tarkastaDiagonal(6, 7));
    }    
    
    @Test
    public void testOikeaYlhaaltaDiagonalVasenAlasAlaosaMerkitErikseen() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        sys.setArvoTaulukkoon(4, 8, 10);
        sys.setArvoTaulukkoon(5, 7, 10);
        sys.setArvoTaulukkoon(6, 6, 10);
        // väli
        sys.setArvoTaulukkoon(8, 4, 10);
        sys.setArvoTaulukkoon(9, 3, 10);
        
        assertNotEquals(true, tark.tarkastaDiagonal(4, 8));
    }



    @Test
    public void testTarkastaDiagonalPieniTaulukko() {
        Pelisysteemi sys = new Pelisysteemi();
        Tarkastaja tark = new Tarkastaja(sys);
        sys.setArvoTaulukkoon(0, 0, 10);
        sys.setArvoTaulukkoon(1, 1, 10);
        sys.setArvoTaulukkoon(2, 2, 10);
        assertEquals(true, tark.tarkastaDiagonalPieniTaulukko()); 
    }

    @Test
    public void testTarkastaDiagonalIsompiLauta() {
        Pelisysteemi sys = new Pelisysteemi(10);
        Tarkastaja tark = new Tarkastaja(sys);         
        int y = 7;
        
        for (int x = 4; x < 9; x++) {
            sys.setArvoTaulukkoon(x, y, 1);
            y--;
        }
        
        assertEquals(true, tark.tarkastaDiagonal(4, 7));
    }
    
}
