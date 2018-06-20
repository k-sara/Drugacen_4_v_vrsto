package inteligenca;

import java.util.List;

import javax.swing.SwingWorker;
import gui.GlavnoOkno;
import logika.Igra;
import logika.Igralec;
import logika.Poteza;

public class AlphaBeta extends SwingWorker<Poteza, Object>  {

	private GlavnoOkno master;
	private int globina;
	private Igralec jaz;

	public AlphaBeta(GlavnoOkno master, int globina, Igralec jaz) {
		this.master = master;
		this.globina = globina;
		this.jaz = jaz;
	}

	@Override
	protected Poteza doInBackground() throws Exception {
		Igra igra = master.kopijaIgre();
		OcenjenaPoteza p = alphaBeta(igra, globina, -100000000, 100000000);
		assert (p.poteza != null);
		return p.poteza;
	}

	@Override
	public void done() {
		try {
			Poteza p = this.get();
			if (p != null) {
				master.odigraj(p);
			}
		} 
		catch (Exception e) {
		}
	}

	private OcenjenaPoteza alphaBeta(Igra igra, int globina, int alpha, int beta) {
		Igralec naPotezi = null;
		int stPotez = igra.steviloPotez();
		switch (igra.stanje()) {
		case NA_VRSTI_RED: naPotezi = Igralec.RED; break;
		case NA_VRSTI_BLUE: naPotezi = Igralec.BLUE; break;
		case ZMAGA_RED: 
			return new OcenjenaPoteza(null, (jaz == Igralec.RED ? (Ocena.ZMAGA - stPotez) : (Ocena.ZGUBA + stPotez)));
		case ZMAGA_BLUE: 
			return new OcenjenaPoteza(null, (jaz == Igralec.BLUE ? (Ocena.ZMAGA - stPotez) : (Ocena.ZGUBA + stPotez)));
		case NEODLOCENO: 
			return new OcenjenaPoteza(null, Ocena.NEODLOCENO);
		}

		if (globina == 0) {
			return new OcenjenaPoteza(null, Ocena.oceniPozicijo(jaz, igra));
		}

		Poteza najboljsaPoteza = null;
		List<Poteza> moznePoteze = igra.poteze();

		if (naPotezi == jaz) { 
			int najboljsaOcena = -1000000000;

			for (Poteza poteza : moznePoteze) {
				Igra kopijaIgre = new Igra(igra);
				kopijaIgre.odigrajPotezo(poteza);
				int ocenaPoteze = alphaBeta(kopijaIgre, globina - 1, alpha, beta).vrednost;
				if (najboljsaOcena < ocenaPoteze) {
					najboljsaOcena = ocenaPoteze;
					najboljsaPoteza = poteza;
				}
				alpha = Math.max(alpha, najboljsaOcena);
				if (beta <= alpha) {
					break;
				}
			}
			return new OcenjenaPoteza(najboljsaPoteza, najboljsaOcena);
		} else { 
			int najboljsaOcena = 100000000;
			for (Poteza poteza : moznePoteze) {
				Igra kopijaIgre = new Igra(igra);
				kopijaIgre.odigrajPotezo(poteza);

				int ocenaPoteze = alphaBeta(kopijaIgre, globina - 1, alpha, beta).vrednost;

				if (najboljsaOcena > ocenaPoteze) {
					najboljsaOcena = ocenaPoteze;
					najboljsaPoteza = poteza;
				}
				beta = Math.min(beta, najboljsaOcena);
				if (beta <= alpha) {
					break;
				}
			}
			return new OcenjenaPoteza(najboljsaPoteza, najboljsaOcena);
		}
	}
}