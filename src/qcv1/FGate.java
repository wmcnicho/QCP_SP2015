package qcv1;

import qcv1.Complex;
import qcv1.QGate;
import Matrix.Matrix;
import Matrix.MatrixFactory;

/**
 * Functional representation of a single qubit gate
 * @author Michael Chiang
 *
 */
public abstract class FGate implements QGate {
	//store the position of the target and control qubits
	private int target;
	private int [] controls = null;
	
	/**
	 * Construct a functional representation of a single qubit gate
	 * @param controlQbits Array of the positions of the controlled qubits
	 * @param targetQbit The position of the qubit that the gate is applied on
	 */
	public FGate(int [] controlQbits, int targetQbit){
		target = targetQbit;
		controls = controlQbits;
	}
	
	//need to know how the gate operates on a single qubit
	public abstract Matrix resultForOn();//result for gate applied to state |0>
	public abstract Matrix resultForOff();//result for gate applied to state |1>
	
	/**
	 * Apply the 
	 */
	public void applyGate(QRegister reg){
		Matrix amps = MatrixFactory.create(reg.numOfStates(), 1, "complex");
		final int mask = 1 << target;
		
		//check if there are any control qubits
		if (controls != null){
			for (int i = 0; i < reg.numOfStates(); i++){
				//check if one of the controlQbit is nonzero
				boolean allOn = true;
				for (int j = 0; j < controls.length; j++){
					int tempMask = 1 << controls[j];
					if ((i & tempMask) != tempMask){//qubit is zero
						allOn = false;
						break;
					}
				}
				if (allOn){
					int pos = i ^ mask;
					if ((i & mask) == mask){//qubit is 1
						amps.setElement(i, 0, Complex.add(amps.getElement(i,0), Complex.multiply( 
								resultForOn().getElement(1,0), reg.getAmplitude(i))));
						amps.setElement(pos, 0, Complex.add(amps.getElement(pos,0), Complex.multiply(
								resultForOn().getElement(0,0), reg.getAmplitude(i))));
					} else {//qubit is 0
						amps.setElement(i, 0, Complex.add(amps.getElement(i,0), Complex.multiply(
								resultForOff().getElement(0,0), reg.getAmplitude(i))));
						amps.setElement(pos, 0, Complex.add(amps.getElement(pos,0), Complex.multiply(
								resultForOff().getElement(1,0), reg.getAmplitude(i))));
					}
				} else {
					amps.setElement(i, 0, Complex.add(amps.getElement(i,0),reg.getAmplitude(i)));
				}
			}
		
		//if there is no control qubits
		} else {
			for (int i = 0; i < reg.numOfStates(); i++){
				int pos = i ^ mask;
				if ((i & mask) == mask){//qubit is 1
					amps.setElement(i, 0, Complex.add(amps.getElement(i,0), Complex.multiply( 
							resultForOn().getElement(1,0), reg.getAmplitude(i))));
					amps.setElement(pos, 0, Complex.add(amps.getElement(pos,0), Complex.multiply(
							resultForOn().getElement(0,0), reg.getAmplitude(i))));
				} else {//qubit is 0
					amps.setElement(i, 0, Complex.add(amps.getElement(i,0), Complex.multiply(
							resultForOff().getElement(0,0), reg.getAmplitude(i))));
					amps.setElement(pos, 0, Complex.add(amps.getElement(pos,0), Complex.multiply(
							resultForOff().getElement(1,0), reg.getAmplitude(i))));
				}
			}
		}
		
		//set each basis state in the register to the new amplitude
		for (int i = 0; i < reg.numOfStates(); i++){
			reg.setAmplitude(i, amps.getElement(i, 0));
		}
	}
	
	
}