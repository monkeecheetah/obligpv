import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class testGUI extends JFrame
{
	public String smurf = "Jeg er en smurf";
	public testGUI() {
		super("bilsauer");
		setLayout( new FlowLayout() );
		setSize(550, 400);
		setVisible(true);
	}
}