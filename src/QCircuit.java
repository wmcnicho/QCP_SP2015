
/**
 * 
 * @author Gennaro
 * List of gates stored with the associated wire
 * 1-Hadamard
 * 2-Pauli X
 * 3-Pauli Y
 * 4-Pauli Z
 * 
 */
public abstract class QCircuit {
	protected int[][] gates;
	protected int noOfQbits;
	
	public int[][] getQCircuit(){
		return gates;
	}
}
