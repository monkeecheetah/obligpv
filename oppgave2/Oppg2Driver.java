import javax.swing.*;

public class Oppg2Driver{
	public static void main(String[]args){
	String filnavn = JOptionPane.showInputDialog(null, "Skriv navnet på en fil eller en katalog");
	Oppgave2 oppg = new Oppgave2( filnavn );
	oppg.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	}
}