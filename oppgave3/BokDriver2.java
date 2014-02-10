//import javax.swing.*;
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