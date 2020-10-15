# Toteustusdokumentti

RistinolloRobo on ristinollapelisovellus joka käyttää minimax-algoritmia apunaan laskemaan vastustajalla (AI) seuraavan siirron ja täten pelaaja (ihminen) voi pelata tietokonetta vastaan.
</br>
</br>
Valittavat lautojen koot 3x3, 10x10 ja 15x15. Pienessä laudassa tietokonevastus on voittamaton, mutta 10x10 sekä 15x15 laudoissa pelaajalla on mahdollisuus voittaa peli jos hieman tietää ristinollan taktiikoista. Algoritmi ei loppujen lopuksi kauhean viisas, mutta estää kaikki viiden suorat sekä kolmesta muodostuneet suorat jotka johtaisi seuraavalla siirrolla voittavaan tilanteeseen.

## Algoritmit sekä niiden laskentateho
Minimax-algoritmi syvyyshakutoiminnalla (DFS) joka toimii O(b^m) ajassa, jossa 'b' on jäljellä olevien tyhjien siirtojen määrä ja 'm' syvyys johon asti siirtoja lasketaan. 
</br>
</br>
Ohjelmassani olen asettanut isompien lautojen alkutilanteisiin (enemmän kun 40 tyhjää siirtoa jäljelle) maksimisyvyydeksi kolmen eli algoritmi toimii pelin alussa O(b^3) nopeudella. Tämän myötä algorimit on hieman 'tyhmä', mutta täten pelaajan ei tarvitse odottaa vastuksen siirronlaskemista kovinkaan pitkään. 
</br>
</br>
Minimax-algoritmiin olen lisännyt AlphaBeta-karsinnan, jolloin algoritmi nopeutuu tietyissä tilanteissa O(b^m/2), mutta ns. 'worst case' tilanteet algoritmit toimii kumminkin O(b^m).
</br>
</br>
Usein Minimax-algoritmissä todetaan onko siirtoja vielä jäljellä erillisellä metodilla, jossa käydään kaksiulotteiden taulukko läpi ja palautetaan 'true' mikäli taulukossa käyty kohta on vapaa. Worst case tilanteessa käydään koko taulukko läpi ja aikavaativuus on O(n^2), jossa 'n' on taulukon pituus. 
</br>
</br>
Oivalsin, että kyseisen voi hoitaa pitämällä kirjaa Minimax-algoritmissa montako ruutua on pelattu eri rekursiivisten tilanteiden aikana ja palauttaa arvon takaisin, kun rekursio palaa kutsusta. Alla esimerkki tilanteesta:
```java
if (!onkoRuutujaJaljella()) return 0; // tämä metodi laukeaa, mikäli kaikki ruudut pelattu

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
```
Kyseinen metodi toimii O(1) ajassa, sillä se vertaa mikäli ruutujaPelattuMaara == ruutujen yhteismäärä (taulukon.pituus * taulukon.pituus). Tosin Minimax-algoritmi laskee pelitilanteita loppuun asti vasta pelin lopussa, kun maksimisyvyys antaa myöden, joten tämä nopeuttaa hieman pelin loppuja.
## Tietorakenteet sekä tilavaativuudet
Ohjelmassani ei hirveästi tarvinnut alkuunkaan Javan valmiita tietorakenteita. Alkuperäisessä versiossa minulla oli käytössä mm. String-olion valmis metodi 'contains' sekä StringBuilder-luokka. Nämä kumminkin vaihtui sovellusta tehostaessa, kun vaihdon kaksiuloitteisen String-taulukon Integer-taulukkoon ja tämän myötä sovellukseni toimii nopeammin vain kokonaislukuja käyttäen voittorivien tarkastamiseen. 
</br>
</br>
Minimax-algoritmin tilavaativuutena on O(bm).
## Voittorivien tarkastamiset
Ohjelma tarkastaa tehokkaasti voittorivien muodostamisen vain kohdasta, johon viimesin siirto on tehty. Isoimmissa tauluissa vaaka- ja pystyrivit tarkastetaan luomalla ensin int[] taulukko, jonka pituus on mahdollisen voittorivien lukujen määrä eli esim jos rivin koko on 10 ja pelattu kohta on indeksi 2, tällöin taulukon pituus on 7, sillä voittorivi voi muodostua indeksien 0-7 välille. Tämän jälkeen taulukko käydään läpi window sliding-tekniikkaa apuna käyttäen, jossa ikkunan koko on 5. 
</br>
</br>
Diagonal rivien tarkastamiseen taulukon pituus on pelilaudan pituus, sillä tarkastaminen oli hieman haastavampi tehdä.
</br>
Tilavaativuutena vaaka- ja pystyrivien tarkastamiseen on O(n), jossa 'n' on int[] taulukon pituus.
</br>
Diagonal tarkastaminen myös O(n), jossa 'n' on pelilaudan pituus.

## Puutteet/parannukset
Aina löytyisi viilattavaa sekä koodia saisi refaktoroitua siistimmäksi, mutta päivässä olevat tunnit ovat rajalliset. </br>
AlphaBeta-karsintaa olisi saanut tehokkaammaksi, jos olisi saanut heuristisesti syötettyä tilanteita ja siirtoja fiksummaksi, mikäli olisi pisteyttänyt laudan esim. kulmakohdat huonommiksi pisteiksi, jolloin AI ei pelaisi näitä niin herkästi ainakaan pelin alussa.</br>
Olisin mielelläni graafiseen käyttöliittymään lisännyt pieniäyksityiskohtia esimerkiksi viimeisimmän siirron kohta värilliseksi ja voittorivin yliviivaus.

## Lähteet
https://www.javatpoint.com/mini-max-algorithm-in-ai </br>
https://www.javatpoint.com/ai-alpha-beta-pruning