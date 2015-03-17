package qcv1;

import Gui.QViewModel;

public class QProcess {
	//final needed because a separate thread is generated
	
	public QProcess(final String simulationType, final int numQubits,
			final String gateRep, final String speedUpString, final int [] indexOfVal){
			Thread runThread = new Thread(){
			public void run(){
			//call constructor	
				int numOfStates = (int) Math.pow(2,numQubits);
				MRegister reg = new MRegister(numQubits, "complex");//change to refresh register		

				QViewModel.printToConsole("Starting Calculation...");
				long t1 = System.nanoTime();
				
				QCircuit q = null;
				switch (simulationType){
				case "Grover's algorithm":
					q = new GroverGateByGate(gateRep, numOfStates, indexOfVal, numQubits, numOfStates);
					q.applyCircuit(reg);
					break;
				case "Shor's algorithm":
					int num = indexOfVal[0];
					ShorsAlgorithm shors = new ShorsAlgorithm(gateRep, num);
					int [] factors = shors.run();
					QViewModel.printToConsole("The factors of " + num + " are " + factors[0] + " and " + factors[1]);
					break;
				default:
					
				}
				double totalProb = 0;
				for (int i = 0; i < reg.numOfStates(); i++){
					double prob = Complex.magSquare(reg.getAmplitude(i));
					QViewModel.printToConsole("The probability of state " + i + " is: " + prob);
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
