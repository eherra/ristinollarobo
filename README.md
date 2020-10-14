# RistinollaRobo
Ristinollapeli, jossa AI vastus. Vaihtoehdot pelata laudan kokoja 3x3, 10x10, 15x15. Isoimmissa laudoissa voittorivin pituus 5 merkkiä.
</br>
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
Mikäli gradle tool löytyy ladattuna koneeltasi, voit käynnistää sovelluksen terminaalista 'gradle run' komennolla kansiosta, jossa 'build.gradle' läsnä (kansio 'RistinollaRoboMain'). Muuten ajathan sovelluksen IDE:lläsi.
</br>
</br>
Ajaaksesi testit, aja 'gradle test' terminaalista 'ristinollarobo' kansiosta.

## Dokumentit 
* [Määrittelydokumentti](https://github.com/eherra/ristinollarobo/blob/master/dokumentaatio/dokumentit/Maarittelydokumentti.md)
* [Toteutusdokumentti](https://github.com/eherra/ristinollarobo/blob/master/dokumentaatio/dokumentit/Toteutusdokumentti.md)
* [Testausdokumentti](https://github.com/eherra/ristinollarobo/blob/master/dokumentaatio/dokumentit/Testausdokumentti.md)

## Viikkoraportit 
* [Viikkoraportti 1](https://github.com/eherra/ristinollarobo/blob/master/dokumentaatio/viikkoraportti1.md)
* [Viikkoraportti 2](https://github.com/eherra/ristinollarobo/blob/master/dokumentaatio/viikkoraportti2.md)
* [Viikkoraportti 3](https://github.com/eherra/ristinollarobo/blob/master/dokumentaatio/viikkoraportti3.md)
* [Viikkoraportti 4](https://github.com/eherra/ristinollarobo/blob/master/dokumentaatio/viikkoraportti4.md)
* [Viikkoraportti 5](https://github.com/eherra/ristinollarobo/blob/master/dokumentaatio/viikkoraportti5.md)
* [Viikkoraportti 6](https://github.com/eherra/ristinollarobo/blob/master/dokumentaatio/viikkoraportti6.md)
</br>





