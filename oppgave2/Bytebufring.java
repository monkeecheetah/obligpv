import java.io.*;
import javax.swing.*;

public class Bytebufring
{
  public static void main(String[] args)
	{
		File f = null;
    boolean okfil = false;
    //ber først brukeren velge fil det skal skrives til
		do
		{
			JFileChooser filvelger = new JFileChooser();
			filvelger.setCurrentDirectory( new File( "." ) );
			int resultat = filvelger.showSaveDialog( null );
			if ( resultat == JFileChooser.APPROVE_OPTION )
			{
				f = filvelger.getSelectedFile();
				if ( !f.exists() )
					okfil = true;
				else
					JOptionPane.showMessageDialog( null, 
							"Fila eksisterer allerede!\n" +
							"Du må velge et annet navn.", 
							"Advarsel", JOptionPane.WARNING_MESSAGE );
			}
			else
			{
				JOptionPane.showMessageDialog( null, 
						"Du har ikke valgt utfil!\n" +
						"Programmet vil bli avsluttet.", 
						"Advarsel", JOptionPane.WARNING_MESSAGE );
				System.exit( 0 );
			}
		} while ( !okfil );
		//åpner fil det skal leses fra og fil det skal skrives til
    try (BufferedInputStream in = new BufferedInputStream( 
            new FileInputStream("farrago.txt"));
            BufferedOutputStream out = new BufferedOutputStream( 
                    new FileOutputStream(f)))
		{
			int c;

			while ((c = in.read()) != -1)
				out.write(c);
		}
		catch ( FileNotFoundException fnfe )
		{
			System.out.println( "Finner ikke fil det skal leses fra." );
		}
		catch ( IOException ioe )
		{
			System.out.println( "Problem med fillesing eller skriving." );
		}
		JOptionPane.showMessageDialog( null, "Du kan nå åpne og lese " + 
				"fila " + f.getName() + " som ble skrevet ut." );
	}
}
