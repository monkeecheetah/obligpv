import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FilGUI extends JFrame {

	private JTextArea output;
	private JButton openFile, saveFile;
	private Kommandolytter lytteren;

	public FilGUI() {
		super("Filleser");
		lytteren = new Kommandolytter();
		setLayout(new FlowLayout());

		openFile = new JButton("Open file");
		openFile.addActionListener(lytteren);
		add(openFile);

		saveFile = new JButton("Save file");
		saveFile.addActionListener(lytteren);
		add(saveFile);		

		add( new JLabel( "Output:" ) );
		output = new JTextArea( 10, 45 );
		output.setEditable( false );
		add( new JScrollPane( output ) );

		setSize(550, 400);
		setVisible(true);
	}
	private class Kommandolytter implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			if ( e.getSource() == openFile ) {
				//printList();
			}
			else if(e.getSource() == saveFile) {
				//
			}
		}
	}
}