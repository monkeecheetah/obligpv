// programmutvikling oblig 1 OPPGAVE 3 
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
//Oppgave 3 sin driverklasse

import java.awt.event.*;

public class BokDriver2{	
	public static void main ( String [] args ){
		final Bokvindu2 vindu = new Bokvindu2();
		vindu.addWindowListener( 
			new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					vindu.skrivFil();
					System.exit(0);
			}
		} );
	}	
}