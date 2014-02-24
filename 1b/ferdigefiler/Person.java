// programutvikling oblig 1 OPPGAVE 1b
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// Subklasse til Bileier-klassen

import java.io.*;

public class Person extends Bileier implements Serializable {

	private int personNummer;

	public Person( String n, String a, int p ) {
		super(n,a);
		personNummer = p;
	}

	public int getPId() {
		return personNummer;
	}	
	public String toString(){
		String output = "";
		output = super.toString();
		output += "\nPersonnummer: "+personNummer;
		return output;
	}	
}