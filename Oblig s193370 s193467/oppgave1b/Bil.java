// programutvikling oblig 1 OPPGAVE 1b
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// Klassen bil som blir satt i billiste

import java.io.*;

public class Bil implements Serializable  {
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
		StringBuilder bygger = new StringBuilder();
		bygger.append(carBrand);
		bygger.append(" ");
		bygger.append(carType);
		bygger.append("\nRegistreringsnummer: ");
		bygger.append(regNr);
		bygger.append("\nRegistrert: ");
		bygger.append(regY);
		bygger.append("\n");
                String tekst = bygger.toString();
		return tekst;
	}
}