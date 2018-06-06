package inteligenca;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import logika.Stirke;

public class Ocena {
	
	/**
	 * Ocenjuje pozicijo.
	 */
	
	//Vrednosti zmage, zgube ali neodločenega rezultata.
	
	public static final int ZMAGA = (1 << 20);
	public static final int ZGUBA = -ZMAGA;
	public static final int NEODLOCENO = 0;
	
	
	//Koliko je vredna stirka, ki ima k zasedenih polj enega igralca 
	public static int vrednostStirke(int k) {
		assert (k < 4);
		return (ZMAGA >> (4* (4 - k)));
	}
	
	public static int oceniPozicijo(Igralec jaz, Igra igra) {
		Igralec naPotezi = null;
		switch (igra.stanje()){
		case ZMAGA_RED:
			return (jaz == Igralec.RED ? ZMAGA : ZGUBA);
		case ZMAGA_BLUE:
			return (jaz == Igralec.BLUE ? ZMAGA : ZGUBA);
		case NEODLOCENO:
			return NEODLOCENO;
		case NA_VRSTI_RED:
			naPotezi = Igralec.RED;
		case NA_VRSTI_BLUE:
			naPotezi = Igralec.BLUE;
		}
		//Preštejemo število štirk vsakega igralca
		Polje[][] plosca = igra.getPlosca();
		int vrednostRED = 0;
		int vrednostBLUE = 0;
		for (Stirke s : Igra.stirke){
			int poljaRED = 0;
			int poljaBLUE = 0;
			for (int k = 0; k < 4 && (poljaRED == 0 || poljaBLUE == 0); k++) {
				//Gledamo koliko polj posamezne štirke zaseda posamezen igralec
				switch (plosca[s.x[k]][s.y[k]]) {
				case RED: poljaRED += 1; break;
				case BLUE: poljaBLUE += 1; break;
				case PRAZNO: break;
				}
			}
			if (poljaRED == 0 && poljaBLUE > 0) { vrednostBLUE += vrednostStirke(poljaBLUE); }
			if (poljaBLUE == 0 && poljaRED > 0) { vrednostRED += vrednostStirke(poljaRED); }
		}
		//if (naPotezi == Igralec.BLUE) { vrednostRED /= 4; }
		//if (naPotezi == Igralec.RED) { vrednostBLUE /= 4; }
		return (jaz == Igralec.BLUE ? vrednostBLUE - vrednostRED : vrednostRED - vrednostBLUE);
	}
}
