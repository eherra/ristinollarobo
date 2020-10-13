# ristinollarobo
Ristinollapeli, jossa AI vastus. Vaihtoehdot pelata laudan kokoja 3x3, 10x10, 15x15. Isoimmissa laudoissa voittorivin pituus 5 merkkiä.
</br>
Sovellus käyttää Java 8:n asti toimivaa JavaFX kirjastoa. Jos sinulla Java versio > 8, lisääthän alla olevan rivin 'build.gradle' tiedostoon joka löytyy projektin juuresta.
</br>
</br>
plugins {
    id 'org.openjfx.javafxplugin' version '0.0.8'
}

javafx {
    version = "14"
    modules = ['javafx.controls']
}
</br>
</br>
Mikäli gradle tool löytyy ladattuna koneeltasi, voit käynnistää sovelluksen terminaalista 'gradle run' komennolla kansiosta, jossa 'build.gradle' läsnä (kansio 'ristinollarobo'). Muuten ajathan sovelluksen IDE:lläsi.
</br>
Ajaaksesi testit, aja 'gradle test' terminaalista 'ristinollarobo' kansiosta.
</br>

[a link](https://github.com/eherra/ristinollarobo/blob/master/dokumentaatio/dokumentit/Maarittelydokumentti.md)
[a link](https://github.com/eherra/ristinollarobo/blob/master/dokumentaatio/dokumentit/Testausdokumentti.md)
[a link](https://github.com/eherra/ristinollarobo/blob/master/dokumentaatio/dokumentit/Toteutusdokumentti.md)
</br>





