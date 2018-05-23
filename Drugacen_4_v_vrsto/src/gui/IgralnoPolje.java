package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logika.Igra;
import logika.Igralec;
import logika.Polje;

@SuppressWarnings("serial")
public class IgralnoPolje extends JPanel implements MouseListener{
	
	public GlavnoOkno master;
	
	public IgralnoPolje (GlavnoOkno master) {
		setBackground(Color.white);
		this.master = master;
		this.addMouseListener(this);
	}
	
	// Velikost okna
	public Dimension getPreferredSize(){
		return new Dimension(600, 600);
	}
	
	//Velikost kvadratka (enega polja)
	private int stranicaKvadrata() {
		return (Math.min(getWidth(), getHeight()) - 40) / Igra.N;
	}
	
	// Èe je polje od rdeèega, ga pobarva rdeèe in obratno.
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Polje[][] p = master.igra.plosca;
		int a = stranicaKvadrata();
		for (int i = 0; i < Igra.N; i++) {
			for (int j = 0; j < Igra.N;j++) {
				Color c = null;
				if (p[i][j] == Polje.RED) c = Color.RED;
				else if (p[i][j] == Polje.BLUE) c = Color.BLUE;
				if (c != null) {
					g.setColor(c);
					g.fillRect(j*a + 20 , i*a + 20, a, a);
				}
				g.setColor(Color.BLACK);
				g.drawRect(j*a + 20 , i*a + 20, a, a);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int a = stranicaKvadrata();
		int i = (y - 20)/a ;
		int j = (x - 20)/a ;
		if (y < 20 || i >= Igra.N || x < 20 || j >= Igra.N) return ;
		master.klikniPolje(i, j);
		repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
