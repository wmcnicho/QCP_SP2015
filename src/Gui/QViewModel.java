package Gui;
import java.util.HashMap;

public class QViewModel{
	

	public static void printToConsole(String s){
		QuantumGuiPanel.console.append(s + "\n");
	}
	public static void printToConsole(double d){
		QuantumGuiPanel.console.append(d + "\n");
	}
	public static void printToConsole(int i){
		QuantumGuiPanel.console.append(i + "\n");
	}
	public static void updateLoadingBar(int percent){
		//QuantumGuiPanel.console.append("Change bar to " + percent + "!");
		QuantumGuiPanel.loadingBar.setValue(percent);
		QuantumGuiPanel.loadingBar.setString(percent + "%");	
	}
	public static void resetLoadingBar(){
		QuantumGuiPanel.loadingBar.setValue(0);
		QuantumGuiPanel.loadingBar.setString("0");
	}
	//Used by the menu bar to tell the Panel that the simulation is loaded
	public static void updateHashMap(HashMap<Integer, Integer> map){
		QuantumGuiPanel.oracleMap = map;
		QuantumGuiPanel.setLoaded(true);
		
	}
	
	public static void updateHistogramValues(double[] regValues){
		QuantumGuiPanel.animations.updateHistogram(regValues);
	}
	
	public static void setVector(int xval, int yval){
		QuantumGuiPanel.animations.updateVector(xval, yval);
	}

}
