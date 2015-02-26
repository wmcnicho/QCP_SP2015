package simulator.mrep;

import simulator.Complex;
import simulator.QRegister;

public class MRegister extends QRegister{
	private DenseMatrix amplitudes = null;
	private int numOfQubits = 0;
	private int numOfStates = 0;

	public MRegister() {}

	//set the nth (count from 0) state with amplitude 1 but others to 0
	public void setRegister(int num, int n){
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = new DenseMatrix(numOfStates,1);
		amplitudes.setElement(n, 0, 1.0, 0.0);
	}
	//set all the states with equal probabilities (with real amplitudes)
	public void setRegister(int num){
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = new DenseMatrix(numOfStates, 1);
		double amp = Math.sqrt(1.0 / numOfStates);
		for (int i = 0; i < numOfStates; i++){
			amplitudes.setElement(i, 0, amp, 0.0);
		}
	}
	
	public void setAmplitude(DenseMatrix amps){
		amplitudes = amps;
	}
	public void setAmplitude(int qubitPos, double [] amps){
		amplitudes.setElement(0, qubitPos, amps[0], amps[1]);
	}
	public void setAmplitude(int qubitPos, double real, double imag){
		amplitudes.setElement(0, qubitPos, real, imag);
	}

	public int numOfQubit(){return numOfQubits;}
	public int numOfStates(){return numOfStates;}
	
	public DenseMatrix getAmplitude(){
		return amplitudes;
	}
	
	public double [] getAmplitude(int i){
		return amplitudes.getElement(0,i);
	}


	public void printAmplitude(){
		for (int i = 0; i < numOfStates; i++){
			System.out.println(amplitudes.getElement(0,i)[0] + " " + amplitudes.getElement(0,i)[1]);
		}
	}
	
	public void measure(){
	double sum=0;
		for(int i=0; i<numOfStates; i++){
			double probability = Complex.magSquare(amplitudes.getElement(i, 0));
			sum+=probability;
			System.out.println("the probability of state "+(i)+" is "+probability);
		}
		System.out.println(sum);
	}
}
