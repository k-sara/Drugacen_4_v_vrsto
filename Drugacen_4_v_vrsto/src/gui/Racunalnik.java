package gui;

import javax.swing.SwingWorker;

import logika.Igralec;
import logika.Poteza;
import inteligenca.Nakljucno;

public class Racunalnik extends Strateg {
	private GlavnoOkno master;
	private SwingWorker<Poteza, Object> mislec;
	private boolean prenehaj;
	
	public Racunalnik(GlavnoOkno master, Igralec jaz) {
		super();
		this.master = master;
	}
	
	@Override
	public void na_potezi() {
		mislec = new Nakljucno(master);
		
		
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
