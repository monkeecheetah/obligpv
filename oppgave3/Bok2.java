import java.io.*;

public abstract class Bok2{

	private String forfatter, tittel, typeBok;
	private int sideantall;
	private double pris;
	Bok2 neste;

	public Bok2(){}

	public Bok2( String type, String f, String t, int s, double p){
		typeBok = type;
		forfatter = f;
		tittel = t;
		sideantall = s;
		pris = p;
		neste = null;
	}

	public void skrivTilFil( DataOutputStream output ) throws IOException{
		output.writeUTF( typeBok );
		output.writeUTF( forfatter );
		output.writeUTF( tittel );
		output.writeInt( sideantall );
		output.writeDouble( pris );

		//< Skriver datafeltenes verdier til fil. >
	}

	public boolean lesFraFil( DataInputStream input) throws IOException{
		if( input != null){
			System.out.println("vi er i lesFraFil metoden til Bok");
			//typeBok = input.readUTF();
			//System.out.println("typeBok =" + typeBok);
			forfatter = input.readUTF();
			System.out.println("Forfatter =" + forfatter);
			tittel = input.readUTF();
			System.out.println("Tittel =" + tittel);
			sideantall = input.readInt();
			pris = input.readDouble();
			return true;
		}
		return false;
	}

	public String toString(){
		return "\nForfatter: " + forfatter + "\n"+
				"Tittel: " + tittel + "\n" +
				"Sideantall: " + sideantall + "\n" +
				"Pris: " + pris + "kr " + "\n";
	}
}