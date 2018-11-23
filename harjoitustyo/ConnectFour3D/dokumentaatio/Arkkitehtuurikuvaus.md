# Arkkitehtuurikuvaus

## Rakenne

_tulossa myöhemmin_

## Käyttöliittymä

Käyttöliittymä sisältää neljä erillistä näkymää
- Pelinäkymä
- Pelin asetusten muuttaminen
- Menneiden pelien tulosten selaaminen
- Pelin sääntöjen lukeminen

_tulossa myöhemmin_

## Pelilogiikka

_tulossa myöhemmin_

## Kolmiulotteisen pelilaudan piirtäminen

_tulossa myöhemmin_

## Tulosten pysyväistallennus

_tulossa myöhemmin_

### Tiedostot

_tulossa myöhemmin_

### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

#### Pelin pelaaminen

_tulossa myöhemmin_

#### Asetusten vaihtaminen

_tulossa myöhemmin_

## Ohjelman rakenteeseen jääneet heikkoudet

### käyttöliittymä

Käyttöliittymä on selkeä sovelluksen käyttäjälle, mutta käyttöliittymän rakentava koodi on pitkä ja sekava.

### Pelilogiikka

Neljän suoran tarkastaminen vaatii sen, että käymme läpi kaikki pelilaudan ruudut. Tämä aiheuttaa vähintään yhden seuraavista tyylivirheistä:
1. Joudumme luomaan yhden erittäin pitkän metodin, joka tarkistaa kaikkiin suuntiin kulkevat suorat ja diagonaalit pelilaudalta.
2. Joudumme luomaan useita eri metodeja, joista jokainen tarkastaa yhden suoran tai diagonaalin. Tällöin jokaisessa metodissa tulisi käydä läpi seuraava kolminkertainen for-loop, joka aiheuttaa paljon ns. "copy-paste koodia". Tämäkään ei näytä hyvältä
```java
for (int i = 0; i < this.width; i++) {
    for (int j = 0; j < this.height; j++) {
        for (int k = 0; k < this.length; k++) {
            // tarkistetaan neljän suora
        }
    }
}
```