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
		regNr = new JTextField(10);
		regNr.addActionListener(lytteren);
		add(regNr);

		add( new JLabel( "Bilmerke:" ) );
		carBrand = new JTextField( 10 );
		carBrand.addActionListener( lytteren );
		add( carBrand );

		add( new JLabel( "Biltype:" ) );
		carType = new JTextField( 10 );
		carType.addActionListener( lytteren );
		add( carType );

		add( new JLabel( "Registreringsar" ) );
		regYear = new JTextField( 5 );
		regYear.addActionListener( lytteren );
		add( regYear );

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

		regCar = new JButton( "Registrer bil" );
		regCar.addActionListener( lytteren );
		add( regCar );

		add( new JLabel( "Biloversikt:" ) );
		list = new JTextArea( 10, 45 );
		list.setEditable( false );
		add( new JScrollPane( list ) );
//		skrivListe();
		setSize(550, 400);
		setVisible(true);
	}
	private void visFeilmelding(String melding) {
	JOptionPane.showMessageDialog(this, melding, 
			"Problem", JOptionPane.ERROR_MESSAGE);
	}

  //leser inn heltall fra brukeren og setter det inn
  // forrest i lista heltallsliste
	public void insertCar() {
		try {
			String cB = carBrand.getText();
			String cT = carType.getText(); 
			int rN = Integer.parseInt(regNr.getText()); 
			int rY = Integer.parseInt(regYear.getText());
			System.out.print(cB+cT+rN+ rY);
			billiste.insertBil(cB,cT, rN, rY);
			carBrand.setText("");
			carType.setText("");
			regNr.setText("");
			regYear.setText("");									
		}
		catch (NumberFormatException e) {
			visFeilmelding("Feil i tallformat.");
		}
	}

	public void printList() {
		billiste.writeList(list);
	}

  //fjerner f?rste forekomst av det innleste tallet fra lista
	public void removeCar() {
		try {
			int r = Integer.parseInt(regNr.getText());
			if (billiste.removeCar(r)) {
				list.setText("\t"+ r +" er fjernet fra listen.");
			} else {
				list.setText("\t"+ r +" finnes ikke i listen.");
			}
			regNr.setText( "" );
		}
		catch (NumberFormatException e) {
			visFeilmelding("Feil i tallformat.");
		}
	}

	public void findCar() {  
		try {
			int r = Integer.parseInt(regNr.getText());
			Bil b = billiste.find(r);
			if (b != null)
        		list.setText("\t"+ b.getRegNr()+" er  funnet i listen.");
			else
        		list.setText("\t"+ r +" er ikke funnet i listen.");
			regNr.setText("");
		}
		catch (NumberFormatException e) {
			visFeilmelding("Feil i tallformat.");
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
				billiste.emptyList();
			}
			else if ( e.getSource() == findCar ) {
				findCar();
			}			
		}
	}
}
