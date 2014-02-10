import java.io.*;
public class UtenlandsRoman2 extends Roman2{
	private String origSpraak;

	public UtenlandsRoman2(){}

	public UtenlandsRoman2(String f, String t, int s, double p, String sj, String o){
		super("uroman", f, t, s, p, sj);
		origSpraak = o;		
	}

	public void skrivTilFil( DataOutputStream output ) throws IOException{
		super.skrivTilFil(output);
		output.writeUTF( origSpraak );
		//< Skriver datafeltenes verdier til fil. >
	}

	public boolean lesFraFil( DataInputStream input) throws IOException{
		if( input != null){
			super.lesFraFil( input );
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