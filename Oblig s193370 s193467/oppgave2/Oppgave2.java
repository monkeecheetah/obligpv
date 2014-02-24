// programutvikling oblig 1 OPPGAVE 2 
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Oppgave2 extends JFrame{

	private String tilTekstfil;
	private JButton lagre;
	private JTextArea utfelt;

	private File navn;
	private File temp;

	private Lytter lytt;
	
	public Oppgave2( String n ) {
		super("superdialogvindu");
		tilTekstfil = "";
		lytt = new Lytter();
		Container c = getContentPane();
		lagVindu(c);

		navn = new File (n);	

		start(c);
	}


	public void lagVindu(Container c){

		c.setLayout( new FlowLayout() );

		lagre = new JButton("LAGRE");
		utfelt = new JTextArea( 30,40);
		c.add( new JLabel("Innlesingsfelt") );	

		c.add( utfelt );
		utfelt.setEditable(false);
		add( new JScrollPane( utfelt ));
		c.add(lagre);
		setSize(550,650);
		setVisible(true);
	}

	public void start(Container c){
		while( !navn.exists() ){
			utfelt.setText("Finner ikke fil eller katalog");
			String filnavn = JOptionPane.showInputDialog(null, "Skriv navnet på en fil eller en katalog");
			navn = new File(filnavn);
		}
		
		if(navn.isDirectory() ){
		 	katalog();
		}
		else if(navn.isFile()){
			fil(c);
		}
	}

	public void katalog(){
		String print = " ";
		String[]filliste = navn.list();
		String regex = "\\w+\\.java";
		int antallFiler = 0;
		int antallLinjerprFil = 0;
		int antallLinjer = 0;

		utfelt.setText( navn + " er en Katalog\n");

		utfelt.append("\nNavn på filer i katalogen:\n\n");
	
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
				antallLinjer += antallLinjerprFil;					
			}
			antallFiler ++;	
			}
		utfelt.append( print +"\n\n-------------------------------------\nAntal kodelinjer i Java filer: " + antallLinjer + "\nTilsammen antall elementer i katalogen = " + antallFiler);
	}

	public void fil(Container c){
		utfelt.setText(navn + " er en fil");
		String filnavn = navn.getName();
		String regexJAVA = "\\w+.java";
		String regexTXT = "\\w+.txt";

		if( filnavn.matches(regexTXT) || filnavn.matches(regexJAVA) ){

			String match = "";
			match = ( filnavn.matches( regexTXT )) ? (" av type tekstfil") : ( "av type javafil");
			utfelt.append("\n"+match);
			lesfraFil();
		}


		lagre.addActionListener(lytt);
	} 

	public void lesfraFil(){
		try(BufferedReader innfil = new BufferedReader(new FileReader(navn)))
		{
			String innlinjer = innfil.readLine();
			String utlinje = "";

			int linjeteller = 1;
			do{			
				utlinje = linjeteller + "   " + innlinjer;
				tilTekstfil += ("\n" + utlinje);
						if(innlinjer != null)
							linjeteller ++;
				innlinjer = innfil.readLine();
			
			}while(innlinjer != null );	

		}catch(IOException ioe){
			System.out.println("error!!!!");
		}
	}

	public void skrivTilFil(){		
		JFileChooser filvelger = new JFileChooser();
		filvelger.setCurrentDirectory( new File("."));
		int resultat = filvelger.showSaveDialog( this );
		File fil;

		if( resultat == JFileChooser.APPROVE_OPTION ){
			fil = filvelger.getSelectedFile();
			String nyFil = fil.getPath();
		
			try(BufferedWriter utfil = new BufferedWriter(new FileWriter(nyFil))){
				utfil.write(tilTekstfil);
				utfelt.append("\n\n-------------------------------\nFilen er lagret som " + fil.getName());
				utfelt.append("\n\n-------------------------------\n"
					+ tilTekstfil);


			}catch(IOException ioe){
				System.out.println("error");
			}

		}
	}

	private class Lytter implements ActionListener{
		public void actionPerformed( ActionEvent e ){
			if(e.getSource() == lagre){
				skrivTilFil();
			}
		}
	}
}