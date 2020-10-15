# Käyttöohje
Sovellus käyttää Java 8:n asti toimivaa JavaFX kirjastoa. 
Jos sinulla Java versio > 8, lisääthän alla olevan rivin 'build.gradle' tiedostoon joka löytyy projektin juuresta.
</br>
```java
plugins {
    id 'org.openjfx.javafxplugin' version '0.0.8'
}

javafx {
    version = "14"
    modules = ['javafx.controls']
}
```
</br>
Mikäli gradle tool löytyy ladattuna koneeltasi, voit käynnistää sovelluksen terminaalista 'gradle run' komennolla kansiosta, jossa 'build.gradle' läsnä (kansio 'RistinollaRoboMain'). Muuten ajathan sovelluksen IDE:lläsi. </br>
Käynnistyessä aukeaa graafinen käyttöliittymä, josta ensin valitaan pelilaudan koko ja painetaan "Käynnistä peli!" nappia. Pelinäytöltä voit navigoida takaisin aloitusnäytölle oikeasta yläkulmasta löytyvällä "Takaisin" napista
</br>
</br>
Ajaaksesi testit, aja 'gradle test' terminaalista 'RistinollaRoboMain' kansiosta.
