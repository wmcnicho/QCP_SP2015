

public class MRegister implements IRegister{
	private Matrix amplitudes;
	private int numOfQubits;
	private int numOfStates;
	
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
		amplitudes = new Matrix(numOfStates,1);
		double prob = 1.0 / numOfStates;
		for (int i = 0; i < numOfStates; i++){
			amplitudes.setElement(i, 0, prob);
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
			System.out.println(amplitudes.getElement(i, 0));
		}
	}
}
