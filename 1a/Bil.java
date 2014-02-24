// programutvikling oblig 1 OPPGAVE 1a
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// Klassen bil

import java.io.*;

public class Bil  {
	private String carBrand, carType; 
	private int regNr, regY;
	Bil next;

	public Bil(String cB, String cT, int rN, int rY) {
		carBrand = cB;
		carType = cT;
		regNr = rN;		
		regY = rY;
		next = null;
	}
	public int getRegNr() {
		return regNr;
	}
	public String toString() {
		return "Regnr: "+regNr+"\nBilmerke: "+carBrand+""+"\nBiltype: "+carType+"\nRegår:"+regY+"\n";
	}
}