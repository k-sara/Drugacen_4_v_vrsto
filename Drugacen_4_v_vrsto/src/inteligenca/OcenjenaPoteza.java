package inteligenca;

import logika.Poteza;

public class OcenjenaPoteza {
	
	/**
	 * Ocenimo potezo glede na to kako dobra je.
	 * Lahko je tudi null, èe ni nobene možne poteze veè (npr. na koncu igre). V tem primeru oceni pozicijo.
	 */
	
	Poteza poteza;
	int vrednost;
	
	public OcenjenaPoteza(Poteza poteza, int vrednost) {
		super();
		this.poteza = poteza;
		this.vrednost = vrednost;
	}

	@Override
	public String toString() {
		return "OcenjenaPoteza [poteza=" + poteza + ", vrednost=" + vrednost + "]";
	}
	
	

}
