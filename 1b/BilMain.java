import java.awt.event.*;

public class BilMain {
	public static void main(String[] args) {
		final BilGUI vindu = new BilGUI();
//		final BilEierGUI vindu2 = new BilEierGUI();
		System.out.println("Du har apnet et vindu");
	//final for ? kunne gj?re aksess p? lokal variabel 
	//fra anonym indre klasse
		vindu.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
/*		vindu2.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});		*/
	}
}