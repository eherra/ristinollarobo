# Käyttöohje
Sovellus käyttää Java 8:n asti toimivaa JavaFX kirjastoa. 
Jos sinulla on käytössä JDK 11, voit käynnistää ohjelman alla olevien ohjeiden mukaan:
</br>
1. lataa openjx kommenolla:
```console
sudo apt install openjfx
```
2. listaa lataamasi javafx kirjaston lokaatio kommenolla:
```console
dpkg-query -L openjfx
```
3. Käynnistä ohjelma kommenollla:
```console
java --module-path $PATH_TO_OPENJFX-LIB --add-modules module_1,module_2,module_3,...,module_n -jar $PATH_TO_JAR_FILE
```

Esimerkkinä:
```console
java --module-path /usr/share/openjfx/lib --add-modules=javafx.controls,javafx.fxml -jar '/home/{USERNAME}/ristinollarobo/RistinollaRobo.jar'
```

</br>
Mikäli gradle tool löytyy ladattuna koneeltasi, voit käynnistää sovelluksen terminaalista 'gradle run' komennolla kansiosta, jossa 'build.gradle' läsnä (kansio 'RistinollaRoboMain'). Muuten ajathan sovelluksen IDE:lläsi. </br>
Käynnistyessä aukeaa graafinen käyttöliittymä, josta ensin valitaan pelilaudan koko ja painetaan "Käynnistä peli!" nappia. Pelinäytöltä voit navigoida takaisin aloitusnäytölle oikeasta yläkulmasta löytyvällä "Takaisin" napista
</br>
</br>
Ajaaksesi testit, aja 'gradle test' terminaalista 'RistinollaRoboMain' kansiosta.
