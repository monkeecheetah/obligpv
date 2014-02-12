// Lag et program som leser inn et katalognavn eller filnavn via et dialogvindu.

// Hvis det innleste navnet er en katalog skal følgende skrives ut på skjermen:

// Navnet representerer en katalog

// Det totale antall elementer som katalogen inneholder (filer og underkataloger) og navnene på disse.

// Hvis elementet er en java-fil skal navnet etterfølges av antall kodelinjer.

// Det totale antall kodelinjer som java-filene inneholder tilsammen.

// Hvis det innleste navnet er en tekstfil (dvs. ikke byte-fil) eller en java-fil, skal det velges en ny, ubrukt tekstfil, ved hjelp av showSaveDialog-metoden i JFileChooser-klassen. Innholdet i den innleste filen skal så skrives til den nye tekstfilen, slik at hver linje starter med linjenummeret. Etter dette skal følgende skrives ut på skjermen:

// Navnet (på filen det leses fra) representerer en fil

// Navnet på den nye filen (med linjenummerne) som er opprettet og opplysninger om hva den inneholder.

// Hint! Del programmet opp i flere mindre metoder! 
// For å få testet ut programmet må katalogen class-filen ligger i inneholde en eller flere underkataloger som inneholder filer, deriblant java-filer. Forøvrig, se Mer om File-klassen.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Oppgave2 extends JFrame{

	private JTextField innfelt;
	private JButton ok;
	private JTextArea utfelt;

	private String navnKatalog;
	private int antallElementer;
	private int antallKodelinjer;
	private int totaltantallKodelinjer;


	private Lytter lytt;
	
	public Oppgave2() {
		super("superdialogvindu");
		lytt = new Lytter();
		lagVindu();
	}

	public void lagVindu(){

		Container c = getContentPane();
		c.setLayout( new FlowLayout() );

		ok = new JButton("OK");
		innfelt = new JTextField(27);
		utfelt = new JTextArea( 30,40);
		c.add( new JLabel("Innlesingsfelt") );	
		c.add( innfelt );
		c.add(ok);
		ok.addActionListener(lytt);
		c.add( utfelt );
		utfelt.setEditable(false);
		setSize(550,550);
		setVisible(true);
	}

	private class Lytter implements ActionListener{
		public void actionPerformed( ActionEvent e ){}
	}

	

}