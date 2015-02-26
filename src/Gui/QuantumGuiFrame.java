package gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class QuantumGuiFrame extends JFrame {
	
	QuantumGuiFrame(){
		super("Quantum Simulator");
		setSize(1000,850);

		JMenuBar bar = new QuantumMenuBar();
		setJMenuBar(bar);
		
		
		QuantumGuiPanel mainPanel = new QuantumGuiPanel();
		add(mainPanel);
		
		
		setVisible(true);
	}

	public static void main(String[] args) {
		
		QuantumGuiFrame qFrame = new QuantumGuiFrame();
		qFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
