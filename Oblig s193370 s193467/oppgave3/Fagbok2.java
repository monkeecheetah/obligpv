// programutvikling oblig 1 OPPGAVE 3 
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// sub klasse av Bok2

import java.io.*;
public class Fagbok2 extends Bok2{

	private String fagbok;
	private  static final String TYPE = "fagbok";	
	public Fagbok2(){}

	public Fagbok2(String f, String t, int s, double p, String fb){
		super(TYPE, f, t, s, p);
		System.out.println("vi er i fagbok");
		fagbok = fb;
		System.out.println("Fagområde = " + fb);
	}

	public void skrivTilFil( DataOutputStream output ) throws IOException{
		super.skrivTilFil(output);
		output.writeUTF( fagbok );
	}

	public boolean lesFraFil( DataInputStream input) throws IOException{
		if( input != null){
			super.lesFraFil( TYPE, input );
			fagbok = input.readUTF();
			return true;
		}
	 	return false;
	}

	public String toString(){
		return "\nFagbok\n" + 
				super.toString() + 
				"Fagbok: " + fagbok + "\n";
	}

}