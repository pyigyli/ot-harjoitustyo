
package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    Kassapaate kassa;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }

    @Test
    public void luodussaKassassaOikeaSaldo() {
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoEdullisestiLisaaKassaan() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateisostoEdullisestiLisaaLounasmaaraa() {
        kassa.syoEdullisesti(1000);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoEdullisestiHylattyLiianPienellaSummalla() {
        kassa.syoEdullisesti(239);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateisostoMaukkaastiLisaaKassaan() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateisostoMaukkaastiLisaaLounasmaaraa() {
        kassa.syoMaukkaasti(1000);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoMaukkaastiHylattyLiianPienellaSummalla() {
        kassa.syoMaukkaasti(399);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void korttiostoEdullisestiEiLisaaKassaan() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void korttiostoEdullisestiLisaaLounasmaaraa() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoMaukkaastiEiLisaaKassaan() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void korttiostoMaukkaastiLisaaLounasmaaraa() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostosEdullisestiOttaaKortiltaRahaa() {
        Maksukortti kortti = new Maksukortti(240);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void korttiostosMaukkaastiOttaaKortiltaRahaa() {
        Maksukortti kortti = new Maksukortti(400);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void korttiostosEdullisestiPalauttaaTrue() {
        Maksukortti kortti = new Maksukortti(240);
        assertTrue(kassa.syoEdullisesti(kortti) == true);
    }
    
    @Test
    public void korttiostosMaukkaastiPalauttaaTrue() {
        Maksukortti kortti = new Maksukortti(400);
        assertTrue(kassa.syoMaukkaasti(kortti) == true);
    }
    
    @Test
    public void korttiostosEdullisestiPalauttaaFalse() {
        Maksukortti kortti = new Maksukortti(239);
        assertTrue(kassa.syoEdullisesti(kortti) == false);
    }
    
    @Test
    public void korttiostosMaukkaastiPalauttaaFalse() {
        Maksukortti kortti = new Maksukortti(399);
        assertTrue(kassa.syoMaukkaasti(kortti) == false);
    }
    
    @Test
    public void korttiostoEdullisestiEiOtaKortilta() {
        Maksukortti kortti = new Maksukortti(239);
        kassa.syoEdullisesti(kortti);
        assertEquals(239, kortti.saldo());
    }
    
    @Test
    public void korttiostoMaukkaastiEiOtaKortilta() {
        Maksukortti kortti = new Maksukortti(399);
        kassa.syoMaukkaasti(kortti);
        assertEquals(399, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKortilleToimii() {
        Maksukortti kortti = new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, 10);
        assertEquals(10, kortti.saldo());
        assertEquals(100010, kassa.kassassaRahaa());
    }
    
    @Test
    public void rahanLataaminenKortilleNegatiivisellaEiToimi() {
        Maksukortti kortti = new Maksukortti(10);
        kassa.lataaRahaaKortille(kortti, -10);
        assertEquals(10, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
