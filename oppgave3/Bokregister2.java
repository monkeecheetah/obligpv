import javax.swing.*;
import java.io.*;

public class Bokregister2{
  private Bok2 head;

  // private Bokvindu = vindu; 
  //registrerer et bokobjekt
  public void settInn( Bok2 ny )
  {
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
      while( true ){
        String t = fil.readUTF();
        System.out.println( "fil.readUTF() = " + t );

        if(t.equals("fagbok") ){
          Fagbok2 ny = new Fagbok2();
          settInn( ny );
          ny.lesFraFil( fil );
        }else if(t.equals("skolebok") )
          settInn( new Skolebok2() );
        else if(t.equals("uroman") )
          settInn( new UtenlandsRoman2() );
        else if(t.equals("nroman") )
          settInn( new NorskRoman2() );
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