package qcv1;

import qcv1.Complex;
import qcv1.QGate;
import Matrix.*;

public abstract class FGate implements QGate {
	private int target;
	private int [] controls = null;
	private String matrixType;
	
	public FGate(String type, int [] controlQbits, int targetQbit){
		target = targetQbit;
		matrixType = type;
		controls = controlQbits;
	}
	
	public abstract Matrix resultForOn();
	public abstract Matrix resultForOff();
	
	public void applyGate(QRegister reg){
		Matrix amps = MatrixFactory.create(reg.numOfStates(), 1, matrixType);
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
		
		//set each state to the new amplitude
		for (int i = 0; i < reg.numOfStates(); i++){
			reg.setAmplitude(i, amps.getElement(0, i));
		}
	}	
}