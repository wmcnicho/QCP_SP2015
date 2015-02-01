package qcv1;

public abstract class FRGate extends QGate {
	public abstract Matrix resultForOn();
	public abstract Matrix resultForOff();
	
	public void updateRegister(QRegister reg, int targetQbit){
		//store the new amplitudes of the register
		Matrix amps = new Matrix(reg.numOfStates(), 1);
		
		final int mask = 1 << targetQbit;
		
		for (int i = 0; i < reg.numOfStates(); i++){
			int pos = i ^ mask;
			if ((i & mask) == mask){//qubit is 1
				amps.setElement(i, 0, amps.getElement(i,0) + 
						resultForOn().getElement(1,0) * reg.getAmplitude(i));
				amps.setElement(pos, 0, amps.getElement(pos,0) + 
						resultForOn().getElement(0,0) * reg.getAmplitude(pos));
			} else {//qubit is 0
				amps.setElement(i, 0, amps.getElement(i,0) + 
						resultForOff().getElement(0,0) * reg.getAmplitude(i));
				amps.setElement(pos, 0, amps.getElement(pos,0) + 
						resultForOff().getElement(1,0) * reg.getAmplitude(pos));
			}
		}
	}
	public void updateRegister(QRegister reg, int [] controlQbits, int targetQbit){
		
	}
}
