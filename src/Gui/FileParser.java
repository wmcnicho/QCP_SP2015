package Gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
/**A file parser that will take in a file of raw comma separated values (csv) and create a map that maps their
 * value to their index location.
 * e.g. 1,2,4,17,91,34,1337,... -> [ [0, 1], [1, 2], [2, 4], [3, 17], ...]
 * This is used to quickly generate the list of indices in which the target value exists and facilitates the creation of
 * the Oracle for Grover's Algorithm.
 * 
 * @author William Hunter McNichols
 */
public class FileParser {
	/**Parses a file by the specifications listed in the class definition
	 * 
	 * @param filename	A string indicating the filepath of the file to parse
	 * @return A map where each value is mapped with the corresponding index in which it is located 
	 */
	public static HashMap<Integer, Integer>  parseFile(String filename){
		 Integer numElements = new Integer(0);
		 BufferedReader br = null;
		 try{
			br = new BufferedReader(new FileReader(filename));
		 }
		 catch (FileNotFoundException e) {
			e.printStackTrace();
		 }
		 String line =  null;
		 HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();

		 try {
			while((line=br.readLine())!=null){
			    String str[] = line.split(",");
			    for(int i=0;i<str.length;i++){
			        map.put(numElements, Integer.parseInt(str[i]));
			        numElements++;
			    }
			}
		 } 
		 catch (NumberFormatException e) {
				e.printStackTrace();
		 } 
		 catch (IOException e) {
				e.printStackTrace();
		 }
		 
		try{
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return map;
	}
}
