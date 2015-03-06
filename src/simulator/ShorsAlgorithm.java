package simulator;

import java.util.Random;

public class ShorsAlgorithm {
	private int number;
	private Random rand = new Random();
	private GateRep gateRep;
	private int numOfQubits;
	
	//constructor
	public ShorsAlgorithm(GateRep rep, int num){
		gateRep = rep;
		
		//check if the number is even
		if (num % 2 == 0){
			
		}
		//check if it is a squared number
		
		number = num;
	}
	
	public double [] run(){
		//choose random int x, 1 <= x <= N-1
		int x = rand.nextInt(number-1) + 1;
		
		//find highest common factor
		if (gcd(x,number) != 1){
			return new double [] {x, number / x};
		}
		//do order finding 
		int r = orderFinding();
		if (r %= 2 && )
	}
	
	public int gcd(int a, int b){
		while (b != 0){
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
	
	public int orderFinding(QRegister reg){
		reg.setEqualAmplitude();
		
		BackwardQFTCircuit bqft = new BackwardQFTCircuit(gateRep, reg.numOfQubit());
		bqft.applyCircuit(reg);
		int r = reg.measure();
		continuedFraction(r, reg.numOfStates());
		
	}
	
	public int continuedFraction(int r, int numOfStates){
		
	}
	 
}
