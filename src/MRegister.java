

public class MRegister implements IRegister{
	private Matrix amplitudes = null;
	private static MRegister instance = null;
	private int numOfQubits = 0;
	private int numOfStates = 0;

	/*
	 * private constructor - exists to avoid automatic instantiation
	 */
	private MRegister(){}


	//set the nth (count from 0) state with amplitude 1 but others to 0
	public void setRegister(int num, int n){
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = new Matrix(1,numOfStates);
		amplitudes.setElement(n, 0, 1.0);
	}
	//set all the states with equal probs
	public void setRegister(int num){
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = new Matrix(1,numOfStates);
		double amp = Math.sqrt(1.0 / numOfStates);
		for (int i = 0; i < numOfStates; i++){
			amplitudes.setElement(0, i, amp);
		}
	}
	//set all the states with equal probs
	public void setRegister(Matrix amps){
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
			System.out.println("the probability of state "+(i)+" is "+probability);
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
				System.out.println(i);

				//Call functional implementation
				//		this.applyGrover(data[i][1]);
				//Matrix implementation
				if(groverMatrix==null){
					GroverMGate grover = new GroverMGate();
					grover.setTarget(data[i][1]);
					groverMatrix = grover.output(numOfQubits);
				}
				this.apply(groverMatrix); 

			} 
			else if(data[i][0]==2){
				this.measure();
			}
		}
	}
	public void applyF(){
		for(int i=0; i<16; i++){
			this.applyGrover(0);
		}
		this.measure();
	}
	public void applyGate(MGate m, int targetQbit){
		instance.amplitudes.multiplyBy(m.generateGate(numOfQubits,targetQbit));
	}
	//for generating a control version of the gate
	public void applyGate(MGate m, int [] controlQbits, int targetQbit){
		instance.amplitudes.multiplyBy(m.generateGate(numOfQubits, controlQbits,targetQbit));
	}

	//get an instance of the register
	public static MRegister getInstance(){
		if (instance == null){
			instance = new MRegister();
		}
		return instance;
	}

	//functional implementation of grover
	public void applyGrover(int target){
		//N on the whiteboard
		int n=numOfStates;
		//use 2/N a lot
		double n2 = 2.0/(double)n;
		//create new matrix to store results
		Matrix newAmp = new Matrix(1, numOfStates);
		
		for(int j=0; j<n; j++){
			double sum = 0;
			for(int k=0; k<n; k++){
				double addThis = 0;
				if(k==j){
					addThis=(1-n2)*amplitudes.getElement(0, k);
				}
				else{
					addThis= -amplitudes.getElement(0, k)*n2;
				}
				if(k==target){
					sum-=addThis;
				}
				else{
					sum+=addThis;
				}
			}
			newAmp.setElement(0, j, sum);
			//System.out.println(newAmp.getElement(0,k));
		}
		this.amplitudes=newAmp;
	}
}
