package qcv1;

public class TestGrover {
	public static void main (String [] args){
		MRegister reg = MRegister.getInstance();
		reg.setRegister(15);
		//reg.printAmplitude();
		
		long t1 = System.nanoTime();
		System.out.println("Starting Calculation...");

		GroverGateByGate grover = new GroverGateByGate(reg.numOfStates(), 2);
		grover.applyCircuit();
		reg.printAmplitude();
		double totalProb = 0;
		for (int i = 0; i < reg.numOfStates(); i++){
			totalProb += Math.pow(reg.getAmplitude(i),2);
		}
		System.out.println(totalProb);
		double runtime = (System.nanoTime()-t1)/(Math.pow(10,9));
		int runtimeMins = (int) Math.floor(runtime/60.0);
		int runtimeSecs = (int) (Math.floor(runtime) % 60);
		int runtimeMSecs = (int) (Math.floor(runtime*1000) % 1000);
		System.out.println("Calculation ended.");
		System.out.println("Total Runtime: "+runtimeMins+" min "+runtimeSecs+" sec "+runtimeMSecs+" msecs");
	}
}
