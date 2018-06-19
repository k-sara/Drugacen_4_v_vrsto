package logika;

import java.util.LinkedList;
import java.util.List;

public class Igra {
	
	/*Velikost plošče je nxn*/
	
	public static final int N = 6;
	
	/* Atributi razreda Igra. */
	
	public Polje[][] plosca;
	public Igralec naPotezi;
	public Stirke crta;
	
	public static final List<Stirke> stirke = new LinkedList<Stirke>();
	
	/* Na začetku igre (ko se prvič požene igra oziroma program) se ustvari seznam vseh možnih štirk.*/
	
	static { /*Določimo možne smeri štirk*/
		int[][] smeri = {{1,0}, {0,1}, {1,1}, {1,-1}};
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				for (int[] smer : smeri){
					int dx = smer[0];
					int dy = smer[1];
					// 3 je ker je (4 - 1)=3 
					if ((0 <= x + 3*dx) && (0 <= y + 3*dy) && (x + 3*dx < N) && (y + 3*dy < N)) {
						/* Ustvari novi tabeli, ena s 4 x in druga s 4 y. */
						int[] stirka_x = new int[4];
						int[] stirka_y = new int[4];
						for (int k = 0; k < 4; k++) {
							/* Doda elemente v tabeli. */
							stirka_x[k] = x + dx*k;
							stirka_y[k] = y + dy*k;
						}
						/* V seznam štirk, ki se ustvari na začetku, doda nove štirke.*/
						stirke.add(new Stirke(stirka_x, stirka_y));
						
					}
				}
			}
		}
	}
	
	/* Ustvari novo igro s praznimi polji in določi, da igro začne rdeči. */
	
	public Igra() {
		plosca = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		naPotezi = Igralec.RED;
		crta = null;
	}
	
	public Igra(Igra igra) {
		plosca = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = igra.plosca[i][j];
			}
		}
		this.naPotezi = igra.naPotezi;
		this.crta = igra.crta;
	}
	
	public Polje[][] getPlosca() {
		return plosca;
}

	/*
	 * Metoda, ki preverja ali je polje prazno in ima nepraznega soseda. če polje ima nepraznega soseda funkcija sosedi vrne true, sicer pa false.
	 */
	
	public boolean smemoIgrati(int x, int y) {
		return
		  (plosca[x][y] == Polje.PRAZNO) &&
		  ((x < N-1 && plosca[x+1][y] != Polje.PRAZNO) ||
		   (x > 0 && plosca[x-1][y] != Polje.PRAZNO) ||
		   (y < N-1 && plosca[x][y+1] != Polje.PRAZNO) ||
		   (y > 0 && plosca[x][y-1] != Polje.PRAZNO)		  
		  );
	}
	
	/*
	 * Metoda, ki preveri ali so vsa polja prazna. če so vrne true, sicer pa false.
	 */
	
	public boolean praznaPlosca() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++){
				if (plosca[i][j] != Polje.PRAZNO){
					return false;
				}
			}
		}
		return true;
	}

	/* Metoda, ki izračuna možne poteze in vrne seznam teh. */

	public List<Poteza> poteze() {
		LinkedList<Poteza> seznamPotez = new LinkedList<Poteza>();
		if (praznaPlosca()){
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++){
					seznamPotez.add(new Poteza(i,j));
				}
			}
		} else {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++){
					if (smemoIgrati(i,j)){
						seznamPotez.add(new Poteza(i,j));
						}
					}
				}
		}
		return seznamPotez;
	}
	
	/* Metoda, ki preverja, če so vsa polja štirke enaka. Torej ali so vsa rdeča oziroma modra. 
	 * Če niso enaka vrne false, če pa so vsa 4 polja enaka pa true. */
	
	private boolean vse_enake(Stirke s, Polje p) {
		for (int k = 0; k < 4; k++) {
			if (plosca[s.x[k]][s.y[k]] != p) {
				return false;
			}
		}
		return true;
	}
	
	/* 
	 * Stanje igre - preveri ali je zmagal rdeči, modri, je neodločeno ali pa igre še ni konec.
	 */
	
	public Stanje stanje() {
		for (Stirke s : stirke) {
			/* Preveri, če je zmagal rdeči in če je nastavi zmagovalno štirko. */
			if (vse_enake(s, Polje.RED)) {
				Stanje stanje = Stanje.ZMAGA_RED;
				stanje.setZmagovalna(s);
				return stanje;
			} 
			/* Preveri;
			 *Preveri, če je zmagal modri in če je nastavi zmagovalno štirko. */
			else if (vse_enake(s, Polje.BLUE)) {
				Stanje stanje = Stanje.ZMAGA_BLUE;
				stanje.setZmagovalna(s);
				return stanje;
				
			}
		}
		/* Preverimo, če je kakšno polje prazno, vkolikor je, igre še ni konec. */
		for (int i = 0; i < N; i++ ) {
			for (int j = 0; j < N; j++) {
				if (plosca[i][j] == Polje.PRAZNO) {
					if (naPotezi == Igralec.RED) {
						return Stanje.NA_VRSTI_RED;
					} else {
						return Stanje.NA_VRSTI_BLUE;
					}
				}
			}
		}
		/* Če ni zmagal noben igralec in so vsa polja zasedena, vrne neodločen rezultat. */
		return Stanje.NEODLOCENO;
	}
	
	/*
	 * Odigraj novo potezo pogleda, če je odigrana poteza ustrezna. 
	 * Če je vrne true in shrani polje ustreznega igralca in nastavi, da je na potezi nasprotni igralec.
	 * Če poteza ni ustrezna, vrne false.
	 */
	
	public boolean odigrajPotezo (Poteza p) {
		List<Poteza> k = poteze();
		if (k.contains(p)) {
			plosca[p.getX()][p.getY()] = naPotezi.getPolje();
			naPotezi = naPotezi.nasprotnik();
			return true;
		} else {
			return false;
		}
	}
	
	public int steviloPotez() {
		int x = 0;
		for (int i = 0; i < N; i++ ) {
			for (int j = 0; j < N; j++) {
				if (plosca[i][j] != Polje.PRAZNO) {
					x += 1;
				}
			}
		}
		return x;
	}
}
	
