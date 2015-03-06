package qcv1;

public class Test {
	public static void main (String [] args){
//		QProcess computer = new QProcess();
//		/*MRegister reg = MRegister.getInstance();
//		long before = System.nanoTime();
//		reg.setRegister(13);
//		GateByGateCircuit circuit = new GateByGateCircuit();
//		for (int i = 0; i < 13; i++){
//			circuit.addGate(i, new FRHGate(i));
//		}
//		computer.process(reg, circuit);
//		reg.printAmplitude();
//		
//		double totalProb = 0;
//		for (int i = 0; i < reg.numOfStates(); i++){
//			totalProb += Math.pow(reg.getAmplitude(i),2);
//		}
//		long after = System.nanoTime();
//		
//		System.out.println(totalProb);
//		System.out.println(after - before);*/
//		int noOfQbits = 5;
//		int findThis = 0;
//		long t1 = System.nanoTime();
//		System.out.println("Starting Calculation...");
//		MRegister register = MRegister.getInstance();
//		register.setRegister(noOfQbits);
//		GroverCircuit groverCircuit = new GroverCircuit(noOfQbits);
//		groverCircuit.setTarget(findThis);
//		groverCircuit.fill();
//		computer.process(register, groverCircuit);
//		
//		double runtime = (System.nanoTime()-t1)/(Math.pow(10,9));
//		int runtimeMins = (int) Math.floor(runtime/60.0);
//		int runtimeSecs = (int) (Math.floor(runtime) % 60);
//		int runtimeMSecs = (int) (Math.floor(runtime*1000) % 1000);
//		System.out.println("Calculation ended.");
//		System.out.println("Total Runtime: "+runtimeMins+" min "+runtimeSecs+" sec "+runtimeMSecs+" msecs");
	}
}