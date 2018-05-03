package logika;

import java.util.LinkedList;
import java.util.List;

public class Igra {
	
	/*Velikost plosce je nxn*/
	
	public static final int N = 6;
	
	private Polje[][] plosca;
	private Igralec naPotezi;
	
	
	
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
			}else {
				return false;
			}
		}
	}

	/* 
	 * Možne poteze
	 */

	public List<Poteza> poteze() {
		LinkedList<Poteza> seznampotez = new LinkedList<Poteza>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++){
				if sosedi
				seznampotez.add(new Poteza(i,j));
	return seznampotez;
	}

	/*
	 * Stanje igre
	 */
	
	/*
	 * Odigraj novo potezo
	 */
	
	public boolean odigrajpotezo (Poteza p) {
		return true;
	}
}
