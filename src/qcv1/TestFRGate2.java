package qcv1;

public class TestFRGate2 {
	public static void main (String [] args){
		MRegister reg = MRegister.getInstance();
		reg.setRegister(3, 0);
		reg.printAmplitude();
		GateByGateCircuit circuit = new GateByGateCircuit();
		for (int i = 0; i < 3; i++){
			circuit.addGate(i, new FRHGate2(i));
		}
		circuit.applyCircuit();
		reg.printAmplitude();
		double totalProb = 0;
		for (int i = 0; i < reg.numOfStates(); i++){
			totalProb += Math.pow(reg.getAmplitude(i),2);
		}
		System.out.println(totalProb);
	}
}
