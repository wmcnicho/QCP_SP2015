package simulator.mrep;

import simulator.Complex;
import simulator.GateByGateCircuit;

public class TestFRGate2 {
	public static void main (String [] args){
		MRegister reg = new MRegister();
		reg.setRegister(2, 0);
		reg.printAmplitude();
		GateByGateCircuit circuit = new GateByGateCircuit();
		for (int i = 0; i < 2; i++){
			circuit.addGate(i, new MHGate(null,i,reg.numOfStates()));
		}
		circuit.applyCircuit(reg);
		reg.printAmplitude();
		double totalProb = 0;
		for (int i = 0; i < reg.numOfStates(); i++){
			totalProb += Complex.magSquare(reg.getAmplitude(i));
		}
		System.out.println(totalProb);
	}
}
