package qcv1;

import Matrix.ComplexMatrix;
import Matrix.Matrix;

public class MSwapGate extends MGate{
	public MSwapGate(String matrixType, int qubit1, int qubit2, int numOfStates){
		MNOTGate [] gates = new MNOTGate [3];
		gates[0] = new MNOTGate(matrixType, new int [] {qubit1}, qubit2, numOfStates);
		gates[1] = new MNOTGate(matrixType, new int [] {qubit2}, qubit1, numOfStates);
		gates[2] = new MNOTGate(matrixType, new int [] {qubit1}, qubit2, numOfStates);
		gate = combineGate(gates).gate;
	}
}
