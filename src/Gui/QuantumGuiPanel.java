package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.text.NumberFormatter;

import qcv1.*;


/** The main panel placed into the frame on launching the program. Conatins the console, animations, 
 * and input controls for the program
 * 
 * @author William Hunter McNichols
 */
public class QuantumGuiPanel extends JPanel implements ActionListener {
	//UI elements that can be adjusted from QViewModel
	static JTextArea console;
	static JButton start_butt;
	static JProgressBar loadingBar;
	static QAnimationTabbedPane animations;
	static boolean isLoaded;

	
	JComboBox gateRep;
	JComboBox simType;
	JSpinner inputSpinner;
	XLabeledUnit inputUnit;

	
	//This is bad that there's model data here at all but it's seemingly unavoidable
	static HashMap<Integer, Integer> oracleMap;
	
	private JButton test_butt;
	
	/**Constructor that orders all of the elements of the panel and sets all their default values. Primarily uses a BorderLayout filled with BoxLayouts */
	public QuantumGuiPanel(){
		setLayout(new BorderLayout());
		
		//SOUTH Console and loading bar
		JPanel south = new JPanel();
		south.setBackground(Color.white);
		south.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));		
		south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
		
		console = new JTextArea();
		console.setEditable(false);
		
		JScrollPane scroll = new JScrollPane (console, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(900, 180));
		scroll.setMaximumSize(new Dimension(900, 180));
		scroll.setMinimumSize(new Dimension(900, 180));
		
		loadingBar = new JProgressBar(0, 100);
		loadingBar.setValue(0);
		//loadingBar.setString();
		loadingBar.setPreferredSize(new Dimension(900, 15));
		loadingBar.setMaximumSize(new Dimension(900, 15));
		UIManager.put("ProgressBar.selectionBackground", Color.blue);
	    UIManager.put("ProgressBar.selectionForeground", Color.white);
	    UIManager.put("ProgressBar.foreground", Color.black);
	    UIManager.put("ProgressBar.background", Color.gray);
		loadingBar.setStringPainted(true);
		loadingBar.setBorder(BorderFactory.createLineBorder(Color.black));
		
	
		south.add(loadingBar);
		south.add(Box.createRigidArea(new Dimension(0,5)));
		south.add(scroll);
		
		add(south, BorderLayout.SOUTH);
		
		//CENTER Graphs and charts, see @class QAnimationTabbedPane
		animations = new QAnimationTabbedPane();
		add(animations, BorderLayout.CENTER);		
		
		//WEST Simulation selection and start button
		JPanel west = new JPanel();
		west.setBackground(Color.WHITE);
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		west.setPreferredSize(new Dimension(300, 800));
		//west.setMaximumSize(new Dimension(800, 800));
		west.setMinimumSize(new Dimension(300, 800));
		west.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		Vector<String> repList = new Vector<String>();
		repList.add("Dense Matrix");
		repList.add("Sparse Matrix");
		repList.add("Functional");
		gateRep = new JComboBox(repList);
		XLabeledUnit gateUnit = new XLabeledUnit(gateRep, "Gate Representation");
		//gateUnit.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		Vector<String> simulationsList = new Vector<String>();
		simulationsList.add("Grover's algorithm");
		simulationsList.add("Shor's algorithm");
		//depending on selection this should expand to add more options
		simType = new JComboBox(simulationsList);
		simType.addActionListener(this);
		
		//index or factorized number option
		SpinnerModel model =
		        new SpinnerNumberModel(0, //initial value
		                               0, //min
		                               Integer.MAX_VALUE, //max
		                               1);   
		inputSpinner = new JSpinner(model);
		inputSpinner.setMaximumSize(new Dimension(50, 50));
		//the following should prevent non-number input
		JFormattedTextField txt = ((JSpinner.NumberEditor) inputSpinner.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
		inputUnit = new XLabeledUnit(inputSpinner, "Value:");
		//inputUnit.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		start_butt = new JButton("Please load data");
		start_butt.addActionListener(this);
		start_butt.setEnabled(false);
		start_butt.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		west.add(new XLabeledUnit(simType, "Simulaton"));
		west.add(inputUnit);
		west.add(gateUnit);	
		west.add(start_butt);
		
		add(west, BorderLayout.WEST);
		
		
	}

	/**The event handling function of the panel. Determines which event to handle based off the input e. 
	 * Called upon any action which includes: Changing the simulation type or starting a simulation
	 * @param e		the ActionEvent that triggered the call to this function 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == test_butt){
			QViewModel.updateHistogramValues(new double[] {2, 4, 8, 6, 2});
			QViewModel.setVector(2, (int) Math.ceil(Math.random() * 5));
		}
		else if(e.getSource() == start_butt){
			QViewModel.clearConsole();
			//determine the gate representation
			String gateString = gateRep.getSelectedItem().toString();
			if(gateString.equals("Dense Matrix")){
				gateString = "complex";
			}
			else if (gateString.equals("Sparse Matrix")){
				gateString = "gate";
			}
			else if (gateString.equals("Functional")){
				gateString = "functional";
			}
			else{
				gateString = "gate";
			}
			String simulationType = simType.getSelectedItem().toString();
			String speedUpString ="";//deprecated
			int [] data = null;
			int numQubits = 0;
			
			switch (simulationType){
				case "Grover's algorithm":
					numQubits = (int) Math.ceil(Math.log10(oracleMap.size())/Math.log10(2));
					int searchValue = (int) inputSpinner.getValue();
					
					//get the indices of the solutions
					ArrayList<Integer> indices = new ArrayList<Integer>();
					for (Entry<Integer, Integer> entry : oracleMap.entrySet()){
						if (entry.getValue() == searchValue){
							indices.add(entry.getKey());
						}
					}
					//convert back to a regular array
					data = new int [indices.size()];
					for (int i = 0; i < data.length; i++){
						data[i] = indices.get(i);
					}
					if(data.length == 0){
						console.append("Value not in list. Canceling Grover's simulation");
						return;
					}
				break;
				
				case "Shor's algorithm":
					if((int) inputSpinner.getValue() > 1023 ||(int) inputSpinner.getValue() < 2){
						console.append("Invlaid Shor's input. See 'Help' for more info on running simulations.");
						return;
					}
					data = new int [] {(int) inputSpinner.getValue()};
				break;
			}
			QProcess sim = new QProcess(simulationType, numQubits, gateString, speedUpString, data);
		}
		else if(e.getSource() == simType){
			if(simType.getSelectedItem().toString().equalsIgnoreCase("Grover's Algorithm")){
				start_butt.setEnabled(isLoaded);
				if(isLoaded){
					start_butt.setText("Start Grover's");
				}
				else{
					start_butt.setText("Please load data");
				}
				inputUnit.label.setText("Value:");
				animations.showVector(true);
			}
			else if(simType.getSelectedItem().toString().equalsIgnoreCase("Shor's Algorithm")){
				start_butt.setEnabled(true);
				start_butt.setText("Start Shor's");
				inputUnit.label.setText("Number:");
				animations.showVector(false);
			}
		}
		else{
		console.append("huh? action function unwritten");
		}
	}
	/**Called when the data is loaded from file or when the program wants the user to input new data. Updates the button to allow the simulation to start.
	 * @param b 	A boolean indicating if the data is loaded or unloaded.*/
	public static void setLoaded(boolean b){
		isLoaded = b;
		start_butt.setEnabled(b);
		if(b){
			start_butt.setText("Start Grover's");
		}
		else{
			start_butt.setText("Please load data");
			
		}
	}
	
	/**An internal class used to simplify code in the rest of the program. Links a component and a label in a vertical fashion */
	private class YLabeledUnit extends JPanel{
		Component component;
		String s;
		JLabel label;
		public YLabeledUnit(Component o, String s){
			this.setBackground(Color.white);
			component = o;
			//component.setBackground(Color.white);
			o.setMaximumSize(new Dimension(500, 25));
			this.s = s;
			label = new JLabel(s);
			//label.setBackground(Color.white);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(label);
			add(o);
		}
		@Override
		public void setVisible(boolean b){
			component.setVisible(b);
			label.setVisible(b);
		}
	}
	/**An internal class used to simplify code in the rest of the program. Links a component and a label in a horizontal fashion */
	private class XLabeledUnit extends JPanel{
		Component component;
		String s;
		JLabel label;
		public XLabeledUnit(Component o, String s){
			this.setBackground(Color.white);
			component = o;
			//component.setBackground(Color.white);
			o.setMaximumSize(new Dimension(500, 25));
			this.s = s;
			label = new JLabel(s);
			//label.setBackground(Color.white);	
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			add(label);
			add(o);
		}
		@Override
		public void setVisible(boolean b){
			component.setVisible(b);
			label.setVisible(b);
		}
	}
	
}

