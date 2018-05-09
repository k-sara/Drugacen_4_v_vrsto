package logika;

import java.util.LinkedList;
import java.util.List;

public class Igra {
	
	/*Velikost plosce je nxn*/
	
	public static final int N = 6;
	
	/* Atributi razreda Igra. */
	
	private Polje[][] plosca;
	private Igralec naPotezi;
	
	private static final List<Stirke> stirke = new LinkedList<Stirke>();
	
	/* Na zaèetku igre (ko se prviè požene igra oziroma program) se ustvari seznam vseh možnih štirk.*/
	
	{ /*Doloèimo možne smeri štirk*/
		int[][] smeri = {{1,0}, {0,1}, {1,1}, {1,-1}};
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				for (int[] smer : smeri){
					int dx = smer[0];
					int dy = smer[1];
					if ((0 <= x + (N - 1)*dx) && (0 <= y + (N -1)*dy) && (x + (N - 1)*dx < N) && (y + (N -1)*dy < N)) {
						/* Ustvari novi tabeli, ena s 4 x in druga s 4 y. */
						int[] stirka_x = new int[4];
						int[] stirka_y = new int[4];
						for (int k = 0; k < 4; k++) {
							/* Doda elemente v tabeli. */
							stirka_x[k] = x + dx*k;
							stirka_y[k] = y + dy*k;
						}
						/* V seznam štirk, ki se ustvari na zaèetku, doda nove štirke.*/
						stirke.add(new Stirke(stirka_x, stirka_y));
						
					}
				}
			}
		}
	}
	
	/* Ustvari novo igro s praznimi polji in doloèi, da igro zaène rdeèi. */
	
	public Igra() {
		plosca = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		naPotezi = Igralec.RED;
	}

	/*
	 * Metoda, ki preverja ali ima prazno polje nepraznega soseda. Èe polje ima nepraznega soseda funkcija osamljeni vrne true, sicer pa false.
	 */
	
	public boolean sosedi(int x, int y) {
		if (plosca[x][y] == Polje.PRAZNO){
			if (plosca[x+1][y] != Polje.PRAZNO || plosca[x-1][y] != Polje.PRAZNO || plosca[x][y+1] != Polje.PRAZNO || plosca[x+1][y-1] != Polje.PRAZNO){
				return true;
			}
		}
		return false;
	}

	/* Metoda, ki izraèuna možne poteze in vrne seznam teh. */

	public List<Poteza> poteze() {
		LinkedList<Poteza> seznampotez = new LinkedList<Poteza>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++){
				if (sosedi(i,j) == true){
				seznampotez.add(new Poteza(i,j));
				}
			}
		}
	return seznampotez;
	}
	
	/* Metoda, ki preverja, èe so vsa polja štirke enaka. Torej ali so vsa rdeèa oziroma modra. 
	 * Èe niso enaka vrne false, èe pa so vsa 4 polja enaka pa true. */
	
	private boolean vse_enake(Stirke s, Polje p) {
		for (int k = 0; k < 4; k++) {
			if (plosca[s.x[k]][s.y[k]] != p) {
				return false;
			}
		}
		return true;
	}
	
	/* Stanje igre */
	
	public Stanje stanje() {
		for (Stirke s : stirke) {
			/* Preveri èe je zmagal rdeèi */
			if (vse_enake(s, Polje.RED)) {
				Stanje stanje = Stanje.ZMAGA_RED;
			}
		}
	}
	
	/*
	 * Odigraj novo potezo
	 */
	
	public boolean odigrajpotezo (Poteza p) {
		return true;
	}
}
