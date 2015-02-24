package complex;

import simulator.frep.CFRGrover;
import simulator.frep.CRegister;

public class TestGrover {
	public static void main (String [] args){
		CRegister reg = CRegister.getInstance();
		reg.setRegister(21);
		
		long t1 = System.nanoTime();
		System.out.println("Starting Calculation...");

		CFRGrover grover = new CFRGrover(reg.numOfStates(), 1);
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
