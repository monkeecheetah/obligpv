// programmutvikling oblig 1 OPPGAVE 3 
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// den abstrakte superklassen til Skolebok2, Fagbok2 og den abstrakte superklassen Roman2. 
//Roman2 er superklassen til NorskRoman2 og UtenlandsRoman2

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
	}

	public boolean lesFraFil( String type, DataInputStream input) throws IOException{
		if( input != null){
			System.out.println("vi er i lesFraFil metoden til Bok");
			System.out.println("Vi er i lesFra");
			typeBok = type;
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