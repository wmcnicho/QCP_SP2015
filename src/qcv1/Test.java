package qcv1;
import java.util.Random;
import java.io.*;
public class Test {
	public static void main (String [] args) throws IOException{
		Random rand = new Random();
		int min = 0;
		int max = 100000;
		int num = 131072;
		
		PrintWriter outPrint = new PrintWriter(new FileWriter("/Users/MichaelChiang/Documents/QCPTesting.txt"));
		for (int i = 0; i < num; i++){
			int gen = rand.nextInt(max - min + 1) + min;
			outPrint.print(gen + ",");	
			System.out.println(gen);
		}
		outPrint.close();
	}
}