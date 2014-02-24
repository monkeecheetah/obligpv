// programutvikling oblig 1 OPPGAVE 1a
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// Klassen Bil sitt brukergrensesnitt

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BilGUI extends JFrame
{
	private JTextField regNr, carBrand, carType, regYear;
	private JTextArea list;
	private JButton printList, regCar, deleteCar, emptyList, findCar;
	private Billiste billiste = new Billiste();
	private Kommandolytter lytteren;

	public BilGUI() {
		super("Bilregistrering");
		lytteren = new Kommandolytter();
		setLayout( new FlowLayout() );

		add( new JLabel("Registreringsnummer:") );
		regNr = new JTextField(8);
		regNr.addActionListener(lytteren);
		add(regNr);

		add( new JLabel( "Bilmerke: " ) );
		carBrand = new JTextField( 12 );
		carBrand.addActionListener( lytteren );
		add( carBrand );

		add( new JLabel( "Biltype: " ) );
		carType = new JTextField( 12 );
		carType.addActionListener( lytteren );
		add( carType );

		add( new JLabel( "Registreringsår" ) );
		regYear = new JTextField( 5 );
		regYear.addActionListener( lytteren );
		add( regYear );

                regCar = new JButton( "Registrer bil" );
		regCar.addActionListener( lytteren );
		add( regCar );
                
		printList = new JButton( "Vis billiste" );
		printList.addActionListener( lytteren );
		add( printList );

		findCar = new JButton( "Finn bil" );
		findCar.addActionListener( lytteren );
		add( findCar );

		emptyList = new JButton( "Slett billiste" );
		emptyList.addActionListener( lytteren );
		add( emptyList );

		deleteCar = new JButton( "Slett bil" );
		deleteCar.addActionListener( lytteren );
		add( deleteCar );

		add( new JLabel( "Biloversikt:" ) );
		list = new JTextArea( 20, 20 );
		list.setEditable( false );
		add( new JScrollPane( list ) );
//		skrivListe();
		setSize(280, 600);
		setVisible(true);
	}
	private void visFeilmelding(String melding) {
	JOptionPane.showMessageDialog(this, melding, 
			"Problem", JOptionPane.ERROR_MESSAGE);
	}

	public void insertCar() {
		try {
			String cB = carBrand.getText();
			String cT = carType.getText(); 
			boolean feil = false;
                        String feilMelding = "Følgende felter må fylles ut: \n";
                        
                        if(cB.length() == 0) {
                            feil = true;
                            feilMelding += "\nBilmerke";
                        }
                        if(cT.length() == 0) {
                            feil = true;
                            feilMelding += "\nBiltype";
                        }
                        if(regNr.getText().length() == 0) {
                            feil = true;
                            feilMelding += "\nRegNr";
                        }
                        if(regYear.getText().length() == 0) {
                            feil = true;
                            feilMelding += "\nRegistreringsår";
                        }              
                        if(feil == true) {
                            list.setText(feilMelding);
                            return;
                        }
                        int rN = Integer.parseInt(regNr.getText()); 
			int rY = Integer.parseInt(regYear.getText());
			billiste.insertBil(cB,cT, rN, rY);
                        list.setText("Bilen er registrert");
                        carBrand.setText("");
			carType.setText("");
			regNr.setText("");
			regYear.setText("");	
		}
		catch (NumberFormatException e) {
			visFeilmelding("Registreringnr og registreringsnummer må være siffer.");
		}
	}

	public void printList() {
		billiste.writeList(list);
	}
        
        public void emptyList() {
                billiste.emptyList();        
                list.setText("Listen er slettet");
        }

  //fjerner f?rste forekomst av det innleste tallet fra lista
	public void removeCar() {
		try {
			int r = Integer.parseInt(regNr.getText());
			if (billiste.removeCar(r)) {
				list.setText(r +" er fjernet fra listen.");
			} else {
				list.setText(r +" ble ikke fjernet fra listen.");
			}
			regNr.setText( "" );
		}
		catch (NumberFormatException e) {
			visFeilmelding("Registreringsnummer må være tall.");
		}
	}

	public void findCar() {  
		try {
			int r = Integer.parseInt(regNr.getText());
			Bil b = billiste.find(r);
			if (b != null)
        		list.setText(b.toString()+" er  funnet i listen.");
			else
        		list.setText(r +" er ikke funnet i listen.");
			regNr.setText("");
		}
		catch (NumberFormatException e) {
			visFeilmelding("Registreringsnummer må være tall.");
		}
	}

	private class Kommandolytter implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			if ( e.getSource() == printList ) {
				printList();
			}
			else if ( e.getSource() == regCar ) {
				insertCar();
			}
			else if ( e.getSource() == deleteCar ) {
				removeCar();
			}
			else if ( e.getSource() == emptyList ) {
                                emptyList();
			}
			else if ( e.getSource() == findCar ) {
				findCar();
			}			
		}
	}
}
