import java.awt.event.*;

public class BilMain {
	public static void main(String[] args) {
		final BilGUI vindu = new BilGUI();

	//final for ? kunne gj?re aksess p? lokal variabel 
	//fra anonym indre klasse
		vindu.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {
				vindu.skrivTilFil();
				System.exit(0);
			}
		});
/*		vindu2.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});		*/
	}
}