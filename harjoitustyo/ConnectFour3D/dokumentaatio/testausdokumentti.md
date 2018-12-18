# Testausdokumentti

Ohjelmaan on luotu automatisoituja JUnit testejä, jotka testaavat kattavasti sovelluslogiikkaa ja suuren osan pelitulosten tallennuksesta vastaavasta koodista. Sovelluksen kaikki toiminnot on myös testattu manuaalisesti.

### Käyttöliittymä

Pelilaudan visuaalisesta esityksestä vastaavalle luokalle ei ole kirjoitettu yhtään testiä, vaikka suurimman osan *Canvas*-luokan metodeista olisi voinut testata. Tämä tuntuu kuitenkin turhalta, kun luokan tarkoitus on piirtää ruudulle visuaalisia elementtejä. Vaikka olisi helppoa luoda testi metodeille, jotka kokoavat kolmiulotteisen pelilaudan pisteet matriisiin ja muuttavat kolmiulotteiset pisteet kaksiulotteisiksi, lopullinen metodi olisi kuitenkin järkevintä testata manuaalisesti avaamalla sovellus ja katsomalla itse, piirtääkö sovellus pelilaudan näytölle halutulla tavalla. Tästä johtuen koko luokan testaaminen päätettiin toteutettaa manuaalisesti. Käyttöliittymälle ei siis ole kirjoitettu yhtäkään testiä ja se on jätetty testikattavuusraportin ulkopuolelle. Mahdollisuus valita pelaajille haluamansa nimi on ainoa syöte, jonka käyttäjä voi "vapaasti" lähettää sovellukseen. Tämä syöte on validoitu rajoittamalla sen pituudeksi 1-10 merkkiä.

### Sovelluslogiikka

Sovelluslogiikalle on luotu automaattisia JUnit testejä, jotka kattavat kaiken, mitä luokasta löytää. Myös pelilogiikka on saanut suuren määrän manuaalista testaamista. Sovelluslogiikan testeissä on keskitytty tarkistamaan, että sovelluksen logiikka seuraa pelin sääntöjä.

### Tietokanta ja DAO-luokka

Tietokantaa käyttäviä luokkia testataan tilapäisellä luokalla, joka luodaan testien ajaksi ja poistetaan heti testien päätyttyä. Väliaikaisen tiedoston nimi on oletusarvoisesti *"TestScores0.db"*, mutta nimen numero voi muuttua, mikäli kansiossa on jo olemassa tämän niminen tiedosto. Tällä halutaan varmistaa, ettei testien ajaminen vahingossa poista käyttäjältä muita tiedostoja.

## Testikattavuus

<img src="https://raw.githubusercontent.com/pyigyli/ot-harjoitustyo/master/harjoitustyo/ConnectFour3D/dokumentaatio/kuvat/testikattavuus.png">

Käyttöliittymän luokkia lukuunottamatta sovelluksen testikattavuuden rivikattavuus on 97% ja haarautumakattavuus 100%. Kolme puuttuvaa prosenttia tulevat Dao-rajapinnan ylikirjoitettavista metodeista, joita luokka *ScoreDao* ei edes käytä.

<img src="https://raw.githubusercontent.com/pyigyli/ot-harjoitustyo/master/harjoitustyo/ConnectFour3D/dokumentaatio/kuvat/kattamattomat_metodit.png">