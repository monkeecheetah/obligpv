import java.awt.event.*;

public class BilMain {
	public static void main(String[] args) {
		final BilGUI vindu = new BilGUI();

		vindu.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}