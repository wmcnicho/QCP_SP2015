package qcv1;


public interface QRegister {
	public int numOfQubit();
	public int numOfStates();
	//public void setRegister(int num);
	//public void setRegister(int num, int n);
	//public void setRegister(Matrix amps);
	public void setAmplitude(Matrix amps);
	public void setAmplitude(int qubitPos, double amps);
	public double getAmplitude(int qubitPos);
	public Matrix getAmplitude();
	public void measure();
}
	
