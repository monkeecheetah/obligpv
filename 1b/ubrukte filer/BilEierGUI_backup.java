import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BilEierGUI extends JFrame
{
	private JTextField navn, adresse, idNr;
	private JTextArea eierListen;
	private JButton printEierList, regPerson, regFirma, deleteEier, emptyEierList, findEier;
	private Bileierliste bileierliste = new Bileierliste();
	private Kommandolytter lytteren;
	public BilEierGUI() {
		super("Bilregistrering");
		lytteren = new Kommandolytter();
		setLayout( new FlowLayout() );

		add( new JLabel("Navn:") );
		navn = new JTextField(10);
		navn.addActionListener(lytteren);
		add(navn);

		add( new JLabel( "Adresse:" ) );
		adresse = new JTextField( 10 );
		adresse.addActionListener( lytteren );
		add( adresse );

		add( new JLabel( "Personnummer/OrgNr:" ) );
		idNr = new JTextField( 10 );
		idNr.addActionListener( lytteren );
		add( idNr );

		printEierList = new JButton( "Vis bileierliste" );
		printEierList.addActionListener( lytteren );
		add( printEierList );

		findEier = new JButton( "Finn bileier" );
		findEier.addActionListener( lytteren );
		add( findEier );

		emptyEierList = new JButton( "Slett bileiere" );
		emptyEierList.addActionListener( lytteren );
		add( emptyEierList );

		deleteEier = new JButton( "Slett eier" );
		deleteEier.addActionListener( lytteren );
		add( deleteEier );

		regPerson = new JButton( "Registrer person" );
		regPerson.addActionListener( lytteren );
		add( regPerson );

		regFirma = new JButton( "Registrer firma" );
		regFirma.addActionListener( lytteren );
		add( regFirma );

		add( new JLabel( "Biloversikt:" ) );
		eierListen = new JTextArea( 10, 45 );
		eierListen.setEditable( false );
		add( new JScrollPane( eierListen ) );
		setSize(550, 400);
		setVisible(true);
	}

	public void insertPerson() {
		String n = navn.getText();
		String a = adresse.getText(); 
		int i = Integer.parseInt(idNr.getText()); 
		System.out.print(n+a+i);
		bileierliste.insertPerson(n,a,i);
		navn.setText("");
		adresse.setText("");
		idNr.setText("");
	}

	public void insertFirma() {
		String n = navn.getText();
		String a = adresse.getText(); 
		int i = Integer.parseInt(idNr.getText()); 
		System.out.print(n+a+i);
		bileierliste.insertPerson(n,a,i);
		navn.setText("");
		adresse.setText("");
		idNr.setText("");
	}	

	public void printEierList() {
		bileierliste.writeList(eierListen);
	}

  //fjerner f?rste forekomst av det innleste tallet fra lista
	public void removeEier() {
		String n = navn.getText();
		if (bileierliste.removeEier(n)) {
			eierListen.setText("\t"+ n +" er fjernet fra listen.");
		} else {
			eierListen.setText("\t"+ n +" finnes ikke i listen.");
		}
		navn.setText( "" );
	}

	public void findEier() {  
		String n = navn.getText();
		Bileier be = bileierliste.find(n);
		if (be != null)
			eierListen.setText("\t"+ be.getNavn()+" er  funnet i listen.");
		else
			eierListen.setText("\t"+ be +" er ikke funnet i listen.");
		navn.setText("");
	}

	private class Kommandolytter implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			if ( e.getSource() == printEierList ) {
				printEierList();
			}
			else if ( e.getSource() == regPerson ) {
				insertPerson();
			}
			else if ( e.getSource() == regFirma ) {
				insertFirma();
			}			
			else if ( e.getSource() == deleteEier ) {
				removeEier();
			}
			else if ( e.getSource() == emptyEierList ) {
				bileierliste.emptyEierList();
			}
			else if ( e.getSource() == findEier ) {
				findEier();
			}			
		}
	}	
}