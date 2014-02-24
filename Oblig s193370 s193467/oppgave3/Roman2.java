// programutvikling oblig 1 OPPGAVE 3 
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// abstrakt sub klasse av bok, superklasse for NorskRoman2 og UtenlandsRoman2

import java.io.*;

public abstract class Roman2 extends Bok2 {
	private String sjanger;

	public Roman2(){}

	public Roman2(String boktype, String f, String t, int s, double p, String sj){
		super(boktype, f, t, s, p);
		sjanger = sj;
	}

	public void skrivTilFil( DataOutputStream output ) throws IOException{
		super.skrivTilFil(output);
		output.writeUTF( sjanger );
	}

	public boolean lesFraFil( String type, DataInputStream input) throws IOException{
		if( input != null){
			super.lesFraFil( type, input );
			sjanger = input.readUTF();
			return true;
		}		
	 	return false;
	}

	public String toString(){
		return super.toString() +
				"Sjanger: " + sjanger + "\n";  
	} 
}