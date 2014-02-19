import java.io.*;
import javax.swing.*;

public class FilbehandlerDriver{
	public static void main(String[]args){
		File f = null;
		boolean okfil = false;

		do{
			JFileChooser filvelger = new JFileChooser();
			filvelger.setCurrentDirectory( new File( "." ) );
			int resultat = filvelger.showSaveDialog( null );
			if( resultat == JFileChooser.APPROVE_OPTION ){
				f = filvelger.getSelectedFile();
				if( !f.exists() )
					okfil = true;
				else{
					JOptionPane.showMessageDialog (null, "Fila eksisterer allerede!\n", "Advarsel", JOptionPane.WARNING_MESSAGE );
				}

			}else JOptionPane.showMessageDialog( null, "IngeN fil er valgt,\nProgrammet avsluttes", "Advarsel", JOptionPane.WARNING_MESSAGE );
			System.exit( 0 );
		}while(!okfil);

		try( BufferedInputStream in = new BufferedInputStream( new FileInputStream("src/farrago.txt" ) );
			BufferedOutputStream out = new BufferedOutputStream( new FileOutputStream(f)))
			{ 
				int c;

				while ((c= in.read()) != -1)
					out.write(c);
		}catch ( FileNotFoundException fnfe ){
				System.out.println("Finner ikke fil det skal leses fra.");
		}catch( IOException ioe){
				System.out.println("Problemer med fillesing eller skriving");
			
		}
	}
}