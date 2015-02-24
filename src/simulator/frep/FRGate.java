package simulator.frep;

import simulator.QGate;
import simulator.mrep.MRegister;
import simulator.mrep.Matrix;

public abstract class FRGate implements QGate {
	
	private int [] column = new int [MRegister.getInstance().numOfStates()*2];
	private int [] row = new int [MRegister.getInstance().numOfStates()*2];
	private double [] value = new double [MRegister.getInstance().numOfStates()*2];
	
	private int target;
	private int [] controls = null;
	
	public FRGate(int [] controlQbits, int targetQbit){
		target = targetQbit;
		controls = controlQbits;
	}
	
	public void init(){
		MRegister reg = MRegister.getInstance();
		
		final int mask = 1 << target;
		int j = 0;
		for (int i = 0; i < reg.numOfStates(); i++){
			int pos = i ^ mask;
			if ((i & mask) == mask){//qubit is 1
				row[j] = i;
				column[j] = i;
				value[j] = resultForOn().getElement(1,0);
				row[j+1] = pos;
				column[j+1] = i;
				value[j+1] = resultForOn().getElement(0,0);
			} else {//qubit is 0
				row[j] = i;
				column[j] = i;
				value[j] = resultForOff().getElement(0,0);
				row[j+1] = pos;
				column[j+1] = i;
				value[j+1] = resultForOff().getElement(1,0);
			}
			j += 2;
		}
	}
	
	public abstract Matrix resultForOn();
	public abstract Matrix resultForOff();
	
	public void applyGate(){
		//store the new amplitudes of the register
		MRegister reg = MRegister.getInstance();
		
		Matrix amps = new Matrix(1, reg.numOfStates());
		for (int i = 0; i < row.length; i++){
			amps.setElement(0, row[i], amps.getElement(0, row[i]) + reg.getAmplitude(column[i]) * value[i]);
		}
		reg.setAmplitude(amps);
	}	
}
