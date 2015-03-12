package Gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class QuantumGuiFrame extends JFrame {
	JMenuBar bar;
	QuantumGuiPanel mainPanel;
	
	QuantumGuiFrame(){
		super("Quantum Simulator");
		setSize(1000,850);

		bar = new QuantumMenuBar();
		setJMenuBar(bar);
		
		
		mainPanel = new QuantumGuiPanel();
		add(mainPanel);
		
		
		setVisible(true);
	}

	public static void main(String[] args) {
		
		QuantumGuiFrame qFrame = new QuantumGuiFrame();
		qFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
