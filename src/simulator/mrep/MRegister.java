package simulator.mrep;

import simulator.Complex;
import simulator.QRegister;
import java.util.Random;

public class MRegister extends QRegister{
	private Matrix amplitudes = null;
	private int numOfQubits = 0;
	private int numOfStates = 0;
	private MatrixType type;
	
	//constructors
	public MRegister(MatrixType t, int num){
		type = t;
		if (num < 1) num = 1;
		numOfQubits = num;
		numOfStates = (int) Math.pow(2, numOfQubits);
		amplitudes = Matrix.create(type, numOfStates, 1);
	}
	
	public MRegister(MatrixType t, int num, int n){
		this(t,num);
		
		if (n < 0 || n > numOfStates) n = 0;
		amplitudes.set(n, 1.0, 0.0);
	}
		
	public void setEqualAmplitude(){
		double amp = Math.sqrt(1.0 / numOfStates);
		for (int i = 0; i < numOfStates; i++){
			amplitudes.set(i, 0, amp, 0.0);
		}
	}
	
	public void setAmplitude(Matrix amps){
		amplitudes = amps;
	}
	public void setAmplitude(int qubitPos, double [] amps){
		amplitudes.set(0, qubitPos, amps[0], amps[1]);
	}
	public void setAmplitude(int qubitPos, double real, double imag){
		amplitudes.set(0, qubitPos, real, imag);
	}

	public int numOfQubit(){return numOfQubits;}
	public int numOfStates(){return numOfStates;}
	
	public Matrix getAmplitude(){
		return amplitudes;
	}
	
	public double [] getAmplitude(int i){
		return amplitudes.get(0,i);
	}


	public void printAmplitude(){
		for (int i = 0; i < numOfStates; i++){
			System.out.println(amplitudes.get(0,i)[0] + " " + amplitudes.get(0,i)[1]);
		}
	}
	
	public void getProbabilities(){
	double sum=0;
		for(int i=0; i<numOfStates; i++){
			double probability = Complex.magSquare(amplitudes.get(i, 0));
			sum+=probability;
			System.out.println("the probability of state "+(i)+" is "+probability);
		}
		System.out.println(sum);
	}
	
	public void measure(){
		Random rand = new Random();
		double key = rand.nextDouble();
		double sum = 0.0;
		for (int i = 0; i < numOfStates; i++){
			sum += Complex.magSquare(amplitudes.get(i));
			if (sum > key){
				//set all the states to zero 
				for (int j = 0; j < numOfStates; j++){
					amplitudes.set(j, 0, 0);
				}
				amplitudes.set(i, 1.0, 0.0);
				break;
			}
		}
	}
}
