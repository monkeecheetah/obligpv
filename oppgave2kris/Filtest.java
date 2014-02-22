//Demonstrerer File-klassen
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.Date;
import java.text.DateFormat;

public class Filtest extends JFrame
{
  private JTextField input;
  private JTextArea output;
  private JButton openFile, saveFile;
  private Tekstfeltlytter kommandolytter;
  private String filePath = null;
  private File f = null;
  private boolean okfil = false;
  public Filtest() {
    super("Tester File-klassen");
    kommandolytter = new Tekstfeltlytter();
    input = new JTextField("Skriv fil- eller katalog-navn her", 35);
    input.addActionListener(kommandolytter);
    output = new JTextArea(20, 35);
    output.setEditable(false);
    setLayout( new FlowLayout() );

    openFile = new JButton("Open file");
    openFile.addActionListener(kommandolytter);
    add(openFile);

    saveFile = new JButton("Save file");
    saveFile.addActionListener(kommandolytter);
    add(saveFile);    

    add(input);
    add(new JScrollPane(output));
    setSize(900, 900);
    setVisible(true);
  }

  public String velgFil() {
    return JOptionPane.showInputDialog(this, "Skriv inn katalog eller filnavn");
  }

  public void saveFile() {
    JFileChooser filvelger = new JFileChooser();
    filvelger.setCurrentDirectory( new File( "." ) );
    int resultat = filvelger.showSaveDialog( null );
    if ( resultat == JFileChooser.APPROVE_OPTION ) {
      try {
      f = filvelger.getSelectedFile();
      System.out.println(f.getPath());

      FileWriter fw = new FileWriter(f.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(output.getText());
      bw.close();
 
      } catch (IOException e) {
      e.printStackTrace();
      }
    } else {
      System.out.println("We speak no americano");
    }
  }

  // public String velgFil() {
  //   JFileChooser filvelger = new JFileChooser();
  //   filvelger.setCurrentDirectory( new File( "." ) );
  //   int resultat = filvelger.showOpenDialog( this );
  //   if ( resultat == JFileChooser.APPROVE_OPTION ) {  
  //     //bruker har klikket pa Open-knappen
  //     File fil = filvelger.getSelectedFile();
  //     return fil.getPath();
  //   }
  //   else //bruker har klikket pa Cancel-knappen eller lukkeknappen
  //     return null;
  //   }

  public void sjekkFil(String fil) {
    output.setText("");
    File navn = new File(fil);

    if (navn.exists()) {
      output.append(
          navn.getName() + " eksisterer\n" +
          (navn.isFile() ? "er en fil\n" : "er ikke en fil\n") +
          (navn.isDirectory() ? "er en katalog\n" : 
              "er ikke en katalog\n") +
          (navn.isAbsolute() ? "er absolutt sti\n" : 
              "er ikke absolutt sti\n") +
          "Sist modifisert: ");
      long millisek = navn.lastModified();
      Date dato = new Date(millisek);
      DateFormat formatterer = DateFormat.getInstance();
      String tidspunkt = formatterer.format(dato);
      output.append(tidspunkt + 
              "\nLengde: " + navn.length() + 
              "\nSti: " + navn.getPath() + 
              "\nAbsolutt sti: " + navn.getAbsolutePath() + 
              "\nForelder: " + navn.getParent());

      if (navn.isFile())
      { // leser og viser fila
        try (BufferedReader innfil = new BufferedReader(
                new FileReader(navn)))
        {
          output.append("\n\n");
          String innlinje = null;

          do
          {
            innlinje = innfil.readLine();
            if (innlinje != null)
              output.append(innlinje + "\n");
          }
          while (innlinje != null);
        }
        catch (IOException e2)
        {
        }
      }
      else if (navn.isDirectory())
      { // viser fillista
        String[] dir = navn.list();
        output.append("\n\nKatalog inneholder:\n");

        for (int i = 0; i < dir.length; i++)
          output.append(dir[i] + "\n");
      }
    }
    else
      output.setText(fil + " eksisterer ikke.\n");
    output.setCaretPosition(0);
  }

  private class Tekstfeltlytter implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if(e.getSource() == openFile) {
        filePath = velgFil();
        sjekkFil(filePath);
      } else if(e.getSource() == saveFile) {
        saveFile();
      }      
      else {
        sjekkFil(e.getActionCommand());
      }
    }
  }
}
