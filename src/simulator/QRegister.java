package simulator;


public interface QRegister {
	public int numOfQubit();
	public int numOfStates();
	//public void setAmplitude(int qubitPos, double amps);
	//public double getAmplitude(int qubitPos);
	public void measure();
	public void setAmplitude(int qubitPos, double real, double imag);
}
	
