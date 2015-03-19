package Gui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**The starting point for the entire program that contains the main function. Essentially creates a JFrame
 * window and adds a QuantumMenuBar and QuantumGuiPanel to that Frame.
 * 
 * @author William Hunter McNichols
 */
public class QuantumGuiFrame extends JFrame {
	JMenuBar bar;
	QuantumGuiPanel mainPanel;
	
	/**Sets up the main frame of the program adding the QuantumMenuBar and QuantumGuiPanel */
	QuantumGuiFrame(){
		super("Quantum Computer Simulator");
		setSize(1000,850);

		bar = new QuantumMenuBar();
		setJMenuBar(bar);
		
		
		mainPanel = new QuantumGuiPanel();
		add(mainPanel);
		
		
		redirectSysError();
		
		setVisible(true);
	}
	/**This function redirects all systems error messages to print at the QuantumGuiPanel console instead of the standard console.
	 * This is done to give the user better feedback when the heap space overflows upon using too large of a data set.
	 */
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

	/**Starting point for the program. Just creates a QuantumGuiFrame*/
	public static void main(String[] args) {
		
		QuantumGuiFrame qFrame = new QuantumGuiFrame();
		qFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
