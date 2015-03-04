

package simulator.mrep;

public class MYGate extends MGate{
	
	private Matrix onResult;
	private Matrix offResult;
	public MYGate(MatrixType type, int [] controlQbits, int targetQbit, int numOfStates){
		super(type, controlQbits, targetQbit, numOfStates);
		initResults(type);
		initGate(type);
	}
	
	public void initResults(MatrixType type){
		offResult = Matrix.create(type, 2, 1);
		offResult.set(0, 0, 0.0, 0.0);
		offResult.set(1, 0, 0.0, 1.0);
		
		onResult = Matrix.create(type, 2, 1);
		onResult.set(0, 0, 0.0, -1.0);
		onResult.set(1, 0, 0.0, 0.0);
	}
	
	public Matrix resultForOff(){
		return offResult;
	}
	public Matrix resultForOn(){
		return onResult;
	}
}
