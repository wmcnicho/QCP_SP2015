package gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class QuantumMenuBar extends JMenuBar{
	JMenu menu, submenu;
	JMenuItem xml, clear;
	//JFileChooser fc;
	public QuantumMenuBar(){
		menu = new JMenu("File");
		//fc = new JFileChooser();
		
		//fc.setCurrentDirectory(new File("C:/Users/Hunter/github"));


		xml = new JMenuItem("Open File");
		//xml.addActionListener(this);
		clear = new JMenuItem("Option 2");
		//clear.addActionListener(this);

		menu.add(xml);
		menu.add(clear);
		
		add(menu);
	}


}
