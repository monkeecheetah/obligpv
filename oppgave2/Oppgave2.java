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


	private File navn;

	private Lytter lytt;
	
	public Oppgave2( String n ) {
		super("superdialogvindu");
		lytt = new Lytter();
		lagVindu();

		navn = new File (n);

		katalog();
		fil();
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

	public void katalog(){
		if(!navn.isDirectory() )
			return;

		String print = " ";
		String[]filliste = navn.list();
		String regex = "\\w+\\.java";
		int antallFiler = 0;
		int antallLinjerprFil = 0;
		int antallLinjer = 0;
		int test = 0;
		utfelt.setText( navn + " er en Katalog\n");

		utfelt.append("\n Navn på filer i " + navn + "katalogen:\n");
		
		for(int i = 0; i < filliste.length; i++ ){
			print += ( "\n" + filliste[i] );

			if( filliste[i].matches( regex )){
				print += "dette er en javafil";
				File temp = new File( filliste[i] );
				test++;
				System.out.println( test + "" + temp);
				
				try( BufferedReader inn = new BufferedReader( 
					new FileReader(temp))){
					System.out.println("vi er i try catch");
					String linje = "";

					do{
						linje = inn.readLine();
						System.out.println("linje");
						if( linje != null)
							antallLinjerprFil ++;
					}while( linje != null);

						print += ("Antall linjer i filen" + antallLinjerprFil );
				}catch( IOException ioe){
					System.out.println("her er en feil");
				}//slutt på try catch
				antallLinjer += antallLinjerprFil;			
				
			}//slutt på for
			antallFiler ++;
		}
		utfelt.append("Tilsammen antall elementer i katalogen = " + antallFiler + print + "\nAntal kodelinjer i Java filer: " + antallLinjer );
	}

	public void fil(){
		if(!navn.isFile())
			return;
		System.out.println("");
		utfelt.append( navn + " er en fil");
	}

	private class Lytter implements ActionListener{
		public void actionPerformed( ActionEvent e ){}
	}



}