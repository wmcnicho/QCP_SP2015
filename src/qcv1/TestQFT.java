package qcv1;

import Matrix.ComplexMatrix;

public class TestQFT {
	public static void main (String [] args){
		MRegister reg = new MRegister(3, "complex");
		/*for (int i = 0; i < reg.numOfStates(); i++){
			reg.setAmplitude(i, 1/Math.sqrt(8), 0.0);
		}*/
		//reg.setEqualAmplitude();
		/*reg.setAmplitude(0,0,0);
		reg.setAmplitude(1,0,0);
		reg.setAmplitude(2,0,0);
		reg.setAmplitude(3,1/Math.sqrt(2),0);
		reg.setAmplitude(4,1/Math.sqrt(2),0);
		reg.setAmplitude(5,0,0);
		reg.setAmplitude(6,0,0);
		reg.setAmplitude(7,0,0);*/
		
		//reg.printAmplitude();
		ForwardQFTCircuit qft = new ForwardQFTCircuit("complex", reg.numOfQubit());
		
		qft.applyCircuit(reg);
		System.out.println();
		reg.printAmplitude();
		System.out.println();
		double [] probs = reg.getProbabilities();
		double sum = 0;
		for (int i = 0; i < probs.length; i++){
			sum += probs[i];
			//System.out.println(probs[i]);
		}
		//System.out.println(sum);
		/*MGate [] gates = new MGate [qft.getCircuitSize()];
		for (int i = 0; i < qft.getCircuitSize(); i++){
			gates[i] = (MGate) qft.getGate(i);
			//ComplexMatrix m = (ComplexMatrix) gates[i].gate;
			//m.printMatrix();
			//System.out.println(qft.getCircuitSize());
		}
		
		MGate g = MGate.combineGate(gates);
		ComplexMatrix matrix = (ComplexMatrix) g.gate;
		matrix.scaleBy(Math.sqrt(8));
		matrix.printMatrix();
		
		/*reg = new MRegister(3,"complex");
		reg.setAmplitude(0,1,0);
		reg.printAmplitude();
		g.applyGate(reg);
		reg.printAmplitude();*/
	}
}
