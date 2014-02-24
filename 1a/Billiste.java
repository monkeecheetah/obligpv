// programutvikling oblig 1 OPPGAVE 1a
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
// Klassen Billiste med en liste av alle bilene

import javax.swing.JTextArea;
import java.io.*;
 
public class Billiste {
	private Bil first;

	public Billiste() {
		first = null;
	} 

	public void insertBil(String cB, String cT, int rN, int rY) {
		Bil ny = new Bil(cB, cT, rN, rY);
    	ny.next = first;
    	first = ny;
	}

	public Bil find(int n) {
		Bil find = first;
		while (first != null && first.getRegNr() != n)
			find = find.next;
		return find;
	}

	public boolean removeCar(int r) {
		if(first == null) 
			return false;
		if(first.getRegNr() == r) {
			first = first.next;
			return true;
		}
		Bil pointer = first;
		while (pointer.next != null) {
			if (pointer.next.getRegNr() == r) {
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
			elements.append( "Tom billiste :(\n" );
		else {
			Bil pointer = first;
			while (pointer != null){
				elements.append(pointer.toString() + " ");
				pointer = pointer.next;
			}
			elements.append("\n");
		}
	}
}