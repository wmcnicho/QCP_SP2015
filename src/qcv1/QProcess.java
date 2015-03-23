package qcv1;

import Gui.QViewModel;

/**
 * The main class of the simulator. It allows one to run 
 * the Grover's algorithm and Shor's algorithm.
 * @author Michael Chiang
 *
 */
public class QProcess {
	//final needed because a separate thread is generated
	/**
	 * Create a thread to run Grover's algorithm or Shor's algorithm
	 * @param simulationType Type of simulation (Grover's algorithm or Shor's algorithm)
	 * @param numQubits Number of qubits
	 * @param gateRep Representation of the gate
	 * @param data Data required for each algorithm
	 * @param updateGui Whether or not to update gui
	 */
	public QProcess(final String simulationType, final int numQubits,
			final String gateRep, final int [] data, final boolean updateGui){
			Thread runThread = new Thread(){
			public void run(){
			//call constructor	
				MRegister reg = new MRegister(numQubits, "complex");
				
				QViewModel.printToConsole("Starting Calculation...");
				long t1 = System.nanoTime();
				
				QCircuit q = null;
				switch (simulationType){
				//run Grover's algorithm
				case "Grover's algorithm":
					q = new GroverGateByGate(gateRep, data, numQubits, updateGui);
					q.applyCircuit(reg);
					int result = reg.measure();
					QViewModel.printToConsole(String.format("Found index: %d", result));
					break;
					
				//run Shor's algorithm
				case "Shor's algorithm":
					int num = data[0];
					ShorsAlgorithm shors = new ShorsAlgorithm(gateRep, num);
					int [] factors = shors.run();
					QViewModel.printToConsole("The factors of " + num + " are " + factors[0] + " and " + factors[1]);
					break;
				default:
					//do nothing
				}
				
				//output statistics of the run time
				double runtime = (System.nanoTime()-t1)/(Math.pow(10,9));
				int runtimeMins = (int) Math.floor(runtime/60.0);
				int runtimeSecs = (int) (Math.floor(runtime) % 60);
				int runtimeMSecs = (int) (Math.floor(runtime*1000) % 1000);
				QViewModel.printToConsole("Calculation ended.");
				QViewModel.printToConsole("Total Runtime: "+runtimeMins+" min "+runtimeSecs+" sec "+runtimeMSecs+" msecs");
			}
		};
		runThread.start();	
	}
}
