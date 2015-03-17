package qcv1;

import Matrix.*;

public class FHGate extends FGate{
	
	private Matrix onResult;
	private Matrix offResult;
	
	public FHGate(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
		initResults();
	}
	
	public void initResults(){
		final double factor = 1.0/Math.sqrt(2);
		offResult = MatrixFactory.create(2, 1, "complex");
		offResult.setElement(0, 0, factor, 0.0);
		offResult.setElement(1, 0, factor, 0.0);
		
		onResult = MatrixFactory.create(2, 1, "complex");
		onResult.setElement(0, 0, factor, 0.0);
		onResult.setElement(1, 0, -factor, 0.0);
	}
	
	public Matrix resultForOff(){
		return offResult;
	}
	public Matrix resultForOn(){
		return onResult;
	}
	
	public String gateType() {
		return "H Gate";
	}
}
