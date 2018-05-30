
package inteligenca;

import javax.swing.SwingWorker;

import gui.GlavnoOkno;
import logika.Igra;
import logika.Igralec;
import logika.Poteza;
import logika.Stanje;

public class Minimax extends SwingWorker<Poteza, Object>{
	
	private GlavnoOkno master;
	//Najve�ja mo�na globina
	private int globina;
	//Kdo je rde�i in kdo modri
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
		//Ugotovimo kak�no je stanje igre (kdo je na potezi/kdo je zmagal/neodlo�eno)
		switch(igra.stanje()) {
		//�e je na vrsti eden izmed igralcev, igre �e ni konec.
		case NA_VRSTI_RED: naPotezi = Igralec.RED; break;
		case NA_VRSTI_BLUE: naPotezi = Igralec.BLUE; break;
		//�e je zmagal eden izmed igralcev, je igre konec, zato ni ve� mo�nih potez. Vrnemo vrednost pozicije.
		case ZMAGA_RED: 
			return new OcenjenaPoteza(null, (jaz == Igralec.RED ? Ocena.ZMAGA : Ocena.ZGUBA));
		case ZMAGA_BLUE: 
			return new OcenjenaPoteza(null, (jaz == Igralec.BLUE ? Ocena.ZMAGA : Ocena.ZGUBA));
		//�e je igre konec in je rezultat neodlo�en, ni ve� mo�nih potez. Vrnemo vrednost pozicije.
		case NEODLOCENO: 
			return new OcenjenaPoteza(null, Ocena.NEODLOCENO);
		}
		//Poteza ne sme biti null, vkolikr je se program ustavi.
		assert (naPotezi != null);
		//�e igre ni konec, je nekdo na potezi in moramo ugotoviti kako ugodno igrati naprej.
		if (k <= globina) {
			/*
			 * �e pridemo �ez maksimalno globino ni okej - ne smemo ve� pregledovati.
			 * Zato vkolikor k ne presega maksimalne globine, vrnemo do sedaj najbolj�o 
			 * predvideno potezo in njeno oceno.
			 */
			
			return new OcenjenaPoteza(null, Ocena.oceniPozicijo(jaz, igra));
		}
		// V spremenjlivki shranimo najbolj�o potezo in oceno, ki sta na za�etku nastavljenji na null in 0.
		
		//? morda bolj�e imeti seznam najbolj�ih potez in potem naklju�no izbrati eno (vse imajo enako oceno)
		Poteza najboljsa = null;
		int ocenaNajboljse = 0;
		// S for zanko se zapeljemo �ez vse mo�ne poteze in vrnemo najbolj�o potezo in oceno.
		for (Poteza p : igra.poteze()) {
			//Ustvarimo kopijo igre, da ne povozimo obstoje�e igre.
			Igra kopijaIgre = new Igra(igra);
			//Odigramo potezo v kopiji igre
			kopijaIgre.odigrajPotezo(p);
			//Pokli�emo minimax na kopijo igre in globino pove�amo za ena in �elimo vrednost
			int OcenaPoteze = minimax(k+1, kopijaIgre).vrednost;
			// �e najbolj�e poteze �e ni ali �e smo na�li bolj�o jo shranimo kot najbolj�o
			if (najboljsa == null 
					|| (naPotezi == jaz && OcenaPoteze > ocenaNajboljse) 
					|| (naPotezi != jaz && OcenaPoteze < ocenaNajboljse)) {
				najboljsa = p;
				ocenaNajboljse = OcenaPoteze;
			}
			
			
		}
	}
	
}
