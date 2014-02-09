/*
Den dynamiske lista Billiste trenger kun et datafelt, nemlig en peker til forste Bil-objekt i lista, men skal ha folgende metoder:

metode som setter et nytt Bil-objekt inn i lista
metode som finner et gitt Bil-objekt inn i lista ut fra bilens registreringsnummer
metode som fjerner et nytt Bil-objekt fra lista ut fra bilens registreringsnummer
metode som avgjor om lista er tom eller ikke
metode som returnerer en tekst med informasjon om alle bilene i lista
Lag et brukergrensesnitt og et hovedprogram som gjor det mulig a teste ut alle metodene.
*/
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
		if(first == null) //tom liste
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
				elements.append(pointer.getRegNr() + " ");
				pointer = pointer.next;
			}
			elements.append("\n");
		}
	}


/*  public void settInnForrest(Bil ny)  
  {
    ny.next = first;
    first = ny;
  }  
*/

}