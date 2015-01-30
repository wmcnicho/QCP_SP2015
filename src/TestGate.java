

public class TestGate {
	/*
	public static void main (String [] args){
		MHGate hadamard = new MHGate();
		MZGate zgate = new MZGate();
		MNOTGate not = new MNOTGate();
		MRegister in = new MRegister(13,0);
		//in.printAmplitude();
		MRegister out = in;
		
		out = hadamard.output(out,0);
		out = hadamard.output(out,1);
		out = hadamard.output(out,2);
		out = not.output(out, 5);
		out = zgate.output(out,3);
		out = hadamard.output(out,4);
		out = zgate.output(out,2);
		out = not.output(out, 8);
		out = not.output(out, 2);
		for (int i = 0; i < 13; i++){
			out = hadamard.output(out,i);
		}
		out.printAmplitude();
		double sum = 0;
		for (int i = 0; i < (int) Math.pow(2,out.numOfQubit()); i++){
			sum += Math.pow(out.getAmplitude(i),2);
		}
		System.out.println(sum);
	}*/
	public static void main (String [] args){
		int noOfQbits = 6;
		int findThis = 56;
		
		MRegister register = new MRegister(noOfQbits);
		GroverQCircuit groverCircuit = new GroverQCircuit(noOfQbits);
		groverCircuit.setTarget(findThis);
		groverCircuit.fill();
		register.apply(groverCircuit);

	}
}
