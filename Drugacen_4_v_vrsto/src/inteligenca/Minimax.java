
package inteligenca;

import java.util.LinkedList;
import java.util.Random;

import javax.swing.SwingWorker;

import gui.GlavnoOkno;
import logika.Igra;
import logika.Igralec;
import logika.Poteza;

public class Minimax extends SwingWorker<Poteza, Object>{
	
	private GlavnoOkno master;
	//Največja močna globina
	private int globina;
	//Kdo je rdeči in kdo modri
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
	
	
	@Override
	protected void done() {
		try {
			Poteza p = this.get();
			if (p != null) { master.odigraj(p); }
		} catch (Exception e) {
		}
		super.done();
	}

	// k je trenutna globina
	
	private OcenjenaPoteza minimax(int k, Igra igra) {
		//Igralca na potezi nastavimo na null
		Igralec naPotezi = null;
		//Ugotovimo kakšno je stanje igre (kdo je na potezi/kdo je zmagal/neodločeno)
		switch(igra.stanje()) {
		//Če je na vrsti eden izmed igralcev, igre še ni konec.
		case NA_VRSTI_RED: naPotezi = Igralec.RED; break;
		case NA_VRSTI_BLUE: naPotezi = Igralec.BLUE; break;
		//Če je zmagal eden izmed igralcev, je igre konec, zato ni veš močnih potez. Vrnemo vrednost pozicije.
		case ZMAGA_RED: 
			return new OcenjenaPoteza(null, (jaz == Igralec.RED ? Ocena.ZMAGA : Ocena.ZGUBA));
		case ZMAGA_BLUE: 
			return new OcenjenaPoteza(null, (jaz == Igralec.BLUE ? Ocena.ZMAGA : Ocena.ZGUBA));
		//Še je igre konec in je rezultat neodločen, ni več močnih potez. Vrnemo vrednost pozicije.
		case NEODLOCENO: 
			return new OcenjenaPoteza(null, Ocena.NEODLOCENO);
		}
		//Poteza ne sme biti null, vkolikor je se program ustavi.
		assert (naPotezi != null);
		//Če igre ni konec, je nekdo na potezi in moramo ugotoviti kako ugodno igrati naprej.
		if (k <= globina) {
			/*
			 * Če pridemo čez maksimalno globino ni okej - ne smemo več pregledovati.
			 * Zato vkolikor k ne presega maksimalne globine, vrnemo do sedaj najboljšo 
			 * predvideno potezo in njeno oceno.
			 */
			
			return new OcenjenaPoteza(null, Ocena.oceniPozicijo(jaz, igra));
		}
		// V spremenjlivki shranimo najboljšo potezo in oceno, ki sta na začetku nastavljenji na null in 0.
		
		int ocenaNajboljse = 0;
		//Seznam, v katerega shranjujemo najboljše poteze z enako oceno
		LinkedList<Poteza> najboljse = new LinkedList<Poteza>();
		// S for zanko se zapeljemo čez vse možne poteze in vrnemo najboljšo potezo in oceno.
		for (Poteza p : igra.poteze()) {
			//Ustvarimo kopijo igre, da ne povozimo obstoječe igre.
			Igra kopijaIgre = new Igra(igra);
			//Odigramo potezo v kopiji igre
			kopijaIgre.odigrajPotezo(p);
			//Pokličemo minimax na kopijo igre in globino povečamo za ena in želimo vrednost
			int ocenaPoteze = minimax(k+1, kopijaIgre).vrednost;
			// Če najboljše poteze še ni ali če smo našli boljšo jo shranimo kot najboljšo
			if (najboljse.size() == 0 
					|| (naPotezi == jaz && ocenaPoteze >= ocenaNajboljse) 
					|| (naPotezi != jaz && ocenaPoteze <= ocenaNajboljse)) {
				if (ocenaPoteze != ocenaNajboljse){
					najboljse.clear();
					ocenaNajboljse = ocenaPoteze;
				}
				najboljse.add(p);
			}
		}
		//
		assert (najboljse.size() != 0);
		Random r = new Random();
		int x = r.nextInt(najboljse.size());
		Poteza izbranaNajboljsa = najboljse.get(x);
		return new OcenjenaPoteza(izbranaNajboljsa,ocenaNajboljse);
	}
	
}
