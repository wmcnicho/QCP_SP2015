package Gui;
import java.util.HashMap;
/**A class that interfaces between model code and the GUI. Roughly follows the Model-View-ViewModel architectural 
 * architecture in which the View is never directly updated by the Model and instead only updated through the ViewModel.
 * All methods in this class are static and update static variables in the QuantumGuiPanel class.
 * 
 *  @author William Hunter McNichols
 */
public class QViewModel{	
	/**Prints the provided string in the QuantumGuiPanel console 
	 * @param s 	The string to print
	 * */
	public static void printToConsole(String s){
		QuantumGuiPanel.console.append(s + "\n");
		QuantumGuiPanel.console.setCaretPosition(QuantumGuiPanel.console.getDocument().getLength());
	}
	/**Prints the provided double in the QuantumGuiPanel console 
	 * @param d 	The double to print
	 * */
	public static void printToConsole(double d){
		QuantumGuiPanel.console.append(d + "\n");
		QuantumGuiPanel.console.setCaretPosition(QuantumGuiPanel.console.getDocument().getLength());
	}
	/**Prints the provided integer in the QuantumGuiPanel console 
	 * @param i 	The integer to print
	 * */
	public static void printToConsole(int i){
		QuantumGuiPanel.console.append(i + "\n");
		QuantumGuiPanel.console.setCaretPosition(QuantumGuiPanel.console.getDocument().getLength());
	}
	/**Updated the value of the loading bar in QuantumGuiPanel to the provided number in the range [0, 100]
	 * @param percent	the value to change the loading bar to
	 */
	public static void updateLoadingBar(int percent){
		if(percent > 100 || percent < 0){
			return;
		}
		QuantumGuiPanel.loadingBar.setValue(percent);
		QuantumGuiPanel.loadingBar.setString(percent + "%");	
	}
	/**Resets the loading bar in QuantuGuiPanel to 0*/
	public static void resetLoadingBar(){
		QuantumGuiPanel.loadingBar.setValue(0);
		QuantumGuiPanel.loadingBar.setString("0");
	}
	/**Sets the values of the state histogram in the animation area to the provided values indicating the probability
	 *  of each of the 2^n states of the quantum register (where n is the number of qubits).
	 *  @param regValues	An array of double values to set each of the states to.  
	 */
	public static void updateHistogramValues(double[] regValues){
		QuantumGuiPanel.animations.updateHistogram(regValues);
	}
	/**Updates the red vector in the Grover's Algorithm animation to take on the value provided for the rotation.
	 * 
	 * @param xval		a number between [-1, 1] reflecting the x position of the rotation vector
	 * @param yval		a number between [-1, 1] reflecting the y position of the rotation vector
	*/
	public static void setVector(double xval, double yval){
		QuantumGuiPanel.animations.updateVector(xval, yval);
	}
	/**Clears the console in the QuantumGuiPanel, removing all current text. */
	public static void clearConsole(){
		QuantumGuiPanel.console.setText("");
		QuantumGuiPanel.console.setCaretPosition(QuantumGuiPanel.console.getDocument().getLength());
	}
	/**Used by the QunatumMenuBar to tell the QuantumGuiPanel that the simulation is loaded and provides it the loaded data
	 * @param map		the mapping of the loaded parsed file
	 * */
	public static void updateHashMap(HashMap<Integer, Integer> map){
		QuantumGuiPanel.oracleMap = map;
		QuantumGuiPanel.setLoaded(true);	
	}
}
