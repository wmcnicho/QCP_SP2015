package qcv1;

import Matrix.ComplexMatrix;
import Matrix.Matrix;
import Matrix.MatrixMultiply;

public class TestQFT {
	public static void main (String [] args){
		MRegister reg = new MRegister(4, "complex");
		double amps = 1 / Math.sqrt(reg.numOfStates());
		reg.setAmplitude(3, amps, 0);
		reg.setAmplitude(7, amps, 0);
		reg.setAmplitude(11, amps, 0);
		reg.setAmplitude(15, amps, 0);
		/*for (int i = 0; i < reg.numOfStates(); i++){
			reg.setAmplitude(i, 1/Math.sqrt(8), 0.0);
		}*/
		//reg.setEqualAmplitude();
		reg.printAmplitude();
		System.out.println();
		//ForwardQFTCircuit forwardQFT = new ForwardQFTCircuit("functional", reg.numOfQubit());
		BackwardQFTCircuit backwardQFT = new BackwardQFTCircuit("functional", reg.numOfQubit());
		
		backwardQFT.applyCircuit(reg);
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
		/*MGate [] gates = new MGate [forwardQFT.getCircuitSize()];
		for (int i = 0; i < forwardQFT.getCircuitSize(); i++){
			gates[i] = (MGate) forwardQFT.getGate(i);
			//ComplexMatrix m = (ComplexMatrix) gates[i].gate;
			//m.printMatrix();
			//System.out.println(qft.getCircuitSize());
		}*/
		
		/*Matrix forward = MGate.combineGate(gates).gate;
		printMatrix(forward);
		System.out.println();
		backwardQFT.applyCircuit(reg);
		reg.printAmplitude();
		
		
		
		gates = new MGate [backwardQFT.getCircuitSize()];
		for (int i = 0; i < backwardQFT.getCircuitSize(); i++){
			gates[i] = (MGate) backwardQFT.getGate(i);
			//ComplexMatrix m = (ComplexMatrix) gates[i].gate;
			//m.printMatrix();
			//System.out.println(qft.getCircuitSize());
		}
		Matrix backward = MGate.combineGate(gates).gate;
		//backward.printMatrix();
		
		printMatrix(backward);*/
	}
	
	public static void printMatrix(Matrix m){
		for (int i = 0; i < 32; i++){
			for (int j = 0; j < 32; j++){
				System.out.printf("%.2f + %.2fi\t",m.getReElement(i, j) * Math.sqrt(32), m.getImElement(i, j) * Math.sqrt(32));
			}
			System.out.println();
		}
	}
}
