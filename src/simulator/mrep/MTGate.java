

package simulator.mrep;

public class MTGate extends MGate{
	
	private DenseMatrix onResult = new DenseMatrix(2,1);
	private DenseMatrix offResult = new DenseMatrix(2,1);
	public MTGate(int [] controlQbits, int targetQbit, int numOfStates){
		super(controlQbits, targetQbit, numOfStates);
		initResults();
		initGate();
	}
	
	private final double factor = 1.0/Math.sqrt(2);
	public void initResults(){
		offResult.setElement(0, 0, 1.0, 0.0);
		offResult.setElement(1, 0, 0.0, 0.0);
		onResult.setElement(0, 0, 0.0, 0.0);
		onResult.setElement(1, 0, factor, factor);
	}
	
	public DenseMatrix resultForOff(){
		return offResult;
	}
	public DenseMatrix resultForOn(){
		return onResult;
	}
}
