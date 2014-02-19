// programmutvikling oblig 1 OPPGAVE 2 
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Oppgave2 extends JFrame{

	private JTextField innfelt;
	private JButton ok;
	private JTextArea utfelt;

	private File navn;
	private File temp;

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
		add( new JScrollPane( utfelt ));
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

		utfelt.setText( navn + " er en Katalog\n");

		utfelt.append("\n Navn på filer i " + navn + "katalogen:\n");
	
		for(int i = 0; i < filliste.length; i++ ){
			print += ( "\n" + filliste[i] );
			temp = new File(navn+"/"+filliste[i]);
			if( filliste[i].matches( regex )){

				try(BufferedReader innfil = new BufferedReader(new FileReader(temp))){
					String innlinjer = null;
					antallLinjerprFil = 0;
					do{
						innlinjer = innfil.readLine();
						if(innlinjer != null)
							antallLinjerprFil ++;
					}while(innlinjer != null );	
					
				}catch(IOException ioe){
					System.out.println("error!!!!");
				}
				print += "\t   <---Antall kodelinjer = " + antallLinjerprFil;

				String fil = filliste[i];
				System.out.println(temp);					
				antallLinjer += antallLinjerprFil;					
			}
			antallFiler ++;	
			}//slutt på for
		utfelt.append("Tilsammen antall elementer i katalogen = " + antallFiler + print + "\nAntal kodelinjer i Java filer: " + antallLinjer );
	}

	public void fil(){
		if(!navn.isFile())
			return;
		utfelt.append(navn + " er en fil");
		String filnavn = navn.getName();
		System.out.println("filnavn = " + filnavn);

		String regexJAVA = "\\+\\.java";
		String regexTXT = "\\.+\\.txt";

		if( filnavn.matches(regexTXT) || filnavn.matches(regexJAVA) ){
			System.out.println("vi er i if");
			String match = "";
			match = ( filnavn.matches( regexTXT )) ? (" av type tekstfil") : ( "av type javafil");
			utfelt.append("\n"+match);
			lesfraFil();
		}
	} 

	public void lesfraFil(){
		try(BufferedReader innfil = new BufferedReader(new FileReader(navn))){
			String innlinjer = innfil.readLine();
			String utlinje = "";
			int linjeteller = 1;
			do{			
				utlinje = linjeteller + "   " + innlinjer;
				utfelt.append("\n" + utlinje);
						if(innlinjer != null)
							linjeteller ++;
				innlinjer = innfil.readLine();
			}while(innlinjer != null );	
		}catch(IOException ioe){
			System.out.println("error!!!!");
		}
	}

	public void skrivTilFil(){
		File minfil = name;
		JFileChooser filvelger = minfil;
		filvelger.setCurrentDirectory( new File("."));

		int resultat = filvelger.showSaveDialog( null );

		if( resultat == JFileChooser.APPROVE_OPTION ){
			String f = "kopi" + navn.getName();
			try( BufferedReader fil = new BufferedReader( new FileInputStream( navn.getName() ))){

			}catch(IOException ioe){
				System.out.println("error you cunt");
			}
		}
	}

	public String kopiFil( String fnavn, String enavn ){
		return "";
	}

	private class Lytter implements ActionListener{
		public void actionPerformed( ActionEvent e ){}
	}
}