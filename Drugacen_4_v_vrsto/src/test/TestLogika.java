package test;

import java.util.List;

import junit.framework.TestCase;

import logika.Igra;
import logika.Stanje;
import logika.Poteza;

public class TestLogika extends TestCase {
	
	public void testIgra() {
		Igra igra = new Igra();

		// Preveri �e je res na za�etku na vrsti rde�i.
		assertEquals(Stanje.NA_VRSTI_RED, igra.stanje());

		// Preveri �e je plo��a res prazna.
		assertEquals(igra.praznaPlosca(), true);
		// Na začetku imamo na voljo N * N potez
		List<Poteza> poteze = igra.poteze();
		assertEquals(Igra.N * Igra.N, poteze.size());

		// odigramo prvo potezo
		igra.odigrajPotezo(poteze.get(0));
		assertEquals(igra.praznaPlosca(), false);

		// preveri eno igro, v kateri zmaga modri
		odigraj7Potez();
		
		//System.out.println(Igra.stirke);
		
		System.out.println(odigrajNapacnoPotezo());
	}
	
	public void odigraj7Potez() {
		Igra igra = new Igra();
		Poteza[] potekIgre = {
				new Poteza(2,0),
				new Poteza(2,1),
				new Poteza(3,0),
				new Poteza(2,2),
				new Poteza(3,1),
				new Poteza(2,3),
				new Poteza(1,1),
				new Poteza(2,4),
		};
		for (Poteza p : potekIgre) { igra.odigrajPotezo(p); }
		assertEquals(Stanje.ZMAGA_BLUE, igra.stanje());
	}

	public boolean odigrajNapacnoPotezo() {
		Igra igra = new Igra();
		igra.odigrajPotezo(	new Poteza(2,0)) ; 
		return igra.smemoIgrati(4,4);
	}
	
}
