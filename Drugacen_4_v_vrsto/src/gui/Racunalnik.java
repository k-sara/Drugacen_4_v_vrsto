package gui;

import javax.swing.SwingWorker;

import logika.Igralec;
import logika.Poteza;
import inteligenca.Minimax;

public class Racunalnik extends Strateg {
	private GlavnoOkno master;
	private Igralec jaz;
	private SwingWorker<Poteza, Object> mislec;
	public Racunalnik(GlavnoOkno master, Igralec jaz) {
		super();
		this.master = master;
		this.jaz = jaz;
	}
	
	@Override
	public void na_potezi() {
		mislec = new Minimax(master, 3 , jaz);
		// Zažene swingworkerja
		mislec.execute();
	}
	
	@Override
	public void prenehaj() {
		if (mislec != null ) {
			mislec.cancel(true);		
		}
		
	}
	@Override
	public void klik(int i, int j) {
		
	}
	
	

}
