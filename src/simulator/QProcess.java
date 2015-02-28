package simulator;

import simulator.mrep.MRegister;
import simulator.mrep.MatrixType;

public class QProcess {
	public QProcess(){
		 /* number of Qubits
		 * type of simulation
		 * gate type
		 * speedUp options
		 * data loaded from file
		 * */
		MRegister reg = new MRegister(MatrixType.SPARSE, 1);
		reg.setEqualAmplitude();
		
		long t1 = System.nanoTime();
		System.out.println("Starting Calculation...");

		GroverGateByGate grover = new GroverGateByGate(
				GateRep.SPARSE_MATRIX, reg.numOfStates(), 0, reg.numOfQubit(), reg.numOfStates());
		grover.applyCircuit(reg);
		reg.measure();
		reg.getProbabilities();
		double runtime = (System.nanoTime()-t1)/(Math.pow(10,9));
		int runtimeMins = (int) Math.floor(runtime/60.0);
		int runtimeSecs = (int) (Math.floor(runtime) % 60);
		int runtimeMSecs = (int) (Math.floor(runtime*1000) % 1000);
		System.out.println("Calculation ended.");
		System.out.println("Total Runtime: "+runtimeMins+" min "+runtimeSecs+" sec "+runtimeMSecs+" msecs");

	}
}
