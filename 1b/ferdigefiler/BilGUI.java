import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BilGUI extends JFrame implements Serializable
{
	private JTextField regNr, carBrand, carType, regYear, nyEier;
	private JTextArea list;
	private JButton printAllBilerList, printList, regCar, deleteCar, emptyList, findCar;
	private Billiste billiste = new Billiste();
	private JTextField navn, adresse, idNr;
	private JTextArea eierListen;
	private JButton printEierList, regPerson, regFirma, deleteEier, emptyEierList, findEier, byttEier;
	private Bileierliste bileierliste = new Bileierliste();
	private Kommandolytter lytteren;

	public BilGUI() {
		super("Bilregistrering");
		lytteren = new Kommandolytter();
		setLayout( new FlowLayout() );

		add( new JLabel("Registreringsnummer: ") );
		regNr = new JTextField(10);
		regNr.addActionListener(lytteren);
		add(regNr);

		add( new JLabel( "Bilmerke: " ) );
		carBrand = new JTextField( 10 );
		carBrand.addActionListener( lytteren );
		add( carBrand );

		add( new JLabel( "Biltype: " ) );
		carType = new JTextField( 10 );
		carType.addActionListener( lytteren );
		add( carType );

		add( new JLabel( "Registreringsar: " ) );
		regYear = new JTextField( 5 );
		regYear.addActionListener( lytteren );
		add( regYear );

		printAllBilerList = new JButton( "Vis ALLE biler" );
		printAllBilerList.addActionListener( lytteren );
		add( printAllBilerList );

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

		add( new JLabel("Ny eier:") );
		nyEier = new JTextField(10);
		nyEier.addActionListener(lytteren);
		add(nyEier);

		byttEier = new JButton( "Bytt eier" );
		byttEier.addActionListener( lytteren );
		add( byttEier );

		add( new JLabel( "Biloversikt:" ) );
		eierListen = new JTextArea( 10, 45 );
		eierListen.setEditable( false );
		add( new JScrollPane( eierListen ) );
		lesFil();
		printEierList();
		setSize(1150, 800);
		setVisible(true);
	}
	private void visFeilmelding(String melding) {
		JOptionPane.showMessageDialog(this, melding, "Problem", JOptionPane.ERROR_MESSAGE);
	}

	public void insertCar() {
		try {
			String n = navn.getText();
			if(!bileierliste.finnesBilEier(n)) {
				return;				
			}

			boolean feil = false;
			String feilMelding = "Du har glemt og fylle feltene";

			if(regNr.getText() == null) {
				feil = true;
				feilMelding += "\nRegistreringsnummer";
			} 
			if(regYear.getText() == null) {
				feil = true;
				feilMelding += "\nYear";
			} 
			if(carBrand.getText() == null) {
				feil = true;
				feilMelding += "\nBiltype";
			} 
			if(carType.getText() == null) {
				feil = true;
				feilMelding += "\nBiltype";
			}
			if(feil == true) {
				list.setText(feilMelding);
				return;
			}

			String cB = carBrand.getText();
			String cT = carType.getText(); 
			int rN = Integer.parseInt(regNr.getText()); 
			int rY = Integer.parseInt(regYear.getText());
			Bileier be = bileierliste.find(n);
			Billiste bl = be.getBilliste(); 
			bl.insertBil(cB,cT, rN, rY);
			carBrand.setText("");
			carType.setText("");
			regNr.setText("");
			regYear.setText("");
		}
		catch (NullPointerException npe) {
			visFeilmelding("Noen felter mangler!");
		}		
		catch (NumberFormatException e) {
			visFeilmelding("Feil i tallformat.");
		}
	}

	public void printAllBilerList() {
		System.out.println(bileierliste.giveMeAllCars());
		list.setText(bileierliste.giveMeAllCars());
	}

	public void printList() {
		String n = navn.getText();
		Bileier be = bileierliste.find(n);
		be.getBilliste().writeList(list);
	}

	public void removeCar() {
		try {
			int r = Integer.parseInt(regNr.getText());
			if (bileierliste.finnBil(r) != null) {
				Bil slettetBil = bileierliste.slettBil(r); 
				list.setText(slettetBil.toString() +" er fjernet fra listen.");
			} else {
				list.setText(r +" finnes ikke i listen.");
			}
			regNr.setText( "" );
		}
		catch (NumberFormatException e) {
			list.setText("Feil i tallformat.");
		}
	}

	public void findCar() {  
		try {
			int r = Integer.parseInt(regNr.getText());
			Bil b = bileierliste.finnBil(r);
			if (b != null)
        		list.setText(b.getRegNr()+" er  funnet i listen.");
			else
        		list.setText(r +" er ikke funnet i listen.");
			regNr.setText("");
		}
		catch (NumberFormatException e) {
			visFeilmelding("Feil i tallformat.");
		}
	}

	public void insertPerson() {
		try{
			String n = navn.getText();
			if(bileierliste.finnesBilEier(n)) {
				eierListen.setText("Bileieren finnes fra for!");
				return;
			}
			String a = adresse.getText(); 
			int i = Integer.parseInt(idNr.getText()); 
			if(bileierliste.finnesPersonId(i)) {
				eierListen.setText("Personen er allerede registrert");
				return;
			}			
			bileierliste.insertPerson(n,a,i);
			navn.setText("");
			adresse.setText("");
			idNr.setText("");
			eierListen.setText("");			
			eierListen.append(n + " er lagt til!");			
		}
		catch (NullPointerException npe) {
			visFeilmelding("Fyll inn navn, adresse og ID!");
		}		
		catch (NumberFormatException e) {
			visFeilmelding("Id ma fylles inn og kan kun vere tall.");
		}
	}

	public void insertFirma() {
		try{
			String n = navn.getText();
			if(bileierliste.finnesBilEier(n)) {
				eierListen.setText("Bileieren er allerede lagt til!");
				return;
			}
			int i = Integer.parseInt(idNr.getText()); 
			if(bileierliste.finnesFirmaId(i)) {
				eierListen.setText("Firmaet er allerede registrert");
				return;
			}
			String a = adresse.getText(); 

			System.out.print(n+a+i);
			bileierliste.insertPerson(n,a,i);
			navn.setText("");
			adresse.setText("");
			idNr.setText("");
			eierListen.setText("");
			eierListen.append(n + " er lagt til!");
		}
		catch (NullPointerException npe) {
			visFeilmelding("Fyll inn navn, adresse og ID!");
		}
		catch (NumberFormatException e) {
			visFeilmelding("Mangler id.");
		}
	}	

	public void printEierList() {
		bileierliste.writeList(eierListen);
	}

  //fjerner f?rste forekomst av det innleste tallet fra lista
	public void removeEier() {
		String n = navn.getText();
		if(navn.getText() == null) {
			eierListen.setText("Du ma fylle inn navn pa person du vil fjerne");
			return;
		}
		if (bileierliste.removeEier(n)) {
			eierListen.setText("\t"+ n +" er fjernet fra listen.");
		} else {
			eierListen.setText("\t"+ n +" finnes ikke i listen.");
		}
		navn.setText( "" );
	}

	public void findEier() {  
		if(navn.getText() == null) {
			eierListen.setText("Det er ikke noe navn her. Skal du lete bor du ha noget a lete efter!");
			return;
		}
		String n = navn.getText();
		Bileier be = bileierliste.find(n);
		if (be != null)
			eierListen.setText("\t"+ be.getNavn()+" er  funnet i listen.");
		else
			eierListen.setText("\t"+ be +" er ikke funnet i listen.");
		navn.setText("");
	}

	public void byttEier() {
		if(nyEier.getText() == null)  {
			eierListen.setText("Du ma fylle inn feltet for ny eier");
			return;
		}
		if(regNr.getText() == null) {
			eierListen.setText("Hvilken bil skal byttes? Fyll inn regnr!");
			return;
		}
		int r = Integer.parseInt(regNr.getText());		
		String nE = nyEier.getText();
		Bileier nyEier = bileierliste.find(nE);
		Billiste neBL = nyEier.getBilliste();

		if(!bileierliste.finnesBilEier(nE)){
			eierListen.setText("Eieren ma vere registrert for du bytter eier.");
			return;
		}
		if(bileierliste.finnBil(r)== null) {
			eierListen.setText("Bilen finnes ikke.");
			return;
		}
		neBL.insertBil(bileierliste.slettBil(r));		
		eierListen.setText("Bilen har byttet eier");
	}	

	private void lesFil() {
		try (ObjectInputStream innfil = new ObjectInputStream(
			new FileInputStream( "test.dta" ))) {
				bileierliste = (Bileierliste) innfil.readObject();
			}
		catch(ClassNotFoundException cnfe) {
			eierListen.setText(cnfe.getMessage());
			eierListen.append("\nOppretter tom liste.\n");
			bileierliste = new Bileierliste();
		}
		catch(FileNotFoundException fne) {
			eierListen.setText("Finner ikke datafil. Oppretter tom liste.\n");
			bileierliste = new Bileierliste();
		}
		catch(IOException ioe) {
			eierListen.setText("Innlesingsfeil. Oppretter tom liste.\n");
			bileierliste = new Bileierliste();
		}
	}

	public void skrivTilFil() {
		try (ObjectOutputStream utfil = new ObjectOutputStream(
			new FileOutputStream("test.dta"))) {
				utfil.writeObject(bileierliste);
		}
		catch( NotSerializableException nse ) {
			visFeilmelding("Objektet er ikke serialisert!");
		}
		catch( IOException ioe ) {
			visFeilmelding("Problem med utskrift til fil.");
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
			else if ( e.getSource() == printEierList ) {
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
			else if ( e.getSource() == byttEier ) {
				byttEier();
			} else if( e.getSource() == printAllBilerList) {
				printAllBilerList();
			}			
		}
	}
}
