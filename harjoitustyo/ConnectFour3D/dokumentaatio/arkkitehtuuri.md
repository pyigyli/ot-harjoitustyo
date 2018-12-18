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

Jokainen näkymä on erillinen Scene-olio. Kaikissa näkymissä yksi yhteinen elementti on ikkunan yläosassa näkyvä valikko, jota käyttämällä näkymiä voi vaihtaa.

Käyttöliittymä ja sovelluslogiikka on pyritty pitämään erillään. Käyttöliittymästä on myös eristetty luokka, joka yksin vastaa pelilaudan visuaalisesta esityksestä.

## Grafiikka

Pelilaudan piirtäminen perustuu matriisien käyttöön. Koska pelilauta on kolmiulotteinen ruudukko, oli luontevaa soveltaa ortografista projektiota, eli heijastaa matriisikertolaskun avulla kolmiulotteiset koordinaatit kaksiulotteiselle näytölle. Matriisien käsittelyssä auttoi Jama.Matrix-kirjasto.

## Pelilogiikka

Pelilogiikassa on toteutettu kaikki vaadittavat metodit, jotka mahdollistavat pelin pelaamisen. Pelilogiikan metodeja on yritetty pitää mahdollisimman lyhyinä ja selkeinä, mikä vaatii pientä ylimääräistä työtä sovelluksen pääluokalta, joka näitä metodeja kutsuu, mutta kaikki pelilogiikkaan liittyvä tehdään kuitenkin täysin pelilogiikan omassa luokassa. Koodia on pyritty myös uudelleenkäyttämään mahdollisimman paljon, joten pelaajasta riippumatta kaikki käyttävät samoja metodeita omien nappuloidensa tarkistamiseen.

## Pelitulosten pysyväistallennus

Pelitulosten tallentamiseen ja niiden hakemiseen on käytetty paikallista SQL-tietokantaa. Tietokantaa käyttävä luokka *ScoreDao* noudattaa Data Access Object -suunnittelumallia. Käyttäjän on täysin mahdollista poistaa tietokantataulun sisältävä tiedosto *Scores.db* laitteeltaan. Sovelluksen käynnistämisen yhteydessä tiedoston puuttuminen johtaa sen uudelleenluomiseen ja tiedoston poistaminen sovelluksen avaamisen jälkeen johtaa pelitulosten näkymässä käyttäjäystävälliseen virheviestiin. Virheviestin yhteydessä tiedosto ja tietokantataulu luodaan uudelleen, jolloin tulosten tallentaminen on jälleen mahdollista.

## Päätoiminnallisuudet

### Uusi peli ja pelinappulan asettaminen

Esitetään seuraavaksi sekvenssikaavio, jossa kuvataan uuden pelin aloittamista pelin oletusasetuksilla ja yhden pelaajan ensimmäisen pelinappulan asettamista. Keskitytään ainoastaan pelilogiikkaan, jotta sekvenssikaavio ei täyty käyttöliittymään ja pelilaudan piirtämiseen liittyvistä metodeista. Jätetään myös pelitulosten tallennus huomiotta, sillä keskitymme siihen seuraavaksi tarkemmin.

<img src="https://raw.githubusercontent.com/pyigyli/ot-harjoitustyo/master/harjoitustyo/ConnectFour3D/dokumentaatio/kuvat/uusi_peli_sekvenssikaavio.png" width="750">

### Pelin päättyminen

Esitetään seuraavaksi sekvenssikaavio, jossa kuvataan 3 pelaajan pelin päättymistä vuorolla 16 (tai 15, sillä koodissa ensimmäinen vuoro on 0). Olkoon Pelaaja 1 voittaja, joka saa neljä pelinappulaansa pystysuoraan jonoon. Jätetään taas käyttöliittymään ja pelilaudan piirtämiseen liittyvät metodit pois sekvenssikaaviosta, jotta tulosten tallennuksen kannalta olennaiset metodit ovat helpommin seurattavissa.

<img src="https://raw.githubusercontent.com/pyigyli/ot-harjoitustyo/master/harjoitustyo/ConnectFour3D/dokumentaatio/kuvat/pelin_paattyminen_sekvenssikaavio.png" width="900">

## Ohjelman rakenteeseen jääneet heikkoudet

### Luokkien pituudet

Luokat App, Canvas ja Gamelogic ovat pitkiä. Kurssin edetessä Canvas ja Gamelogic ovat onneksi lyhentyneet huimasti. Tämä tekee niistä ehkä monimutkaisempia seurata, mutta useita metodeja on onnistuttu kokoamaan yhteen siten, että muutamia eroavaisuuksia samankaltaisten metodien välillä voidaan muuttaa parametrien avulla. Tätä ei kuitenkaan voi toteuttaa App-luokalle, joka lepää yli 700 rivin pituudessa.