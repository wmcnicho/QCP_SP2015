package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicProgressBarUI;

import simulator.QProcess;



public class QuantumGuiPanel extends JPanel implements ActionListener {
	JTextArea console;
	JButton start_butt;
	JButton confirmOptions;
	JProgressBar loadingBar;
	
	JComboBox gateRep;
	JComboBox moreOptions;

	JTextField qubitsNum;
	
	Boolean isLoaded;
	Boolean qubitSet;
	Boolean isReady;
	
	//center labels
	JLabel data_status;
	JLabel qubits;
	JLabel gateType;
	JLabel speedUps;
	JLabel start;
	
	QuantumGuiPanel(){
		setLayout(new BorderLayout());
		
		//NORTH (file menu)
		
		
		
		//SOUTH Console
		JPanel south = new JPanel();
		
		south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
		console = new JTextArea();
		console.setEditable(false);
		
		console.setPreferredSize(new Dimension(900, 200));
		//center.setMaximumSize(new Dimension(800, 800));
		//center.setMinimumSize(new Dimension(1000, 800))
		
		loadingBar = new JProgressBar(0, 100);
		loadingBar.setValue(1);
		loadingBar.setString("1%");
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
		south.add(new JScrollPane(console));
		
		add(south, BorderLayout.SOUTH);
		
		
		//EAST options

		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(200, 850));
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		//east.setBackground(Color.DARK_GRAY);
		
		Vector<String> repList = new Vector<String>();
		repList.add("Functional");
		repList.add("String 2");
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
		
		east.add(new MyLabeledUnit(gateRep, "Gate Representation"));
		east.add(new MyLabeledUnit(qubitsNum, "Qubits"));
		east.add(new MyLabeledUnit(moreOptions, "Speedup Options"));
		east.add(confirmOptions);
		
		add(east, BorderLayout.EAST);
		
		//Center checklist and go button
		JPanel center = new JPanel();
		center.setBackground(Color.WHITE);
		
		//center.setPreferredSize(new Dimension(1000, 800));
		//center.setMaximumSize(new Dimension(800, 800));
		//center.setMinimumSize(new Dimension(1000, 800));
		
		
		data_status = new JLabel("Not loaded");
		qubits = new JLabel("Qubits: ");
		gateType = new JLabel("Gate type: ");
		speedUps = new JLabel("Speedups: ");
		start = new JLabel("Not Ready");
		Vector<String> simulationsList = new Vector<String>();
		simulationsList.add("<none>");
		simulationsList.add("Grover's algorithm");
		//depending on selection this should expand to add more options
		JComboBox simType = new JComboBox(simulationsList);
		start_butt = new JButton("GO!");
		start_butt.addActionListener(this);
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		
		
		center.add(data_status);
		center.add(qubits);
		center.add(new MyLabeledUnit(simType, "Simulaton"));
		center.add(gateType);
		center.add(speedUps);
		center.add(start);
		
		center.add(start_butt);
	
		add(center, BorderLayout.CENTER);
		
		
		//WEST Currently empty
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == confirmOptions){
		//edit the center stuff
			gateRep.getSelectedItem().toString();
			qubitsNum.getText();
			moreOptions.getSelectedItem().toString();
			
			qubits.setText("Qubits: " + qubitsNum.getText());
			//TODO set qubit number in local model
			gateType.setText("Gate type: " + gateRep.getSelectedItem().toString());
			speedUps.setText("Speedups: " + moreOptions.getSelectedItem().toString());
			//TODO update start label and button
			System.out.println("updated");
		}
		else if(e.getSource() == start_butt){
			Thread runThread = new Thread(){
        		public void run(){
        			//call constructor
		//given the following information, launch a simulation in a separate thread/threads
			/*
			 * number of Qubits
			 * type of simulation
			 * gate type
			 * speedUp options
			 * data loaded from file
			 */
        				QProcess sim = new QProcess();
        		}
			};
			runThread.start();
		}
		else{
			console.append("huh? action function unwritten");
		}
	}
	
	public class MyLabeledUnit extends JPanel{
		Component component;
		String s;
		JLabel label;
		public MyLabeledUnit(Component o, String s){
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
	
}

