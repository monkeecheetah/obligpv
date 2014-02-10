/*Dere skal na lage register over bileiere. Klassen Bileier skal vare en abstrakt superklasse til de to konkrete subklassene Person og Firma. Ogsa dette registeret skal lages som en dynamisk liste, Bileierliste, der objektene i lista enten vil vare et Person-objekt eller et Firma-objekt, (og indirekte objekter av typen Bileier).

Den abstrakte superklassen Bileier skal ha folgende datafelt:

navn
adresse
liste av bilene (0 eller flere) som bileieren eier (Bruk billisten fra oppgave a)
en peker til neste Bileier-objekt i lista (Person-objekt eller Firma-objekt)
For bileiere av typen Person skal det dessuten registreres

personnummer (trenger ikke a vare et "ekte" personnummer)
For bileiere av type Firma skal det isteden registreres

foretaksnummer (trenger ikke a vare et "ekte" foretaksnummer)
(Foretaksnummer er noe som alle virkelige firmaer ma ha. Det brukes blant annet i forskjellige offentlige registre.)

*/
public abstract class Bileier {
	private String navn, adresse;
	private Billiste billiste = new Billiste();
	Bileier next;

	public Bileier(String n, String a) {
		navn = n;
		adresse = a;
		next =  null;
	}
}
/*
Programmet skal kunne utfore folgende:
registrere ny bileier av typen Person eller Firma
registrere ny bil og eier til denne
skrive ut hele registeret (alle bileiere og eventuelle biler de eier)
fjerne en bil fra registeret (bileieren skal da bli staende i registeret inntil denne eventuelt blir fjernet)
fjerne bileier fra registeret, men da under forutsetning av at vedkommende ikke lenger eier noen bil
hente ut data om eieren av en bil med gitt kjennetegn
registrere eierskifte for en bil (bilen ma fjernes fra gammel eier og registreres pa ny eier)
Sorg for at klassen Bileierliste har metoder som stotter disse funksjonene og lag et brukergrensesnitt og et hovedprogram som gjor det mulig a teste ut funksjonaliteten.

For hver av registreringsoperasjonene ma relevante data leses inn fra bruker. Personnummer skal identifisere personer og foretaksnummer skal identifisere firmaer. Ved registrering av en ny bileier skal derfor programmet hindre at den nye eieren far samme person- eller foretaksnummer som en bileier som allerede er registrert av programmet. Pa tilsvarende mate skal kjennetegn for biler identifisere biler, slik at programmet ma hindre dobbeltregistrering av bilkjennetegn.

I tilfelle eierskifte, ma det leses inn kjennetegn (bilnummer) for bilen som skal skifte eier, samt person- eller foretaksnummer for ny eier. Dersom ny eier ikke er registrert tidligere, ma denne registreres for eierskifte kan gjennomfores. All innlesing av data skal foretas pa "sikker" mate, dvs. ved bruk av try-catch (NB! Bruk try-catch med ressurser!) og/eller test pa at tekstfelt for nodvendige data er fylt ut.

Hver gang programmet blir avsluttet, skal alle data som er lagret i programmets datastruktur skrives ut til en fil for lagring. Ved oppstart skal eventuell fil med lagrede data leses inn og plasseres i programmets datastruktur. Bruk serialisering, der dele tildeler hver klasse (og subklasse) et eget versjonsnummer, og bruk filklassene ObjectOutputStream og ObjectInputStream ved utskrift til fil og innlesing fra fil. Programmer en egen vinduslytter til programmet.*/