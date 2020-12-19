# Käyttöohje
Jos sinulla on käytössä JDK > 8, voit käynnistää ohjelman alla olevien ohjeiden mukaan:

1. kloonaa repositorio komennolla:
```console
git clone https://github.com/eherra/ristinollarobo.git
```

* Jos sinulla ei ole openjx vielä ladattuna, lataa openjx kommenolla:
```console
sudo apt install openjfx
```

2. Mikäli **Gradle tool** löytyy ladattuna koneeltasi, voit käynnistää sovelluksen terminaalista:
```console
gradle run
```

* Mikäli gradlea ei löydy sinulta, voit ajaa sovelluksen myös IDE:lläsi. 
* linkki gradlen lautaukseen [lataa gradle](https://gradle.org/install)

Ohjelman käynnistyessä aukeaa graafinen käyttöliittymä, josta valitaan pelilaudan koko ja painetaan "Käynnistä peli!" nappia. Pelinäytöltä voit navigoida takaisin aloitusnäytölle oikeasta yläkulmasta löytyvällä "Takaisin" napista

Ajaaksesi testit, aja terminaalista komento:
```console
gradle test
```
