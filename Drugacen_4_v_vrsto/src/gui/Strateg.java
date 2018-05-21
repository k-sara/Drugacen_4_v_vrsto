package gui;

/**
 * Objekt, ki zna odigrati potezo. �lovek ali ra�unalnik.
 */

public abstract class Strateg {

	/**
	* Glavno okno kli�e to metodo, ko je strateg na potezi.
	*/
	public abstract void na_potezi();
		
	/**
	* Metoda, ki re�e strategu naj preneha igrati.
	*/
		
	public abstract void prenehaj();
	
	/**
	 * Glavno okno kli�e to metodo, ko uporabnik klikne na polje (i,j).
	 */
	public abstract void klik(int i, int j);
	
}
