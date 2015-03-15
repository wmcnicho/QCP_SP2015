package qcv1;

import Matrix.*;

public class FXGate extends FGate{
	
	private Matrix onResult;
	private Matrix offResult;
	
	public FXGate(int [] controlQbits, int targetQbit){
		super(controlQbits, targetQbit);
		initResults();
	}
	
	public void initResults(){
		offResult = MatrixFactory.create(2, 1, "complex");
		offResult.setElement(0, 0, 0.0, 0.0);
		offResult.setElement(1, 0, 1.0, 0.0);
		
		onResult = MatrixFactory.create(2, 1, "complex");
		onResult.setElement(0, 0, 1.0, 0.0);
		onResult.setElement(1, 0, 0.0, 0.0);
	}
	
	public Matrix resultForOff(){
		return offResult;
	}
	public Matrix resultForOn(){
		return onResult;
	}
	
	public String gateType() {
		return "X Gate";
	}
}
