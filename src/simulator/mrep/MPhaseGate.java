

package simulator.mrep;

public class MPhaseGate extends MGate{
	
	private Matrix onResult;
	private Matrix offResult;
	private double phase;
	public MPhaseGate(MatrixType type, int [] controlQbits, int targetQbit, int numOfStates, double phaseFrac){
		super(type, controlQbits, targetQbit, numOfStates);
		phase = Math.PI / phaseFrac;
		initResults(type);
		initGate(type);
	}
	
	public void initResults(MatrixType type){
		offResult = Matrix.create(type, 2, 1);
		offResult.set(0, 0, 1.0, 0.0);
		offResult.set(1, 0, 0.0, 0.0);
		onResult = Matrix.create(type, 2, 1);
		onResult.set(0, 0, 0.0, 0.0);
		onResult.set(1, 0, Math.cos(phase), Math.sin(phase));
	}
	
	public Matrix resultForOff(){
		return offResult;
	}
	public Matrix resultForOn(){
		return onResult;
	}
}
