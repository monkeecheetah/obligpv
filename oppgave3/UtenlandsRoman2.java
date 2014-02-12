// programmutvikling oblig 1 OPPGAVE 3 
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// sub klasse av Roman2

import java.io.*;
public class UtenlandsRoman2 extends Roman2{
	private String origSpraak;
	private final static String TYPE = "uroman";

	public UtenlandsRoman2(){}

	public UtenlandsRoman2(String f, String t, int s, double p, String sj, String o){
		super(TYPE, f, t, s, p, sj);
		origSpraak = o;		
	}

	public void skrivTilFil( DataOutputStream output ) throws IOException{
		super.skrivTilFil(output);
		output.writeUTF( origSpraak );
	}

	public boolean lesFraFil( DataInputStream input) throws IOException{
		if( input != null){
			super.lesFraFil(TYPE, input );
			origSpraak = input.readUTF();
			return true;
		}
		return false;
	}

	public String toString(){
		return "\nUtenlandsk Roman\n" + super.toString() + 
				"Originalsprål: " + origSpraak + "\n";
	}
}