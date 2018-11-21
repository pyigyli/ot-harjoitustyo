# Connect Four 3D

Klassinen peli, neljän suora, toteutettuna kolmiulotteisessa muodossa ja ehkä muilla pienillä variaatioilla. Sopii hupikäyttöön 2-4 pelaajalle.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/pyigyli/ot-harjoitustyo/blob/master/harjoitustyo/ConnectFour3D/dokumentaatio/vaatimusmaarittelu.md)

[Työaikakirjanpito](https://github.com/pyigyli/ot-harjoitustyo/blob/master/harjoitustyo/ConnectFour3D/dokumentaatio/tuntikirjanpito.md)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _ConnectFour3D-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/pyigyli/ot-harjoitustyo/blob/master/harjoitustyo/ConnectFour3D/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_