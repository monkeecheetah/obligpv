import java.io.*;
public class Fagbok2 extends Bok2{

	private String fagbok;
	private  String typeBok = "fagbok";	public Fagbok2(){}

	public Fagbok2(String f, String t, int s, double p, String fb){
		super("fagbok", f, t, s, p);
		fagbok = fb;
	}

	public void skrivTilFil( DataOutputStream output ) throws IOException{
		super.skrivTilFil(output);
		output.writeUTF( fagbok );
		//< Skriver datafeltenes verdier til fil. >
	}

	public boolean lesFraFil( DataInputStream input) throws IOException{
		if( input != null){
			super.lesFraFil( input );
			fagbok = input.readUTF();
			return true;
		}
		
	 	//< Leser verdier fra fil og plaserer dem i de tilhørende datafeltene>
	 	return false;
	}

	public String toString(){

		return "\nFagbok\n" + 
				super.toString() + 
				"Fagbok: " + fagbok + "\n";
	}

}