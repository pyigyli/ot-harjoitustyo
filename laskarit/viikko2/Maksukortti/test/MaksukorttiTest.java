/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pyigyli
 */
public class MaksukorttiTest {
    
    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        Maksukortti kortti = new Maksukortti(10);       
        assertEquals("Kortilla on rahaa 10.0 euroa",  kortti.toString());
    }
    
    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
        Maksukortti kortti = new Maksukortti(10);

        kortti.syoEdullisesti();

        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        Maksukortti kortti = new Maksukortti(10);

        kortti.syoMaukkaasti();

        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }

    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        Maksukortti kortti = new Maksukortti(10);

        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        // nyt kortin saldo on 2
        kortti.syoEdullisesti();

        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }

    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200);
        assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        Maksukortti kortti = new Maksukortti(8);

        kortti.syoEdullisesti();
        kortti.syoEdullisesti();
        // nyt kortin saldo on 3
        kortti.syoMaukkaasti();

        assertEquals("Kortilla on rahaa 3.0 euroa", kortti.toString());
    }
    
    @Test
    public void negatiivisenSummanLataaminenEiMuutaKortinSaldoa() {
        kortti.lataaRahaa(-1);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    
    @Test
    public void edullisenLounaanOstaminenMahdollistaEdullisenLounaanSaldolla() {
        Maksukortti kortti = new Maksukortti(2.5);

        kortti.syoEdullisesti();
        
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
    }
    
    @Test
    public void maukkaanLounaanOstaminenMahdollistaMaukkaanLounaanSaldolla() {
        Maksukortti kortti = new Maksukortti(4);

        kortti.syoMaukkaasti();
        
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
    }
}
