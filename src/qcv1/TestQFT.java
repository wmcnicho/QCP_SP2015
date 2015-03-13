package qcv1;

import Matrix.ComplexMatrix;
import Matrix.MatrixMultiply;

public class TestQFT {
	public static void main (String [] args){
		MRegister reg = new MRegister(8, "complex");
		/*for (int i = 0; i < reg.numOfStates(); i++){
			reg.setAmplitude(i, 1/Math.sqrt(8), 0.0);
		}*/
		reg.setEqualAmplitude();
		//reg.printAmplitude();
		ForwardQFTCircuit forwardQFT = new ForwardQFTCircuit("complex", reg.numOfQubit());
		BackwardQFTCircuit backwardQFT = new BackwardQFTCircuit("complex", reg.numOfQubit());
		
		forwardQFT.applyCircuit(reg);
		System.out.println();
		reg.printAmplitude();
		System.out.println();
		/*double [] probs = reg.getProbabilities();
		double sum = 0;
		for (int i = 0; i < probs.length; i++){
			sum += probs[i];
			//System.out.println(probs[i]);
		}*/
		//System.out.println(sum);
		MGate [] gates = new MGate [forwardQFT.getCircuitSize()];
		for (int i = 0; i < forwardQFT.getCircuitSize(); i++){
			gates[i] = (MGate) forwardQFT.getGate(i);
			//ComplexMatrix m = (ComplexMatrix) gates[i].gate;
			//m.printMatrix();
			//System.out.println(qft.getCircuitSize());
		}
		
		ComplexMatrix forward = (ComplexMatrix) MGate.combineGate(gates).gate;
		//forward.printMatrix();
		
		backwardQFT.applyCircuit(reg);
		reg.printAmplitude();
		
		
		
		gates = new MGate [backwardQFT.getCircuitSize()];
		for (int i = 0; i < backwardQFT.getCircuitSize(); i++){
			gates[i] = (MGate) backwardQFT.getGate(i);
			//ComplexMatrix m = (ComplexMatrix) gates[i].gate;
			//m.printMatrix();
			//System.out.println(qft.getCircuitSize());
		}
		ComplexMatrix backward = (ComplexMatrix) MGate.combineGate(gates).gate;
		//backward.printMatrix();
		
		((ComplexMatrix) MatrixMultiply.Multiply(backward, forward)).printReMatrix();
	}
}
