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



@SuppressWarnings("serial")
public class GlavnoOkno extends JFrame implements ActionListener {
	
	private IgralnoPolje polje;
	private JLabel status;
	public Igra igra;
	private Strateg strategRED;
	private Strateg strategBLUE;
	
	//Meni - izbire
	private JMenuItem igraClovekClovek;
	private JMenuItem igraClovekRacunalnik;
	private JMenuItem igraRacunalnikClovek;
	private JMenuItem igraRacunalnikRacunalnik;
	
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
		

		igraClovekRacunalnik = new JMenuItem("Človek - računalnik");
		igra_menu.add(igraClovekRacunalnik);
		igraClovekRacunalnik.addActionListener(this);
		
		igraRacunalnikClovek = new JMenuItem("Računalnik - človek");
		igra_menu.add(igraRacunalnikClovek);
		igraRacunalnikClovek.addActionListener(this);

		igraRacunalnikRacunalnik = new JMenuItem("Računalnik - računalnik");
		igra_menu.add(igraRacunalnikRacunalnik);
		igraRacunalnikRacunalnik.addActionListener(this);

		igraClovekClovek = new JMenuItem("Človek - človek");
		igra_menu.add(igraClovekClovek);
		igraClovekClovek.addActionListener(this);

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
		
		//Začnemo novo igro človeka proti računalniku
		novaIgra(new Clovek(this, Igralec.RED), new Racunalnik(this, Igralec.BLUE));
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
		osveziGUI();
		switch (igra.stanje()) {
		case NA_VRSTI_RED: strategRED.na_potezi(); break;
		case NA_VRSTI_BLUE: strategBLUE.na_potezi(); break;
		case ZMAGA_RED: break;
		case ZMAGA_BLUE: break;
		case NEODLOCENO: break;
			}
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
		if (e.getSource() == igraClovekRacunalnik) {
			novaIgra(new Clovek(this, Igralec.RED),
					  new Racunalnik(this, Igralec.BLUE));
		}
		else if (e.getSource() == igraRacunalnikClovek) {
			novaIgra(new Racunalnik(this, Igralec.RED),
					  new Clovek(this, Igralec.BLUE));
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
