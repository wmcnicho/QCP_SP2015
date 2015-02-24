package complex;

import simulator.*;

public abstract class CFRGate implements QGate {
	
	private int [] column = new int [CRegister.getInstance().numOfStates()*2];
	private int [] row = new int [CRegister.getInstance().numOfStates()*2];
	private Complex [] value = new Complex [CRegister.getInstance().numOfStates()*2];
	
	private int target;
	private int [] controls = null;
	
	public CFRGate(int [] controlQbits, int targetQbit){
		target = targetQbit;
		controls = controlQbits;
	}
	
	public void init(){
		CRegister reg = CRegister.getInstance();
		
		final int mask = 1 << target;
		int j = 0;
		for (int i = 0; i < reg.numOfStates(); i++){
			int pos = i ^ mask;
			if ((i & mask) == mask){//qubit is 1
				row[j] = i;
				column[j] = i;
				value[j] = resultForOn()[1];
				row[j+1] = pos;
				column[j+1] = i;
				value[j+1] = resultForOn()[0];
			} else {//qubit is 0
				row[j] = i;
				column[j] = i;
				value[j] = resultForOff()[0];
				row[j+1] = pos;
				column[j+1] = i;
				value[j+1] = resultForOff()[1];
			}
			j += 2;
		}
	}
	
	public abstract Complex [] resultForOn();
	public abstract Complex [] resultForOff();
	
	public void applyGate(){
		//store the new amplitudes of the register
		CRegister reg = CRegister.getInstance();
		
		Complex [] amps = new Complex [reg.numOfStates()];
		for (int i = 0; i < amps.length; i++){
			amps[i] = new Complex();
		}
		for (int i = 0; i < row.length; i++){
			amps[row[i]].add(Complex.multiply(reg.getAmplitude(column[i]), value[i]));
		}
		reg.setAmplitude(amps);
	}	
}
