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
		//< Skriver datafeltenes verdier til fil. >
	}

	public boolean lesFraFil( DataInputStream input) throws IOException{
		if( input != null){
			super.lesFraFil( input );
			sjanger = input.readUTF();
			return true;
		}
		
	 	//< Leser verdier fra fil og plaserer dem i de tilhørende datafeltene>
	 	return false;
	}

	public String toString(){
		return super.toString() +
				"Sjanger: " + sjanger + "\n";  
	} 
}