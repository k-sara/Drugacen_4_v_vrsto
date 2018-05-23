package inteligenca;

import java.util.List;
import java.util.Random;

import javax.swing.SwingWorker;

import gui.GlavnoOkno;
import logika.Igra;
import logika.Poteza;

public class Nakljucno extends SwingWorker<Poteza, Object> {
	
	private GlavnoOkno master;

	public Nakljucno(GlavnoOkno master) {
		super();
		this.master = master;
	}

	@Override
	protected Poteza doInBackground() throws Exception {
		Igra igra = master.kopijaIgre();
		Random r = new Random();
		List<Poteza> poteze = igra.poteze();
		Poteza p = poteze.get(r.nextInt(poteze.size()));
		return p;
	}

	@Override
	protected void done() {
		try {
			Poteza p = this.get();
			if (p != null) {
				master.odigraj(p);
			}
		} catch (Exception e) {
		}
		super.done();
	}

}