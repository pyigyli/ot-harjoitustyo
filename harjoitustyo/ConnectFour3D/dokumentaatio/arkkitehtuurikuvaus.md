# Arkkitehtuurikuvaus

## Rakenne

_tulossa pian_

## Käyttöliittymä

_tulossa pian_

## Pelilogiikka

_tulossa pian_

## Pelitulosten pysyväistallennus

_tulossa pian_

## Päätoiminnallisuudet

### Uusi peli ja siirrot

_tulossa pian_

### Peliasetusten muuttaminen

_tulossa pian_

## Ohjelman rakenteeseen jääneet heikkoudet

### Pelilogiikka

Pelin luonteesta johtuen, joudumme käymään läpi liki saman toimenpiteen 13 kertaa läpi. Kyseessä on siis se, että joudumme joka siirron jälkeen käymään kaikki pelilaudan ruudut läpi ja tarkistamaan jokaisen suoran ja diagonaalin suhteen, onko pelaaja saanut neljä omaa nappulaansa suoraan. Tästä seuraa valinta, haluammeko yhden todella pitkän metodin, joka sisältää kaikki nämä suorien ja diagonaalien tarkistukset, vai haluammeko jakaa ne useaan metodiin, joista jokainen näyttää lähes samalta. Jälkimmäinen vaihtoehto on valinta, jonka itse valitsin, mutta koodi näyttää hyvin paljon vain copy-paste metodilla toteutetulta. Jokainen suoran ja diagonaalin tarkastus näyttää likimain samalta metodilta, mutta ratkaisut tämän välttämiseksi sisältävät muita ongelmia. Tässä esimerkkinä x-akselin suuntaisen suorien tarkastamisesta vastaava metodi.
```java
public Boolean checkWinX(int[][][] playerBoard) {
    for (int i = 0; i < this.width; i++) {
        for (int j = 0; j < this.height; j++) {
            for (int k = 0; k < this.length; k++) {
                if (i < this.width - 3) {
                    if (playerBoard[i][j][k] == 1 &&
                            playerBoard[i + 1][j][k] == 1 &&
                            playerBoard[i + 2][j][k] == 1 &&
                            playerBoard[i + 3][j][k] == 1) {
                        return true;
                    }
                }
            }
        }
    }
    return false;
}
´´´