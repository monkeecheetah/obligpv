// programmutvikling oblig 1 OPPGAVE 3 
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
//Oppgave 3 sin vindusklasse

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Bokvindu2 extends JFrame{

private JTextField forfatter, tittel, sideAntall, pris, fagomraade, skoleFag, klassetrinn, sjanger, maalform, spraak;
private JButton regFagbok, regSkolebok, regNorskRoman, regUtenlandsRoman, visBokregister;
private JTextArea utskrift;
private Listener lytter;
private Bokregister2 register;
private Bok2 books;

	public Bokvindu2(){
		super( "Bokarkiv" );
		lytter = new Listener();
		register = new Bokregister2();

		Container c = getContentPane();
		c.setLayout( new FlowLayout() );

		c.add( new JLabel("Forfatter:"));
		forfatter = new JTextField( 16 );
		forfatter.addActionListener( lytter );
		c.add( forfatter );

		c.add( new JLabel("Tittel:"));
		tittel = new JTextField( 16 );
		tittel.addActionListener( lytter );
		c.add( tittel );

		c.add( new JLabel("Sideantall:"));
		sideAntall = new JTextField( 5 );
		sideAntall.addActionListener( lytter );
		c.add( sideAntall );		

		c.add( new JLabel("Pris:"));
		pris = new JTextField( 5 );
		pris.addActionListener( lytter );
		c.add( pris );		

		c.add( new JLabel("Fagområde:"));
		fagomraade = new JTextField( 14 );
		fagomraade.addActionListener( lytter );
		c.add( fagomraade );

		c.add( new JLabel("Skolefag:"));
		skoleFag = new JTextField( 10 );
		skoleFag.addActionListener( lytter );
		c.add( skoleFag );		

		c.add( new JLabel("Klassetrinn:"));
		klassetrinn = new JTextField( 3 );
		klassetrinn.addActionListener( lytter );
		c.add(	klassetrinn );		

		c.add( new JLabel("Sjanger:"));
		sjanger = new JTextField( 10 );
		sjanger.addActionListener( lytter );
		c.add( sjanger );		

		c.add( new JLabel("Målform (b=bokmål, n=nynorsk):"));
		maalform = new JTextField( 3 );
		maalform.addActionListener( lytter );
		c.add( maalform );		

		c.add( new JLabel("Språk:"));
		spraak = new JTextField( 7 );
		spraak.addActionListener( lytter );
		c.add( spraak );

		regFagbok = new JButton( "Registrer Fagbok" );
		regFagbok.addActionListener( lytter );
		c.add( regFagbok );

		regSkolebok = new JButton( "Registrer Skolebok" );
		regSkolebok.addActionListener( lytter );
		c.add( regSkolebok );

		regNorskRoman = new JButton( "Registrer Norsk Roman" );
		regNorskRoman.addActionListener( lytter );
		c.add( regNorskRoman );

		regUtenlandsRoman = new JButton( "Registrer Utenlandsk Roman" );
		regUtenlandsRoman.addActionListener( lytter );
		c.add( regUtenlandsRoman );

		visBokregister = new JButton( "Vis Bokregister" );
		visBokregister.addActionListener( lytter );
		c.add( visBokregister );

		utskrift = new JTextArea( 20, 40);
		utskrift.setEditable(false);
		c.add( utskrift );
		c.add( new JScrollPane( utskrift ) );
		
		register.lesFraFil( "bokhylla.dta" );
		visRegister();

		setVisible(true);
		setSize( 570, 600 );	
	}

	public void skrivFil(){
		register.skrivTilfil("bokhylla.dta");
	}

	public void registrerFagbok(){
		String f = forfatter.getText();
		String t = tittel.getText();
		String fb = fagomraade.getText();
		if( f.length() == 0 || t.length() == 0 || fb.length() == 0 ){
			visMelding( "Fyll inn forfatter, tittel og fagområde\npris og sideantall" );
			return;
		}
		try{
			int s = Integer.parseInt( sideAntall.getText() );
			double p = Double.parseDouble( pris.getText() );
			register.settInn( new Fagbok2(f, t, s, p, fb) );
			visMelding("Ny fagbok registrert");
		}catch( NumberFormatException e ){
			visMelding("Ikke registrert pga. feil i tallformat" );
			return;
		}
	}

	public void registrerSkolebok(){
		String f = forfatter.getText();
		String t = tittel.getText();
		String sf = skoleFag.getText() ;
		if( f.length() == 0 || t.length() == 0 || sf.length() == 0  ){
			visMelding( "Fyll inn forfatter, tittel og skolefad\nklassetrinn, pris og sideantall" );
			return;
		}
		try{	
			int s = Integer.parseInt( sideAntall.getText() );
			double p = Double.parseDouble( pris.getText() );
			int k = Integer.parseInt( klassetrinn.getText() );
			register.settInn( new Skolebok2(f, t, s, p, k, sf) );
			visMelding( "Ny Skolebok registrert" );
		}
		catch(NumberFormatException e){
			visMelding("Ingen registrering pga. feil i tallformat");
		}
	}

	public void registrerNorskRoman(){
		String f = forfatter.getText();
		String t = tittel.getText();
		String sj = sjanger.getText();
		String m = maalform.getText();
		if( f.length() == 0 || t.length() == 0 || sj.length() == 0 || m.length() == 0 ){
			visMelding( "Alle felter må fylles inn:\nForfatter, tittel, sjanger, målformm\npris og sideantall" );
			return;
		}
		try{
			int s = Integer.parseInt( sideAntall.getText() );
			double p = Double.parseDouble( pris.getText() );
			register.settInn( new NorskRoman2(f, t, s, p, sj, m ) );
			char maalkode = m.charAt(0);
			if( maalkode == 'b' )
				m = "bokmål";
			else if( maalkode == 'n' )
				m = "nynorsk";
			else{
				visMelding("Målform må være enten nynorsk eller bokmål ");
				return;
			}
			visMelding( "Ny Norsk Roman Registert" );
		}
		catch ( NumberFormatException e ){
			visMelding("Ingen registrering pga. feil i tallformat");
		}
	}

	public void registrerUtenlandskRoman(){
		String f = forfatter.getText();
		String t = tittel.getText();
		String sj = sjanger.getText();
		String o = spraak.getText();
		if( f.length() == 0 || t.length() == 0 || sj.length() == 0 || o.length() == 0 ){
			visMelding("Fyll inn: forfatter, tittel, sjanger, språk,\nsideantall og pris");
			return;
		}
		try{
			int s = Integer.parseInt( sideAntall.getText() );
			double p = Double.parseDouble( pris.getText() );
			register.settInn( new UtenlandsRoman2(f, t, s, p, sj, o));
			visMelding( "Ny  Utenlandsk Roman Registrert" );
		}
		catch( NumberFormatException e){
			visMelding("Ingen registrering pga. feil i tallformat");
		}
	}

	public void visRegister(){
		register.skrivListe( utskrift );
	}

	private void slettFelter(){}

	private void visMelding( String melding ){
		JOptionPane.showMessageDialog( this, melding );
	}

	private class Listener implements ActionListener{

		public void actionPerformed( ActionEvent e){
			if ( e.getSource() == regFagbok ){
				registrerFagbok();
			}else if( e.getSource() == visBokregister ){
			 	visRegister();
			}else if( e.getSource() == regSkolebok ){
				registrerSkolebok();
			}else if( e.getSource() == regNorskRoman ){
				registrerNorskRoman();
			}else if( e.getSource() == regUtenlandsRoman ){
				registrerUtenlandskRoman();
			}
		}
	}
}