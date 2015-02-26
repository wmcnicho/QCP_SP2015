package simulator;

import simulator.mrep.MRegister;

public class QProcess {
	public QProcess(){
		 /* number of Qubits
		 * type of simulation
		 * gate type
		 * speedUp options
		 * data loaded from file
		 * */
		MRegister reg = new MRegister();
		reg.setRegister(10);
		//reg.printAmplitude();
		
		long t1 = System.nanoTime();
		System.out.println("Starting Calculation...");

		GroverGateByGate grover = new GroverGateByGate(reg.numOfStates(), 0, reg.numOfQubit(), reg.numOfStates());
		grover.applyCircuit(reg);
		reg.measure();
		double runtime = (System.nanoTime()-t1)/(Math.pow(10,9));
		int runtimeMins = (int) Math.floor(runtime/60.0);
		int runtimeSecs = (int) (Math.floor(runtime) % 60);
		int runtimeMSecs = (int) (Math.floor(runtime*1000) % 1000);
		System.out.println("Calculation ended.");
		System.out.println("Total Runtime: "+runtimeMins+" min "+runtimeSecs+" sec "+runtimeMSecs+" msecs");

	}
}
