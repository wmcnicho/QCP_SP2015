package simulator.mrep;

import simulator.QRegister;
import Matrix.*;

public class MRegister extends QRegister{
	private DenseComplexMatrix amplitudes = null;
	private static MRegister instance = null;
	private int numOfQubits = 0;
	private int numOfStates = 0;

	public MRegister() {}

	//set the nth (count from 0) state with amplitude 1 but others to 0
	public void setRegister(int num, int n){
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = new DenseComplexMatrix(1,numOfStates);
		amplitudes.setElement(0, n, 1.0, 0.0);
	}
	//set all the states with equal probabilities (with real amplitudes)
	public void setRegister(int num){
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = new DenseComplexMatrix(1,numOfStates);
		double amp = Math.sqrt(1.0 / numOfStates);
		for (int i = 0; i < numOfStates; i++){
			amplitudes.setElement(0, i, amp, 0.0);
		}
	}
	
	public void setAmplitude(DenseComplexMatrix amps){
		amplitudes = amps;
	}
	public void setAmplitude(int qubitPos, double amps){
		amplitudes.setElement(0, qubitPos, amps, 0.0);
	}
	public void setAmplitude(int qubitPos, double real, double imag){
		amplitudes.setElement(0, qubitPos, real, imag);
	}

	public int numOfQubit(){return numOfQubits;}
	public int numOfStates(){return numOfStates;}
	
	/*public double getAmplitude(int i){
		return amplitudes.getElement(0, i);
	}*/
	
	public double [] getAmplitude(int i){
		return amplitudes.getElement(1,i);
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

}
