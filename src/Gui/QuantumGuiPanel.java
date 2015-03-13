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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.plaf.basic.BasicProgressBarUI;

import qcv1.*;



public class QuantumGuiPanel extends JPanel implements ActionListener {
	static JTextArea console;
	static JButton start_butt;
	JButton confirmOptions;
	static JProgressBar loadingBar;
	static QAnimationTabbedPane animations;
	
	JComboBox gateRep;
	JComboBox moreOptions;
	JComboBox simType;
	
	JSpinner searchSpinner;

	JTextField qubitsNum;
	
	static boolean isLoaded;
	Boolean qubitSet;
	Boolean isReady;
	
	//center labels
	static JLabel data_status;
	JLabel qubits;
	JLabel gateType;
	JLabel speedUps;
	JLabel start;
	
	//This is bad that there's data here at all but it's seemingly unavoidable
	static HashMap<Integer, Integer> oracleMap;
	
	private JButton test_butt;
	
	QuantumGuiPanel(){
		setLayout(new BorderLayout());
		
		//NORTH (file menu)
		
		
		
		//SOUTH Console
		JPanel south = new JPanel();
		
		south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
		console = new JTextArea();
		console.setEditable(false);
		
//		
//		console.setPreferredSize(new Dimension(900, 200));
//		console.setMaximumSize(new Dimension(900, 200));
//		console.setMinimumSize(new Dimension(900, 200));
		
		
		JScrollPane scroll = new JScrollPane (console, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(900, 180));
		scroll.setMaximumSize(new Dimension(900, 180));
		scroll.setMinimumSize(new Dimension(900, 180));
		
		
		
		loadingBar = new JProgressBar(0, 100);
		loadingBar.setValue(0);
		//loadingBar.setString();
		loadingBar.setPreferredSize(new Dimension(1000, 15));
		//bar.setMaximumSize(new Dimension(775, 15));
		//bar.setMinimumSize(new Dimension(775, 15));

		loadingBar.setUI(new BasicProgressBarUI() {
			protected Color getSelectionBackground() { return Color.black; }
			protected Color getSelectionForeground() { return Color.white; }
		});

		loadingBar.setBackground(Color.WHITE);
		loadingBar.setForeground(Color.BLUE);
		loadingBar.setStringPainted(true);
		
		south.add(loadingBar);
		south.add(scroll);
		
		add(south, BorderLayout.SOUTH);
		
		
		//EAST options currently disabled

		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(200, 850));
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		//east.setBackground(Color.DARK_GRAY);
		
		Vector<String> repList = new Vector<String>();
		repList.add("Dense Matrix");
		repList.add("Sparse Matrix");
		repList.add("Functional");
		gateRep = new JComboBox(repList);
		
		
		JLabel qubitsCount = new JLabel("Qubits");
		//TODO change this to a ticker
		qubitsNum = new JTextField();
		
		Vector<String> optionList = new Vector<String>();
		optionList.add("none");
		optionList.add("String 2");
		moreOptions = new JComboBox(optionList);
		
		confirmOptions = new JButton("Confirm");
		confirmOptions.addActionListener(this);
		
		east.add(new XLabeledUnit(gateRep, "Gate Representation"));
		
		XLabeledUnit qubitsUnit = new XLabeledUnit(qubitsNum, "Qubits");
		qubitsUnit.setVisible(false);
		east.add(qubitsUnit);
		//TODO Write speedUp options, hiding until this is implemented
		//east.add(new MyLabeledUnit(moreOptions, "Speedup Options"));
		east.add(confirmOptions);
		
		//add(east, BorderLayout.EAST);
		
		//Center Graphs and charts
		//JPanel center  = new JPanel();
		//center.setBackground(Color.black);
		animations = new QAnimationTabbedPane();
		add(animations, BorderLayout.CENTER);

		
		
		//WEST Simulation selection and start button
		JPanel west = new JPanel();
		west.setBackground(Color.WHITE);
		
		west.setPreferredSize(new Dimension(300, 800));
		//west.setMaximumSize(new Dimension(800, 800));
		//west.setMinimumSize(new Dimension(1000, 800));
		
		
		data_status = new JLabel("Not Loaded");
		data_status.setForeground(Color.RED.darker());
		qubits = new JLabel("Qubits: ");
		//TODO temporarily changed because Grover's doesn't use qubits
		qubits.setVisible(false);
		gateType = new JLabel("Gate type: ");
		//TODO temporaily changed until speedups are added
		speedUps = new JLabel("Speedups: ");
		speedUps.setVisible(false);
		start = new JLabel("Not Ready");
		Vector<String> simulationsList = new Vector<String>();
		//simulationsList.add("<none>");
		simulationsList.add("Grover's algorithm");
		simulationsList.add("Shor's algorithm");
		//depending on selection this should expand to add more options
		simType = new JComboBox(simulationsList);
		start_butt = new JButton("GO!");
		start_butt.addActionListener(this);
		start_butt.setEnabled(true);
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		
		//index or factorized number option
		SpinnerModel model =
		        new SpinnerNumberModel(0, //initial value
		                               0, //min
		                               Integer.MAX_VALUE, //max
		                               1);   
		searchSpinner = new JSpinner(model);
		searchSpinner.setMaximumSize(new Dimension(50, 50));
		
		test_butt = new JButton("test it");
		test_butt.addActionListener(this);
		
		west.add(data_status);
		west.add(qubits);
		west.add(new XLabeledUnit(simType, "Simulaton"));
		west.add(new XLabeledUnit(searchSpinner, "Value:"));
		//west.add(gateType);
		west.add(new XLabeledUnit(gateRep, "Gate Representation"));
		west.add(speedUps);
		//west.add(start);
		
		west.add(start_butt);
		//west.add(test_butt);
		add(west, BorderLayout.WEST);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == test_butt){
			QViewModel.updateHistogramValues(new double[] {2, 4, 8, 6, 2});
			QViewModel.setVector(2, (int) Math.ceil(Math.random() * 5));
		}
		else if(e.getSource() == start_butt){
			//given the following information, launch a simulation in a separate thread/threads
			/*
			 * number of Qubits
			 * type of simulation
			 * gate type
			 * speedUp options
			 * data loaded from file
			 * number/index being searched for
			 */	
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
			
				
			int numQubits = (int) Math.ceil(Math.log10(oracleMap.size())/Math.log10(2));
			String speedUpString = moreOptions.getSelectedItem().toString();
			String simulationType = simType.getSelectedItem().toString();
			int searchValue = (int) searchSpinner.getValue();
			
			//get the indices of the solutions
			ArrayList<Integer> indices = new ArrayList<Integer>();
			for (Entry<Integer, Integer> entry : oracleMap.entrySet()){
				if (entry.getValue() == searchValue){
					indices.add(entry.getKey());
				}
			}
			//convert back to a regular array
			int [] targets = new int [indices.size()];
			for (int i = 0; i < targets.length; i++){
				targets[i] = indices.get(i);
			}
			//int index = oracleMap.get(searchValue);
			
			/*int numQubits = 3;
			int [] targets = null;*/
			QProcess sim = new QProcess(simulationType, numQubits, gateString, speedUpString, targets);
		}
		else{
		console.append("huh? action function unwritten");
		}
	}
	
	public static void setLoaded(boolean b){
		isLoaded = b;
		start_butt.setEnabled(b);
		if(b){
			data_status.setText("Loaded");
			data_status.setForeground(Color.GREEN.darker());
		}
		else{
			data_status.setText("Not Loaded");
			data_status.setForeground(Color.RED.darker());
			
		}
	}
	
	public class YLabeledUnit extends JPanel{
		Component component;
		String s;
		JLabel label;
		public YLabeledUnit(Component o, String s){
			component = o;
			o.setMaximumSize(new Dimension(150, 25));
			this.s = s;
			label = new JLabel(s);
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
	public class XLabeledUnit extends JPanel{
		Component component;
		String s;
		JLabel label;
		public XLabeledUnit(Component o, String s){
			component = o;
			o.setMaximumSize(new Dimension(200, 25));
			this.s = s;
			label = new JLabel(s);
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

