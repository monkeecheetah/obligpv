// programutvikling oblig 1 OPPGAVE 3 
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// sub klasse av Roman2

import java.io.*;

public class NorskRoman2 extends Roman2{

	private String maalform;
	private static final String TYPE = "nroman";

	public NorskRoman2(){}

	public NorskRoman2(String f, String t, int s, double p, String sj, String m){
		super(TYPE, f, t, s, p, sj);
		maalform = m;
	}

	public void skrivTilFil( DataOutputStream output ) throws IOException{
		super.skrivTilFil(output);
		output.writeUTF( maalform );
	}

	public boolean lesFraFil( DataInputStream input) throws IOException{
		if( input != null){
			System.out.println("vi er i norskroman");
			super.lesFraFil( TYPE, input );
			maalform = input.readUTF();
			System.out.println("Målform= " + maalform );
			return true;
		}		
	 	return false;
	}

	public String toString(){
		return "\nNorsk Roman\n" + super.toString() + 
			"Målform: " + maalform + "\n";
	}
}