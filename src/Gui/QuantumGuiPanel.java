package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class QuantumGuiPanel extends JPanel implements ActionListener {
	JTextArea console;
	JButton start_butt;
	JProgressBar loadingBar;
	
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
		
		
		JLabel matrixRep = new JLabel("Matrix Representation");
		//TODO add drop down for Matrix Representation
		
		JLabel qubitsCount = new JLabel("Qubits");
		//TODO add ticker
		
		JLabel extendedOptions = new JLabel("Speedup Options");
		//TODO add drop down for multithreading/gpu options
		
		east.add(matrixRep);
		east.add(qubitsCount);
		east.add(extendedOptions);
		
		add(east, BorderLayout.EAST);
		
		//Center checklist and go button
		JPanel center = new JPanel();
		//JButton center = new JButton("Center");
		
		//center.setPreferredSize(new Dimension(1000, 800));
		//center.setMaximumSize(new Dimension(800, 800));
		//center.setMinimumSize(new Dimension(1000, 800));
		
		
		JLabel data_status = new JLabel("Not loaded");
		JLabel qubits = new JLabel("Qubits: ");
		JLabel start = new JLabel("Not Ready");
		//TODO: add dropdown menu with different simulations
		start_butt = new JButton("GO!");
		start_butt.addActionListener(this);
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		
		center.add(data_status);
		center.add(qubits);
		center.add(start);
		center.add(start_butt);
	
		add(center, BorderLayout.CENTER);
		
		
		//WEST Currently empty
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start_butt){
		console.append("Pressed shhit!\n");
		}
		else{
		console.append("huh?");
		}
	}
}
