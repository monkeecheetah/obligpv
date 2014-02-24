import javax.swing.JTextArea;
import java.io.*;

public class Bileierliste implements Serializable {
	private Bileier first;

	public Bileierliste(){
		first = null;
	}

	public Bileier getFirst() {
		return first;
	}

	public Bil finnBil(int r) {
		if(first == null) {
			return null;
		}
		Bileier pointer = first;
		Billiste tempListe;
		Bil funnet;
		while(pointer != null){
			tempListe = pointer.getBilliste();
			if(tempListe.findBiler(r) != null) {
				return tempListe.findBiler(r);
			}
			pointer = pointer.next;
		}
		return null;
	}

	public Bileier finnEier(int r) {
		if(first == null) {
			return null;
		}
		Bileier pointer = first;
		Billiste tempListe;
		Bil funnet;
		while(pointer != null){
			tempListe = pointer.getBilliste();
			if(tempListe.findBiler(r) != null) {		
				return pointer;
			}
			pointer = pointer.next;
		}
		return null;
	}

	public String giveMeAllCars() {
		String output = "";
		if(first == null) {
                        output += "Det finnes ingen bileiere";
			return output;
		}
		Bileier pointer = first;
		Billiste tempListe;
		Bil funnet;
		while(pointer != null){
			tempListe = pointer.getBilliste();
			output += pointer.toString()+"\n";
			output += "Har disse bilene:\n";
			output += tempListe.listOfCars();
                        output += "\n";
			pointer = pointer.next;
		}
		return output;
	}

	public Bil slettBil(int r) {

		if(first == null) {
			return null;
		}
		Bileier pointer = first;
		Billiste tempListe;
		Bil funnet;
		while(pointer != null){
			tempListe = pointer.getBilliste();
			if(tempListe.findBiler(r) == null) {
				
			} else if(tempListe.findBiler(r) != null) {
				funnet = tempListe.findBiler(r);
				tempListe.removeCar(r);
				return funnet;
			}
			pointer = pointer.next;
		}
		return null;

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

	public boolean finnesPersonId(int i) {
		if(first == null) {
			return false;
		}
		Bileier pointer = first;

		while(pointer != null){
			if(pointer instanceof Person){
				Person temp = (Person) pointer;
				if(temp.getPId() == i) {
					return true;
				}				
			}

			pointer = pointer.next;
		}
		return false;
	}

	public boolean finnesFirmaId(int i) {
		if(first == null) {
			return false;
		}
		Bileier pointer = first;
		while(pointer != null){
			if(pointer instanceof Firma) {
				Firma temp = (Firma) pointer;
				if(temp.getFId() == i) {
					return true;
				}
			}

			pointer = pointer.next;
		}
		return false;
	}

	public boolean finnesBilEier(String n) {
		if(first == null) {
			return false;
		}
		Bileier pointer = first;
		while(pointer != null){
			if(pointer.getNavn().equals(n) ) {
				return true;
			}
			pointer = pointer.next;
		}
		return false;
	}

	public Bileier findEier(String n) {
		Bileier pointer = first;
		while (pointer != null && !(pointer.getNavn().equals(n)))
			pointer = pointer.next;
		return pointer;
	}

	public boolean removeEier(String n) {
		if(first == null) //tom liste
			return false;
		if(first.getNavn().equals(n) && first.getBilliste() != null) {
			first = first.next;
			return true;
		}
		Bileier pointer = first;
		while (pointer.next != null) {
			if (pointer.next.getNavn().equals(n)) {
				pointer.next = pointer.next.next;
				return true;
			}
			pointer = pointer.next;
		}
		return false;
	}

	public void emptyEierList() {
		first = null;
	}

	public void writeList(JTextArea elements) {
		elements.setText("");
		if (first == null)
			elements.append( "Tom bileierliste :(\n" );
		else {
			Bileier pointer = first;
			while (pointer != null){
				elements.append(pointer.toString() + "\n");
				pointer = pointer.next;
			}
			elements.append("\n");
		}
	}
}