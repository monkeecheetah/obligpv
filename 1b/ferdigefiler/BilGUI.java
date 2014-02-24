import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BilGUI extends JFrame implements Serializable
{
	private JTextField regNr, carBrand, carType, regYear, nyEier;
	private JTextArea list;
	private JButton printAllBilerList, printList, regCar, deleteCar,findCar; // emptyList;
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
                Container c = getContentPane();

                c.add( new JLabel("Navn:") );
		navn = new JTextField(16);
		navn.addActionListener(lytteren);
		c.add(navn);

		c.add( new JLabel( "Adresse:" ) );
		adresse = new JTextField( 16 );
		adresse.addActionListener( lytteren );
		c.add( adresse );

		c.add( new JLabel( "Personnummer/OrgNr:" ) );
		idNr = new JTextField( 10 );
		idNr.addActionListener( lytteren );
		c.add( idNr );

		printEierList = new JButton( "Vis bileierliste" );
		printEierList.addActionListener( lytteren );
		c.add( printEierList );

		emptyEierList = new JButton( "Slett bileiere" );
		emptyEierList.addActionListener( lytteren );
		c.add( emptyEierList );

		deleteEier = new JButton( "Slett eier" );
		deleteEier.addActionListener( lytteren );
		c.add( deleteEier );

		regPerson = new JButton( "Registrer person" );
		regPerson.addActionListener( lytteren );
		c.add( regPerson );

		regFirma = new JButton( "Registrer firma" );
		regFirma.addActionListener( lytteren );
		c.add( regFirma );

		eierListen = new JTextArea( 10, 45 );
		eierListen.setEditable( false );
		c.add( new JScrollPane( eierListen ) );
                
                add( new JLabel("Registreringsnummer: ") );
		regNr = new JTextField(8);
		regNr.addActionListener(lytteren);
		c.add(regNr);

		c.add( new JLabel( "Bilmerke: " ) );
		carBrand = new JTextField(15);
		carBrand.addActionListener( lytteren );
		c.add( carBrand );

		c.add( new JLabel( "Biltype: " ) );
		carType = new JTextField( 10 );
		carType.addActionListener( lytteren );
		c.add( carType );

		c.add( new JLabel( "Registreringsår: " ) );
		regYear = new JTextField( 5 );
		regYear.addActionListener( lytteren );
		c.add( regYear );

		printAllBilerList = new JButton( "Vis ALLE biler" );
		printAllBilerList.addActionListener( lytteren );
		c.add( printAllBilerList );

		printList = new JButton( "Vis billiste" );
		printList.addActionListener( lytteren );
		c.add( printList );

		findCar = new JButton( "Finn bil" );
		findCar.addActionListener( lytteren );
		c.add( findCar );

                findEier = new JButton( "Finn bileier" );
		findEier.addActionListener( lytteren );
		c.add( findEier );
                
/*		emptyList = new JButton( "Slett billiste" );
		emptyList.addActionListener( lytteren );
		c.add( emptyList );
*/
		deleteCar = new JButton( "Slett bil" );
		deleteCar.addActionListener( lytteren );
		c.add( deleteCar );

		regCar = new JButton( "Registrer bil" );
		regCar.addActionListener( lytteren );
		c.add( regCar );

                c.add( new JLabel("Ny eier:") );
		nyEier = new JTextField(10);
		nyEier.addActionListener(lytteren);
		c.add(nyEier);

		byttEier = new JButton( "Bytt eier" );
		byttEier.addActionListener( lytteren );
		c.add( byttEier );

		list = new JTextArea( 10, 45 );
		list.setEditable( false );
		c.add( new JScrollPane( list ) );

		lesFil();
		printEierList();
		setSize(550, 800);
		setVisible(true);
	}
	private void visFeilmelding(String melding) {
		JOptionPane.showMessageDialog(this, melding, "Problem", JOptionPane.ERROR_MESSAGE);
	}

	public void insertCar() {
		try {
			boolean feil = false;
			String feilMelding = "Du har glemt og fylle feltene";

			if(regNr.getText().length() == 0) {
				feil = true;
				feilMelding += "\nRegistreringsnummer";
			} 
			if(regYear.getText().length() == 0) {
				feil = true;
				feilMelding += "\nÅr";
			} 
			if(carBrand.getText().length() == 0) {
				feil = true;
				feilMelding += "\nBiltype";
			} 
			if(carType.getText().length() == 0) {
				feil = true;
				feilMelding += "\nBiltype";
			}
			if(feil == true) {
				list.setText(feilMelding);
				return;
			}

			String n = navn.getText();                        
			if(!bileierliste.finnesBilEier(n)) {
                                list.setText("Bileieren finnes ikke. Bilen må ha en bileier før du legger inn en ny bil");
				return;				
			}
                        
                        String cB = carBrand.getText();
			String cT = carType.getText(); 
			int rN = Integer.parseInt(regNr.getText()); 
			int rY = Integer.parseInt(regYear.getText());
			Bileier be = bileierliste.findEier(n);
			Billiste bl = be.getBilliste(); 
			bl.insertBil(cB,cT, rN, rY);
			carBrand.setText("");
			carType.setText("");
			regNr.setText("");
			regYear.setText("");
                        list.setText("Bilen er registrert");
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

                if(navn.getText().length() == 0) {
                    list.setText("Du må skriv inn en bileier du vil se bilene til");
                    return;
                }
                String n = navn.getText();                
                if(bileierliste.findEier(n) == null) {
                    list.setText("Eieren finnes ikke. Eieren må være registrert hos oss for at du skal finne hvilke biler han har");
                    return;
                }
		Bileier be = bileierliste.findEier(n);
		be.getBilliste().writeList(list);
	}

	public void removeCar() {
		try {
                        if(regNr.getText().length() == 0){
                            list.setText("Du må fylle inn registreringsnummer på bil du ønsker å fjerne");
                            return;
                        }
			int r = Integer.parseInt(regNr.getText());
			if (bileierliste.finnBil(r) != null) {
				Bil slettetBil = bileierliste.slettBil(r); 
				list.setText(slettetBil.toString() +" er fjernet fra listen.");
			} else {
				list.setText(r +" finnes ikke i listen.");
                                return;
                        }
			regNr.setText( "" );
		}
		catch (NumberFormatException e) {
			list.setText("Feil i tallformat.");
		}
	}

	public void findCar() {  
		try {
                        if(regNr.getText().length() == 0) {
                            list.setText("Du må fylle inn registreringsnummer");
                            return;
                        }			
                        int r = Integer.parseInt(regNr.getText());
                        Bil b = bileierliste.finnBil(r);
			if (b != null)
        		list.setText(b.getRegNr()+" er registrert i våre lister.");
			else
        		list.setText(r +" er ikke funnet registrert i vår liste.");
			regNr.setText("");
		}
		catch (NumberFormatException e) {
			visFeilmelding("Registreringsnummer må være et tall.");
		}
	}

	public void insertPerson() {
		try{
			String n = navn.getText();
			String a = adresse.getText(); 
			int i = Integer.parseInt(idNr.getText()); 
                        
                        boolean feil = false;
			String feilMelding = "Du har glemt og fylle feltene for:";

			if(navn.getText().length() == 0) {
				feil = true;
				feilMelding += "\nNavn";
			} 
                        if(adresse.getText().length() == 0) {
                            feil = true;
                            feilMelding += "\nAdresse";
                        }
                        if(idNr.getText().length() == 0) {
                            feil = true;
                            feilMelding += "\nPersonnummer";
                        }
                        if(feil){
                            eierListen.setText(feilMelding);
                            return;
                        }
                        if(bileierliste.finnesBilEier(n)) {
				eierListen.setText("Bileieren finnes fra for!");
				return;
			}

			if(bileierliste.finnesPersonId(i)) {
				eierListen.setText("Personen er allerede registrert");
				return;
			}			
			bileierliste.insertPerson(n,a,i);
			navn.setText("");
			adresse.setText("");
			idNr.setText("");
			eierListen.setText(n + " er lagt til!");			
		}
		catch (NullPointerException npe) {
			visFeilmelding("Fyll inn navn, adresse og ID!");
		}		
		catch (NumberFormatException e) {
			visFeilmelding("Personnummer kan kun være tall.");
		}
	}

	public void insertFirma() {
		try{
			String n = navn.getText();
			String a = adresse.getText(); 
                        boolean feil = false;
                        String output = "Følgende felt må fylles ut:\n";
                        
                        if(n.length() == 0) {
                            feil = true;
                            output += "\nNavn";
                        }
                        if(a.length() == 0) {
                            feil = true;
                            output += "\nAdresse";
                        }                        
                        if(idNr.getText().length() == 0) {
                            feil = true;
                            output += "\nOrgNr";
                        }
                        if(feil == true) {
                            eierListen.setText(output);
                            return;
                        }
			if(bileierliste.finnesBilEier(n)) {
				eierListen.setText("Bileieren er allerede lagt til!");
				return;
			}
			int i = Integer.parseInt(idNr.getText()); 
			if(bileierliste.finnesFirmaId(i)) {
				eierListen.setText("Firmaet er allerede registrert");
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
			visFeilmelding("Orgnr kan kun være tall.");
		}
	}	

	public void printEierList() {
		bileierliste.writeList(eierListen);
	}

  //fjerner f?rste forekomst av det innleste tallet fra lista
	public void removeEier() {
		String n = navn.getText();
		if(n.length() == 0) {
			eierListen.setText("Du må fylle inn navn på person du vil fjerne");
			return;
		}
                if(bileierliste.findEier(n).getBilliste().isListEmpty() == false){
                    eierListen.setText("Bileieren eier fortsatt biler. Du må fjerne hans biler eller gi de ny eier før du kan slette bileieren fra registeret.");
                    return;
                }
		if (bileierliste.removeEier(n)) {
			eierListen.setText(n +" er fjernet fra listen.");
		} else {
			eierListen.setText(n +" finnes ikke i listen.");
		}
		navn.setText( "" );
	}

        public void deleteAlleEiere() {
            int sletteBE = JOptionPane.showOptionDialog( null,"Sikker på at du vil slette alle?", "Slette alle bileiere", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null );
            if(sletteBE == JOptionPane.YES_OPTION){
                bileierliste.emptyEierList();        
                eierListen.setText("Alle eiere er slettet!");
            }
        }

        public void finnEier() {
            if(regNr.getText().length() == 0) {
                list.setText("Du må fylle inn registreringsnummer å søke på");
                return;
            }
            try{
            int r = Integer.parseInt(regNr.getText());
            if(bileierliste.finnEier(r) == null) {
                eierListen.setText("Bileier er ikke registrert på denne bilen. Det skal ikke skje!");
                return;
            }
            eierListen.setText(bileierliste.finnEier(r).toString());
            }catch (NumberFormatException e) {
		visFeilmelding("Regnr må være tallkombinasjon.");
            }
        }
/*        
	public void findEier() {  
		String n = navn.getText();
                if(n.length() == 0) {
			eierListen.setText("Det er ikke skrevet noe navn her. Skal du finne noe, bør du vite hva du leter etter!");
			return;
		}
		Bileier be = bileierliste.findEier(n);
		if (be != null)
			eierListen.setText(be.getNavn()+" er  funnet registrert i våre lister.");
		else
			eierListen.setText(be +" er ikke funnet registrert i våre lister.");
		navn.setText("");
	}
*/
	public void byttEier() {
                try{
		String nE = nyEier.getText();                
		int r = Integer.parseInt(regNr.getText());
		Bileier nyEier = bileierliste.findEier(nE);
		Billiste neBL = nyEier.getBilliste();
                
		if(!bileierliste.finnesBilEier(nE)){
			eierListen.setText("Eieren må være registrert for den skal få registrert bil på seg.");
			return;
		}
		if(bileierliste.finnBil(r)== null) {
			eierListen.setText("Bilen finnes ikke.");
			return;
		}
		neBL.insertBil(bileierliste.slettBil(r));		
		eierListen.setText("Bilen har byttet eier");
                } catch (NumberFormatException e) {
			visFeilmelding("Regnr kan kun være tall.");
		} catch (NullPointerException npe) {
			visFeilmelding("Du må fylle inn navn på ny eier og registreringsnummer på bilen som skal bytte eier");
		}
	}	

	private void lesFil() {
		try (ObjectInputStream innfil = new ObjectInputStream(
			new FileInputStream( "test.dta" ))) {
				bileierliste = (Bileierliste) innfil.readObject();
			}
		catch(ClassNotFoundException cnfe) {
			eierListen.setText(cnfe.getMessage());
			eierListen.append("Problemer med klassen. Oppretter en ny (og tom) liste med bileiere.\n");
			bileierliste = new Bileierliste();
		}
		catch(FileNotFoundException fne) {
			eierListen.setText("Finner ikke datafil. Oppretter en ny (og tom) liste med bileiere.\n");
			bileierliste = new Bileierliste();
		}
		catch(IOException ioe) {
			eierListen.setText("Innlesingsfeil med filene. Oppretter en ny (og tom) liste med bileiere.\n");
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
/*			else if ( e.getSource() == emptyList ) {
				billiste.emptyList();
			}
        */
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
				deleteAlleEiere();
			}
			else if ( e.getSource() == findEier ) {
				finnEier();
			}
			else if ( e.getSource() == byttEier ) {
				byttEier();
			} else if( e.getSource() == printAllBilerList) {
				printAllBilerList();
			}			
		}
	}
}
