

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
		double sum=0;
		for(int i=0; i<amplitudes.getColumnDimension(); i++){
			double probability = amplitudes.getElement(0, i)*amplitudes.getElement(0, i);
			sum+=probability;
			System.out.println("the probability of state "+(i)+" is "+probability);
		}
		System.out.println(sum);
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

				//Call functional implementation
				this.applyGrover(data[i][1]);
				//Matrix implementation
				/*if(groverMatrix==null){
					GroverMGate grover = new GroverMGate();
					grover.setTarget(data[i][1]);
					groverMatrix = grover.output(numOfQubits);
				}
				this.apply(groverMatrix); */

			} 
			else if(data[i][0]==2){
				this.measure();
			}
		}
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
	//applies the Grover step once
	public void applyGrover(int target){
		boolean thread1 = false;
		boolean thread2 = false;
		int n=numOfStates;
		double n2 = 2.0/(double)n;

		//create new matrix to store results
		Matrix newAmp1 = new Matrix(1, numOfStates/2);
		Matrix newAmp2 = new Matrix(1, numOfStates/2);

		//precompute everything for performance
		double nonTargetNonDiagonalResult1;
		double nonTargetDiagonalResult1;
		double targetNonDiagonalResult1;
		double targetDiagonalResult1;

		targetNonDiagonalResult1 = -amplitudes.getElement(0, target)*n2;
		targetDiagonalResult1 = (1-n2)*amplitudes.getElement(0, target);
		if(target==0){
			nonTargetNonDiagonalResult1 = -amplitudes.getElement(0, 1)*n2;
			nonTargetDiagonalResult1 = (1-n2)*amplitudes.getElement(0, 1);
		}
		else{
			nonTargetNonDiagonalResult1 = -amplitudes.getElement(0, 0)*n2;
			nonTargetDiagonalResult1 = (1-n2)*amplitudes.getElement(0, 0);
		}
		
		double nonTargetNonDiagonalResult2=nonTargetNonDiagonalResult1;
		double nonTargetDiagonalResult2=nonTargetDiagonalResult1;
		double targetNonDiagonalResult2=targetNonDiagonalResult1;
		double targetDiagonalResult2=targetDiagonalResult1;

		Thread t1 = new Thread(){
			public void run(){
				for(int j=0; j<n/2; j++){
					double sum = 0;
					for(int k=0; k<n; k++){
						//on the diagonal
						if(k==j){
							if(k==target){
								sum-=targetDiagonalResult1;
							}
							else{
								sum+=nonTargetDiagonalResult1;
							}
						}
						//not on diagonal
						else{
							if(k==target){
								sum-=targetNonDiagonalResult1;
							}
							else{
								sum+=nonTargetNonDiagonalResult1;
							}
						}
					}
					newAmp1.setElement(0, j, sum);
				}
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				for(int j=n/2; j<n; j++){
					double sum = 0;
					for(int k=0; k<n; k++){
						//on the diagonal
						if(k==j){
							if(k==target){
								sum-=targetDiagonalResult2;
							}
							else{
								sum+=nonTargetDiagonalResult2;
							}
						}
						//not on diagonal
						else{
							if(k==target){
								sum-=targetNonDiagonalResult2;
							}
							else{
								sum+=nonTargetNonDiagonalResult2;
							}
						}
					}
					newAmp2.setElement(0, j-n/2, sum);
				}
			}
		};
		t1.start();
		t2.start();
		while(t1.isAlive()&&t2.isAlive()){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.amplitudes=newAmp1;
	}
}
