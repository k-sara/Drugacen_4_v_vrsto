package gui;

import logika.Igralec;
import logika.Poteza;

public class Clovek extends Strateg {
	private GlavnoOkno master;
	private Igralec jaz;

	public Clovek(GlavnoOkno master, Igralec jaz) {
		super();
		this.master = master;
		this.jaz = jaz;
	}
	@Override
	public void na_potezi() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void prenehaj() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void klik(int i, int j) {
		master.odigraj(new Poteza(i, j));
		
	}

}
