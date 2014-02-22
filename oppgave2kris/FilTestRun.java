import java.awt.event.*;

public class FilTestRun {
	public static void main(String[] args) {
		final FilGUI vindu = new FilGUI();
		System.out.println("Du har apnet et vindu");
		vindu.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});		
	}
}