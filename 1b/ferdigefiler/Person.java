import java.io.*;

public class Person extends Bileier implements Serializable {

	private int personNummer;

	public Person( String n, String a, int p ) {
		super(n,a);
		personNummer = p;
	}

	public int getPId() {
		return personNummer;
	}	
	public String toString(){
		String output = "";
		output = super.toString();
		output += "\n"+personNummer;
		return output;
	}	
}