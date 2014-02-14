import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BilEierGUI extends JFrame
{
	private JTextArea eierListen2;
	private JButton skrivListe;
	private JTextField navn;

	private Kommandolytter lytteren;

	public BilEierGUI() {
		super("Biler");
		lytteren = new Kommandolytter();
		setLayout( new FlowLayout() );		

		skrivListe = new JButton("Skriv ut billiste");
		skrivListe.addActionListener(lytteren);
		add(skrivListe);

		add( new JLabel( "Biloversikt:" ) );
		eierListen2 = new JTextArea( 10, 45 );
		eierListen2.setEditable( false );
		add( new JScrollPane( eierListen2 ) );
		
		setSize(500, 800);
		setVisible(true);
	}
	public void printList() {

	}

	private class Kommandolytter implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			if ( e.getSource() == skrivListe ) {
				printList();
			}
		}
	}
}