package inteligenca;

import java.util.List;
import java.util.Random;

import javax.swing.SwingWorker;

import gui.GlavnoOkno;
import logika.Igra;
import logika.Poteza;

public class Nakljucno extends SwingWorker<Poteza, Object> {
	
	private GlavnoOkno master;
	Random r;

	public Nakljucno(GlavnoOkno master) {
		super();
		this.master = master;
		r = new Random();
	}

	//Ta metoda se izvede v ozadju in naključno izbere eno potezo iz seznama možnih potez.
	
	@Override
	protected Poteza doInBackground() throws Exception {
		Igra igra = master.kopijaIgre();		
		List<Poteza> poteze = igra.poteze();
		int x = r.nextInt(poteze.size());
		Poteza poteza = poteze.get(x);
		return poteza;
	}

	//Ko konča prejšnja metoda, ta metoda odigra naključno izbrano potezo.
	
	@Override
	protected void done() {
		try {
			Poteza poteza = this.get();
			if (poteza != null) {
				master.odigraj(poteza);
			}
		} catch (Exception e) {
		}	
		super.done();
	}

}