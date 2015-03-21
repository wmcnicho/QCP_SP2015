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

/**The class the handles all of the menu bar operations for the program which includes opening a file for test data and
 * opening the help screen to explain program usage.
 * 
 * @author William Hunter McNichols
 */
public class QuantumMenuBar extends JMenuBar implements ActionListener{
	JMenu menu1;
	JMenuItem file_open, help;
	JFileChooser fc;
	
	/**Sets up the menu bar adding the appropriate buttons*/
	public QuantumMenuBar(){
		menu1 = new JMenu("File");
		fc = new JFileChooser();	
		String workingDir = System.getProperty("user.dir");
		fc.setCurrentDirectory(new File(workingDir));
		
		file_open = new JMenuItem("Open File");
		file_open.addActionListener(this);
		menu1.add(file_open);
		help = new JMenuItem("Help");
		help.addActionListener(this);
		add(menu1);
		add(help);
	}
	/**The event handling function of the panel. Determines which event to handle based off the input e. 
	 * Called upon clicking a menu option which is either "File"->"Open" or "Help"
	 * @param e		the ActionEvent that triggered the call to this function 
	 */
	public void actionPerformed(ActionEvent e) {
		//Uses the FileParser to parse the selected file and returns it to the QuantumGuiPanel
		if (e.getSource() == file_open) {
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				HashMap<Integer, Integer> parsedMap = FileParser.parseFile(file.getAbsolutePath());
				QViewModel.updateHashMap(parsedMap);
			}
			else {
				QViewModel.printToConsole("Open command cancelled by user.");
			}
			
		}
		//Creates a new window that gives the user help on running the simulations
		else if(e.getSource() == help){
			JFrame helpFrame = new JFrame("Help");
			helpFrame.setSize(new Dimension(500, 300));
			helpFrame.setLayout(new BorderLayout());
			helpFrame.add(new JLabel("<html><body> <h1> Basic Usage </h1> <p> Use the drop down"
					+ "menu to the left to choose between running Shor's or Grover's algorithm. "
					+ "In order to start a Grover's simulation, import a txt file with comma separated numbers"
					+ "(i.e. 3,14,157,9,...) and a enter value to search for. To use Shor's choose an integer "
					+ "between 2 to 1023 and it will be factorized.</p> </body></html>"), BorderLayout.CENTER);
			helpFrame.setVisible(true);
			helpFrame.setLocationRelativeTo(null);
		}
	}



}
