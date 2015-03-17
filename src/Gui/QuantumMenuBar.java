package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class QuantumMenuBar extends JMenuBar implements ActionListener{
	JMenu menu1, menu2;
	JMenuItem file_open, help;
	JFileChooser fc;
	public QuantumMenuBar(){
		menu1 = new JMenu("File");
		//menu2 = new JMenu("Help");
		fc = new JFileChooser();	
		//fc.setCurrentDirectory(new File("C:/Users/Hunter/github"));
		file_open = new JMenuItem("Open File");
		file_open.addActionListener(this);
		menu1.add(file_open);
		help = new JMenuItem("Help");
		help.addActionListener(this);
		add(menu1);
		add(help);
		//add(menu2);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == file_open) {
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				//System.out.println(file.getAbsolutePath());
				//System.out.println(file.getName());
				HashMap<Integer, Integer> parsedMap = FileParser.parseFile(file.getAbsolutePath());
				//Given the selected file name print that out to the screen
				System.out.println(parsedMap.toString());
				QViewModel.updateHashMap(parsedMap);
			}
			else {
				System.out.println("Open command cancelled by user.");
			}
			
		}
		else if(e.getSource() == help){
			JFrame helpFrame = new JFrame("Help");
			helpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			helpFrame.setSize(new Dimension(500, 300));
			helpFrame.setLayout(new BorderLayout());
			helpFrame.add(new JLabel("<html><body> <h1> Basic Usage </h1> <p> Use the drop down"
					+ "menu to the left to choose between running Shor's or Grover's algorithm."
					+ "In order to start a Grover's simulation, import a txt file with comma separated numbers"
					+ "(i.e. 3,14,157,9,...) and a value to search for. To use Shor's chose a number between ?"
					+ " and ?? </p> </body></html>"), BorderLayout.CENTER);
			helpFrame.setVisible(true);
			helpFrame.setLocationRelativeTo(null);
			
		}
	}



}
