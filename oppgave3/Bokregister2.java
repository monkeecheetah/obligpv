import javax.swing.*;
import java.io.*;

public class Bokregister2{
  private Bok2 head;

  // private Bokvindu = vindu; 
  //registrerer et bokobjekt
  public void settInn( Bok2 ny )
  {
    System.out.println("vi er i sett inn metoden");
    if(ny == null){
      System.out.println("Objektet er tomt");
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
    String fagBok = "fagbok";  
      while( true ){
      String t = fil.readUTF();
      System.out.println( "fil.readUTF() = " + t );


        if(t.equals("fagbok") ){
          System.out.println("vi er i 'fagbok' sin if gren ");
          Fagbok2 nyFagbok = new Fagbok2();
          System.out.println("det er laget et nytt objekt av fagbok");
          nyFagbok.lesFraFil( fil );
          System.out.println("panda3");
          settInn( nyFagbok );
        }else if(t.equals("skolebok") ){
          Skolebok2 nySkolebok = new Skolebok2();
          nySkolebok.lesFraFil( fil );
          System.out.println("nySkolebok.lesFraFil( fil ) =" + nySkolebok.lesFraFil( fil ));
          settInn( nySkolebok );
        }else if(t.equals("uroman") ){
          UtenlandsRoman2 nyURoman = new UtenlandsRoman2();
          nyURoman.lesFraFil( fil );
          System.out.println("nyURoman.lesFraFil( fil ) =" + nyURoman.lesFraFil( fil ));
          settInn( nyURoman );
        }else if(t.equals("nroman") ){
          NorskRoman2 nyNRoman = new NorskRoman2();
          nyNRoman.lesFraFil( fil );
          System.out.println("nyNRoman.lesFraFil( fil ) =" + nyNRoman.lesFraFil( fil ));
          settInn( nyNRoman );
        }
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