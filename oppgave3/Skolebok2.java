import java.io.*;

public class Skolebok2 extends Bok2{
	private int klassetrinn;
	private String skolefag;

	public Skolebok2(){}

	public Skolebok2( String f, String t, int s, double p,  int k, String sf ){
		super("skolebok", f, t, s, p  );
		klassetrinn = k;
		skolefag = sf;
	}

	public void skrivTilFil( DataOutputStream output ) throws IOException{
		super.skrivTilFil(output);
		output.writeInt( klassetrinn );
		output.writeUTF( skolefag );
		//< Skriver datafeltenes verdier til fil. >
	}

	public boolean lesFraFil( DataInputStream input) throws IOException{
		if( input != null){
			super.lesFraFil( input );
			klassetrinn = input.readInt();
			skolefag = input.readUTF();
			return true;
		}
		return false;
	}

	public String toString(){
		 
		String s =  "\nSkolebok\n" + 
					super.toString() +
			  		"Klassetrinn: " + klassetrinn + "\n" + 
			  		"Fag: " + skolefag + "\n";

		return s;
	}
}