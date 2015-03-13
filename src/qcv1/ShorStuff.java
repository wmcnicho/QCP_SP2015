package simulator;

import java.util.Random;

public class ShorStuff {
	private int number;
	private Random rand = new Random();
	private GateRep gateRep;
	private int numOfQubits;
	private int x;
	//constructor
	public ShorStuff(GateRep rep, int num){
		gateRep = rep;
		
		//check if the number is even
		if (num % 2 == 0){
			
		}
		//check if it is a squared number
		
		number = num;
	}
	
	public double [] run(){
		//choose random int x, 1 <= x <= N-1
		x = rand.nextInt(number-1) + 1;
		
		//find highest common factor
		if (gcd(x,number) != 1){
			return new double [] {x, number / x};
		}
		//do order finding 
		int r = orderFinding();
		int y = (int)Math.pow(x, r/2);
		if (y==-1){
			System.out.println("something broke");
			return null;
		}
		double[] out = new double[2];
		out[0] = ShorStuff.gcd(y+1, number);
		out[1] = ShorStuff.gcd(y-1, number);
		return out;
	}
	
	public static int gcd(int a, int b){
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
		int c = reg.measure();
		continuedFraction(c, reg.numOfStates());
		int r = 0;
		int max = 0;
		int order;
		
		
		//get convergents of c/q to be d/r. pick largest r such that r<n
		for(int i=0; i<max; i++){
			if((int)Math.pow(x, r*i)%n==1){
				return r*i;
			}
		}
		
	}
	
	public int continuedFraction(int r, int numOfStates){
		
	}
	
	public static int modularPow(int _base, int _exponent, int _modulus){

		int modulus = _modulus;
		int result = 1;
		int base = _base%_modulus;
		int exponent = _exponent;
		while(exponent > 0){
			if (exponent%2 == 1){
				result = (result * base)%modulus;
			}
			exponent = exponent >> 1;
			base = (base * base)%modulus;

		}
		return result;
	}

	public static int[] reg2(int noOfStates, int x, int n){
		int[] states = new int[noOfStates];
		for(int i=0; i<noOfStates; i++){
			states[i] = modularPow(x, i, n);
		}
		Random rnd = new Random();
		int temp = rnd.nextInt(noOfStates-1);
		temp = states[temp];
		ArrayList<Integer> list = new ArrayList();
		for(int i=0; i<noOfStates; i++){
			if(states[i]==temp){
				list.add(new Integer(i));
			}
		}
		int[] ret = new int[list.size()];
		Iterator<Integer> iterator = list.iterator();
		for (int i = 0; i < ret.length; i++)
		{
			ret[i] = iterator.next().intValue();
		}
		return ret;
	} 
	 
}
