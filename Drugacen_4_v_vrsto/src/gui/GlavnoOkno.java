package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import logika.Poteza;
import logika.Stanje;



@SuppressWarnings("serial")
public class GlavnoOkno extends JFrame implements ActionListener {
	
	private IgralnoPolje polje;
	private JLabel status;
	public Igra igra;
	private Strateg strategRED;
	private Strateg strategBLUE;
	
	public int tezavnost;
	
	//Meni - izbire
	private JMenuItem igraClovekClovek;
	private JMenuItem igraClovekRacunalnik;
	private JMenuItem igraRacunalnikClovek;
	private JMenuItem igraRacunalnikRacunalnik;
	private JMenuItem tezavnost1;
	private JMenuItem tezavnost2;
	private JMenuItem tezavnost3;
	private JMenuItem tezavnost1b;
	private JMenuItem tezavnost2b;
	private JMenuItem tezavnost3b;
	
	//Ustvari glavno okno.
	public GlavnoOkno() {
		this.setTitle("Drugačen 4-v-vrsto");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		//menu
		JMenuBar menu_bar = new JMenuBar();
		this.setJMenuBar(menu_bar);
		JMenu igra_menu = new JMenu("Igra");
		menu_bar.add(igra_menu);
		

		JMenu igraClovekRacunalnik = new JMenu("Človek - računalnik");
		igra_menu.add(igraClovekRacunalnik);
		igraClovekRacunalnik.addActionListener(this);
		
		JMenu igraRacunalnikClovek = new JMenu("Računalnik - človek");
		igra_menu.add(igraRacunalnikClovek);
		igraRacunalnikClovek.addActionListener(this);

		igraRacunalnikRacunalnik = new JMenuItem("Računalnik - računalnik");
		igra_menu.add(igraRacunalnikRacunalnik);
		igraRacunalnikRacunalnik.addActionListener(this);

		igraClovekClovek = new JMenuItem("Človek - človek");
		igra_menu.add(igraClovekClovek);
		igraClovekClovek.addActionListener(this);
		
		tezavnost1 = new JMenuItem("Lahka");
		igraClovekRacunalnik.add(tezavnost1);
		tezavnost1.addActionListener(this);
		
		tezavnost2 = new JMenuItem("Srednja");
		igraClovekRacunalnik.add(tezavnost2);
		tezavnost2.addActionListener(this);
		
		tezavnost3 = new JMenuItem("Težka");
		igraClovekRacunalnik.add(tezavnost3);
		tezavnost3.addActionListener(this);
		
		tezavnost1b = new JMenuItem("Lahka");
		igraRacunalnikClovek.add(tezavnost1b);
		tezavnost1b.addActionListener(this);
		
		tezavnost2b = new JMenuItem("Srednja");
		igraRacunalnikClovek.add(tezavnost2b);
		tezavnost2b.addActionListener(this);
		
		tezavnost3b = new JMenuItem("Težka");
		igraRacunalnikClovek.add(tezavnost3b);
		tezavnost3b.addActionListener(this);
		
		tezavnost = 6;

		//Definiraj igralno polje
		polje = new IgralnoPolje(this);
		GridBagConstraints polje_layout = new GridBagConstraints();
		polje_layout.gridx = 0;
		polje_layout.gridy = 0;
		polje_layout.fill = GridBagConstraints.BOTH;
		polje_layout.weightx = 1.0;
		polje_layout.weighty = 1.0;
		getContentPane().add(polje, polje_layout);
		
		//Statusna vrstica
		status = new JLabel();
		status.setFont(new Font(status.getFont().getName(), status.getFont().getStyle(), 20));
		GridBagConstraints status_layout = new GridBagConstraints();
		status_layout.gridx = 0;
		status_layout.gridy = 1;
		status_layout.anchor = GridBagConstraints.CENTER;
		getContentPane().add(status, status_layout);
		
		//Začnemo novo igro človeka proti človeku
		novaIgra(new Clovek(this, Igralec.RED), new Clovek(this, Igralec.BLUE));
}
	
		public Polje[][] getPlosca() {
			return (igra == null ? null : igra.getPlosca());
		}
	
	private void novaIgra(Strateg noviStrategRED, Strateg noviStrategBLUE) {
		//Prekinemo stare stratege
		if (strategRED != null) { strategRED.prenehaj();}
		if (strategBLUE != null) { strategBLUE.prenehaj();}
		//Ustvari novo igro in stratege
		this.igra = new Igra();
		strategRED = noviStrategRED;
		strategBLUE = noviStrategBLUE;
		//Povemo kdo je na potezi
		switch (igra.stanje()) {
		case NA_VRSTI_RED: strategRED.na_potezi(); break;
		case NA_VRSTI_BLUE: strategBLUE.na_potezi(); break;
		default: break;
		}
		osveziGUI();
		repaint();
	}
		
	public void odigraj(Poteza p) {
		igra.odigrajPotezo(p);
		Stanje s = igra.stanje();
		switch (s) {
			case NA_VRSTI_RED: strategRED.na_potezi(); break;
			case NA_VRSTI_BLUE: strategBLUE.na_potezi(); break;
			case ZMAGA_RED: igra.crta = s.getZmagovalna(); break;
			case ZMAGA_BLUE: igra.crta = s.getZmagovalna(); break;
			case NEODLOCENO: break;
		}
		osveziGUI();
	}
		
	public void osveziGUI() {
		if (igra == null) {
			status.setText("Igra ni v teku.");
		}
		else {
			switch(igra.stanje()) {
				case NA_VRSTI_RED: status.setText("Na potezi je rdeči"); break;
				case NA_VRSTI_BLUE: status.setText("Na potezi je modri"); break;
				case ZMAGA_RED: status.setText("Zmagal je rdeči"); break;
				case ZMAGA_BLUE: status.setText("Zmagal je modri"); break;
				case NEODLOCENO: status.setText("Neodločeno!"); break;
			}
		}
		polje.repaint();
	}

	
	
	//Glede na to kdo igra proti komu, nastavi karteri igralec je rdeči in katreri je modri.
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tezavnost1) {
			tezavnost = 1;
			novaIgra(new Clovek(this, Igralec.RED), new Racunalnik(this, Igralec.BLUE));
		}
		else if (e.getSource() == tezavnost2) {
			tezavnost = 3;
			novaIgra(new Clovek(this, Igralec.RED), new Racunalnik(this, Igralec.BLUE));
		}
		else if (e.getSource() == tezavnost3) {
			tezavnost = 8;
			novaIgra(new Clovek(this, Igralec.RED), new Racunalnik(this, Igralec.BLUE));
		}
		else if (e.getSource() == tezavnost1b) {
			tezavnost = 1;
			novaIgra(new Racunalnik(this, Igralec.RED), new Clovek(this, Igralec.BLUE));
		}
		else if (e.getSource() == tezavnost2b) {
			tezavnost = 3;
			novaIgra(new Racunalnik(this, Igralec.RED), new Clovek(this, Igralec.BLUE));
		}
		else if (e.getSource() == tezavnost3b) {
			tezavnost = 8;
			novaIgra(new Racunalnik(this, Igralec.RED), new Clovek(this, Igralec.BLUE));
		}
		else if (e.getSource() == igraRacunalnikRacunalnik) {
			novaIgra(new Racunalnik(this, Igralec.RED),
					  new Racunalnik(this, Igralec.BLUE));
		}
		else if (e.getSource() == igraClovekClovek) {
			novaIgra(new Clovek(this, Igralec.RED),
			          new Clovek(this, Igralec.BLUE));
		}
		
	}
	
	//Ko igralec klikne na polje (i, j) se izvede ta metoda
	
	public void klikniPolje (int i, int j) {
		if (igra != null) {
			switch (igra.stanje()) {
			case NA_VRSTI_RED: strategRED.klik(i, j); break;
			case NA_VRSTI_BLUE: strategBLUE.klik(i, j); break;
			default: break;
			}
		}
	}
	
	public Igra kopijaIgre() {
		return new Igra(igra);
	}


}
