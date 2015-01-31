

public class TestGate {
	public static void main (String [] args){
		MNOTGate not = new MNOTGate();
		MRegister reg = MRegister.getInstance();
		reg.setRegister(3);
		//reg.printAmplitude();
		int [] controls = {1};
		reg.applyGate(not,controls, 0);
		reg.printAmplitude();
		double sum = 0;
		for (int i = 0; i < (int) Math.pow(2,reg.numOfQubit()); i++){
			sum += Math.pow(reg.getAmplitude(i),2);
		}
		System.out.println(sum);
	}
}
