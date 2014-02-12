import javax.swing.JTextArea;
import java.io.*;

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

	public Bileier find(String n) {
		Bileier find = first;
		while (first != null && !(first.getNavn().equals(n)))
			find = find.next;
		return find;
	}

	public boolean removeEier(String n) {
		if(first == null) //tom liste
			return false;
		if(first.getNavn().equals(n)) {
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

	public boolean emptyList() {
		if(first != null) {
			return false;
		}
		return true;
	}

	public void writeList(JTextArea elements) {
		elements.setText("");
		if (first == null)
			elements.append( "Tom bileierliste:(\n" );
		else {
			Bileier pointer = first;
			while (pointer != null){
				elements.append(pointer.getNavn() + "\n");
				pointer = pointer.next;
			}
			elements.append("\n");
		}
	}

}