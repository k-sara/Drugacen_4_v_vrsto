package gui;

/**
 * Objekt, ki zna odigrati potezo. človek ali računalnik.
 */

public abstract class Strateg {

	/**
	* Glavno okno kliče to metodo, ko je strateg na potezi.
	*/
	public abstract void na_potezi();
		
	/**
	* Metoda, ki reče strategu naj preneha igrati.
	*/
		
	public abstract void prenehaj();
	
	/**
	 * Glavno okno kliče to metodo, ko uporabnik klikne na polje (i,j).
	 */
	public abstract void klik(int i, int j);
	
}
