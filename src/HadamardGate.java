public class HadamardGate {
	public static void main (String [] args){
		//set total number of qubits
		int numOfQubits = 3;
		int maxNum = (int) Math.pow(2, numOfQubits) - 1;
		
		/*
		 * very crude model to represent the Hadamard transform 
		 * as matrix (denoated as hgate) - not even considering
		 * complex numbers!
		 * 
		 */
		
		//instantiate a 2^n x 2^n matrix with zeros
		Matrix hgate = new Matrix(maxNum+1, maxNum+1);
		
		//hgate applied to the following qubit only!
		int qubitPos = 1;
		int mask = 1 << qubitPos;
		
		for (int i = 0; i <= maxNum; i++){	
			/*
			 * recall the Hadamard transform
			 * 		H|0> = 1/sqrt(2) (|0> + |1>)
			 * 		H|1> = 1/sqrt(2) (|0> - |1>)
			 */
			//check if that qubit is 0 or 1
			if ((i & mask) == mask){//qubit is 1
				hgate.setElement(i, i, -1.0/Math.sqrt(2));
				hgate.setElement(i ^ mask, i, 1.0/Math.sqrt(2));
			} else {//qubit is 0
				hgate.setElement(i, i, 1.0/Math.sqrt(2));
				hgate.setElement(i ^ mask, i, 1.0/Math.sqrt(2));
			}
		}
		hgate.printMatrix();
	}
		
	public static void log(String str){
		System.out.println(str);
	}
}
