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
	 * Možne poteze
	 */

	public List<Poteza> poteze() {
		LinkedList<Poteza> seznampotez = new LinkedList<Poteza>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++){
				if (plosca[i][j] == Polje.PRAZNO){
					if (plosca[i+1][j] != Polje.PRAZNO || plosca[i-1][j] != Polje.PRAZNO || plosca[i][j+1] != Polje.PRAZNO || plosca[i+1][j-1] != Polje.PRAZNO){
						seznampotez.add(new Poteza(i,j));
					}
				}
			}
		}
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
