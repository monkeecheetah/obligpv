public class Firma extends Bileier {

	private int foretaksNummer;

	public Firma( String n, String a, int f ) {
		super(n,a);
		foretaksNummer = f;
	}
	public int getFId() {
		return foretaksNummer;
	}
	public String toString(){
		String output = "";
		output = super.toString();
		output += "\n"+foretaksNummer;
		return output;
	}

}