// programmutvikling oblig 1 OPPGAVE 3 
// stine marie aas grumheden s193467
// kristoffer johansen s193370
// klasse HINGDATA13H1AA
//Bokregister setter inn objekter av klassen Bok2 og dens subklasser i en liste
//samt skriver datastrukturen til fil og leser fra fil ved oppstart.

import javax.swing.*;
import java.io.*;

public class Bokregister2{
  private Bok2 head;

  public void settInn( Bok2 ny )
  {
    System.out.println("vi er i sett inn metoden");
    if(ny == null){
      return;
    }
    ny.neste = head;
    head = ny;
  }

  public void skrivTilfil( String filnavn ){
    try( DataOutputStream fil = new DataOutputStream( new FileOutputStream(filnavn) ) ){
      Bok2 pointer = head;
      while( pointer != null ){
        pointer.skrivTilFil( fil );
        pointer = pointer.neste;
      }
    }catch( IOException ioe){
        System.out.equals( "Fikk ikke skrevet datafil");
    }
  }

  public void lesFraFil( String filnavn ){
    try( DataInputStream fil = new DataInputStream( new FileInputStream(filnavn))){
      String t = fil.readUTF();  
      while( true ){
      System.out.println( "fil.readUTF() = " + t );

        if(t.equals("fagbok") ){
          System.out.println("vi er i 'fagbok' sin if gren ");
          Fagbok2 nyFagbok = new Fagbok2();
          nyFagbok.lesFraFil( fil );
          settInn( nyFagbok );
        }else if(t.equals("skolebok") ){
          Skolebok2 nySkolebok = new Skolebok2();
          nySkolebok.lesFraFil( fil );
          settInn( nySkolebok );
        }else if(t.equals("uroman") ){
          UtenlandsRoman2 nyURoman = new UtenlandsRoman2();
          nyURoman.lesFraFil( fil );
          settInn( nyURoman );
        }else if(t.equals("nroman") ){
          NorskRoman2 nyNRoman = new NorskRoman2();
          nyNRoman.lesFraFil( fil );
          settInn( nyNRoman );
        }
        t = fil.readUTF();
      }
    }catch( FileNotFoundException fnfe ){
      System.err.println("Finner ikke fil" + filnavn );
    }catch( EOFException eofe ){

    }catch( IOException ioe ){
      System.err.println("Får ikke lest fil " + filnavn );
    }
  }

  public void skrivListe( JTextArea b){
    if(head == null){
      b.setText("Ingen bøker i listen");
      return;
    }

    Bok2 pointer = head;
    b.setText( "Bokregister \n" );

    while(pointer != null){
      b.append( pointer.toString() );
      pointer = pointer.neste;
    }
  }
}