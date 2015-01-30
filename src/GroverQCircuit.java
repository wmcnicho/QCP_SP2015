
/**
 * 
 * @author Gennaro
 * List of gates stored with the associated wire
 * 1-Grover step
 * 2-Measure
 */

public class GroverQCircuit extends QCircuit {

	int noOfIterations, findThis;
	
	public GroverQCircuit(int noOfQbits){
		this.noOfQbits = noOfQbits;
		noOfIterations = (int)((Math.PI/4.0)*(Math.sqrt(Math.pow(2, noOfQbits))-1))+2;
		gates = new int[noOfIterations+1][3];
		this.fill();
	}
	
	private void fill(){
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
}
