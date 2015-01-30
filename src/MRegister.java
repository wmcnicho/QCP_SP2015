

public class MRegister implements IRegister{
	private Matrix amplitudes;
	private final int numOfQubits;
	private final int numOfStates;
	
	//constructor
	//set the nth (count from 0) state with amplitude 1 but others to 0
	public MRegister(int num, int n){
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = new Matrix(numOfStates,1);
		amplitudes.setElement(n, 0, 1.0);
	}
	//set all the states with equal probs
	public MRegister(int num){
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = new Matrix(1, numOfStates);
		double prob = 1.0 / Math.sqrt(numOfStates);
		for (int i = 0; i < numOfStates; i++){
			amplitudes.setElement(0, i, prob);
		}
	}
	public MRegister(Matrix amps){
		amplitudes = new Matrix(amps);
		numOfStates = amplitudes.getRowDimension();
		numOfQubits = (int) (Math.log(numOfStates)/Math.log(2));
	}
	
	public int numOfQubit(){return numOfQubits;}
	public double getAmplitude(int i){
		return amplitudes.getElement(i, 0);
	}
	public Matrix getAmplitude(){
		return new Matrix(amplitudes);
	}
	
	public void printAmplitude(){
		for (int i = 0; i < numOfStates; i++){
			System.out.println(amplitudes.getElement(0,i));
		}
	}
	public void apply(Matrix gateMatrix) {
		amplitudes=Matrix.multiply(amplitudes, gateMatrix);
		
	}
	public void measure(){
		for(int i=0; i<amplitudes.getColumnDimension(); i++){
			double probability = amplitudes.getElement(0, i)*amplitudes.getElement(0, i);
			System.out.println("the probability of state "+(i+1)+" is "+probability);
		}
	}
	/*need to add all other gates, only required ones are implemented
	 * 1-combined grover gate
	 * 2-measure system
	 */
	public void apply(QCircuit circuit){
		int[][] data = circuit.gates;
		Matrix groverMatrix=null;
		for(int i=0; i<data.length;i++){
			if(data[i][0]==1){
				if(groverMatrix==null){
					GroverMGate grover = new GroverMGate();
					groverMatrix = grover.output(numOfQubits);
				}
				this.apply(groverMatrix);
			}
			else if(data[i][0]==2){
				this.measure();
			}
		}
	}
}
