package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        Maksukortti kortti = new Maksukortti(123);
        assertEquals(123, kortti.saldo());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(123);
        assertEquals("saldo: 1.33", kortti.toString());
    }
    
    @Test
    public void kortiltaVoiOttaaRahaa() {
        Maksukortti kortti = new Maksukortti(123);
        kortti.otaRahaa(123);
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void saldoaEiSaaNegatiiviseksi() {
        Maksukortti kortti = new Maksukortti(123);
        kortti.otaRahaa(124);
        assertEquals("saldo: 1.23", kortti.toString());
    }
    
    @Test
    public void rahanRiittaessaPalauttaaTrue() {
        Maksukortti kortti = new Maksukortti(123);
        assertTrue(kortti.otaRahaa(123) == true);
    }
    
    @Test
    public void rahanEiRiittaessaPalauttaaFalse() {
        Maksukortti kortti = new Maksukortti(123);
        assertTrue(kortti.otaRahaa(124) == false);
    }
}
