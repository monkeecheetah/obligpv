public class Bileierliste {
	private Bileier first;

	public Bileierliste(){
		first = null;
	}

	public void insertPerson(String n, String a, int p) {
		Person ny = new Person(n, a, p);
		ny.next = first;
		first = ny;
	}
	public void insertFirma(String n, String a, int f) {
		Firma ny = new Firma(n, a, f);
		ny.next = first;
		first = ny;
	}	
}