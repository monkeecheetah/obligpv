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
		//< Skriver datafeltenes verdier til fil. >
	}

	public boolean lesFraFil( DataInputStream input) throws IOException{
		System.out.println("Vi er i fagbok les Fra fil metoden");
		if( input != null){
			super.lesFraFil( TYPE, input );
			fagbok = input.readUTF();
			System.out.println("Fagområde =" + fagbok);

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