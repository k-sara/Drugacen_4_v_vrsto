
package inteligenca;

import javax.swing.SwingWorker;

import gui.GlavnoOkno;
import logika.Igra;
import logika.Igralec;
import logika.Poteza;
import logika.Stanje;

public class Minimax extends SwingWorker<Poteza, Object>{
	
	private GlavnoOkno master;
	//Najveèja možna globina
	private int globina;
	//Kdo je rdeèi in kdo modri
	private Igralec jaz;

	public Minimax(GlavnoOkno master, int globina, Igralec jaz) {
		super();
		this.master = master;
		this.globina = globina;
		this.jaz = jaz;
	}

	@Override
	protected Poteza doInBackground() throws Exception {
		Igra igra = master.kopijaIgre();
		OcenjenaPoteza p = minimax(0, igra);
		assert (p.poteza != null);
		return p.poteza;
	}
	
	// k je trenutna globina
	
	private OcenjenaPoteza minimax(int k, Igra igra) {
		//Igralca na potezi nastavimo na null
		Igralec naPotezi = null;
		//Ugotovimo kakšno je stanje igre (kdo je na potezi/kdo je zmagal/neodloèeno)
		switch(igra.stanje()) {
		//Èe je na vrsti eden izmed igralcev, igre še ni konec.
		case NA_VRSTI_RED: naPotezi = Igralec.RED; break;
		case NA_VRSTI_BLUE: naPotezi = Igralec.BLUE; break;
		//Èe je zmagal eden izmed igralcev, je igre konec, zato ni veè možnih potez. Vrnemo vrednost pozicije.
		case ZMAGA_RED: 
			return new OcenjenaPoteza(null, (jaz == Igralec.RED ? Ocena.ZMAGA : Ocena.ZGUBA));
		case ZMAGA_BLUE: 
			return new OcenjenaPoteza(null, (jaz == Igralec.BLUE ? Ocena.ZMAGA : Ocena.ZGUBA));
		//Èe je igre konec in je rezultat neodloèen, ni veè možnih potez. Vrnemo vrednost pozicije.
		case NEODLOCENO: 
			return new OcenjenaPoteza(null, Ocena.NEODLOCENO);
		}
		//Poteza ne sme biti null, vkolikr je se program ustavi.
		assert (naPotezi != null);
		//Èe igre ni konec, je nekdo na potezi in moramo ugotoviti kako ugodno igrati naprej.
		if (k <= globina) {
			/*
			 * Èe pridemo èez maksimalno globino ni okej - ne smemo veè pregledovati.
			 * Zato vkolikor k ne presega maksimalne globine, vrnemo do sedaj najboljšo 
			 * predvideno potezo in njeno oceno.
			 */
			
			return new OcenjenaPoteza(null, Ocena.oceniPozicijo(jaz, igra));
		}
		// V spremenjlivki shranimo najboljšo potezo in oceno, ki sta na zaèetku nastavljenji na null in 0.
		
		//? morda boljše imeti seznam najboljših potez in potem nakljuèno izbrati eno (vse imajo enako oceno)
		Poteza najboljsa = null;
		int ocenaNajboljse = 0;
		// S for zanko se zapeljemo èez vse možne poteze in vrnemo najboljšo potezo in oceno.
		for (Poteza p : igra.poteze()) {
			//Ustvarimo kopijo igre, da ne povozimo obstojeèe igre.
			Igra kopijaIgre = new Igra(igra);
			//Odigramo potezo v kopiji igre
			kopijaIgre.odigrajPotezo(p);
			//Poklièemo minimax na kopijo igre in globino poveèamo za ena in želimo vrednost
			int OcenaPoteze = minimax(k+1, kopijaIgre).vrednost;
			// Èe najboljše poteze še ni ali èe smo našli boljšo jo shranimo kot najboljšo
			if (najboljsa == null 
					|| (naPotezi == jaz && OcenaPoteze > ocenaNajboljse) 
					|| (naPotezi != jaz && OcenaPoteze < ocenaNajboljse)) {
				najboljsa = p;
				ocenaNajboljse = OcenaPoteze;
			}
			
			
		}
	}
	
}
