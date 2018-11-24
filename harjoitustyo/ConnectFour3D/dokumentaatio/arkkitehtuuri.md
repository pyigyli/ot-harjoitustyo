# Arkkitehtuurikuvaus

## Rakenne

Sovelluksen rakenteen voi jakaa neljään erilliseen osaan. Käyttöliittymä, pelin graafinen visualisointi, pelilogiikka ja tietokannan käyttö. Näillä kaikilla on oma selkeä tehtävänsä.

### Luokkakaavio

<img src="https://raw.githubusercontent.com/pyigyli/ot-harjoitustyo/master/harjoitustyo/ConnectFour3D/dokumentaatio/kuvat/luokkakaavio.png" width="900">

## Käyttöliittymä

Käyttöliittymä koostuu neljästä eri näkymästä:
- Pelinäkymä
- Asetusten muuttaminen
- Menneiden pelien tulokset
- Pelin säännöt

<p float="left">
    <img src="https://raw.githubusercontent.com/pyigyli/ot-harjoitustyo/master/harjoitustyo/ConnectFour3D/dokumentaatio/kuvat/kayttoliittyma_pelinakyma.png" width="430">
    <img src="https://raw.githubusercontent.com/pyigyli/ot-harjoitustyo/master/harjoitustyo/ConnectFour3D/dokumentaatio/kuvat/kayttoliittyma_asetusnakyma.png" width="430">
</p>
<p float="left">
    <img src="https://raw.githubusercontent.com/pyigyli/ot-harjoitustyo/master/harjoitustyo/ConnectFour3D/dokumentaatio/kuvat/kayttoliittyma_tulosnakyma.png" width="430">
    <img src="https://raw.githubusercontent.com/pyigyli/ot-harjoitustyo/master/harjoitustyo/ConnectFour3D/dokumentaatio/kuvat/kayttoliittyma_saantonakyma.png" width="430">
</p>

jokainen näistä on toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen stageen. Kaikissa näkymissä yksi yhteinen elementti on ikkunan yläosassa näkyvä valikko, jota käyttämällä näkymiä voi vaihtaa.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta. Käyttöliittymästä on myös eristetty luokka, joka yksin vastaa pelilaudan visuaalisesta esityksestä.

## Grafiikka

Pelilaudan piirtäminen perustuu matriisien käyttöön. Koska pelilauta on kolmiulotteinen ruudukko, oli luontevaa soveltaa ortografista projektiota, eli heijastaa matriisikertolaskun avulla kolmiulotteiset koordinaatit kaksiulotteiselle näytölle. Matriisien käsittelyssä auttoi Jama.Matrix-kirjasto.

## Pelilogiikka

Pelilogiikassa on toteutettu kaikki vaadittavat metodit, jotka mahdollistavat pelin pelaamisen. Pelilogiikan metodeja on yritetty pitää mahdollisimman lyhyinä ja selkeinä, mikä vaatii pientä ylimääräistä työtä sovelluksen pääluokalta, joka metodeja kutsuu, mutta kaikki pelilogiikan työ tehdään kuitenkin täysin pelilogiikan omassa luokassa. Koodia on pyritty myös uudelleenkäyttämään mahdollisimman paljon, joten pelaajasta riippumatta kaikki käyttävät samoja metodeita omien nappuloidensa tarkistamiseen.

## Pelitulosten pysyväistallennus

Pelitulosten tallentamiseen ja niiden hakemiseen on käytetty paikallista SQL-tietokantaa. Käyttäjän on täysin mahdollista poistaa kyseinen tiedosto laitteeltaan, jolloin tietokannan puuttumisesta johtuva virheviesti tulee näkyviin pelitulosten näkymässä ja virheviestin yhteydessä tietokantataulut luodaan uudellee, jolloin tulosten tallentaminen on jälleen mahdollista.

Luokat noudattavat Data Access Object -suunnittelumallia ja ne on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan talletustapaa päätetään vaihtaa. Luokat onkin eristetty rajapinnan _ScoreDao_ taakse ja käyttöliittymä ei käytä luokkia suoraan.

## Päätoiminnallisuudet

### Uusi peli ja pelinappulan asettaminen

Esitetään seuraavaksi sekvenssikaavio, jossa kuvataan uuden pelin aloittamista pelin oletusasetuksilla ja yhden pelaajan ensimmäisen pelinappulan asettamista. Keskitytään ainoastaan pelilogiikkaan, jotta sekvenssikaavio ei täyty käyttöliittymään ja pelilaudan piirtämiseen liittyvistä metodeista. Jätetään myös pelitulosten tallennus huomiotta, sillä keskitymme siihen seuraavaksi tarkemmin.

<img src="https://raw.githubusercontent.com/pyigyli/ot-harjoitustyo/master/harjoitustyo/ConnectFour3D/dokumentaatio/kuvat/uusi_peli_sekvenssikaavio.png" width="750">

### Pelin päättyminen

Esitetään seuraavaksi sekvenssikaavio, jossa kuvataan 3 pelaajan pelin päättymistä vuorolla 16 (tai 15, sillä koodissa ensimmäinen vuoro on 0). Olkoon Pelaaja 1 voittaja, joka saa neljä pelinappulaansa pystysuoraan jonoon. Jätetään taas käyttöliittymään ja pelilaudan piirtämiseen liittyvät metodit pois sekvenssikaaviosta, jotta tulosten tallennuksen kannalta olennaiset metodit ovat helpommin seurattavissa.

<img src="https://raw.githubusercontent.com/pyigyli/ot-harjoitustyo/master/harjoitustyo/ConnectFour3D/dokumentaatio/kuvat/pelin_paattyminen_sekvenssikaavio.png" width="900">

## Ohjelman rakenteeseen jääneet heikkoudet

### Pelilogiikka

Pelin luonteesta johtuen, joudumme käymään läpi liki saman toimenpiteen 13 kertaa läpi. Kyseessä on siis se, että joudumme joka siirron jälkeen käymään kaikki pelilaudan ruudut läpi ja tarkistamaan jokaisen suoran ja diagonaalin suhteen, onko pelaaja saanut neljä omaa nappulaansa suoraan. Tästä seuraa valinta, haluammeko yhden todella pitkän metodin, joka sisältää kaikki nämä suorien ja diagonaalien tarkistukset, vai haluammeko jakaa ne useaan metodiin, joista jokainen näyttää lähes samalta. Menin itse jälkimmäisellä valinnalla, mutta koodi näyttää tällöin hyvin paljon vain copy-paste metodilla toteutetulta. Jokainen suoran ja diagonaalin tarkastus näyttää likimain samalta metodilta, mutta ratkaisut tämän välttämiseksi sisältävät muita ongelmia. Tässä esimerkkinä x-akselin suuntaisen suorien tarkastamisesta vastaava metodi.
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
```