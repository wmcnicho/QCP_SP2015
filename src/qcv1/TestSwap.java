package qcv1;

import Matrix.ComplexMatrix;

public class TestSwap {
	public static void main (String [] args){
		MSwapGate swap = new MSwapGate("complex",0,2,8);
		ComplexMatrix g = (ComplexMatrix) swap.gate;
		//g.printMatrix();
	}
}
