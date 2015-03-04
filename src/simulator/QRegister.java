package simulator;

import simulator.mrep.Matrix;

public abstract class QRegister {
	public abstract int numOfQubit();
	public abstract int numOfStates();
	public abstract void setAmplitude(Matrix amps);
	public abstract void setAmplitude(int qubitPos, double [] amps);
	public abstract void setAmplitude(int qubitPos, double real, double imag);
	public abstract void setEqualAmplitude();
	public abstract double [] getAmplitude(int qubitPos);
	public abstract Matrix getAmplitude();
	public abstract void measure();
}
	
