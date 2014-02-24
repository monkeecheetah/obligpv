// programutvikling oblig 1 OPPGAVE 1a
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// Klassen mainklassen for Bil

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