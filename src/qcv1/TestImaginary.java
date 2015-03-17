package qcv1;

public class TestImaginary {
	public static void main (String [] args){
		GateByGateCircuit circuit = new GateByGateCircuit();
		MRegister reg = new MRegister(4,"complex");
		reg.setEqualAmplitude();
		String rep = "complex";
		for (int i = 0; i < reg.numOfQubit(); i++){
			double phase = Math.PI / Math.pow(2, i);
			MPhaseGate g = new MPhaseGate(rep, new int [] {2}, i, reg.numOfStates(), phase);
			circuit.addGate(g);
		}
		circuit.applyCircuit(reg);
		reg.printAmplitude();
	}
}
