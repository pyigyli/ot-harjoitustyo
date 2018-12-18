# Connect Four 3D

Klassinen peli, neljän suora, toteutettuna kolmiulotteisessa muodossa ja muilla pienillä variaatioilla, kuten mahdollisuudella muuttaa pelilaudan pituuksia ja pelaajien lukumäärää. Sopii hupikäyttöön 2-4 pelaajalle.

## Dokumentaatio

[Käyttöohje](https://github.com/pyigyli/ot-harjoitustyo/blob/master/harjoitustyo/ConnectFour3D/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/pyigyli/ot-harjoitustyo/blob/master/harjoitustyo/ConnectFour3D/dokumentaatio/vaatimusmaarittelu.md)

[Arkkiitehtuurikuvaus](https://github.com/pyigyli/ot-harjoitustyo/blob/master/harjoitustyo/ConnectFour3D/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/pyigyli/ot-harjoitustyo/blob/master/harjoitustyo/ConnectFour3D/dokumentaatio/testausdokumentti.md)

[Työaikakirjanpito](https://github.com/pyigyli/ot-harjoitustyo/blob/master/harjoitustyo/ConnectFour3D/dokumentaatio/tuntikirjanpito.md)


## Releaset

[Viikko 5](https://github.com/pyigyli/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/pyigyli/ot-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/pyigyli/ot-harjoitustyo/releases/tag/loppupalautus)

## Komentorivitoiminnot

Komentorivin komennot suoritetaan kansiossa *ConnectFour3D*.

### Testit

Testit suoritetaan komennolla `mvn test`.

Testikattavuusraportti luodaan komennolla `mvn jacoco:report`. Testikattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*.

### Suoritettava jar-tiedosto

Suoritettavan jar-tiedoston voi luoda komennolla `mvn package`. Luodun tiedoston polku on *target/ConnectFour3D-1.0-SNAPSHOT.jar*.

### JavaDoc

JavaDoc generoidaan komennolla `mvn javadoc:javadoc`. JavaDocia voi tarkastella avaamalla tiedosto *target/site/apidocs/index.html*.

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/pyigyli/ot-harjoitustyo/blob/master/harjoitustyo/ConnectFour3D/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla `mvn jxr:jxr checkstyle:checkstyle`. Määriteltyjen tarkistusten rikkeet ovat tarkasteltavissa avaamalla tiedosto *target/site/checkstyle.html*.