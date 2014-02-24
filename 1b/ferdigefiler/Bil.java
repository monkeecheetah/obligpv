/*
Dere skal lage bilregister som lagrer informasjon om forskjellige biler. Registeret skal lages som en dynamisk liste der hvert objekt i lista er av typen Bil. Klassen Bil skal ha folgende datafelt:

kjennetegn (registreringsnummer)
merke og type (f.eks. Ferrari Testarossa)
arstall for forste gangs registrering
en peker til neste bil-objekt i lista
Klassen Bil skal ha folgende metoder:

konstruktor som initialiserer datafeltene
get-metode for kjennetegn
en toString-metode

*/
import java.io.*;

public class Bil implements Serializable  {
	private String carBrand, carType; 
	private int regNr, regY;
	Bil next;

	public Bil(String cB, String cT, int rN, int rY) {
		carBrand = cB;
		carType = cT;
		regNr = rN;		
		regY = rY;
		next = null;
	}
	public int getRegNr() {
		return regNr;
	}
	public String toString() {
		StringBuilder bygger = new StringBuilder();
		bygger.append(carBrand);
		bygger.append(" ");
		bygger.append(carType);
		bygger.append("\nRegistreringsnummer: ");
		bygger.append(regNr);
		bygger.append("\nRegistrert: ");
		bygger.append(regY);
		bygger.append("\n");
                String tekst = bygger.toString();
		return tekst;
	}
}