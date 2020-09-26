<h1>Testausdokumentti</h1>
Ohjelman eri luokkien metodeita on testattu JUNitia apuna käyttäen sekä olen paljon testannut peliä graafista käyttöliittymää käyttäen. Mikäli 3x3 kokoisessa pelissä menee jokin pieleen tämä tulee nopeasti ilmi GUI:sta ja olen pystynyt tekemään nopeasti tarvittavat muutokset luokkiin. </br>
Testeissä olen panostanut vaikeampien metodien kattavampaan testaukseen esimerkiksi isomman taulun diagonaaliset voitontarkastukseen olen luonut monet eri testit sillä tarkastuksessa pitää ottaa huomioon monta asiaa mm. mikäli merkit eivät ole peräkkäin tarkastusrivillä ja tämän tunnistus. 
</br>
Tähän tulee vielä muutoksia seuraavan viikon aikana, sillä muutan tarkastusmetodeita tehokkaammaksi, jotta koko taulua ei tarvitse käydä lävitse tarkastuksen yhteydessä vaan vain kohta johon viimeisin merkki on pelattu.

<h2> Milläisillä syötteillä testattu</h2>
JUnit testeissä olen luonut eri pelitilanteita matriisiin ja syöttänyt näitä pelitilainteita testimetodeilla. 
</br>
</br>
Testejä ajetaan Gradle toolin avulla terminaalista. Testeistä saa kattavan raportin html-muodossa. 