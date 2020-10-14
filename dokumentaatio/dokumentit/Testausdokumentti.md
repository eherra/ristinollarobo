<h1>Testausdokumentti</h1>
Ohjelman eri luokkien metodeita on testattu JUNitia apuna käyttäen sekä olen paljon testannut peliä graafista käyttöliittymää käyttäen. Mikäli 3x3 kokoisessa pelissä menee jokin pieleen tämä tulee nopeasti ilmi GUI:sta ja olen pystynyt tekemään nopeasti tarvittavat muutokset luokkiin. </br>
Isompia tauluja olen testannut, kun olen rajannut minimax-algoritmin syvyyttä eli pelitilanteita on laskettu vain johonkin tietylle syvyydelle. Kun syvyys on rajattu, olen pystynyt testaamaan algoritmin älykkyyttä pelaamalla sitä vastaan. Tosin kun syvyyttä ei ole paljoa, niin tekoäly ei hirveän älykäs ole pelaamaan omaa peliään. Tekoäly kumminkin saa estettyä hyvin voittotilanteiden synnyn sekä omaa peliään kehitettyä. Kun tilanne on varma voitto pelaajalle (esim. 2 kolmen suoraa, josta toisesta varmasti muodustuu 5 suora), tekoäly "luovuttaa" eikä edes yritä enää blokata siirtoja. </br></br>
Testeissä olen panostanut vaikeampien metodien kattavampaan testaukseen esimerkiksi isomman taulun diagonaaliset voitontarkastukseen olen luonut monet eri testit sillä tarkastuksessa pitää ottaa huomioon monta asiaa mm. mikäli merkit eivät ole peräkkäin tarkastusrivillä ja tämän tunnistus. 
<h2> Milläisillä syötteillä testattu</h2>
JUnit testeissä olen luonut eri pelitilanteita matriisiin ja syöttänyt näitä pelitilainteita testimetodeilla ja tarkastanut 
</br>
</br>
Testejä ajetaan Gradle toolin avulla terminaalista. Tämä onnistuu navigoimalla 'RistinollaRoboMain' kansiion ja suorittamalla 'gradle test' komennon. Mikäli testeistä jokin epäonnistuu, testeistä saa kattavan raportin html-muodossa. 
</br>
</br>
Alla kuvat jacocon luomista testauskattavuusyhteenvedoista:
<img src="https://ibb.co/7Jbc6Cm">

