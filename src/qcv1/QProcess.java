package qcv1;

import Gui.QViewModel;

public class QProcess {
	public QProcess(){
		 /* number of Qubits
		 * type of simulation
		 * gate type
		 * speedUp options
		 * data loaded from file
		 * */
//		MRegister reg = MRegister.getInstance();
//		reg.setRegister(4);
//		reg.printAmplitude();
//		
//		long t1 = System.nanoTime();
//		System.out.println("Starting Calculation...");
//
//		GroverGateByGate grover = new GroverGateByGate(reg.numOfStates(), 12);
//		grover.applyCircuit();
//		reg.printAmplitude();
//		double totalProb = 0;
//		for (int i = 0; i < reg.numOfStates(); i++){
//			totalProb += Math.pow(reg.getAmplitude(i),2);
//		}
//		System.out.println(totalProb);
//		double runtime = (System.nanoTime()-t1)/(Math.pow(10,9));
//		int runtimeMins = (int) Math.floor(runtime/60.0);
//		int runtimeSecs = (int) (Math.floor(runtime) % 60);
//		int runtimeMSecs = (int) (Math.floor(runtime*1000) % 1000);
//		System.out.println("Calculation ended.");
//		System.out.println("Total Runtime: "+runtimeMins+" min "+runtimeSecs+" sec "+runtimeMSecs+" msecs");

	}
	//final needed because a separate thread is generated
public QProcess(final String simulationType, final int numQubits, final String gateRep, final String speedUpString){
	Thread runThread = new Thread(){
			public void run(){
			//call constructor		
				MRegister reg = MRegister.getInstance();//change to refresh register		
				int numOfStates = (int) Math.pow(2,numQubits);
				
				
				QViewModel.printToConsole("Starting Calculation...");
				
				QCircuit q = null;
				switch (simulationType){
				case "Grover's algorithm":
					q = new GroverGateByGate(gateRep, numOfStates, numQubits);
					break;
				case "Shor's algorithm":
					
					break;
				default:
					
				}
				
				long t1 = System.nanoTime();
				q.applyCircuit();
				double totalProb = 0;
				for (int i = 0; i < reg.numOfStates(); i++){
					double prob = Math.pow(reg.getAmplitude(i),2);
					QViewModel.printToConsole("The probability of state " + (i+1) + " is: " + prob);
					totalProb += prob;
				}
				QViewModel.printToConsole(totalProb);
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
