<h1>Määrittelydokumentti</h1>
Luon tekoälyn laajennetulle ristinollapelille Minimax-algoritmia apuna käyttäen. Impletoin myös AlphaBeta-karsimisen algoritmin tehokkuuden parantamiseksi. </br>
Sovellukseni tulee käyttämään ainakin String-olion valmista metodia "contains", Math-luokan Min()- ja Max()- metodeita sekä Javan valmista luokkaa StringBuilder. Tulen itse tekemään kyseiset metodit/oliot.
Algoritmin syötteenä toimii muuttuvat pelitilanteet, jotka syötetään 2D matriisin muodossa. 
<h1>Tavoitteena olevat aika- ja tilavaativuudet</h1>
Minimax-algoritmi suorittaa DFS:n (syvyyshahaku) pelitilanteiden läpikäymiseen, joten algoritmin aikavaativuutena O(b^m). AlphaBeta-karsimisille saadaan parhaimmassa tapauaksessa aikavaativuudeksi O(b^m/2) ja huonoimmassa tapauksessa aikavaativuus pysyy samana eli O(b^m). AlphaBeta-karsimisen tehokkuus riippuu siitä, missä järjestyksessä solmuja käydään lävitse. </br>
Tilavaativuutena on O(bm).

<h2>Kielivalinnat</h2>
Tulen tekemään projektin Javalla sekä dokumenataatiokielenä toimii suomen kieli  
<h2>Opinto-ohjelma</h2>
Tietojenkäsittelytieteen kandidaatti (TKT)
<h2>Lähteet</h2>
https://www.javatpoint.com/mini-max-algorithm-in-ai </br>
https://www.javatpoint.com/ai-alpha-beta-pruning