package simulator;

import simulator.mrep.MRegister;
import simulator.mrep.MatrixType;

import Gui.QViewModel;

public class QProcess {
	public QProcess(){}
	
	//final needed because a separate thread is generated
	public QProcess(final String simulationType, final int numQubits,
			final String gateRep, final String speedUpString){
			Thread runThread = new Thread(){
			public void run(){
			//call constructor	
				MRegister reg = new MRegister(MatrixType.SPARSE, numQubits);//change to refresh register
				
				QViewModel.printToConsole("Starting Calculation...");
				
				QCircuit q = null;
				switch (simulationType){
				case "Grover's algorithm":
					q = new GroverGateByGate(
							GateRep.SPARSE_MATRIX, reg.numOfStates(), 0, reg.numOfQubit(), reg.numOfStates());
					break;
				case "Shor's algorithm":
					
					break;
				default:
					
				}
				
				long t1 = System.nanoTime();
				q.applyCircuit(reg);
				reg.measure();
				reg.getProbabilities();
				double totalProb = 0;
				for (int i = 0; i < reg.numOfStates(); i++){
					double prob = Complex.magSquare(reg.getAmplitude(i));
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
