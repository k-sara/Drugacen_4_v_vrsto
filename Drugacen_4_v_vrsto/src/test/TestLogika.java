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
	}
	
	public void odigraj7Potez() {
		Igra igra = new Igra();
		Poteza[] potekIgre = {
				new Poteza(2,3),
				new Poteza(2,2),
				// Tu manjkajo še poteze, ki peljejo do zmage modrega
		};
		for (Poteza p : potekIgre) { igra.odigrajPotezo(p); }
		assertEquals(Stanje.ZMAGA_BLUE, igra.stanje());
	}

}
