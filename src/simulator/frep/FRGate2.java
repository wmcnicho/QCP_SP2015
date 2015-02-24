package simulator.frep;
import simulator.QGate;
import simulator.mrep.MRegister;
import Matrix.*;

public abstract class FRGate2 extends QGate {
	
	int [] column = new int [MRegister.getInstance().numOfStates()*2];
	int [] row = new int [MRegister.getInstance().numOfStates()*2];
	double [] value = new double [MRegister.getInstance().numOfStates()*2];
	
	public FRGate2(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
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
	
	public abstract DenseMatrix resultForOn();
	public abstract DenseMatrix resultForOff();
	
	public void applyGate(){
		//store the new amplitudes of the register
		MRegister reg = MRegister.getInstance();
		
		DenseMatrix amps = new DenseMatrix(1, reg.numOfStates());
		for (int i = 0; i < row.length; i++){
			amps.setElement(0, row[i], amps.getElement(0, row[i]) + reg.getAmplitude(column[i]) * value[i]);
		}
		reg.setAmplitude(amps);
	}	
}
