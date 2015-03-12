package qcv1;

import Matrix.*;
import Gui.QViewModel;

/**
 * 
 * @author Michael
 *
 */
public class GroverGateByGate extends GateByGateCircuit {
	private int [] targets;
	private int numOfEntries;
	private String gateRep;
	private Matrix solutionVector;
	private Matrix nonSolutionVector;
	
	/**
	 * 
	 * GroverGateByGate implements the quantum search algorithm (Grover's algorithm).
	 * 
	 * @param rep Representation of the gate
	 * @param num 
	 * @param targetIndices The target/solution states of the search problem
	 * @param numOfQubits
	 * @param numOfStates
	 */
	public GroverGateByGate(String rep, int num, int [] targetIndices, int numOfQubits, int numOfStates){
		gateRep = rep;
		targets = targetIndices;
		numOfEntries = num;
		System.out.println(gateRep);
		//create the vectors that span the plane where the state vector is rotating on
		solutionVector = MatrixFactory.create(1, numOfStates, rep);
		nonSolutionVector = MatrixFactory.create(1, numOfStates, rep);
		//solutionVector.printMatrix();
		//nonSolutionVector.printMatrix();
		double solutionFactor = 1.0/Math.sqrt(numOfStates - 1);
		double nonSolutionFactor = 1.0/Math.sqrt(numOfStates - targets.length);
		
		int j = 0;
		for (int i = 0; i < numOfStates; i++){
			if (j < targets.length && i == targets[j]){
				solutionVector.setElement(0, i, solutionFactor, 0.0);
			} else {
				nonSolutionVector.setElement(0, i, nonSolutionFactor, 0.0);
			}
		}
		
		//build the circuit for one loop
		//create the oracle
		class Oracle implements QGate{
			public void applyGate(QRegister reg){
				for (int i = 0; i < targets.length; i++){
					reg.setAmplitude(targets[i], Complex.multiply(-1, reg.getAmplitude(targets[i])));
				}
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
		reg.setEqualAmplitude();
		
		//need to apply approximately r = pi/4 sqrt(N) times
		final int iterations = (int) (Math.PI / 4.0 * Math.sqrt((double) numOfEntries/ targets.length));
		for (int i = 0; i < iterations; i++){
			super.applyCircuit(reg);
			System.out.println(i);
			//Matrix solComp = Matrix.Multiply(solutionVector, reg.getAmplitude());
			//Matrix nonSolComp = Matrix.Multiply(nonSolutionVector, reg.getAmplitude());
			//solComp.printMatrix();
			//nonSolComp.printMatrix();
			//System.out.printf("(%.6f %.6f)\n",solComp.getReal(0),nonSolComp.getReal(0));
			int percent = (int) ((double) i / iterations * 100);
			//System.out.println(percent);
			QViewModel.updateLoadingBar(percent);
			QViewModel.updateHistogramValues(reg.getProbabilities());
			QViewModel.setVector(1, 1);
		}
		QViewModel.updateLoadingBar(100);
	}
}