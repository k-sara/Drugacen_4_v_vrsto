package gui;

/**
 * Objekt, ki zna odigrati potezo. Èlovek ali raèunalnik.
 */

public abstract class Strateg {

	/**
	* Glavno okno klièe to metodo, ko je strateg na potezi.
	*/
	public abstract void na_potezi();
		
	/**
	* Metoda, ki reèe strategu naj preneha igrati.
	*/
		
	public abstract void prenehaj();
	
	/**
	 * Glavno okno klièe to metodo, ko uporabnik klikne na polje (i,j).
	 */
	public abstract void klik(int i, int j);
	
}
