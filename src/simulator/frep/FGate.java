package simulator.frep;

import simulator.Complex;
import simulator.QGate;
import simulator.QRegister;
import simulator.mrep.*;

public abstract class FGate implements QGate {
	protected int target;
	protected int [] controls = null;
	protected int numOfStates;
	protected MatrixType matrixType;
	
	public FGate(MatrixType type, int [] controlQbits, int targetQbit){
		target = targetQbit;
		matrixType = type;
		controls = controlQbits;
	}
	
	public abstract Matrix resultForOn();
	public abstract Matrix resultForOff();
	
	public void applyGate(QRegister reg){
		Matrix amps = Matrix.create(matrixType, reg.numOfStates(), 1);
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
						amps.set(i, 0, Complex.add(amps.get(i,0), Complex.multiply( 
								resultForOn().get(1,0), reg.getAmplitude(i))));
						amps.set(pos, 0, Complex.add(amps.get(pos,0), Complex.multiply(
								resultForOn().get(0,0), reg.getAmplitude(i))));
					} else {//qubit is 0
						amps.set(i, 0, Complex.add(amps.get(i,0), Complex.multiply(
								resultForOff().get(0,0), reg.getAmplitude(i))));
						amps.set(pos, 0, Complex.add(amps.get(pos,0), Complex.multiply(
								resultForOff().get(1,0), reg.getAmplitude(i))));
					}
				}
			}
		} else {
			for (int i = 0; i < reg.numOfStates(); i++){
				int pos = i ^ mask;
				if ((i & mask) == mask){//qubit is 1
					amps.set(i, 0, Complex.add(amps.get(i,0), Complex.multiply( 
							resultForOn().get(1,0), reg.getAmplitude(i))));
					amps.set(pos, 0, Complex.add(amps.get(pos,0), Complex.multiply(
							resultForOn().get(0,0), reg.getAmplitude(i))));
				} else {//qubit is 0
					amps.set(i, 0, Complex.add(amps.get(i,0), Complex.multiply(
							resultForOff().get(0,0), reg.getAmplitude(i))));
					amps.set(pos, 0, Complex.add(amps.get(pos,0), Complex.multiply(
							resultForOff().get(1,0), reg.getAmplitude(i))));
				}
			}
		}
		reg.setAmplitude(amps);
	}	
}
