// programutvikling oblig 1 OPPGAVE 1b 
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// Abstractklasse type bileiere

import java.io.*;
public abstract class Bileier implements Serializable {
	private String navn, adresse;
	private Billiste billiste = new Billiste();
	Bileier next;

	public Bileier(String n, String a) {
		navn = n;
		adresse = a;
		next =  null;
	}
	public Billiste getBilliste() {
		return billiste;
	}

	public String getNavn() {
		return navn;
	}

	public String toString() {
		return "Navn:"+navn+"\nAdresse:"+adresse;
	}
}