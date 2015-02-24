package simulator;

import simulator.frep.FRGrover;
import simulator.mrep.MRegister;

public class TestGrover {
	public static void main (String [] args){
		MRegister reg = MRegister.getInstance();
		reg.setRegister(17);
		
		long t1 = System.nanoTime();
		System.out.println("Starting Calculation...");

		FRGrover grover = new FRGrover(reg.numOfStates(), 2);
		grover.applyCircuit();
		reg.measure();
	
		double runtime = (System.nanoTime()-t1)/(Math.pow(10,9));
		int runtimeMins = (int) Math.floor(runtime/60.0);
		int runtimeSecs = (int) (Math.floor(runtime) % 60);
		int runtimeMSecs = (int) (Math.floor(runtime*1000) % 1000);
		System.out.println("Calculation ended.");
		System.out.println("Total Runtime: "+runtimeMins+" min "+runtimeSecs+" sec "+runtimeMSecs+" msecs");
	}
}
