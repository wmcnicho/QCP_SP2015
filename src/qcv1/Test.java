package qcv1;

public class Test {
	public static void main (String [] args){
		QProcess computer = new QProcess();
		/*MRegister reg = MRegister.getInstance();
		long before = System.nanoTime();
		reg.setRegister(13);
		GateByGateCircuit circuit = new GateByGateCircuit();
		for (int i = 0; i < 13; i++){
			circuit.addGate(i, new FRHGate(i));
		}
		computer.process(reg, circuit);
		reg.printAmplitude();
		
		double totalProb = 0;
		for (int i = 0; i < reg.numOfStates(); i++){
			totalProb += Math.pow(reg.getAmplitude(i),2);
		}
		long after = System.nanoTime();
		
		System.out.println(totalProb);
		System.out.println(after - before);*/
		int noOfQbits = 15;
		int findThis = 0;
		long t1 = System.nanoTime();
		MRegister register = MRegister.getInstance();
		register.setRegister(noOfQbits);
		GroverCircuit groverCircuit = new GroverCircuit(noOfQbits);
		groverCircuit.setTarget(findThis);
		groverCircuit.fill();
		computer.process(register, groverCircuit);
		System.out.println(System.nanoTime()-t1);
	}
}
