package qcv1;
import Matrix.*;
public class MRegister implements QRegister{
	private DenseMatrix amplitudes = null;
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
		amplitudes = new DenseMatrix(1,numOfStates);
		amplitudes.setElement(0, n, 1.0);
	}
	//set all the states with equal probs
	public void setRegister(int num){
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = new DenseMatrix(1,numOfStates);
		double amp = Math.sqrt(1.0 / numOfStates);
		for (int i = 0; i < numOfStates; i++){
			amplitudes.setElement(0, i, amp);
		}
	}
	
	public void setAmplitude(DenseMatrix amps){
		amplitudes = amps;
	}
	public void setAmplitude(int qubitPos, double amps){
		amplitudes.setElement(0, qubitPos, amps);
	}

	public int numOfQubit(){return numOfQubits;}
	public int numOfStates(){return numOfStates;}

	public double getAmplitude(int i){
		return amplitudes.getElement(0, i);
	}


	public void printAmplitude(){
		for (int i = 0; i < numOfStates; i++){
			System.out.println(amplitudes.getElement(0,i));
		}
	}
	public void apply(DenseMatrix gateMatrix) {
		amplitudes=Matrix.Multiply(amplitudes, gateMatrix);

	}
	public void measure(){
	double sum=0;
		for(int i=0; i<numOfStates; i++){
			double probability = amplitudes.getElement(0, i)*amplitudes.getElement(0, i);
			sum+=probability;
			System.out.println("the probability of state "+(i)+" is "+probability);
		}
		System.out.println(sum);
	}

	//get an instance of the register
	public static MRegister getInstance(){
		if (instance == null){
			instance = new MRegister();
		}
		return instance;
	}


	@Override
	public Matrix getAmplitude() {
		// TODO Auto-generated method stub
		return null;
	}

}
