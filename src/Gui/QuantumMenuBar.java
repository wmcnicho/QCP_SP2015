package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class QuantumMenuBar extends JMenuBar implements ActionListener{
	JMenu menu, submenu;
	JMenuItem file_open, clear;
	JFileChooser fc;
	public QuantumMenuBar(){
		menu = new JMenu("File");
		fc = new JFileChooser();
		
		//fc.setCurrentDirectory(new File("C:/Users/Hunter/github"));


		file_open = new JMenuItem("Open File");
		file_open.addActionListener(this);
		clear = new JMenuItem("Option 2");
		//clear.addActionListener(this);

		menu.add(file_open);
		menu.add(clear);
		
		add(menu);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == file_open) {
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				//System.out.println(file.getAbsolutePath());
				//System.out.println(file.getName());
				HashMap<Integer, Integer> oracleMap = FileParser.parseFile(file.getAbsolutePath());
				//Given the selected file name print that out to the screen
				System.out.println(oracleMap.toString());
			}
			else {
				System.out.println("Open command cancelled by user.");
			}
			
		}
	}



}
