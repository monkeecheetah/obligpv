import java.io.*;

public class NorskRoman2 extends Roman2{

	private String maalform;

	public NorskRoman2(){}

	public NorskRoman2(String f, String t, int s, double p, String sj, String m){
		super("nroman", f, t, s, p, sj);
		maalform = m;
	}

	public void skrivTilFil( DataOutputStream output ) throws IOException{
		super.skrivTilFil(output);
		output.writeUTF( maalform );
		//< Skriver datafeltenes verdier til fil. >
	}

	public boolean lesFraFil( DataInputStream input) throws IOException{
		if( input != null){
			System.out.println("vi er i norskroman");
			super.lesFraFil( input );
			maalform = input.readUTF();
			System.out.println("Målform= " + maalform );
			return true;
		}		
	 	//< Leser verdier fra fil og plaserer dem i de tilhørende datafeltene>
	 	return false;
	}

	public String toString(){
		return "\nNorsk Roman\n" + super.toString() + 
			"Målform: " + maalform + "\n";
	}
}