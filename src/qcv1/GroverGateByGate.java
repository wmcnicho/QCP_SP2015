package qcv1;

import Matrix.*;

public class GroverGateByGate extends GateByGateCircuit {
	private int targetIndex;
	private int numOfEntries;
	private String gateRep;
	private Matrix solutionVector;
	private Matrix nonSolutionVector;
	
	public GroverGateByGate(String rep, int num, int target, int numOfQubits, int numOfStates){
		gateRep = "gate";
		targetIndex = target;
		numOfEntries = num;
		//testing rotation
		solutionVector = MatrixFactory.create(1, numOfStates, "gate");
		solutionVector.setReElement(0, target, 1.0);
		//solutionVector.printMatrix();
		nonSolutionVector = MatrixFactory.create(1, numOfStates, "gate");
		//nonSolutionVector.printMatrix();
		double factor = 1.0/Math.sqrt(numOfStates - 1);
		for (int i = 0; i < numOfStates; i++){
			if (i != target){
				nonSolutionVector.setReElement(0, i, factor);
			}
		}
		
		//build the circuit for one loop
		//create the oracle
		class Oracle implements QGate{
			public void applyGate(QRegister reg){
				reg.setAmplitude(targetIndex, Complex.multiply(-1, reg.getAmplitude(targetIndex)));
			}
		}
		
		addGate(new Oracle());
		
		//perform Hadamard on all qubits
		for (int i = 0; i < numOfQubits; i++){
			addGate(GateFactory.createHGate(gateRep, null, i, numOfStates));
		}
		
		//create phase shift gate - perform the 2|0><0| - I operation
		class PhaseShiftGate implements QGate{
			public void applyGate(QRegister reg){
				for (int j = 1; j < reg.numOfStates(); j++){
					reg.setAmplitude(j, Complex.multiply(-1,reg.getAmplitude(j)));
				}
			}
		}
		addGate(new PhaseShiftGate());
		for (int i = 0; i < numOfQubits; i++){
			addGate(getGate(1+i));
		}
		System.out.println("Gate setup completed.");
	}
	
	public void applyCircuit(QRegister reg){
		//need to apply approximately r = pi/4 sqrt(N) times
		final int iterations = (int) (Math.PI / 4.0 * Math.sqrt(numOfEntries));
		for (int i = 0; i < iterations; i++){
			System.out.println(i);
			super.applyCircuit(reg);
			//Matrix solComp = Matrix.Multiply(solutionVector, reg.getAmplitude());
			//Matrix nonSolComp = Matrix.Multiply(nonSolutionVector, reg.getAmplitude());
			//solComp.printMatrix();
			//nonSolComp.printMatrix();
			//System.out.printf("(%.6f %.6f)\n",solComp.getReal(0),nonSolComp.getReal(0));
		}
	}
}