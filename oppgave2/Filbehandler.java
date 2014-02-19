import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Filbehandler{
	
	File f;
	Boolean okfil;

	public Filbehandler(){
		//super("Fil og Katalog Behandler");

		File f = null;
		boolean okfil = false;

	}

	public void openFile(){

	}


	public void lagreKopi(){
		do{
			JFileChooser filvelger = new JFileChooser();
			filvelger.setCurrentDirectory( new File("."));
			int resultat = filvelger.showSaveDialog(null);
			if( resultat == JFileChooser.APPROVE_OPTION )
			{
				f = filvelger.getSelectedFile();
				if( !f.exists() )
					okfil = true;
				else
					JOptionPane.showMessageDialog( null, "Denne filen eksisterer. Du må velge et annet navn.", "Advarsel", JOptionPane.WARNING_MESSAGE );
			}else{
				JOptionPane.showMessageDialog(null, "Du har ikke valgt utfil.\n Programmet avsluttes.", "Advarsel", JOptionPane.WARNING_MESSAGE );
				System.exit(0);
			}
		}while( !okfil ); 

		try(BufferedInputStream in = new BufferedInputStream( new FileInputStream( "farrago.txt" ) );
			BufferedOutputStream out = new BufferedOutputStream( new FileOutputStream (f))){
			int x;
			int antall = 0;

			while ( ( x = in.read() ) != -1 )
				out.write(x);
			System.out.println( out.write(x) );
		}catch( FileNotFoundException fnfe ){
		System.out.println(" Finner ikke fil det skal leses fra");
		}catch( IOException ioe){
			System.out.println("Problemer med fillesing eller skriving til fil");
		}
		JOptionPane.showMessageDialog( null, "Fillen er lagret som " + f.getName() );
	}

}