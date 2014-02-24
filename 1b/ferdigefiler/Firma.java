// programutvikling oblig 1 OPPGAVE 1b 
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// Subklasse til Bileier-klassen

import java.io.*;

public class Firma extends Bileier implements Serializable {

	private int foretaksNummer;

	public Firma( String n, String a, int f ) {
		super(n,a);
		foretaksNummer = f;
	}
	public int getFId() {
		return foretaksNummer;
	}
	public String toString(){
		String output = "";
		output = super.toString();
		output += "\nOrgNr: "+foretaksNummer;
		return output;
	}

}