package simulator.frep;

import simulator.mrep.*;

public class FHGate extends FGate{
	
	private Matrix onResult;
	private Matrix offResult;
	
	public FHGate(MatrixType type, int [] controlQbits, int targetQbit){
		super(type, controlQbits, targetQbit);
		initResults(type);
	}
	
	private final double factor = 1.0/Math.sqrt(2);
	public void initResults(MatrixType type){
		offResult = Matrix.create(type, 2, 1);
		offResult.set(0, 0, factor, 0.0);
		offResult.set(1, 0, factor, 0.0);
		
		onResult = Matrix.create(type, 2, 1);
		onResult.set(0, 0, factor, 0.0);
		onResult.set(1, 0, -factor, 0.0);
	}
	
	public Matrix resultForOff(){
		return offResult;
	}
	public Matrix resultForOn(){
		return onResult;
	}
}
