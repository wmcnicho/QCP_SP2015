

package simulator.mrep;

public class MXGate extends MGate{
	
	private DenseMatrix onResult = new DenseMatrix(2,1);
	private DenseMatrix offResult = new DenseMatrix(2,1);
	public MXGate(int [] controlQbits, int targetQbit, int numOfStates){
		super(controlQbits, targetQbit, numOfStates);
		initResults();
		initGate();
	}
	
	public void initResults(){
		offResult.setElement(0, 0, 0.0, 0.0);
		offResult.setElement(1, 0, 1.0, 0.0);
		onResult.setElement(0, 0, 1.0, 0.0);
		onResult.setElement(1, 0, 0.0, 0.0);
	}
	
	public DenseMatrix resultForOff(){
		return offResult;
	}
	public DenseMatrix resultForOn(){
		return onResult;
	}
}
