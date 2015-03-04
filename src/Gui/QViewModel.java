package Gui;

public class QViewModel{
	

	public static void printToConsole(String s){
		QuantumGuiPanel.console.append(s + "\n");
		//QuantumGuiPanel.console.setCaretPosition(QuantumGuiPanel.console.getDocument().getLength());
	}
	public static void printToConsole(double d){
		QuantumGuiPanel.console.append(d + "\n");
		//QuantumGuiPanel.console.setCaretPosition(QuantumGuiPanel.console.getDocument().getLength());
	}
	public static void printToConsole(int i){
		QuantumGuiPanel.console.append(i + "\n");
		//QuantumGuiPanel.console.setCaretPosition(QuantumGuiPanel.console.getDocument().getLength());
	}
	public static void updateLoadingBar(int percent){
		QuantumGuiPanel.console.append("Change bar to " + percent + "!");
		//QuantumGuiPanel.console.setCaretPosition(QuantumGuiPanel.console.getDocument().getLength());
	}

}
