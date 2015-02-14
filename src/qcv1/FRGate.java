package qcv1;

public abstract class FRGate extends QGate {
	
	public FRGate(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
	}
	
	public abstract Matrix resultForOn();
	public abstract Matrix resultForOff();
	
	public void applyGate(){
		//store the new amplitudes of the register
		MRegister reg = MRegister.getInstance();
		
		Matrix amps = new Matrix(reg.numOfStates(), 1);
		final int mask = 1 << target;
		
		//check if there are any control qubits
		if (controls != null){
			
		} else {
			for (int i = 0; i < reg.numOfStates(); i++){
				int pos = i ^ mask;
				if ((i & mask) == mask){//qubit is 1
					amps.setElement(i, 0, amps.getElement(i,0) + 
							resultForOn().getElement(1,0) * reg.getAmplitude(i));
					amps.setElement(pos, 0, amps.getElement(pos,0) + 
							resultForOn().getElement(0,0) * reg.getAmplitude(i));
				} else {//qubit is 0
					amps.setElement(i, 0, amps.getElement(i,0) + 
							resultForOff().getElement(0,0) * reg.getAmplitude(i));
					amps.setElement(pos, 0, amps.getElement(pos,0) + 
							resultForOff().getElement(1,0) * reg.getAmplitude(i));
				}
			}
		}
		reg.setAmplitude(amps);
	}	
}
