package simulator;

import simulator.mrep.MRegister;
import simulator.mrep.Matrix;

public class GroverCircuit implements QCircuit{
	private int noOfIterations, findThis;
	private int [][] gates;
	private MRegister reg;
	
	public GroverCircuit(int noOfQbits){
		noOfIterations = (int)((Math.PI/4.0)*(Math.sqrt(Math.pow(2, noOfQbits))-1))+2;
		gates = new int[noOfIterations+1][3];
	}
	
	public void fill(){
		for(int i=0; i<noOfIterations; i++){
			if(i<noOfIterations-1){
				gates[i][0]=1;
				gates[i][1]=findThis;
			}
			if(i==noOfIterations-1){
				gates[i][0]=2;
				gates[i][1]=0;
			}
		}
	}

	public void setTarget(int findThis) {
		this.findThis = findThis;	
	}
	public int getTarget(){
		return findThis;
	}
	
	public void applyCircuit(){
		reg = MRegister.getInstance();
		for(int i=0; i<gates.length;i++){
			if(gates[i][0]==1){

				//Call functional implementation
				this.applyGrover(gates[i][1]);
				//Matrix implementation
				/*if(groverMatrix==null){
					GroverMGate grover = new GroverMGate();
					grover.setTarget(data[i][1]);
					groverMatrix = grover.output(numOfQubits);
				}
				this.apply(groverMatrix); */

			} 
			else if(gates[i][0]==2){
				reg.measure();
			}
		}
	}
	
	public void applyGrover(int target){
		int n= reg.numOfStates();
		double n2 = 2.0/(double)n;
		
		//create new matrix to store results
		Matrix newAmp = new Matrix(1, n);
		
		//precompute everything for performance
		double nonTargetNonDiagonalResult;
		double nonTargetDiagonalResult;
		double targetNonDiagonalResult;
		double targetDiagonalResult;
		
		targetNonDiagonalResult = -reg.getAmplitude(target)*n2;
		targetDiagonalResult = (1-n2)*reg.getAmplitude(target);
		if(target==0){
			nonTargetNonDiagonalResult = -reg.getAmplitude(1)*n2;
			nonTargetDiagonalResult = (1-n2)*reg.getAmplitude(1);
		}
		else{
			nonTargetNonDiagonalResult = -reg.getAmplitude(0)*n2;
			nonTargetDiagonalResult = (1-n2)*reg.getAmplitude(0);
		}
		
		for(int j=0; j<n; j++){
			double sum = 0;
			for(int k=0; k<n; k++){
				//on the diagonal
				if(k==j){
					if(k==target){
						sum-=targetDiagonalResult;
					}
					else{
						sum+=nonTargetDiagonalResult;
					}
				}
				//not on diagonal
				else{
					if(k==target){
						sum-=targetNonDiagonalResult;
					}
					else{
						sum+=nonTargetNonDiagonalResult;
					}
				}
			}
			newAmp.setElement(0, j, sum);
		}
		
		reg.setAmplitude(newAmp);
	}
}
