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
		System.out.println("Vi er i skriv til fil i bok");
		System.out.println("Type bok = " + typeBok);
		System.out.println("forfatter =" + forfatter);
		System.out.println("Tittel = " + tittel);
		System.out.println("Sideantall = " + sideantall);
		System.out.println("Pris = " + pris);
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
			forfatter = input.readUTF();
			System.out.println("Forfatter = " + forfatter);
			tittel = input.readUTF();
			System.out.println("Tittel = " + tittel);
			sideantall = input.readInt();
			System.out.println("sideantall = " + sideantall);
			pris = input.readDouble();
			System.out.println("pris = " + pris);
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