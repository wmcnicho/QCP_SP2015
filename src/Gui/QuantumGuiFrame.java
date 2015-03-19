package Gui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

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
		
		
		redirectSysError();
		
		setVisible(true);
	}
	
	private void redirectSysError(){
		 OutputStream out = new OutputStream() {
			    @Override
			    public void write(int b) throws IOException {
			     QViewModel.printToConsole(b);
			    }
			 
			    @Override
			    public void write(byte[] b, int off, int len) throws IOException {
			    QViewModel.printToConsole(new String(b, off, len));
			    }
			 
			    @Override
			    public void write(byte[] b) throws IOException {
			     QViewModel.printToConsole(new String(b, 0, b.length));
			    }
		};	 
		System.setErr(new PrintStream(out, true));
	}

	public static void main(String[] args) {
		
		QuantumGuiFrame qFrame = new QuantumGuiFrame();
		qFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
