package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logika.Igra;
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
	
	// Še je polje od rdečega, ga pobarva rdeče in obratno.
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Polje[][] p = master.igra.plosca;
		int a = stranicaKvadrata();
		for (int i = 0; i < Igra.N; i++) {
			for (int j = 0; j < Igra.N;j++) {
				Color c = null;
				if (p[i][j] == Polje.RED) c = Color.RED;
				else if (p[i][j] == Polje.BLUE) c = Color.BLUE;
				if (c != null) {
					g2.setColor(c);
					g2.fillRect(j*a + 20 , i*a + 20, a, a);
				}
				g2.setColor(Color.BLACK);
				g2.drawRect(j*a + 20 , i*a + 20, a, a);
			}
		}
		if (master.igra.crta != null) {
			g2.setStroke(new BasicStroke(20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g.setColor(Color.YELLOW);
			int x1 = master.igra.crta.y[0]*a + 20 + a/2;
			int y1 = master.igra.crta.x[0]*a + 20 + a/2;
			int x2 = master.igra.crta.y[3]*a + 20 + a/2;
			int y2 = master.igra.crta.x[3]*a + 20 + a/2;
			g.drawLine(x1, y1, x2, y2);
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
